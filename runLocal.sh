#!/bin/bash

mvn clean package -T2C
mvn wildfly:run -f ear/pom.xml
