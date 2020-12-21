#To run, change execution policy. Open powershell as user and execute "Set-ExecutionPolicy RemoteSigned". Choose A. Done.
mvn clean package -T2C
mvn wildfly:run -f ear/pom.xml
