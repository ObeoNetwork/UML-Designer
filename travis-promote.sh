set -e
if [ $TRAVIS_PULL_REQUEST == 'false' ]
then
echo "Build $TRAVIS_JOB_NUMBER"
echo "Git: $TRAVIS_COMMIT [$TRAVIS_BRANCH]"
echo "Root dir: $TRAVIS_BUILD_DIR"
cd $TRAVIS_BUILD_DIR
echo "Promote umldesigner.org web site -- Start"
echo "Clone UML Designer repo"
git clone https://1b4c95609b39759204c68d18460382328c1c667b@github.com/ObeoNetwork/UML-Designer.git -b gh-pages --quiet
cd UML-Designer/
echo "Remove the old version of the web site"
git rm -rf *
ls
echo "Copy new generated web site"
cp -r ../documentation/_site/* .
ls
rm -rf ../documentation/_site/
echo "Promote umldesigner.org web site -- End"
git config user.email "melanie.bats@obeo.fr"
git config user.name "Mélanie Bats"
git status
git add .
git commit -m "Promoting new website for https://github.com/ObeoNetwork/UML-Designer/commit/$TRAVIS_COMMIT [$TRAVIS_BRANCH]"
git status
echo "cat .git/config"
cat .git/config
echo "git push origine gh-pages"
git push origin gh-pages

echo "Build promoted."
fi
