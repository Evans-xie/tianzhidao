<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>弹幕测试</title>
    <link rel="stylesheet" href="resources/css/danmu.css" type="text/css"/>

</head>
<body>
<div class="mv_play">
    <img width="750px"
         src="http://123.207.87.34:8080/qingxie-img/icon/0cee7de9fdbc7c00fa2ed998b2d206b2/4373f88efa67250db592fe7679d46849.jpg"></img>
    <div id="fontdiv" class="fontdiv"></div>
</div>
<div class="pushtext">
    <textarea id="message" maxlength="15"></textarea>
    <div id="pushDanmu" class="pushbtn">发送</div>
</div>
<%
    InetAddress addr = InetAddress.getLocalHost();
    response.getWriter().println("主机地址：" + addr.getHostAddress());
    response.getWriter().println("主机名：" + addr.getHostName());
%>
</body>
<script type="text/javascript" src="resources/scripts/danmu.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    /* const serverPath = 'http://localhost:8787/';
     const access_token = '';
     */
    $(function () {
        $('#pushDanmu').click( function () {
            pushDanmu();
        });
        getDanmu();
    })


</script>
</html>
