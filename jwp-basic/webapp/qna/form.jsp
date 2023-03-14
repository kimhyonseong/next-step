<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="/layout/header.jspf" %>
</head>
<body>
<%@ include file="/layout/nav.jspf" %>
<%@ include file="/layout/subnav.jspf" %>
<div class="container" id="main">
   <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="${question != null ? "/qna/modify" : "/qna/write"}">
              <input type="hidden" name="questionId" value="${question != null ? question.questionId:""}">
              <div class="form-group">
                  <label for="writer">글쓴이</label>
                  <input class="form-control" id="writer" name="writer" placeholder="글쓴이" value="${question != null ? question.writer:""}"/>
              </div>
              <div class="form-group">
                  <label for="title">제목</label>
                  <input type="text" class="form-control" id="title" name="title" placeholder="제목" value="${question != null ? question.title:""}"/>
              </div>
              <div class="form-group">
                  <label for="contents">내용</label>
                  <textarea name="contents" id="contents" rows="5" class="form-control">${question != null ? question.contents:""}</textarea>
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">${question != null ? "수정하기":"질문하기"}</button>
              <div class="clearfix"></div>
          </form>
        </div>
    </div>
</div>

<!-- script references -->
<script src="../js/jquery-2.2.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/scripts.js"></script>
	</body>
</html>