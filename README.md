# Reference Documentation

Reference implementation for [blog](http://dhaval-shah.com/refactoring-from-imperative-to-reactive-implementation) demonstrating refactoring existing [Spring Boot]() application
from imperative to reactive implementation along with capturing of load testing results
which mainly covers following :
* Throughput and Response time via [Gatling](https://gatling.io/) based load tests
* CPU utilization via [JVisualVM](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jvisualvm.html) and [JRMC](https://www.oracle.com/middleware/technologies/jrockit.html)
* Thread dump analysis via [Fast Thread](https://fastthread.io/)

## Bootstrapping downstream system a.k.a 'alias-service-api'
1. Go to `bin` directory of [Go](https://golang.org/)
2. Run this command - `go run <Full Path>\alias-service-api.go`
