<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<form id="imageForm" enctype="multipart/form-data" method="post" action="${request.getContextPath}/app/uploadImageRetJsonStream">
<input type="file" name="imageFile" />
    <input type="submit" value="提交" />
</form>
</body>
</html>
