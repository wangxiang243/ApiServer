<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jack
  Date: 14-7-1
  Time: 上午8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    System.out.println("basePath="+basePath);
%>
<html>
<head>
    <title>查看图片信息</title>
</head>
<body>
    <form id="imageListForm" method="post" action="<=basePath>app/queryAllImageInfo">
        <table>
            <c:forEach items="${imageInfoList}" varStatus="cnt" var="imageInfo">
                <c:if test="${(cnt.count-1)%5==0}">
                    <tr>
                </c:if>
                <td><img src="<%=basePath%>${imageInfo.imagePath}" height="120" width="120" /></td>
                <c:if test="${(cnt.count%5)==0}">
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </form>
</body>
</html>
