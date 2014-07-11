#!/bin/sh -x
set -e

# This script must be executed from the root of the UML-Designer git repo
# $ ./documentation/gen-doc.sh "My commit message"

# Generate documentation
(cd documentation;jekyll build)

# Update Eclipse doc
git rm -rf ./plugins/org.obeonetwork.dsl.uml2.design.doc/html/*
mkdir ./plugins/org.obeonetwork.dsl.uml2.design.doc/html
cp -r ./documentation/_site/* ./plugins/org.obeonetwork.dsl.uml2.design.doc/html
ls ./plugins/org.obeonetwork.dsl.uml2.design.doc/html

# Commit changes in master branch
git add ./documentation
git add ./plugins/org.obeonetwork.dsl.uml2.design.doc/html/*
git commit -m"$1"

# Copy generated doc to tmp
tmpdir=$(mktemp -d) 
cp -r ./documentation/_site/* $tmpdir
rm -rf ./documentation/_site/

# Update gh-pages
cur_github_branch=$(git branch | awk '/^\*/ { print $2 }')
git co gh-pages
git rm -rf *
cp -r $tmpdir/* .
rm -rf $tmpdir
git add .
git commit -m"$1"
git co $cur_github_branch
