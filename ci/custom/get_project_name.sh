#!/usr/bin/env bash

set -o errexit -o nounset -o pipefail

type -P mvn || sudo ./ci/custom/install_maven.sh

NAME=$(mvn -B -q help:evaluate -Dexpression=project.name -DforceStdout)
echo "${NAME}"
