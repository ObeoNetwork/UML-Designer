echo "Build $TRAVIS_JOB_NUMBER"
echo "Git: $TRAVIS_COMMIT [$TRAVIS_BRANCH]"
echo "List all current repo available branches"
git branch -v -a
echo "List all current folder content"
ls
echo "Create temp folder"
mkdir temp
echo "Move to temp folder"
cd temp
echo "Clone UML Designer repo and checkout gh-pages branch"
git clone https://$GITHUB_TOKEN@github.com/ObeoNetwork/UML-Designer.git -b gh-pages --quiet
ls
echo "Move into new cloned gh-pages branch of the repo"
cd documentation/
echo "List content of gh-pages"
ls
echo "Remove existing documentation web site old content"
find . | grep -v ".git\|toc.xml\|_site" | xargs rm -r
ls
echo "Move new documentation web site content to root"
mv ../../_site/* .
ls
echo "Remove generated site repository"
rm -r ../../_site/
echo "gh-pages branch root content"
ls
echo "Push new documentation web site content on gh-pages branch"
git config user.email "melanie.bats@obeo.fr"
git config user.name "Mélanie Bats"
git remote rm origin
git remote add origin https://ObeoNetwork:$GITHUB_TOKEN@github.com/UML-Designer.git
git add -A
git commit -m "Promoting a new documentation web site for https://github.com/ObeoNetwork/UML-Designer/commit/$TRAVIS_COMMIT [$TRAVIS_BRANCH]"
git push origin gh-pages --quiet &>/dev/null
echo "Site promoted."
