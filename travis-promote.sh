echo "Build $TRAVIS_JOB_NUMBER"
echo "Git: $TRAVIS_COMMIT [$TRAVIS_BRANCH]"
echo "Java version: $TRAVIS_JDK_VERSION"
cd packaging/org.obeonetwork.dsl.uml2.update/target
git clone https://$GITHUB_TOKEN@github.com/UML-Designer/p2.git -b gh-pages --quiet
if [ -d p2/nightly ]
then
    rm -r p2/nightly
    echo "An old version of the nightly repository has been found and removed"
fi
echo "Creating the nighly repository"
cp -r repository p2/
mv p2/repository p2/nightly
echo "Nightly repository created"
ls p2/nightly
cd p2
git config user.email "melanie.bats@obeo.fr"
git config user.name "Mélanie Bats"
git remote rm origin
git remote add origin https://UML-Designer:$GITHUB_TOKEN@github.com/UML-Designer/p2.git
git add -A
git commit -m "Promoting a new nightly build for https://github.com/UML-Designer/UML-Designer/commit/$TRAVIS_COMMIT [$TRAVIS_BRANCH]"
git push origin gh-pages --quiet &>/dev/null
echo "Build promoted."
