/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package org.eclipse.emf.eef.runtime.ui.widgets.referencestable;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

/**
 * This class implements an ICellModifier which is called when the user modifies
 * a cell in the tableViewer.
 */
public class MemberEndsTableCellModifier implements ICellModifier {
    private MemberEndsTable memberEndsTable;

    /**
     * Constructor referencesTableListener
     * 
     * @param AssociationMemberEndsTable
     *            an instance of a TableViewerExample
     */
    public MemberEndsTableCellModifier(MemberEndsTable checkboxReferencesTable) {
        super();
        this.memberEndsTable = checkboxReferencesTable;
    }

    /**
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
     *      java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        boolean result;
        // Find the index of the column
        int columnIndex = memberEndsTable.getColumnNames().indexOf(property);

        switch (columnIndex) {
        case 1: // COMPLETED_COLUMN
            result = true;
            break;
        case 0: // DESCRIPTION_COLUMN
            result = false;
            break;
        case 2: // OWNER_COLUMN
            result = true;
            break;
        default:
            result = false;
        }
        return result;
    }

    /**
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
     *      java.lang.String)
     */
    public Object getValue(Object element, String property) {

        // Find the index of the column
        int columnIndex = memberEndsTable.getColumnNames().indexOf(property);

        Object result = null;
        Property prop = (Property) element;

        switch (columnIndex) {
        case 0: // PROPERTY_COLUMN
            result = prop.getName();
            break;
        case 1: // NAVIGABLE_COLUMN
            result = new Boolean(prop.isNavigable());
            break;
        case 2: // OWNED_COLUMN
            result = new Boolean(prop.getOwner() instanceof Association);
            break;
        default:
            result = "";
        }
        return result;
    }

    /**
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
     *      java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value) {

        // Find the index of the column
        int columnIndex = memberEndsTable.getColumnNames().indexOf(property);

        TableItem item = (TableItem) element;
        Property associationEnd = (Property) item.getData();

        switch (columnIndex) {
        case 1: // NAVIGABLE_COLUMN
            if (associationEnd.isNavigable()) {
                memberEndsTable.getReferencesTableListener().handleUncheckNavigableEnds(associationEnd);
            } else {
                memberEndsTable.getReferencesTableListener().handleCheckNavigableEnds(associationEnd);
            }
            break;
        case 2: // OWNED_COLUMN
            if (associationEnd.eContainer() instanceof Association) {
                memberEndsTable.getReferencesTableListener().handleUncheckOwnedEnds(associationEnd);
            } else {
                memberEndsTable.getReferencesTableListener().handleCheckOwnedEnds(associationEnd);
            }
        default:
        }
    }
}
