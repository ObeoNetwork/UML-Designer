/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.properties.service;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.obeonetwork.dsl.uml2.properties.service.TableLabelService.TableColumnName;

/**
 * Manage table column creation.
 * 
 * @author <a href="mailto:melanie.bats@obeo.fr">Melanie Bats</a>
 */
public class TableColumnService {
    /**
     * List of all the created columns.
     */
    private ArrayList<TableColumnName> columns = new ArrayList<TableColumnName>();

    private TableColumn createColumn(Table table, TableColumnName columnName) {
        TableColumn column = new TableColumn(table, SWT.NONE);
        column.setText(columnName.label());
        columns.add(columnName);
        return column;
    }

    public void createSmallColumn(Table table, TableColumnName columnName) {
        TableColumn column = createColumn(table, columnName);
        column.setWidth(75);
    }

    public void createMediumColumn(Table table, TableColumnName columnName) {
        TableColumn column = createColumn(table, columnName);
        column.setWidth(150);
    }

    public void createLargeColumn(Table table, TableColumnName columnName) {
        TableColumn column = createColumn(table, columnName);
        column.setWidth(250);
    }

    public int indexOf(TableColumnName columnName) {
        return columns.indexOf(columnName);
    }
}
