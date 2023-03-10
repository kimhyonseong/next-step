## 내장 톰캣을 활용한 서블릿 이해
#### 환경
인텔리제이

웹서버 : 웹페이지를 사용자에게 전달해주는 서버

서블릿 컨테이너 : 서블릿을 관리하는 환경
- 클라이언트의 요청을 받아 응답할 수 있게 웹서버와 소켓을 만들어 통신함 = 톰캣  
- 서버를 시작할때 클래스 패스에 있는 클래스 중 HttpServlet을 상속하는 클래스를 찾은 후 @WebServlet 애노테이션의 값을 읽어 요청 URL과 서블릿을 연결하는 MAP 생성
- 서블릿 클래스의 인스턴스 생성
- 요청 URL과 서블릿과의 맵핑
- 클라이언트 요청에 해당하는 서블릿을 찾은 후 서블릿에 작업을 위힘하는 역할

내장 톰캣이 자바 클래스를 인식하는 디렉토리 구조
<pre>
MyWebApp/
    web.xml
    WEB-INF/
        lib/
            MyLib.jar
        classes/
            MyPackage/
                MyServlet.class
</pre>
위에서 "MyWebApp/WEB-INF/"는 프로잭트 내의 /webapp/WEB-INF/에 해당  
→ 인텔리제이 output 디렉토리 변경하기

참고 자료  
https://steady-coding.tistory.com/462  
https://thxwelchs.github.io/EmbeddedTomcat%EA%B3%BCTomcat%EC%9D%98%EC%B0%A8%EC%9D%B4/  
https://velog.io/@han_been/%EC%84%9C%EB%B8%94%EB%A6%BF-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88Servlet-Container-%EB%9E%80