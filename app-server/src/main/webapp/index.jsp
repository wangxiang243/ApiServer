<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    System.out.println("basePath="+basePath);
%>
<html>
<body>
<h2>Hello World!</h2>
<form id="imageForm" enctype="multipart/form-data" method="post" action="<%=basePath%>app/uploadImageRetStream">
<input type="file" name="imageFile" />
    <input type="submit" value="提交" />
</form>
</body>
</html>
