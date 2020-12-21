#!/bin/bash

cd $JBOSS_HOME/bin

./standalone.sh -c standalone-full.xml -b 0.0.0.0 &

sleep 10

./jboss-cli.sh --connect --command="/subsystem=security/security-domain=javaee7:add"
./jboss-cli.sh --connect --command="/subsystem=security/security-domain=javaee7/authentication=classic:add(login-modules=[{code=\"Database\",flag=\"required\",module-options=[dsJndiName=>\"java:jboss/datasources/ExampleDS\",principalsQuery=>\"select a.password from AuthUser a where a.username=?\",rolesQuery=>\"select  u.roles, 'Roles' from (select au.username, aur.roles from AuthUser au inner join AuthUserRoles aur on aur.AuthUser_id = u.id  union  select au.username, agr.roles from AuthUser au inner join AuthGroup ag on ag.User_id = u.id inner join AuthGropuRoles agr on agr.Group_id = ag.id  ) u where u.username=? \"]}])"
./jboss-cli.sh --connect --command="reload"
./jboss-cli.sh --connect --command="shutdown"