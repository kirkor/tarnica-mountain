tarnica-mountain
================

Sample application with two servers: JBoss for EJB (Service/Microservice layer) -> (remote EJB; JBoss remoting) -> Tomcat for WEB (CDI + JSF)

Project description
----
It's simple education project. The idea is to have two serwers where on is connecting to another. Project tree looks like this:
```
api
--parent
--security-api
--user-api

ejb
--security
--user

libraries
--model
--web-security

web
--tarnica

```

TODO
----

* write junit test with mockito and spoock
* make the project to work
* clean code and refactorin
* add new stuff with TDD

Build status
[![Build Status](https://travis-ci.org/kirkor/tarnica-mountain.svg?branch=master)](https://travis-ci.org/kirkor/tarnica-mountain)
