#!/bin/bash

set -o errtrace -o errexit -o nounset -o pipefail
set -x
apt-get -q update
apt-get -q -y install curl tar gzip
apt-get clean

MVN_VER="3.6.2"
URL="http://www-eu.apache.org/dist/maven/maven-3/${MVN_VER}/binaries/apache-maven-${MVN_VER}-bin.tar.gz"
curl -C - -kLO "${URL}"
tar -C /usr/local -xzf "apache-maven-${MVN_VER}-bin.tar.gz"
rm -f "apache-maven-${MVN_VER}-bin.tar.gz"

export PATH="/usr/local/apache-maven-${MVN_VER}/bin:$PATH"
export MAVEN_OPTS="-B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
mvn clean test package spring-boot:repackage

PACKAGE="$(mvn -o -q -B help:evaluate -Dexpression=project.build.finalName -DforceStdout).jar"
FOLDER="$(mvn -o -q -B help:evaluate -Dexpression=project.build.directory -DforceStdout)"
cp -a "${FOLDER}/${PACKAGE}" app.jar
