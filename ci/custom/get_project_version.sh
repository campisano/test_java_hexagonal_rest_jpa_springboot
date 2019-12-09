#!/bin/bash

set -o errtrace -o errexit -o nounset -o pipefail

NAME=$(mvn -o -q -B help:evaluate -Dexpression=project.name -DforceStdout)
VERS=$(mvn -o -q -B help:evaluate -Dexpression=project.version -DforceStdout)
echo "${NAME}-${VERS}"
