#!/usr/bin/env bash
while :; do
    echo -n 'Day: '
    read day
    echo -n 'Part: '
    read part

    url="http://localhost:8080/day/${day}/part/${part}"
    command -v 'jq' &> /dev/null \
        && curl -s "$url" | jq \
        || curl -s "$url"
    echo
done
