#!/usr/bin/env bash

set -o errexit -o nounset -o pipefail

NAME=$(set -e; mvn -B -q help:evaluate -Dexpression=project.name -DforceStdout)
VERS=$(set -e; mvn -B -q help:evaluate -Dexpression=project.version -DforceStdout)
echo "${NAME}-${VERS}"
