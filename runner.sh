#!/usr/bin/env bash
while :; do
    echo -n 'Day: '
    read day
    echo -n 'Part: '
    read part

    url="http://localhost:8080/day/${day}/part/${part}"
    data="$(curl -s ${url})"

    if [ "$(command -v 'jq')" ]; then
        echo "$data" | jq
        echo -e '\n.data.result:'
        echo "$data" | jq -r '.data.result'
    else
        echo -e '\nRaw JSON:'
        echo "$data"
        echo -e '\n.data.result:'

        # Poor man's jq...
        # Cut out everything before and including:   result":"
        # Then cut out everything after and including:    "},"status"
        echo -e "$data" \
            | sed -e 's/^.*result":"//' \
            | sed -e 's/"},"status".*$//'
    fi
    echo
done
