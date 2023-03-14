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


### 셀프체크
1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
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