<%@ page language="java" contentType="text/html; charset=utf-8"

pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>fileUpLoad</title>
</head>

<body>

<form name="serForm" action="/fileupdown/fileUpload" method="post" enctype="multipart/form-data">

    <h1>采用流的方式上传文件</h1>


    <input type="files" name="files">


    <input type="submit" value="upload"/>

</form>

<form name="Form2" action="/fileupdown/fileUpload2" method="post" enctype="multipart/form-data">

    <h1>采用multipart提供的file.transfer方法上传文件</h1>


    <input type="files" name="files">


    <input type="submit" value="upload"/>

</form>

<form name="Form2" action="/fileupdown/springUpload" method="post" enctype="multipart/form-data">

    <h1>使用spring mvc提供的类的方法上传文件</h1>


    <input type="files" name="files">


    <input type="submit" value="upload"/>

</form>

</body>

</html>