#!/bin/bash
#

show_usage()
{
    echo "Usage:    "`basename $0`" <host:port> <reader>"
    echo "Example:  "`basename $0`" 127.0.0.1:8080 john"
}

if test -z "$1" -o -z "$2"
then
    show_usage >&2
    exit 1
fi

HOSTPORT="$1"
READER="$2"

URL="http://${HOSTPORT}/readingList/${READER}"

set -x
curl -q -m 5 -sSw '\n\nhttp_code: %{http_code}\n' \
-X GET \
${URL} \
-H 'Cache-Control: no-cache' \
-H 'accept: application/json'



# End
