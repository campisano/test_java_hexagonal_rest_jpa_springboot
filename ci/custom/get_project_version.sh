#!/usr/bin/env bash

set -o errexit -o nounset -o pipefail

NAME=$(xmlstarlet sel -N mvn=http://maven.apache.org/POM/4.0.0 -t -v mvn:project/mvn:name pom.xml)
VERS=$(xmlstarlet sel -N mvn=http://maven.apache.org/POM/4.0.0 -t -v mvn:project/mvn:version pom.xml)

echo "${NAME}-${VERS}"
