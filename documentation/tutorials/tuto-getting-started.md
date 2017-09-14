---
layout: article
title: Getting started
subtitle: Tutorial
category: Tutorials
relativePath: ..
---

This section describes how to start using the UML Designer either from existing UML models or by creating a new one.

After installing UML Designer, cheatsheets are available in your Eclipse installation which explain how to :

-   Create a new project
-   Create a new diagram
-   Import an existing model
-   Start with the sample model
    Open the [welcome page](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Fconcepts%2Fwelcome.htm) to get direct links to these cheatsheets.

Starting from the Travel Agency example
---------------------------------------

Import the TravelAgency project using the menu `File/New/Example...`.

![]({{page.relativePath}}/images/gettingStarted/example-1-menu.png)

Pick the **Travel Agency UML** wizard and click on `Next` and then `Finish`.

![]({{page.relativePath}}/images/gettingStarted/example-2-wizard.png)

Click Finish on the wizard and a **TravelAgency** project appears in your workspace.

![]({{page.relativePath}}/images/gettingStarted/example-3-workspace.png)

When the project gets opened, you will be greeted with the main package hierarchy diagram.

![]({{page.relativePath}}/images/gettingStarted/example-4-maindiag.png)

From here you can navigate to existing diagrams by right clicking on the **Model**.

Create a new UML model & UML project
------------------------------------

To create a new UML project, enable the Modeling Perspective.

![]({{page.relativePath}}/images/gettingStarted/perspective.png)

Then click on the wizard shortcuts at the top-right of the Eclipse Workbench. Select *New UML Project.*

![]({{page.relativePath}}/images/gettingStarted/newUMLModel.png)

You can choose specific name for the project and the root element for the project on the next pages.

![]({{page.relativePath}}/images/gettingStarted/newUMLProject.png)

When you press the *Finish* button, the projects gets created and automatically enabled the UML viewpoints.

![]({{page.relativePath}}/images/gettingStarted/new-workspace.png)

Now you can right click on the displayed package to create all the available diagrams or use the palette to create the package structure that fits to your needs. You can also open the Package hierarchy diagram and starts from here:

![]({{page.relativePath}}/images/gettingStarted/new-maindiag.png)

Display & edit an existing UML model
------------------------------------

To create a new UML design on existing UML models, simply drag & drop the models in the Project or use the *Add Model* action available on the *Project Dependencies* item.

![]({{page.relativePath}}/images/gettingStarted/new-add-dependency.png)

### UML designer viewpoints

Four viewpoints are specified on the UML Designer each bringing the following views:

-   Capture
    -   Package hierarchy diagram
    -   Use case diagram
-   Design
    -   Class diagram
    -   Component diagram
    -   Composite diagram
    -   Deployment diagram
    -   State machine diagram
    -   Activity diagram
    -   Sequence diagram
-   Review
-   Extend
    -   Profile diagram

You can select either viewpoints or all depending on your use case. So it is advised to select at least the *Capture* and *Design* viewpoints.
