# 1. Overview
The common-service is a chassis to which services can be made. A chassis, as in the [Chassis Pattern](https://microservices.io/patterns/microservice-chassis.html) of Microservices patterns, gives a head-start to the development of services, as well as address cross-cutting concerns.

# 2. Cross-cutting Concerns
Cross-cutting concerns are basically concerns that are applicable throughout an application. When ignored, cross-cutting concerns will lead to repetitive code or inconsistent design. The common-service acts as the chassis of services to address the following concerns.

## 2.1. Logging
The logger being used for the implementing services will be [Apache's Log4j 2](https://logging.apache.org/log4j/2.x/). Since we've also included [Lombok](https://projectlombok.org/) as a dependency, it's even easier to use the logger. Instead of making a logger instance from the logger factory, developers can just annotate the class instead with `@Log4j2`.
```java
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Example {
    ...
}
```

## 2.2. Response Format
A common standardized format for a response is beneficial to the integration of services. It establishes the basic rules of response that the developers of varying teams should always follow and expect.

Successful response will follow this format.
```json
{
    "response": {
        ...
    },
    "meta": {
        "message": ...,
        "timestamp": ...
    }
}
``` 

Occurrence of errors will follow this format.
```json
{
    "error": {
        "timestamp": ...,
        "status": ...,
        "message": ...
    }
}
```

## 2.3. Exception Handling
Expected errors are designed to throw custom Exceptions, which are handled by the chassis' custom version of Spring's `ResponseEntityExceptionHandler`.

## 2.4. Meta Messages
A set of meta messages is also available for a standardized output in meta message of basic API operations.

## 2.5 Libraries
With implementing services possibly having common implementation of certain tasks, libraries are made to make the job easier. The following are some of the these libraries:
1. `ApiCache` - a patterned implementation for the requesting and caching of data from the APIs within the network.

# 3. Implementing Services
1. [general-info-service](https://github.com/carzanodev/genuniv-general-info-service)
2. [college-service](https://github.com/carzanodev/genuniv-college-service)
3. [personal-records-service](https://github.com/carzanodev/genuniv-personal-records-service)
4. [enrolment-service](https://github.com/carzanodev/genuniv-enrolment-service)
