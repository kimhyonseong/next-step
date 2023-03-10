<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    alert("${errorMsg}");

    if ("${location}".length > 0) {
        location.href = "${location}";
    } else {
        history.back();
    }
</script>