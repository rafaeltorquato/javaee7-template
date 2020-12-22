FROM jboss/wildfly:21.0.1.Final

COPY prepare-wildfly.sh /opt/jboss/prepare-wildfly.sh

RUN cd /opt/jboss/ && ./prepare-wildfly.sh

ADD ear/target/javaee7-template.ear /opt/jboss/wildfly/standalone/deployments/