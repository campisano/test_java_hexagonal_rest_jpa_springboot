#!/usr/bin/env bash

set -o errexit -o nounset -o pipefail

if type -P xmlstarlet &>/dev/null
then
    apt-get -qq -y update
    apt-get -qq -y install xmlstarlet > /dev/null
    apt-get -qq -y clean
fi;
