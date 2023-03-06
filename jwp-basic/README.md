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