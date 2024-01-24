사내 인트라넷
=============

프로젝트 개발 동기
------------------
이번 프로젝트는 기존의 방식에서 한 발짝 더 나아가, 프론트엔드와 백엔드를 완전히 분리한 형태로 진행되었습니다. 이는 프론트엔드와 백엔드가 서로 영향을 미치지 않고 독립적으로 발전하고, 효율적인 협업을 가능하게 해 유지보수가 편리하다는 장점이 있습니다.

프론트엔드와 백엔드를 분리한 형태의 방식을 시도하기 위해 레퍼런스를 찾던 중, @RestController와 토큰인증 로그인 방식, Swagger를 알게되었습니다. 이전에는 @Controller를 통한 뷰의 반환과 Spring Security를 이용한 세션인증 로그인 방식을 통해 진행되었다면, 이번 프로젝트는 @RestController와 토큰인증 로그인 방식을 통해 구성되었습니다.

@RestController와 토큰인증 로그인 방식을 채택함으로써, 이전 방식에 비해 몇 가지 장점을 누릴 수 있었습니다. @RestController를 사용함으로써, HTTP 요청의 본문을 객체로 자동으로 변환하고, 반환값 역시 객체에서 JSON으로 자동 변환되는 편리함을 누릴 수 있었습니다. 이는 클라이언트와 서버 간의 데이터 송수신을 더욱 간편하고 정확하게 만들어 주었습니다.

토큰인증 로그인 방식은 세션인증 방식에 비해 서버의 부담을 줄이고, 사용자의 로그인 상태를 클라이언트에서 관리할 수 있어 보안성과 확장성이 향상되었습니다. 또한, 클라이언트가 요청을 보낼 때마다 토큰을 함께 보내므로, 서버는 별도의 인증 과정 없이 이 토큰을 통해 사용자를 식별할 수 있어, 처리 속도가 빠르다는 장점도 있습니다.

또, 프론트엔드와 백엔드가 분리된 형태로 진행되어서 구상이나 진행 방향을 프론트엔드 개발자에게 일일이 설명해 줄 수 없기 때문에 프론트엔드 요구사항 명세서와 Swagger를 통한 엔드 포인트를 전달하여 협업이 원활하게 진행되었습니다.

프로젝트 소개
-------------
스프링 시큐리티 + JWT를 이용한 사내 인트라넷을 구현해 보았습니다. 

스프링 시큐리티를 통해서 비밀번호를 암호화하여 DB에 저장하고 DB에 저장된 사용자의 계정과 비밀번호로 로그인하고, JWT를 사용하여 로그인한 사용자에게 토큰을 발급합니다.

시큐리티를 통해 인가된 토큰의 권한에 따라 API접근 권한을 제어하였고, 이를 통해 발생하는 예외는 클라이언트에게 응답합니다.

회원가입, 로그인, 게시판, 댓글, 보고서, 스케줄, 쪽지의 기능을 구현하여, 회사에서 사용할법한 사내 인트라넷을 구축했습니다.

개발 기간
---------
2023.12.15 ~ 2024.01.22

멤버구성
--------
* matddung(윤준혁) - 백엔드 전체, 구상
* DanHumphry(김대현) - 프론트엔드 전체

개발 환경
---------
Windows 10, 64bit

Java 17

Spring Boot 3.0.6

Spring Security

Springdoc 2.1.0

Spring Data JPA

Maria DB

Gradle 7.6.1

Lombok

테스트 데이터
---------
1. 관리자 로그인
   * ID : admin
   * PW : admin
2. 일반 회원 로그인
   * 김대리
     * ID : test1
     * PW : test1
   * 김과장
     * ID : test2
     * PW : test2
   * 김부장
     * ID : test3
     * PW : test3

로컬 환경 실행 방법
-------------------
git clone

    git clone https://github.com/matddung/intranet.git

yml 경로

    file path: intranet/src/main/resources/application-secret.default

yml 수정할 부분

    application-secret.default -> application.yml로 수정
    
    DB정보 기입)
    datasource:
    url: jdbc:mariadb://localhost:3306/'db명'  ex) testtest
    username: '사용자명'  ex) test
    password: '비밀번호'  ex) 1234

메일 서비스 이용 시 수정(임시 비밀번호 발급) - [참조](https://velog.io/@studyjun/SpringBoot-SMTP-%EB%84%A4%EC%9D%B4%EB%B2%84-%EC%9D%B4%EB%A9%94%EC%9D%BC-%EC%9D%B8%EC%A6%9D)

    username: '네이버 아이디'@naver.com
    password: '네이버 비밀번호'

gradle 빌드

    ./gradlew build

실행

    ./gradlew bootRun


ERD
---

<a href="https://ibb.co/GHwTRDx"><img src="https://i.ibb.co/y4TSqCf/sss.png" alt="sss" border="0"></a>

패키지 구조
----------

``` 
Project
├── HELP.md
├── build.gradle
├── gradle
│ └── wrapper
│     ├── gradle-wrapper.jar
│     └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── readme.md
├── settings.gradle
└── src
    └── main
        ├── java
        │   └── com
        │       └── intranet
        │           ├── common
        │           │   ├── ApiStatus
        │           │   ├── MemberType
        │           │   ├── QuestionType
        │           │   ├── ReportType
        │           │   └── ScheduleType
        │           ├── controller
        │           │   ├── AdminController
        │           │   ├── LoginCommonController
        │           │   ├── MemberController
        │           │   └── UnLoginCommonController
        │           ├── dto
        │           │   ├── answer
        │           │   │   ├── request
        │           │   │   │   └── AnswerCreateRequest
        │           │   │   ├── response
        │           │   │   │   ├── AnswerCreateResponse
        │           │   │   │   └── AnswerDeleteResponse
        │           │   ├── member
        │           │   │   ├── request
        │           │   │   │   ├── MemberDepartmentRequest
        │           │   │   │   └── MemberUpdateRequest
        │           │   │   ├── response
        │           │   │   │   ├── MemberApproveResponse
        │           │   │   │   ├── MemberDeleteResponse
        │           │   │   │   ├── MemberDepartmentResponse
        │           │   │   │   ├── MemberInfoResponse
        │           │   │   │   └── MemberUpdateResponse
        │           │   ├── note
        │           │   │   ├── request
        │           │   │   │   └── NoteSendRequest
        │           │   │   ├── response
        │           │   │   │   ├── NoteDeleteResponse
        │           │   │   │   ├── NoteInfoResponse
        │           │   │   │   └── NoteSendResponse
        │           │   ├── password
        │           │   │   ├── request
        │           │   │   │   └── FindPasswordRequest
        │           │   │   ├── response
        │           │   │   │   └── FIndPasswordResponse
        │           │   ├── question
        │           │   │   ├── request
        │           │   │   │   └── QuestionCreateRequest
        │           │   │   ├── response
        │           │   │   │   ├── QuestionCreateResponse
        │           │   │   │   ├── QuestionDeleteResponse
        │           │   │   │   └── QuestionInfoResponse
        │           │   ├── report
        │           │   │   ├── request
        │           │   │   │   └── ReportSubmitRequest
        │           │   │   ├── response
        │           │   │   │   ├── ReportFirstApproveResponse
        │           │   │   │   ├── ReportSecondApproveResponse
        │           │   │   │   └── ReportSubmitResponse
        │           │   ├── schedule
        │           │   │   ├── request
        │           │   │   │   ├── ScheduleCreateRequest
        │           │   │   │   └── ScheduleUpdateRequest
        │           │   │   ├── response
        │           │   │   │   ├── ScheduleCreateResponse
        │           │   │   │   ├── ScheduleInfoResponse
        │           │   │   │   └── ScheduleUpdateResponse
        │           │   ├── signIn
        │           │   │   ├── request
        │           │   │   │   └── SignInRequest
        │           │   │   ├── response
        │           │   │   │   └── SignInResponse
        │           │   ├── signUp
        │           │   │   ├── request
        │           │   │   │   └── SignUpRequest
        │           │   │   ├── response
        │           │   │   │   └── SignUpResponse
        │           │   └── ApiResponse
        │           │
        │           ├── entity
        │           │   ├── Answer
        │           │   ├── Member
        │           │   ├── MemberRefreshToken
        │           │   ├── Note
        │           │   ├── Question
        │           │   ├── Report
        │           │   └── Schedule
        │           ├── repository
        │           │   ├── AnswerRepository
        │           │   ├── MemberRefreshTokenRepository
        │           │   ├── MemberRepository
        │           │   ├── NoteRepository
        │           │   ├── QuestionRepository
        │           │   ├── ReportRepository
        │           │   └── ScheduleRepository
        │           ├── security
        │           │   ├── AdminAuthorize
        │           │   ├── JwtAuthenticationEntryPoint
        │           │   ├── JwtAuthenticationFilter
        │           │   ├── LoginAuthorize
        │           │   ├── SecurityConfig
        │           │   ├── TokenProvider
        │           │   └── UserAuthorize
        │           ├── service
        │           │   ├── AdminService
        │           │   ├── AnswerService
        │           │   ├── CommonService
        │           │   ├── EmailService
        │           │   ├── MemberService
        │           │   ├── NoteServiece
        │           │   ├── QuestionService
        │           │   ├── ReportService
        │           │   └── ScheduleService
        │           ├── service
        │           │   └── SwaggerConfig
        │           ├── DataInitializer
        │           └── IntranetApplication
        └── resources
            ├── application.yml
            ├── application-secret.default
            └── jwt.yml
```