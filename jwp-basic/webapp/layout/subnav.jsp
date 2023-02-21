<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-default" id="subnav">
    <div class="col-md-12">
        <div class="navbar-header">
            <a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home <small><i class="glyphicon glyphicon-chevron-down"></i></small></a>
            <ul class="nav dropdown-menu">
                <li><a href="../user/profile.html"><i class="glyphicon glyphicon-user" style="color:#1111dd;"></i> Profile</a></li>
                <li class="nav-divider"></li>
                <li><a href="#"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>
            </ul>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse2">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/">Posts</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="#" role="button">로그아웃</a></li>
                        <li><a href="#" role="button">개인정보수정</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/user/login" role="button">로그인</a></li>
                        <li><a href="../user/form.html" role="button">회원가입</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>