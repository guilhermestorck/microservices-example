#!/bin/bash

if [[ $ENV == "dev" ]]
    then
        HOST=$HOST_IP
    else
        HOST="mocks"
fi

# For each mocked service...

# ... register it on Consul ...
curl \
    --request PUT \
    --data "{ \"Name\": \"catalog-service\", \"Port\": 8081, \"Address\": \"$HOST\" }" \
    http://consul:8500/v1/agent/service/register

# ... and start the stubby server
stubby -d catalog-service/mocks.yml -s 8081 -a 8905 -w
