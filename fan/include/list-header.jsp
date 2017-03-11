<head>
<%@ page import="com.f1jeeframework.model.FormItemBean"%>
<%@ page import="com.f1jeeframework.view.ViewExpandAction"%>
<%@ page import="com.f1jeeframework.view.TypeBean"%>
<%@ page import="com.f1jeeframework.util.*"%>
<%@ page import="java.util.*"%>
<% HashMap hashMap =(HashMap)request.getAttribute("hashMap"); 
   String showName=(String)hashMap.get("showName");
   String appName=(String)hashMap.get("appName");
   String[][] viewMenus=(String[][])hashMap.get("viewMenus"); 
%>
<title><%=(showName == null) ? "未定义" : (showName.equals("null") ? "未定义" : showName)%></title>
<link rel="stylesheet" href="/shares/jquery-ui-1.9.0/themes/ysk-restaurant/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/menu_aiyou.css">
<!-- <link rel="stylesheet" href="/shares/jquery-ui-1.8.24.custom/development-bundle/themes/hot-sneaks/jquery.ui.all.css"> -->
<script src="/shares/jquery-ui-1.8.24.custom/development-bundle/jquery-1.8.2.js"></script>
<script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.core.js"></script>
<script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.widget.js"></script>
<script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.tabs.js"></script>
<script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.button.js"></script>

<script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.accordion.js"></script>	
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/plug-in/jquery.info.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/include/top.js"></script>
<script type="text/javascript"	src="/shares/js/plug-in/jquery.typemenu.js"></script>
<SCRIPT src="/shares/js/list.js" type="text/javascript"></SCRIPT>
<script type="text/javascript">
$(document).ready(function(){	    
	$("#jsddm1").typemenu();
	$(".typemenu").menu();	
	//$( "input:submit, a, input:button").button();
	//$("#jsddm1 li,#jsddm li").button();	
	
	//$("#jsddm1 li").with("100px");	
});
</script>  
</head>