#To run, change execution policy. Open powershell as administrator and execute "Set-ExecutionPolicy RemoteSigned". Choose A. Done.
docker build --tag=javaee7-study .
docker run -it --rm -p 8080:8080 javaee7-study