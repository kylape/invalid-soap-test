#!/bin/bash

curl -s -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/client-config-test/HelloClient
