# Spring Boot with Security (Token based auth)
Spring Boot 기반 restful API 서버 프로젝트 입니다.
- [spring-boot-common](https://github.com/joyoungc/spring-boot-sample-project/tree/master/spring-boot-common) 를 dependency로 추가해야 합니다.

## 요구사항
- Token 기반 인증체계 - users will provide its credentials and get unique and time limited access token. I would like to manage token creation, checking validity, expiration in my own implementation.
- 권한별로 API 접근을 제어할 수 있어야 함. 
- 특정 resource에 대하서는 public으로 접근 가능.
- Java configuration 기반으로 설정(not XML)

## 개발환경
| Environment |  Version |
| ----- | ----- |
| Java | 1.8 |
| [spring-boot](https://github.com/spring-projects/spring-boot) | 1.5.6 |