<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="oasession" class="com.f1jeeframework.util.OASession"	scope="session" />
<%
	String okCode=(String)request.getAttribute("sucessCode");           
        //String okCode="�������!";
        //String okMessage=okCode;
%>
<HTML>
<HEAD>
<TITLE>�����Ϣ</TITLE> 
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/easyui/themes/icon.css">
  <script type="text/javascript" src="/shares/js/jquery-1.3.2.min.js"></script>		  
  <script type="text/javascript" src="/shares/js/plug-in/jquery.easyui.min.js"></script>
<SCRIPT language="JavaScript">
function backForm(){
	window.history.back();
   
}
function init(){


//window.alert("test"+"<%=okCode%>");

        tmp="<%=okCode%>";

        if ((tmp=="null") || (tmp=="")){ tmp="�������"; }
	alert(tmp);
        window.close(); 
	//backForm();
}
</SCRIPT></HEAD>
<BODY>
<div id="hi" class="easyui-panel" title="������ʾ"  collapsible="true"  style="width:700px;height:auto;padding:10px;background:#fafafa;">
<hi>�������!<hi>

</div>

</BODY>
</HTML>