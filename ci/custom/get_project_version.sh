#!/bin/bash

set -o errtrace -o errexit -o nounset -o pipefail

NAME=$(mvn -B -q help:evaluate -Dexpression=project.name -DforceStdout)
VERS=$(mvn -B -q help:evaluate -Dexpression=project.version -DforceStdout)
echo "${NAME}-${VERS}"
