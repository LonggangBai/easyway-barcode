<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="qrservlet" method="get">
      <p>Enter Text to create QR Code</p>
      <input type="text" name="qrtext" />
      <input type="submit" value="Generate QR Code" />
    </form>

</body>
</html>