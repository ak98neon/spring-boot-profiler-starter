#bin/sh

mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} versions:commit
mvn clean deploy -DperformRelease=true -P release:prepare
git add --all
git commit -m "Release new version"
git push -u origin master
