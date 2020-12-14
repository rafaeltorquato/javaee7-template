#To run, change execution policy. Open powershell as administrator and execute "Set-ExecutionPolicy RemoteSigned". Choose A. Done.
mvn clean package -T2C
mvn wildfly:run -f ear/pom.xml
