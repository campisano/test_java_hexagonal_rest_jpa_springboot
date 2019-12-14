#!/usr/bin/env bash

set -o errexit -o nounset -o pipefail

type -P mvn &>/dev/null || sudo ./ci/custom/install_maven.sh &>/dev/null

NAME=$(mvn -B -q help:evaluate -Dexpression=project.name -DforceStdout)
echo "${NAME}"
