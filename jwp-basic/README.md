## 서블릿/JSP를 활용해 동적인 웹 애플리케이션 개발하기

원본
https://github.com/slipp/jwp-basic/tree/step0-getting-started

#### DAO 리팩토링 순서
1. 메소드 분리
   - Extract Method 리팩토링
2. 클래스 분리
   - 공통 라이브러리로 구현할 부분 / 개발자가 매번 구현해야 할 부분
   - InsertJdbcTemplate / UpdateJdbcTemplate
3. UserDao / InsertJdbcTemplate 의존관계 분리
4. InsertJdbcTemplate / UpdateTemplate 통합
5. User 의존관계 제거
6. SELECT문에 대한 리팩토링
7. JdbcTemplate / SelectJdbcTemplate 통합
8. 인터페이스 추가
9. 런타임 Exception / AutoClosable
    - try - with - resources
10. 제너릭 적용
11. 가변인자 활용
12. 람다 구현
___
### 셀프체크
1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
   1. 서블릿 컨테이너는 웹 애플리케이션의 상태를 관리하는 ServletContext 생성
   2. ServletContext가 초기화되면서 컨텍스트의 초기화 이벤트 발생
   3. 등록된 ServletContextListener의 콜백 메소드 contextInitialized 호출
      - 여기서는 ContextLoaderListener의 contextLiInitialized 메소드가 호출
   4. jwp.sql 파일에서 SQL 문을 실행해 데이터베이스 테이블 초기화
   5. 서블릿 컨테이너는 클라이언트로부터의 최초 요청시(또는 컨테이너에 서블릿 인스턴스를 생성하도록 미리 설정을 한다면 최초 요청 전에) DispatcherServlet 인스턴스를 생성
      - 이에 대한 설정은 @WebServlet의 loadOnStartup 속성으로 설정 가능
      - 이 문제에서는 loadOnStartup 속성이 설정되어 있기 때문에 서블릿 컨테이너가 시작하는 시점에 인스턴스 생성
   6. DispatcherServlet 인스턴스의 init() 메소드를 호출해 초기화 작업 진행
   7. init() 메소드 안에서 RequestMapping 객체 생성
   8. RequestMapping 인스턴스의 initMapping() 메소드 호출 - initMapping() 메소드에서는 URL과 Controller 인스턴스 맵핑
2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
   1. localhost:8080으로 접근하면 요청을 처리할 서블릿에 접근하기 전에 먼저 ResourceFilter와 CharacterEncodingFilter의 doFilter() 메소드가 실행
   2. 요청 처리는 "/"으로 맵핑되어 있는 DispatcherServlet이므로 이 서블릿의 service() 메소드 실행
   3. service() 메소드는 요청받은 URL을 분석해 해당 Controller 객체를 RequestMapping에서 가져온다. 요청 URL은 "/"이며, 이와 연결되어 있는 HomeController가 반환
   4. service() 메소드는 HomeController의 execute() 메소드에게 작업을 위임 / execute() 메소드의 반환 값은 ModelAndView
   5. service() 메소드는 반환 받은 ModelAndView의 모델 데이터를 뷰의 render() 메소드에게 전달 / 이 요청에서 View는 JspView / JspView는 render() 메소드로 전달 된 모델 데리터를 homt.jsp에 전달해 HTML 생성 및 응답
3. 질문하기 구현하기 - 완료
4. 로그인한 사용자만 질문하도록 수정하기 - 완료
5. 상세보기 데이터베이스를 이용하여 구현하기 - 완료
6. 한글 깨지는 문제 해결하기 - 
7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
8. 답변하기 기능 사용 시 댓글수 증가 하도록 데이터베이스를 이용하여 구현하기 - 완료
9. 질문 목록 API로 구현하기 - 완료
10. 댓글 삭제 기능 구현하기 - 완료
11. 질문 수정 기능 구현하기 - 완료
12. JdbcTemplate 하나만 생성되게 하기 - 완료
13. 질문 삭제 기능 구현하기 - 완료
14. 13번의 문제를 구현할때 단위 테스트가 가능하도록 구현
15. 서블릿과 애노테이션을 활용해 설정을 추가하고 서버가 시작할때 자동으로 매핑되도록 개선