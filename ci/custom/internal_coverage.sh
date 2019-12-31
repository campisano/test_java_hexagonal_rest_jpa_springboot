#!/usr/bin/env bash

set -x -o errexit -o nounset -o pipefail

TOKEN="$1"

apt-get -qq -y update
apt-get -qq -y install curl tar gzip > /dev/null
apt-get -qq -y clean

./ci/custom/install_maven.sh
mvn -B -ntp cobertura:cobertura

bash <(curl -s https://codecov.io/bash) -f target/cobertura/cobertura.ser -t "${TOKEN}"
