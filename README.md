# 1. Overview
The common-service is a chassis to which genuniv-services can be made. A chassis, as in the [Chassis Pattern](https://microservices.io/patterns/microservice-chassis.html) of Microservices patterns, gives a head-start to the developemnt of services, as well as address cross-cutting concerns (e.g. logging).

# 2. Cross-cutting concerns
Cross-cutting concerns are basically concerns that are applicable throughout an application. When ignored, cross-cutting concerns will lead to repetitive code, or worse, inconsistent design. The common-service acts as the chassis of services to address the following (more will be added soon):
1. Logging
2. Response format
3. Exception handling
4. Common error messages
4. Re-usable libraries

# 3. Implementing Services
1. [general-info-service](https://github.com/carzanodev/genuniv-general-info-service)
2. [college-service](https://github.com/carzanodev/genuniv-college-service)
3. [personal-records-service](https://github.com/carzanodev/genuniv-personal-records-service)
4. [enrolment-service](https://github.com/carzanodev/genuniv-enrolment-service)