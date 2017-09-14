---
layout: article
title: Contribute
subtitle: developer guide
relativePath: ..
---

How you can help
----------------

UML Designer is an open source project and everyone is encouraged to get involved! There are lots of ways you can contribute:

-   join our [forum]({{page.relativePath }}/developer-guide/contribute.html\#Forum) 
-   find and [report bugs]({{page.relativePath }}/developer-guide/contribute.html\#Report\_bugs) 
-   write tests for bugs
-   [improve the documentation]({{page.relativePath }}/developer-guide/contribute.html\#Contribute\_to\_doc) 
-   [add new features]({{page.relativePath }}/developer-guide/contribute.html\#Contribute\_to\_code) 
-   [fix bugs]({{page.relativePath }}/developer-guide/contribute.html\#Contribute\_to\_code) 
-   fill our [survey](http://bit.ly/UMLDesignerSurvey).

### Get the source code

This UML modeling environment is based on the [Sirius](https://www.eclipse.org/sirius). It is under an
Open Source license (**EPL**) and the source code is hosted on [github](https://github.com/ObeoNetwork/UML-Designer).
It is intended to cover most use cases behind the usage of UML, and more importantly to be reused when you apply a
model driven approach which uses both **UML and some DSL**. Everything should integrate seamlessly in the same environment.

To get the code just [clone](https://help.github.com/articles/which-remote-url-should-i-use) the repository :

-   SSH : [git@github.com:ObeoNetwork/UML-Designer.git](git@github.com:ObeoNetwork/UML-Designer.git)
-   HTTPS : <https://github.com/ObeoNetwork/UML-Designer/tree/master#>

Contribute
----------

### Contribute to the code

UML Designer's code is hosted on Github. It's easy to get the code and build UML Designer from source. Just clone our repository, make your changes, adapt it to your need and trigger a [pull request](https://help.github.com/articles/using-pull-requests).

### Contribute to the documentation

Found an error in this web-site or in the documentation? Please help us and contribute.
UML Designer's documentation is hosted on Github: <https://github.com/ObeoNetwork/UML-Designer/tree/master/documentation>. Just clone our repository, update the documentation and trigger a [pull request](https://help.github.com/articles/using-pull-requests). The documentation is written with the [textile](http://en.wikipedia.org/wiki/Textile_(markup_language)) syntax, so it is really easy to contribute. Have a look to the [Documentation]({{page.relativePath }}/developer-guide/documentation.html)  section in the developer guide to learn more about how we build the UML Designer documentation.

Providing Feedback
------------------

We're constantly looking for feedback about this modeler, if you think something is missing, some behavior is weird or even just to tell us you are happy with it in its current state, **please come and exchange**.

### Report bugs

Bug reports are really important, so please report any issues you have. When you report bugs, make sure you include lots of detail, reproducible tests, example code or anything else you think might help!

The **bug tracker** is available on github : <https://github.com/ObeoNetwork/UML-Designer/issues>.
We are using the github [integrated issue tracker](https://guides.github.com/features/issues/).

To enter a **new issue** you just need to [create a github account](https://help.github.com/articles/signing-up-for-a-new-github-account).

To **search for issues**, have a look to the github help page to learn the [searching issue syntax](https://help.github.com/articles/searching-issues).

You can visualize also on which issues we are working on thanks to our **card wall**: <https://waffle.io/obeonetwork/uml-designer>

### Forum

If you have any **question**, do not hesitate ask a question on [Stack Overflow](http://stackoverflow.com/questions/ask?tags=uml-designer). When you ask a question do not forget to add the tag <code>uml-designer</code>.

### Survey

Give us feedback by filling in our [survey](http://bit.ly/UMLDesignerSurvey)!

Get the developer environment
-----------------------------

An [Oomph](https://projects.eclipse.org/projects/tools.oomph) setup is provided to ease the installation of the developer environment : [UMLDesigner.setup](https://github.com/ObeoNetwork/UML-Designer/blob/master/releng/org.obeonetwork.dsl.uml2.settings/UMLDesigner.setup)

1. Download the [Oomph installer](https://wiki.eclipse.org/Eclipse_Oomph_Installer) and launch it.
2. Switch to the *Advanced mode*, and make sure your settings are ok for Github (your key is registered at Github).
3. Select Eclipse.org -&gt; Eclipse Platform and click on *Next*.
4. Add the [UML Designer setup file](https://github.com/ObeoNetwork/UML-Designer/blob/master/releng/org.obeonetwork.dsl.uml2.settings/UMLDesigner.setup) (either download it and choose it via the file browser or copy [this link](https://raw.githubusercontent.com/ObeoNetwork/UML-Designer/master/releng/org.obeonetwork.dsl.uml2.settings/UMLDesigner.setup) into URI field). Select the UMLDesigner **AND double-click** on it to be sure that it is listed in projects listing on the bottom. Then click *Next*. Then select where you want to install the UML Designer development environment.
5. When the installation is finished, you need to activate manually the target platform to use : releng/org.obeonetwork.dsl.uml2.target. We use target-platforms to define explicitly plugins and features available in the build context. The same are also used to work on UML Designer in our Eclipse IDE.
6. Let's go contributing to UML Designer!
