<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>�촬-���ERPϵͳ</title>
<script type="text/javascript"	src="/shares/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
<!-- 
var time = 8; //ʱ��,�� 
function timeOut(){
//alert("===");
$("#wait-info").text("��Ǹ,����ʱ.")
}
//timer=setInterval('dis()', 1000);//��ʾʱ��
timer=setTimeout('timeOut()',10000); //��ת 
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
<img src="008.gif" />����������,���Ժ�...
</div>
<center>
</div>
<div id="layer2"  style="display:none" >
<iframe   frameborder="0" scrolling="yes"  background="#fff" height="100%" width="100%" src="li.do?v=<%=request.getParameter("v")%>">
�������֧��Ƕ��ʽ��ܣ�������Ϊ����ʾǶ��ʽ��ܡ�</iframe></div>
</body>
</html>
<script language="javascript">
//layer2.style.display="none";
</script>
