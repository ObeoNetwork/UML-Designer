set -e
echo "Build $TRAVIS_JOB_NUMBER"
echo "Git: $TRAVIS_COMMIT [$TRAVIS_BRANCH]"
echo "Root dir: $TRAVIS_BUILD_DIR"
cd $TRAVIS_BUILD_DIR
echo "Clone UML Designer repo"
git clone https://$GITHUB_TOKEN@github.com/ObeoNetwork/UML-Designer.git -b gh-pages --quiet
cd UML-Designer/
echo "Remove the old version of the web site"
git rm -rf *
ls
echo "Copy new generated web site"
cp -r ../documentation/_site/* .
ls
rm -rf ../documentation/_site/
git config user.email "melanie.bats@obeo.fr"
git config user.name "Mélanie Bats"
git add .
git commit -m "Promoting a new website for https://github.com/ObeoNetwork/UML-Designer/commit/$TRAVIS_COMMIT [$TRAVIS_BRANCH]"
git push origin gh-pages --quiet &>/dev/null
echo "Build promoted."
