<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>EASYPOS+����Ӫ����</title>
<script src="/shares/jquery-ui-1.9.0/jquery-1.8.2.js"></script>
<script type="text/javascript">
<!-- 
var time = 8; //ʱ��,�� 
function timeOut(){
//alert("===");
$("#wait-info").text("��Ǹ,����ʱ.")
}
//timer=setInterval('dis()', 1000);//��ʾʱ��
timer=setTimeout('timeOut()',1000); //��ת 
//-->
</script>
</head>
<style type="text/css">
*{margin:0; padding:0;background:#fff}
</style> 
<body>
<input type="hidden" name="index" id="index" value="<%=request.getParameter("index")%>">
<input type="hidden" name="param" id="param" value="<%=request.getParameter("result")%>">
<div id="layer1" width="100%">
<center>
<div id="wait-info" style="width:100%;height:24px;background:#ff4400 ;margin:auto;border:2px solid #f1f1f1" >
����������,���Ժ�...
</div>
<center>
</div>
<div id="layer2"  style="display:none" >
<iframe  frameborder="0" scrolling="yes"  background="#fff" height="100%" width="100%" src="liDlg.do?v=<%=request.getParameter("v")%>">
�������֧��Ƕ��ʽ��ܣ�������Ϊ����ʾǶ��ʽ��ܡ�</iframe></div>
</body>
</html>
<script language="javascript">
//layer2.style.display="none";
</script>
