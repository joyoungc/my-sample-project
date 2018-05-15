# Spring Boot with Netty
Spring Boot에 Netty TCP Server를 적용한 예제 프로젝트 입니다.
- TCP Socket 서버에 여러 개의 client가 접속하여 서로 메세지를 공유할 수 있습니다. ( ex) http:localhost:9010/call/user?msg=메세지내용)
- API 호출을 통해 접속해 있는 client들에 대해 Broadcast 메세지를 전송할 수 있습니다. (index.html)
- [spring-boot-common](https://github.com/joyoungc/spring-boot-sample-project/tree/master/spring-boot-common) 를 dependency로 추가해야 합니다.

## 개발환경
| Environment |  Version |
| ----- | ----- |
| Java | 1.8 |
| [spring-boot](https://github.com/spring-projects/spring-boot) | 2.0.1 |
| [netty](https://github.com/netty/netty) | 4.1.15.Final |
