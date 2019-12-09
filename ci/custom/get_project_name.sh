#!/bin/bash

set -o errtrace -o errexit -o nounset -o pipefail

NAME=$(mvn -o -q -B help:evaluate -Dexpression=project.name -DforceStdout)
echo "${NAME}"
