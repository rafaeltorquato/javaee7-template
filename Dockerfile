FROM jboss/wildfly:21.0.1.Final

COPY prepare.sh /opt/jboss/prepare.sh

RUN cd /opt/jboss/ && ./prepare.sh

ADD ear/target/javaee7-template.ear /opt/jboss/wildfly/standalone/deployments/