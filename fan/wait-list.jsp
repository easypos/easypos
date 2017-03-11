<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>快船-点菜ERP系统</title>
<script type="text/javascript"	src="/shares/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
<!-- 
var time = 8; //时间,秒 
function timeOut(){
//alert("===");
$("#wait-info").text("抱歉,处理超时.")
}
//timer=setInterval('dis()', 1000);//显示时间
timer=setTimeout('timeOut()',10000); //跳转 
//-->
</script>
</head>
<style type="text/css">
*{margin:0; padding:0;background:#fff}
</style> 
<body>
<div id="layer1" width="100%">
<center>
<div id="wait-info" style="width:100%;height:24px;background:#fff ;margin:auto;border:2px solid #f1f1f1" >
<img src="008.gif" />处理数据中,请稍后...
</div>
<center>
</div>
<div id="layer2"  style="display:none" >
<iframe   frameborder="0" scrolling="yes"  background="#fff" height="100%" width="100%" src="li.do?v=<%=request.getParameter("v")%>">
浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe></div>
</body>
</html>
<script language="javascript">
//layer2.style.display="none";
</script>
