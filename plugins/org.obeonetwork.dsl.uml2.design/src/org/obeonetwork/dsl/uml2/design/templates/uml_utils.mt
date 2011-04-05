<%--
-- Copyright (c) 2009, 2011 Obeo.
-- All rights reserved. This program and the accompanying materials
-- are made available under the terms of the Eclipse Public License v1.0
-- which accompanies this distribution, and is available at
-- http://www.eclipse.org/legal/epl-v10.html
-- 
-- Contributors:
--     Obeo - initial API and implementation
--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
%>

<%script type="uml.Package" name="availablePackages"%>
<%self + ancestor.filter("Package") + eAllContents.filter("Package") + packageImport.importedPackage%> 

<%script type="uml.Type" name="isUsableAsPropertyType"%>
<%eClass.name == "Class" || eClass.name == "Interface" || filter("DataType")%>

<%script type="uml.Package" name="availableTypes"%>
<%availablePackages.ownedType[isUsableAsPropertyType == true].nMinimize.trace%>

<%script type="uml.Element" name="allAvailableRootPackages"%>
<%(getRootContainer().filter("Package") + rootPackagesFromImportedModel).nMinimize()%>

<%script type="uml.Element" name="rootPackagesFromImportedModel"%>
<%getRootContainer().eAllContents("PackageImport").importedPackage.nMinimize().getRootContainer().filter("Package").nMinimize()%>
