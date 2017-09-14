---
layout: article
title: Release
subtitle: developer guide
relativePath: ..
---

This table lists all the steps to follow to release a new UML Designer version.

Prepare/validate nightly
------------------------

### Step 0

Update the "release checklist" page.

### Step 1

Bump plugins version numbers in git: In UML Designer all the org.obeonetwork.dsl.uml2.xxx plugins are synchronized to the same version.

### Step 2

Bump UML product version:

-   [sketcher.product](https://github.com/ObeoNetwork/UML-Designer/blob/master/packaging/org.obeonetwork.dsl.uml2.product/sketcher.product)
-   [branding/plugin.xml](https://github.com/ObeoNetwork/UML-Designer/blob/master/plugins/org.obeonetwork.dsl.uml2.branding/plugin.xml) the `aboutText`.
-   and for *major* version update also the [splash screen (bmp, png)](https://github.com/ObeoNetwork/UML-Designer/blob/master/plugins/org.obeonetwork.dsl.uml2.branding/)

### Step 3

Prepare the draft release notes in github: <https://github.com/ObeoNetwork/UML-Designer/releases>

### Step 4

Update umldesigner.org [download](http://www.umldesigner.org/download/) page to add the new release.
To do this, update in the documentation the [download page](https://github.com/ObeoNetwork/UML-Designer/blob/master/documentation/download/index.textile).

### Step 5

Build and deploy the UML Designer nightly update site and products: The [build](https://travis-ci.org/ObeoNetwork/UML-Designer/) is automatically launched when a commit is pushed on the [github repository](https://github.com/ObeoNetwork/UML-Designer).
The nightly build results are available on S3: <http://umldesigner.s3.amazonaws.com/nightly/master/bundles/UMLDesigner-linux.gtk.x86_64.zip>
For more details about the build, have a look to the [build section]({{page.relativePath }}/developer-guide/build.html)  of the developer guide.

### Step 6

Validate the unit tests: Check the results of the unit test in the nightly build: <https://travis-ci.org/ObeoNetwork/UML-Designer/builds>.

### Step 7

Validate the product: Relaunch the [UI tests build](https://travis-ci.org/ObeoNetwork/UML-Designer-UI-Tests) for the branch which must be validated. A *re-launch* button is available on the build/job page. The tests results are available on S3: https://s3-eu-west-1.amazonaws.com/umldesigner/nightly/${BRANCH\_NAME}/test-results/${JOB\_NUMBER}/rcpttTests.html:https://s3-eu-west-1.amazonaws.com/umldesigner/nightly/master/test-results/28.13/rcpttTests.html

### Step 8

Test install from jee (using nightly build update-site) : Download an Eclipse JEE and try to install the nightly build update-site.

Deploy on umldesigner.org
-------------------------

### Step 9

Tag UML Designer version in git :
`git tag x.x.x`

### Step 10

[Release](https://github.com/ObeoNetwork/UML-Designer/releases) the version in github.
Build and deploy the UML Designer released update site and products: The [build](https://travis-ci.org/ObeoNetwork/UML-Designer/) is automatically launched when a tag is pushed on the [github repository](https://github.com/ObeoNetwork/UML-Designer).
`git push origin master --tags`

The build results are available on S3: http://umldesigner.s3.amazonaws.com/x.x.x/
For more details about the build, have a look to the [build section]({{page.relativePath }}/developer-guide/build.html) of the developer guide.

### Step 11

Test check for updates from previous version. From a previous installed version of the UML Designer product, try to use check for updates to update to the new released version.

### Step 12

Close all the related [issues](https://github.com/ObeoNetwork/UML-Designer/issues) in github.

### Step 13

Close all the related [milestones](https://github.com/ObeoNetwork/UML-Designer/milestones) in github.

### Step 14

Test install from umldesigner.org [download](http://www.umldesigner.org/download/) page :

-   Install product
-   Install update-site

Deploy on Eclipse marketplace
-----------------------------

### Step 15

[Download](http://www.umldesigner.org/download/) the update site zip and test to install it locally.

### Step 16

Update UML Designer description on the [Eclipse marketplace](http://marketplace.eclipse.org)

Deploy on the obeonetwork
-------------------------

### Step 17

Test install from [Eclipse marketplace](https://marketplace.eclipse.org/content/uml-designer)

### Step 18

Modify aggregation network :
`git repo https://github.com/ObeoNetwork/Aggregation sirius_x_x branch`

### Step 19

Launch aggregation build : <https://travis-ci.org/ObeoNetwork/Aggregation>
Just push the updates on the repo. The build is launched automatically.

### Step 20

Synchronize the [obeonetwork marketplace](http://marketplace.obeonetwork.com/admin)

### Step 21

Synchronize the [obeonetwork jira](http://marketplace.obeonetwork.com/admin)

### Step 22

Test install from [Obeo marketplace](http://marketplace.obeonetwork.com/module/uml/download) using the update site.

Spread the world
----------------

### Step 23

Send mail to Obeo.

### Step 24

Post a blog post.

### Step 25

Post message on [G+](https://plus.google.com) \#UML-Designer.

### Step 26

Post message on [twitter](https://twitter.com/) \#UML-Designer.

### Step 27

Create [google analytics](https://www.google.com/analytics) annotation for the web site and the usage plugin.

### Step 28

Update wikipedia pages:

-   [Comparaison des logiciels d'UML](http://fr.wikipedia.org/w/index.php?title=Comparaison_des_logiciels_d%27UML&veaction=edit&vesection=1),
-   [List of Unified Modeling Language tools](https://en.wikipedia.org/wiki/List_of_Unified_Modeling_Language_tools),
-   [UML Designer](https://en.wikipedia.org/wiki/UML_Designer)

### Step 29

Answer to the [Eclipse marketplace](https://marketplace.eclipse.org/content/uml-designer) questions.
