#!/usr/bin/env bash

set -o errexit -o nounset -o pipefail

NAME=$(mvn -B -q help:evaluate -Dexpression=project.name -DforceStdout)
VERS=$(mvn -B -q help:evaluate -Dexpression=project.version -DforceStdout)
echo "${NAME}-${VERS}"
