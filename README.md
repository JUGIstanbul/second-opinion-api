# second-opinion-api
[![Build Status](https://travis-ci.org/JUGIstanbul/second-opinion-api.svg?branch=master)](https://travis-ci.org/JUGIstanbul/second-opinion-api)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=org.jugistanbul.secondopinion:secondopinion)](https://sonarcloud.io/dashboard/index/org.jugistanbul.secondopinion:secondopinion)
[![codecov](https://codecov.io/gh/JUGIstanbul/second-opinion-api/branch/master/graph/badge.svg)](https://codecov.io/gh/JUGIstanbul/second-opinion-api)


RestFul API Service uses following technologies:
* Spring-Boot
* Spring-Web
* Spring-Data
* Spring-Security
* Hibernate
* JaCoCo
* Sonar
* Swagger2 
* Flyway
* Junit
* Mockito

##Start to develop

1- Create "secondopinion" Scheme in your Mysql Database.

2- Create 'config.yml' file and put following in the file:

``` yaml
spring:
  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/secondopinion?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&?useSSL=true&requireSSL=false
    username: $SPECIFY YOUR DB USERNAME
    password: $SPECIFY YOUR DB PASSWORD

api:
  security:
    username: $SPECIFY USERNAME
    password: $SPECIFY PASSWORD
```

3- We have several profiles according to environments you want to execute application on.
Run application with following VM options:
``` 
-Dspring.profiles.active=local -Dspring.config.location=$ 'config.yml' directory you have created at step 2.
```

Now you are ready to start developing application. Happy coding!