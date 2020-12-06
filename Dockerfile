FROM jboss/wildfly:8.2.1.Final
ADD ear/target/javaee7-template.ear /opt/jboss/wildfly/standalone/deployments/