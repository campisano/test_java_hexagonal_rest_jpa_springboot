#!/bin/bash
#

show_usage()
{
    echo "Usage:    "`basename $0`" <host:port> <reader> <isbn> <title> <author> <description>"
    echo "Example:  "`basename $0`" 127.0.0.1:8080 john 00011122 Foo Bar 'Foo and Bar'"
}

if test -z "$1" -o -z "$2" -o -z "$3" -o -z "$4" -o -z "$5" -o -z "$6"
then
    show_usage >&2
    exit 1
fi

HOSTPORT="$1"
READER="$2"
ISBN="$3"
TITLE="$4"
AUTHOR="$5"
DESCRIPTION="$6"

URL="http://${HOSTPORT}/readingList/${READER}"

set -x
curl -q -m 5 -sSw '\n\nhttp_code: %{http_code}\n' \
-X POST \
${URL} \
-H 'Cache-Control: no-cache' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{ "isbn": "'"${ISBN}"'", "title": "'"${TITLE}"'", "author": "'"${AUTHOR}"'", "description": "'"${DESCRIPTION}"'"}'



# End
