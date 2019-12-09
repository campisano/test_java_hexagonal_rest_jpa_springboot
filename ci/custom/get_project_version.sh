#!/bin/bash

set -o errtrace -o errexit -o nounset -o pipefail

NAME="$(mvn -B -o -q -Dexec.executable=echo -Dexec.args='${project.name}' --non-recursive exec:exec)"
VERS="$(mvn -B -o -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec)"
echo "${NAME}-${VERS}"
