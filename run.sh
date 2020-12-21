#!/bin/bash

docker build --tag=javaee7-study .
docker run -it --rm -p 8080:8080 \
-e TZ=America/Sao_Paulo \
javaee7-study /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c standalone-full.xml