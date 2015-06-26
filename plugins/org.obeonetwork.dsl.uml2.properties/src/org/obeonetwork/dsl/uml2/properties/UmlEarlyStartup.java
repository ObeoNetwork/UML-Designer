package org.obeonetwork.dsl.uml2.properties;

import org.eclipse.ui.IStartup;

public class UmlEarlyStartup implements IStartup {

    public void earlyStartup() {
        // Call a method of the eef adapters just to activate the plugin
        org.eclipse.sirius.eef.adapters.Activator.getDefault();
    }

}
