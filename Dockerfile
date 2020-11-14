FROM jboss/wildfly:8.2.1.Final
ADD target/app.war /opt/jboss/wildfly/standalone/deployments/