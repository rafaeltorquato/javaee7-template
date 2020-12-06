FROM jboss/wildfly:8.2.1.Final
ADD ear/target/app.ear /opt/jboss/wildfly/standalone/deployments/