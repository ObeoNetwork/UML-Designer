/*******************************************************************************
 * Copyright (c) 2008 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Philippe Roland (Atos) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Variable;
import org.eclipse.umlgen.reverse.java.logging.LogUtils;

/**
 * Activity generation (nodes and control flow).
 */
public class JavaReverseCUVisitor extends ASTVisitor {

    /** annotation. */
    private static final String ANNOTATION_ACTIVITY = "@activity";

    /** The uml package object. */
    protected Package packageObject;

    /** The current CU name. */
    protected String currentCUName;

    /** The current activity. */
    protected Activity currentActivity;

    /** The initial node. */
    protected InitialNode initNode;

    /** The final node. */
    protected FinalNode finalNode;

    /** The last statement. */
    protected Statement lastStatement;

    /** The last activity node. */
    protected ActivityNode lastActivityNode;

    // Map where the different object were saved
    protected Map<Statement, ActivityNode> entryNodeMap;

    protected Map<Statement, ActivityNode> exitNodeMap;

    protected Map<Statement, ActivityNode> innerExitMap;

    protected Map<Statement, ActivityNode> innerEntryMap;

    // If/then/else processing
    protected Map<Statement, ActivityNode> choiceMap;

    protected Map<Statement, Statement> choiceThenFMap;

    protected Map<Statement, Statement> choiceElseFMap;

    protected Map<Statement, Statement> choiceElseMap;

    protected Map<Statement, Statement> choiceThenMap;

    protected Map<Statement, ActivityNode> choiceOutMap;

    protected LiteralString lastGuard;

    /** parent for loop and various blocks. */
    protected List<Statement> parentStatements;

    /** comments (added to next node). */
    protected String nextComment = "";

    protected boolean addcomment;

    /** Tag for the start of the user code. */
    protected String userCodeStartTag = "Start of user code";

    /** Tag for the stop of the user code. */
    protected String userCodeStopTag = "End of user code";

    /** True if isAnnontatedOnly is checked. */
    private boolean isAnnotatedOnly;

    /** For activity node names. */
    private int countLoopStatment;

    private int countChoiceStatment;

    private String[] sources;

    /** use code. */
    private List<int[]> userCodeRanges;

    /** if set do not visit the node. */
    private boolean novisit;

    /**
     * Constructor.
     *
     * @param packageObject
     *            : current package
     * @param isAnnotatedOnly
     *            : if true, process only annotated methods
     * @param source
     */
    public JavaReverseCUVisitor(Package packageObject, boolean isAnnotatedOnly, String[] source) {
        this.packageObject = packageObject;
        this.isAnnotatedOnly = isAnnotatedOnly;
        this.currentCUName = null;
        sources = source;
    }

    /**
     * Extract code ranges inside "user code" tags.
     *
     * @param node
     * @return ranges list
     */
    private List<int[]> getRanges(CompilationUnit node) {

        ArrayList<int[]> userCodeRange = new ArrayList<int[]>();
        int range1 = -1;
        int range2 = -1;

        List<Comment> listc = node.getCommentList();

        for (Comment com : listc) {
            if (com instanceof LineComment) {
                LineComment linecom = (LineComment)com;
                int startpos = linecom.getStartPosition();
                int linepos = node.getLineNumber(startpos) - 1;
                String text = sources[linepos];
                if (text.indexOf(userCodeStartTag) > 0) {
                    if (range1 < 0) {
                        range1 = linecom.getStartPosition();
                    }
                }
                if (text.indexOf(userCodeStopTag) > 0) {
                    range2 = linecom.getStartPosition();
                    int[] range = {range1, range2 };
                    userCodeRange.add(range);
                    range1 = -1;
                }
            }
        }
        if (range1 > range2) {
            range2 = node.getLength() + 1;
            int[] range = {range1, range2 };
            userCodeRange.add(range);
        }
        return userCodeRange;

    }

    // Only for specific reverse (isAnnotatedOnly) : do not parse user code
    @Override
    public boolean preVisit2(ASTNode node) {
        if (userCodeRanges != null) {
            // test if user code (then no visit)
            int r1 = node.getStartPosition();
            int r2 = node.getLength() + r1;

            for (int[] pair : userCodeRanges) {
                if (r1 > pair[0] && r2 < pair[1]) {
                    novisit = true;
                    return false;
                }
            }
        }
        // else default behavior : visit if "visit" exists.
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#postVisit(org.eclipse.jdt.core.dom.ASTNode)
     */
    @Override
    public void postVisit(ASTNode node) {
        if (currentActivity == null || novisit) {
            novisit = false;
            return;
        }

        // Unprocessed nodes
        if (node instanceof Statement && !exitNodeMap.containsKey(node)) {
            visitUnknownStat((Statement)node);
        }

        boolean ok1 = node.getParent() instanceof IfStatement;
        boolean ok2 = node.getParent() instanceof Block
                && node.getParent().getParent() instanceof IfStatement;
        boolean dolink = true;

        if (node instanceof Statement && (ok1 || ok2)) {
            IfStatement parent;
            if (ok2) {
                parent = (IfStatement)node.getParent().getParent();
            } else {
                parent = (IfStatement)node.getParent();
            }

            List<Statement> thenstat = new ArrayList<Statement>();
            if (isKindofBlock(parent.getThenStatement())) {
                thenstat.addAll(((Block)parent.getThenStatement()).statements());
            } else if (parent.getThenStatement() != null) {
                thenstat.add(parent.getThenStatement());
            }
            int nthen = 0;
            if (thenstat != null) {
                nthen = thenstat.size();
            }

            List<Statement> elsestat = new ArrayList<Statement>();
            if (isKindofBlock(parent.getElseStatement())) {
                elsestat.addAll(((Block)parent.getElseStatement()).statements());
            } else if (parent.getElseStatement() != null) {
                elsestat.add(parent.getElseStatement());
            }
            int nelse = elsestat.size();

            // Link if / else branch to correct decision node
            if (choiceThenFMap != null && choiceThenFMap.containsKey(parent)
                    && choiceThenFMap.get(parent).equals(node)) {
                ActivityNode source = innerExitMap.get(choiceThenFMap.get(parent));
                ActivityNode target = innerEntryMap.get(node);
                if (source != null && target != null) {
                    ControlFlow flow = createControlFlow(source, target);
                    flow.setName(source.getName() + "_toElse_" + target.getName());
                }
                choiceThenFMap.remove(node);
                dolink = false;

            } else if (choiceElseFMap != null && choiceElseFMap.containsKey(parent)
                    && choiceElseFMap.get(parent).equals(node)) {
                ActivityNode source = innerExitMap.get(choiceElseFMap.get(parent));
                ActivityNode target = innerEntryMap.get(node);
                if (source != null && target != null) {
                    ControlFlow flow = createControlFlow(source, target);
                    flow.setName(source.getName() + "_toThen_" + target.getName());
                }
                choiceElseFMap.remove(node);
                dolink = false;

            } else if (nthen > 0 && thenstat.get(nthen - 1).equals(node)) {
                choiceThenMap.put(parent, (Statement)node);
            } else if (nelse > 0 && elsestat.get(nelse - 1).equals(node)) {
                choiceElseMap.put(parent, (Statement)node);
            }

        }

        // Add comments if exists
        ActivityNode act = null;
        if (node instanceof Statement && "".equals(nextComment)) {
            nextComment = ((Statement)node).getLeadingComment();
            if (nextComment == null) {
                nextComment = "";
            }
            act = entryNodeMap.get(node);
        }
        if (act != null && !"".equals(nextComment)) {
            act.createOwnedComment().setBody(nextComment);
            addcomment = false;
            LogUtils.logCreation(null, null, node, "add comment" + nextComment);
            nextComment = "";
        }

        // link children, remove traces of this being a parent and set this as last statement
        if (parentStatements != null && parentStatements.contains(node) && dolink) {
            linkToLastChild(node);
            parentStatements.remove(node);
            // remove this statement's inner nodes from the maps
            innerEntryMap.remove(node);
            innerExitMap.remove(node);
            lastStatement = (Statement)node;
        }
    } // end of postVisit

    /**
     * check that statement can be casted into Block.
     *
     * @param stat
     * @return true/false
     */
    private boolean isKindofBlock(Statement stat) {
        if (stat == null) {
            return false;
        }
        try {
            Block b = (Block)stat;
            return true;
        } catch (ClassCastException e) {
        }
        return false;

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.CompilationUnit)
     */
    @Override
    public boolean visit(CompilationUnit node) {
        if (node == null) {
            return false;
        }
        if (sources != null) {
            userCodeRanges = getRanges(node);
        }

        LogUtils.logEntering(null, null);

        currentCUName = node.getJavaElement().getElementName();
        LogUtils.logCreation(null, node, null, "Visit " + currentCUName + " for activity");
        LogUtils.logExiting();
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.MethodDeclaration)
     */
    @Override
    public boolean visit(MethodDeclaration node) {

        boolean ret = false;

        entryNodeMap = new HashMap<Statement, ActivityNode>();
        exitNodeMap = new HashMap<Statement, ActivityNode>();
        innerExitMap = new HashMap<Statement, ActivityNode>();
        innerEntryMap = new HashMap<Statement, ActivityNode>();
        choiceMap = new HashMap<Statement, ActivityNode>();
        choiceThenFMap = new HashMap<Statement, Statement>();
        choiceElseFMap = new HashMap<Statement, Statement>();
        choiceThenMap = new HashMap<Statement, Statement>();
        choiceElseMap = new HashMap<Statement, Statement>();
        choiceOutMap = new HashMap<Statement, ActivityNode>();
        parentStatements = new ArrayList<Statement>();

        // add activity to current class
        String methodname = node.getName().toString();
        TypeDeclaration classnode = null;
        String classname = "";
        NamedElement specObject = null;
        if (node.getParent() instanceof TypeDeclaration) {
            classnode = (TypeDeclaration)node.getParent();
            classname = classnode.getName().toString();
            specObject = packageObject.getOwnedMember(classname);
        }
        if (specObject != null && specObject instanceof Class) {
            List<SingleVariableDeclaration> params = node.parameters();
            List<String> paramTypeNames = new ArrayList<String>();
            EList<Parameter> pset = null;
            for (SingleVariableDeclaration p : params) {
                paramTypeNames.add(p.getType().toString());
            }

            if (paramTypeNames.size() == 0) {
                paramTypeNames = null;
            }

            BehavioralFeature op = ((Class)specObject).getOwnedOperation(methodname, null, null);
            // check parameters
            if (op != null) {
                pset = op.getOwnedParameters();
                // parameters names
                boolean pnok = ctrlParamTypes(paramTypeNames, pset);

                List<Operation> ops2 = ((Class)specObject).getOperations();
                List<Operation> ops = new ArrayList<Operation>();
                for (Operation oo : ops2) {
                    if (oo.getName().toString().equals(methodname)) {
                        ops.add(oo);
                    }
                }
                while (!pnok && ops.size() > 1) {
                    ops.remove(op);
                    op = ops.get(0);
                    pset = op.getOwnedParameters();
                    pnok = ctrlParamTypes(paramTypeNames, pset);
                }
                if (!pnok) {
                    op = null;
                }
            }

            if (op == null) {
                op = ((Class)specObject).getOwnedReception(methodname, null, null);
                if (op != null) {
                    List<Reception> ops2 = ((Class)specObject).getOwnedReceptions();
                    List<Reception> ops = new ArrayList<Reception>();
                    for (Reception oo : ops2) {
                        if (oo.getName().toString().equals(methodname)) {
                            ops.add(oo);
                        }
                    }
                    pset = op.getOwnedParameters();
                    boolean pnok = ctrlParamTypes(paramTypeNames, pset);
                    while (!pnok && ops.size() > 1) {
                        ops.remove(op);
                        op = ops.get(0);
                        pset = op.getOwnedParameters();
                        pnok = ctrlParamTypes(paramTypeNames, pset);
                    }
                    if (!pnok) {
                        op = null;
                    }
                }
            }

            // process only methods annotated @activity
            if (op != null && isAnnotatedOnly) {

                // list of modifiers and annotations
                List<IExtendedModifier> modifiers = node.modifiers();

                if (modifiers.isEmpty()) {
                    op = null;
                } else {
                    // list of annotations names
                    List<String> flags = new ArrayList<String>();
                    for (IExtendedModifier flag : modifiers) {
                        if (flag.isAnnotation()) {
                            flags.add(flag.toString());
                        }
                    }
                    // check annotation
                    if (!flags.contains(ANNOTATION_ACTIVITY)) {
                        op = null;
                    }
                }
            }

            if (op != null) {
                currentActivity = UMLFactory.eINSTANCE.createActivity();
                currentActivity.setName(node.getName().toString());

                LogUtils.logEntering(currentActivity, "Activity for " + methodname);

                currentActivity.setSpecification(op);

                ((BehavioredClassifier)specObject).getOwnedBehaviors().add(currentActivity);

                // parameters type from "op"

                for (Parameter paramOp : pset) {
                    ActivityParameterNode paramu = UMLFactory.eINSTANCE.createActivityParameterNode();
                    paramu.setName(paramOp.getName());
                    paramu.setActivity(currentActivity);
                    paramu.setType(paramOp.getType());
                    paramu.setParameter(paramOp);
                }

                initNode = UMLFactory.eINSTANCE.createInitialNode();
                initNode.setName("InitNode");
                initNode.setActivity(currentActivity);

                // create Final node (connections later)
                finalNode = UMLFactory.eINSTANCE.createActivityFinalNode();
                finalNode.setName("FinalNode");
                finalNode.setActivity(currentActivity);
                ret = true;
            }
        }

        // ((BehavioredClassifier) packageObject).getOwnedBehaviors().add(currentActivity);

        return ret;
    }; // end visitMethodDeclaration
    // return true = visit all inner nodes

    /**
     * ctrlParamTypes : check a list of parameter have expected type (control on type name).
     *
     * @param paramNames
     *            : type names
     * @param pset
     *            : parameter sets
     * @return true/false
     */
    private boolean ctrlParamTypes(List<String> paramNames, EList<Parameter> pset) {
        boolean pnok = true;
        if (paramNames == null) {
            return pnok;
        }
        if (pset == null) {
            return false;
        }

        // last parameter can be a return parameter (no type recorded in that case)
        if (pset.size() < paramNames.size() || pset.size() > paramNames.size() + 1) {
            return false;
        }

        for (int ii = 0; ii < paramNames.size(); ii++) {
            Parameter pp = pset.get(ii);
            if (pp != null || pp.getType() != null) {
                String typename = pp.getType().getName();
                if (!typename.equals(paramNames.get(ii))) {
                    pnok = false;
                }
            }
        }
        return pnok;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.MethodDeclaration)
     */
    @Override
    public void endVisit(MethodDeclaration node) {
        if (!(lastStatement instanceof ReturnStatement) && currentActivity != null) {

            ActivityNode sourceNode = null;
            if (lastActivityNode != null) {
                sourceNode = lastActivityNode;
                lastActivityNode = null;
            } else if (lastStatement == null || !exitNodeMap.containsKey(lastStatement)) {
                sourceNode = initNode;
            } else if (exitNodeMap.containsKey(lastStatement)) {
                sourceNode = exitNodeMap.get(lastStatement);
            }
            createControlFlow(finalNode, sourceNode);
        }

        LogUtils.logExiting();
        currentActivity = null;
    }

    // Simple Statement case
    /**
     * call by postVisit for non processed statement => create an OpaqueAction.
     *
     * @param node
     */
    public void visitUnknownStat(Statement node) {
        // not applicable to structured nodes
        if (node instanceof DoStatement || node instanceof EnhancedForStatement
                || node instanceof BreakStatement || node instanceof SwitchStatement
                || node instanceof SwitchCase || node instanceof Block) {
            return;
        }

        OpaqueAction act = UMLFactory.eINSTANCE.createOpaqueAction();
        LogUtils.logCreation(null, null, act, null);
        act.setActivity(currentActivity);
        act.setName(node.toString().trim());
        entryNodeMap.put(node, act);
        exitNodeMap.put(node, act);
        linkToLastStatement(node);
        lastStatement = (Statement)node;

        // Java text for Body
        act.getLanguages().add("JAVA");
        act.getBodies().add(node.toString());
        return;
    } // end visit visitUnknownStat

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ReturnStatement) TODO : final
     * node when no value to return
     */
    @Override
    public boolean visit(ReturnStatement node) {
        if (currentActivity == null) {
            return false;
        }
        // finalNode already exists : created at method declaration
        entryNodeMap.put(node, finalNode);
        exitNodeMap.put(node, finalNode);
        linkToLastStatement(node);
        lastStatement = (Statement)node;
        return false;
    }; // end return statement

    // Comment linked to next statement
    @Override
    public boolean visit(BlockComment node) {
        nextComment = nextComment + node.toString();
        addcomment = true;
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.LineComment)
     */
    @Override
    public boolean visit(LineComment node) {
        nextComment = nextComment + node.toString();
        addcomment = true;
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.Javadoc)
     */
    @Override
    public boolean visit(Javadoc node) {
        nextComment = nextComment + node.toString();
        addcomment = true;
        return false;
    }

    /*
     * (non-Javadoc) While structure creation => nodes creation (merge, decision + Ctrl flow)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.WhileStatement)
     */
    @Override
    public boolean visit(WhileStatement node) {
        if (currentActivity == null) {
            return false;
        }
        // create nodes
        MergeNode mergeNode = UMLFactory.eINSTANCE.createMergeNode();
        mergeNode.setActivity(currentActivity);
        DecisionNode decisionNode = UMLFactory.eINSTANCE.createDecisionNode();
        decisionNode.setActivity(currentActivity);

        LogUtils.logCreation(null, null, mergeNode, "Create while loop");

        // add tags
        entryNodeMap.put(node, mergeNode);
        exitNodeMap.put(node, decisionNode);
        parentStatements.add(node);
        innerExitMap.put(node, decisionNode);
        innerEntryMap.put(node, mergeNode);

        // Add name
        String name = node.toString();
        name = "While" + "_" + countLoopStatment++;
        mergeNode.setName(name + "_merge");
        decisionNode.setName(name + "_cond");

        // create flow
        ControlFlow flow = createControlFlow(decisionNode, mergeNode);
        LiteralString stringGuard = UMLFactory.eINSTANCE.createLiteralString();
        stringGuard.setValue(node.getExpression().toString());
        // flow.setGuard(stringGuard);

        flow.setName(name + "_merge_cond");

        // control Flow
        linkToLastStatement(node);
        // set this as the last statement for our children to link to it
        lastStatement = (Statement)node;

        lastGuard = stringGuard;

        // skip the expression statement
        node.getBody().accept(this);
        return false;
    } // end visit WhileStatement

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.IfStatement) Choice "If"
     */
    @Override
    public boolean visit(IfStatement node) {
        if (currentActivity == null) {
            return false;
        }
        // create nodes
        DecisionNode decisionNode = UMLFactory.eINSTANCE.createDecisionNode();
        decisionNode.setActivity(currentActivity);
        MergeNode mergeNode = UMLFactory.eINSTANCE.createMergeNode();
        mergeNode.setActivity(currentActivity);

        // Add name and log
        String name = node.toString();
        name = "If" + "_" + countChoiceStatment++;
        mergeNode.setName(name + "_merge");
        decisionNode.setName(name + "_cond");
        LogUtils.logCreation(null, null, decisionNode, "Create if structure");

        // add tags
        choiceMap.put(node, decisionNode);
        choiceOutMap.put(node, mergeNode);

        entryNodeMap.put(node, decisionNode);
        exitNodeMap.put(node, mergeNode);
        parentStatements.add(node);
        innerExitMap.put(node, decisionNode);
        innerEntryMap.put(node, mergeNode);

        // Control Flow
        linkToLastStatement(node);

        // control flow guard for "then" path
        LiteralString stringGuard = UMLFactory.eINSTANCE.createLiteralString();
        stringGuard.setValue(node.getExpression().toString());

        // then path
        if (node.getThenStatement() == null) {
            lastGuard = stringGuard;
            ControlFlow flow = createControlFlow(mergeNode, decisionNode);
            flow.setGuard(stringGuard);
            lastGuard = null;
            flow.setName(name + "_then");
        } else {
            choiceThenFMap.put(node, (Statement)node.getThenStatement());
            lastGuard = stringGuard;
        }

        if (node.getElseStatement() == null) {
            LiteralString elseGuard = UMLFactory.eINSTANCE.createLiteralString();
            elseGuard.setValue("else");
            ControlFlow flow = createControlFlow(mergeNode, decisionNode);
            flow.setGuard(elseGuard);
            flow.setName(name + "_else");

            lastGuard = stringGuard;
        } else {
            // add link decision node first
            choiceElseFMap.put(node, (Statement)node.getElseStatement());
        }

        // set this as the last statement for our children to link to it
        lastStatement = (Statement)node;

        return true;
    } // end visit IfStatement

    /**
     * End of visit the given type-specific AST node. The default implementation does nothing. Subclasses may
     * reimplement.
     *
     * @param node
     *            the node to visit
     * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.IfStatement)
     */
    @Override
    public void endVisit(IfStatement node) {
        if (currentActivity == null) {
            return;
        }
        ActivityNode mergeNode = exitNodeMap.get(node);

        // connect then path to merge node if needed
        if (node.getThenStatement() != null && node.getElseStatement() != null && mergeNode != null) {
            Statement lastThenNode = choiceThenMap.get(node);
            if (exitNodeMap.containsKey(lastThenNode)) {
                ActivityNode act = exitNodeMap.get(lastThenNode);
                ControlFlow flow = createControlFlow(mergeNode, act);
                if (flow != null) {
                    flow.setName("thenPathTo_" + mergeNode.getName());
                }
            }
        }

    } // end endVisit IfStatement

    /**
     * Visits the given type-specific AST node. The default implementation does nothing and return true.
     * Subclasses may reimplement.
     *
     * @param node
     *            the node to visit
     * @return true if the children of this node should be visited, and false if the children of this node
     *         should be skipped
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ForStatement)
     */
    @Override
    public boolean visit(ForStatement node) {
        // for loop

        if (currentActivity == null) {
            return false;
        }
        // create nodes
        MergeNode mergeNode = UMLFactory.eINSTANCE.createMergeNode();
        mergeNode.setActivity(currentActivity);
        String name = "For" + "_" + countLoopStatment++;
        mergeNode.setName(name + "_merge");
        DecisionNode decisionNode = UMLFactory.eINSTANCE.createDecisionNode();
        decisionNode.setActivity(currentActivity);
        mergeNode.setName(name + "_cond");
        LogUtils.logCreation(null, null, mergeNode, "Create for loop");

        Variable index = UMLFactory.eINSTANCE.createVariable();
        index.setActivityScope(currentActivity);

        AddVariableValueAction indexNode = UMLFactory.eINSTANCE.createAddVariableValueAction();
        indexNode.setActivity(currentActivity);

        // max condition
        Expression expr = node.getExpression();
        // Assignements
        List init = node.initializers();
        List<Expression> incr = (List<Expression>)node.updaters();

        // get index name
        if (init.size() > 0) {
            String myName;
            if (init.size() > 0) {
                myName = init.get(0).toString();
            } else {
                myName = init.toString();
            }
            indexNode.setName(myName);
        } else {
            indexNode.setName("");
        }
        indexNode.setVariable(index);

        // create flows
        ControlFlow flow1 = createControlFlow(mergeNode, indexNode);
        LiteralString stringGuard = UMLFactory.eINSTANCE.createLiteralString();
        flow1.setName(indexNode.getName() + "_merge");

        ControlFlow flow = createControlFlow(decisionNode, mergeNode);
        if (expr != null) {
            stringGuard.setValue(expr.toString());
        }
        flow.setName(name + "_merge_cond");

        // increment
        AddVariableValueAction incrNode = UMLFactory.eINSTANCE.createAddVariableValueAction();
        incrNode.setActivity(currentActivity);
        if (incr.size() > 0) {
            incrNode.setName(incr.get(0).toString());
        } else {
            incrNode.setName("");
        }
        flow = createControlFlow(mergeNode, incrNode);
        flow.setName(incrNode.getName() + "_merge");

        // add tags
        entryNodeMap.put(node, indexNode);
        exitNodeMap.put(node, decisionNode);
        parentStatements.add(node);
        innerExitMap.put(node, decisionNode);
        innerEntryMap.put(node, incrNode);

        // Add names and log
        mergeNode.setName(name + "_merge");
        decisionNode.setName(name + "_cond");
        flow.setName(name + "_cond_merge");

        // Control Flow
        linkToLastStatement(node);
        // set this as the last statement for our children to link to it
        lastStatement = (Statement)node;

        // condition for first inner node
        lastGuard = stringGuard;

        // skip the expression statement
        node.getBody().accept(this);

        return false;
    } // end visit ForStatement

    /**
     * visitMethod : to be called when expression is XX = M(...) in place of variable add action.
     *
     * @param node
     * @return CallOperationAction associated to method invocation
     */
    public ActivityNode visitMethod(MethodInvocation node) {

        CallOperationAction act = UMLFactory.eINSTANCE.createCallOperationAction();
        act.setActivity(currentActivity);
        act.setName(node.getName().toString());
        LogUtils.logCreation(null, null, act, "Create method invocation");

        List<?> args = node.arguments();
        for (Object arg : args) {
            act.createArgument(arg.toString(), null);
        }

        return act;
    } // end MethodInvocation

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ExpressionStatement)
     */
    @Override
    // TODO : add variable declaration / VariableDeclarationFragment
    public boolean visit(ExpressionStatement node) {
        if (currentActivity == null) {
            return false;
        }

        ActivityNode act;
        Expression expr = node.getExpression();
        Expression exprvar = null;
        Expression exprmeth = null;

        if (expr instanceof Assignment) {
            exprvar = ((Assignment)expr).getLeftHandSide();
            exprmeth = ((Assignment)expr).getRightHandSide();
        }

        // CallOperation
        if (expr instanceof MethodInvocation) {
            MethodInvocation callmeth = (MethodInvocation)expr;
            act = visitMethod(callmeth);
        } else if (expr instanceof Assignment && exprmeth instanceof MethodInvocation) {
            MethodInvocation callmeth = (MethodInvocation)exprmeth;
            act = visitMethod(callmeth);
            ((CallOperationAction)act).createResult(exprvar.toString(), null);
        } else {
            act = UMLFactory.eINSTANCE.createOpaqueAction();
            // + body : Java code
            ((OpaqueAction)act).getLanguages().add("JAVA");
            ((OpaqueAction)act).getBodies().add(node.toString());
        }

        act.setActivity(currentActivity);
        act.setName(node.toString().trim());
        LogUtils.logCreation(null, null, act, "Create expression declaration");
        entryNodeMap.put(node, act);
        exitNodeMap.put(node, act);
        linkToLastStatement(node);
        lastStatement = (Statement)node;

        return super.visit(node);
    } // end visit ExpressionStatement

    /*
     * (non-Javadoc) Add variable
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ExpressionStatement)
     */
    @Override
    // FIXME : translate expression
    public boolean visit(VariableDeclarationStatement node) {
        if (currentActivity == null) {
            return false;
        }
        AddVariableValueAction act = UMLFactory.eINSTANCE.createAddVariableValueAction();
        act.setActivity(currentActivity);
        // String name = "Action_"+countStatment++;
        act.setName(node.toString().trim());
        entryNodeMap.put(node, act);
        exitNodeMap.put(node, act);
        linkToLastStatement(node);
        lastStatement = (Statement)node;
        LogUtils.logCreation(null, null, act, "Create variable declaration");

        // Create variable
        Variable var = UMLFactory.eINSTANCE.createVariable();
        var.setActivityScope(currentActivity);
        act.setVariable(var);

        // create variable initialisation
        // FIXME Expression expr = node.getDeclaration();
        act.setValue(null);
        return super.visit(node);
    } // end visit VariableDeclarationStatement

    /**
     * linkToLastChild : look for previous statement and call control flow creation loop only (while, for)0.
     *
     * @param node
     */
    protected void linkToLastChild(ASTNode node) {
        ActivityNode targetNode = null;
        ActivityNode sourceNode = null;
        // If we have an inner entry, link to last statement (child)'s exit
        if (innerEntryMap.containsKey(node) && exitNodeMap.containsKey(lastStatement)) {
            targetNode = innerEntryMap.get(node);
            sourceNode = exitNodeMap.get(lastStatement);
        }
        // otherwise, create final node and link it to child
        createControlFlow(targetNode, sourceNode);
    }

    /**
     * linkToLastStatement : look for last statement and call control flow creation simple statement.
     *
     * @param node
     */
    protected void linkToLastStatement(Statement node) {
        ActivityNode targetNode = null;
        ActivityNode sourceNode = null;
        // If last statement was a parent node, link its inner exit to us.
        if (lastStatement != null && parentStatements.contains(lastStatement)) {
            // if last statement has a designated exit map, use that
            if (innerExitMap.containsKey(lastStatement)) {
                sourceNode = innerExitMap.get(lastStatement);
            } else {
                // TODO Otherwise, we might be inside a region. Add a start point and link to it
                // FIXME maybe the parent can create the nodes instead
            }
        } else {
            LiteralString stringuard = UMLFactory.eINSTANCE.createLiteralString();
            stringuard.setValue("else");

            // Specific case : else statement
            // parentStatements does not contains lastStatement any more
            if (node.getParent().getNodeType() == ASTNode.IF_STATEMENT) {
                IfStatement parent = (IfStatement)node.getParent();
                if (choiceMap.containsKey(parent) && parent.getElseStatement() != null
                        && parent.getElseStatement().equals(node)) {
                    sourceNode = choiceMap.get(parent);
                    choiceMap.remove(parent);
                    if (lastGuard == null) {
                        lastGuard = stringuard;
                    }
                }
            }
            if (node.getParent().getNodeType() == ASTNode.BLOCK
                    && node.getParent().getParent().getNodeType() == ASTNode.IF_STATEMENT) {
                IfStatement parent = (IfStatement)node.getParent().getParent();
                Block block;

                if (parent.getElseStatement() instanceof IfStatement) {
                    AST ast = new AST();
                    block = (Block)ast.createInstance(ASTNode.BLOCK);
                    List<Statement> elseblock = new ArrayList<Statement>();
                    elseblock.add(parent.getElseStatement());
                } else {
                    block = (Block)parent.getElseStatement();
                }
                if (choiceMap.containsKey(parent) && block != null && block.statements().size() > 0
                        && block.statements().get(0).equals(node)) {
                    sourceNode = choiceMap.get(parent);
                    choiceMap.remove(parent);
                    if (lastGuard == null) {
                        lastGuard = stringuard;
                    }
                }
            }

        }

        // if this has a registered entry node, use it
        if (entryNodeMap.containsKey(node)) {
            targetNode = entryNodeMap.get(node);
            // if we haven't yet found a sourceNode, use the exitMap's or a new one
            if (sourceNode == null) {
                if (lastActivityNode != null) {
                    sourceNode = lastActivityNode;
                    lastActivityNode = null;
                } else if (lastStatement == null || !exitNodeMap.containsKey(lastStatement)) {
                    sourceNode = initNode;
                } else if (exitNodeMap.containsKey(lastStatement)) {
                    sourceNode = exitNodeMap.get(lastStatement);
                }
            }
        }
        createControlFlow(targetNode, sourceNode);
    }

    /**
     * createControlFlow : create a control flow from sourceNode to targetNode.
     *
     * @param targetNode
     * @param sourceNode
     * @return a ControlFlow
     */
    protected ControlFlow createControlFlow(ActivityNode targetNode, ActivityNode sourceNode) {

        final int step1 = 30;
        final int step2 = 60;

        if (sourceNode != null && targetNode != null) {
            // no loop on same node nor flow from FinalNode
            if (sourceNode == targetNode || sourceNode instanceof FinalNode) {
                return null;
            }

            ControlFlow flow = UMLFactory.eINSTANCE.createControlFlow();
            flow.setSource(sourceNode);
            flow.setTarget(targetNode);
            flow.setActivity(currentActivity);

            String name = sourceNode.getName();
            if (name.length() > step1) {
                name = name.substring(0, step1);
            }
            name = name + "_" + targetNode.getName();
            if (name.length() > step2) {
                name = name.substring(0, step2);
            }
            flow.setName(name);
            LogUtils.logCreation(null, null, flow, null);

            if (sourceNode instanceof DecisionNode) {
                if (lastGuard != null) {
                    flow.setGuard(lastGuard);
                    lastGuard = null;
                } else {
                    LiteralString sGuard = UMLFactory.eINSTANCE.createLiteralString();
                    sGuard.setValue("else");
                    flow.setGuard(sGuard);
                }
            }
            return flow;
        }
        return null;
    }

    /**
     * first attempt for a translation AST type / UML type (not written).
     *
     * @param asttype
     * @return UML type
     */
    protected Type getUMLType(org.eclipse.jdt.core.dom.Type asttype) {
        // ZZZ! datatype or other objects (primitive type, etc.)
        // TODO : UML types
        // get AST type (simple or primitive type)
        String typeName = asttype.toString();
        Type umltype = UMLFactory.eINSTANCE.createDataType();
        umltype.setName(typeName);

        return umltype;
    }
}
