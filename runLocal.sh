#!/bin/bash

mvn clean install -T2C
mvn wildfly:run -f ear/pom.xml $1
