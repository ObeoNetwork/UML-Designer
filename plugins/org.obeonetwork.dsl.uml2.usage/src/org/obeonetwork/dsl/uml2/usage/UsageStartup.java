package org.obeonetwork.dsl.uml2.usage;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class UsageStartup implements IStartup {

	SiriusEditorsListener usageListener = null;

	public void earlyStartup() {
		PlatformUI.getWorkbench().addWindowListener(new IWindowListener() {

			public void windowOpened(IWorkbenchWindow window) {

			}

			public void windowDeactivated(IWorkbenchWindow window) {
				// TODO Auto-generated method stub

			}

			public void windowClosed(IWorkbenchWindow window) {
				// TODO Auto-generated method stub

			}

			public void windowActivated(IWorkbenchWindow window) {
				if (usageListener == null
						&& PlatformUI.getWorkbench() != null
						&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
					usageListener = new SiriusEditorsListener();
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getPartService().addPartListener(usageListener);
				}

			}
		});
	}

}
