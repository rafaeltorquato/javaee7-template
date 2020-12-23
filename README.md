# javaee7-template

### What's this?

A scaffold project to study Java EE 7:
* Maven project with JDK 1.7 (as required by the specification)

### Requirements
* Docker for running on docker

### Running on docker

```
$ ./build_and_run 
or
PS ./build_and_run.ps1
```

### Running on a local machine with wildfly plugin

```
$ ./runLocal
or for debugging on port 5005
$ ./runLocal -Pdebug 
or
PS ./runLocal
or for debugging on port 5005
PS ./runLocal -Pdebug
```

Open [http://localhost:8080/view](http://localhost:8080/view)