#!/bin/bash

cd $JBOSS_HOME/bin

./standalone.sh -c standalone-full.xml -b 0.0.0.0 &

sleep 10

#Logging
#./jboss-cli.sh --connect --command="/subsystem=logging/root-logger=ROOT:write-attribute(name=\"level\", value=\"DEBUG\")"
#./jboss-cli.sh --connect --command="/subsystem=logging/console-handler=CONSOLE:write-attribute(name=\"level\", value=\"DEBUG\")"
#Add security domain
./jboss-cli.sh --connect --command="/subsystem=security/security-domain=application-security:add"
./jboss-cli.sh --connect --command="/subsystem=security/security-domain=application-security/authentication=classic:add(login-modules=[{code=\"Database\",flag=\"required\",module-options=[dsJndiName=>\"java:jboss/datasources/ExampleDS\",principalsQuery=>\"select a.password from AuthUser a where a.username=?\",rolesQuery=>\"select distinct u.roles, 'Roles' from (select au.username, aur.roles from AuthUser au inner join AuthUserRoles aur on aur.User_id = au.id union select au.username, agr.roles from AuthUser au inner join UserAuthGroup uag on uag.User_id = au.id inner join AuthGroup ag on uag.groups_id = au.id inner join AuthGroupRoles agr on agr.Group_id = ag.id) u where u.username = ?\",hashAlgorithm=\"md5\",hashEncoding=\"base64\"]}])"

#Transform to elytron auth mechanism
#./jboss-cli.sh --connect --command="/subsystem=security/elytron-realm=application-security:add(legacy-jaas-config=application-security)"
#./jboss-cli.sh --connect --command="/subsystem=elytron/security-domain=application-security:add(realms=[{realm=application-security}], default-realm=application-security, permission-mapper=default-permission-mapper)"
#./jboss-cli.sh --connect --command="/subsystem=elytron/http-authentication-factory=application-security-http:add(http-server-mechanism-factory=global, security-domain=application-security, mechanism-configurations=[{mechanism-name=FORM}])"
#./jboss-cli.sh --connect --command="/subsystem=undertow/application-security-domain=application-security:add(http-authentication-factory=application-security-http)"

#new wildfly elytron auth mechanism
#./jboss-cli.sh --connect --command="/subsystem=elytron/jdbc-realm=exampleDbRealm:add(principal-query=[{sql=\"select password, u.roles from (select au.username, au.password, aur.roles       from AuthUser au                inner join AuthUserRoles aur on aur.AuthUser_id = u.id       union       select au.username, au.password, agr.roles       from AuthUser au                inner join AuthGroup ag on ag.User_id = u.id                inner join AuthGroupRoles agr on agr.Group_id = ag.id\",data-source=ExampleDS,clear-password-mapper={password-index=1},attribute-mapping=[{index=2,to=groups}]}])"
#./jboss-cli.sh --connect --command="/subsystem=elytron/security-domain=exampleDbSD:add(realms=[{realm=exampleDbRealm,role-decoder=groups-to-roles}],default-realm=exampleDbRealm,permission-mapper=default-permission-mapper)"
#./jboss-cli.sh --connect --command="/subsystem=elytron/http-authentication-factory=example-db-http-auth:add(http-server-mechanism-factory=global,security-domain=exampleDbSD,mechanism-configurations=[{mechanism-name=FORM,mechanism-realm-configurations=[{realm-name=exampleDbSD}]}])"
#./jboss-cli.sh --connect --command="/subsystem=undertow/application-security-domain=exampleApplicationDomain:add(http-authentication-factory=example-db-http-auth)"

./jboss-cli.sh --connect --command="reload"
./jboss-cli.sh --connect --command="shutdown"