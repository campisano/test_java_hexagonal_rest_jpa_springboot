#!/bin/bash

set -x -o errtrace -o errexit -o nounset -o pipefail

apt-get -qq -y update
apt-get -qq -y install curl tar gzip > /dev/null
apt-get -qq -y clean

MVN_VER="3.6.2"
URL="http://www-eu.apache.org/dist/maven/maven-3/${MVN_VER}/binaries/apache-maven-${MVN_VER}-bin.tar.gz"
curl -C - -kLO "${URL}"
tar -C /usr/local -xzf "apache-maven-${MVN_VER}-bin.tar.gz"
rm -f "apache-maven-${MVN_VER}-bin.tar.gz"

export PATH="/usr/local/apache-maven-${MVN_VER}/bin:$PATH"
mvn -B -ntp clean test package spring-boot:repackage

PACKAGE="$(mvn -B -q help:evaluate -Dexpression=project.build.finalName -DforceStdout).jar"
FOLDER="$(mvn -B -q help:evaluate -Dexpression=project.build.directory -DforceStdout)"
cp -a "${FOLDER}/${PACKAGE}" app.jar
