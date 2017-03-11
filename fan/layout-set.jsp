<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
  <title>YSK餐饮运营配置管理平台</title>     
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-fan-menu.css">
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/table.css">    
  <!-- <script type="text/javascript" src="/shares/js/jquerylayout/jquery.js"></script>	-->
  <!--  <script type="text/javascript" src="/shares/js/jquery-1.5.1.min.js"></script>	-->    
  <link rel="stylesheet" href="/shares/jquery-ui-1.9.0/themes/ysk-restaurant/jquery.ui.all.css">
  <script src="/shares/jquery-ui-1.8.24.custom/development-bundle/jquery-1.8.2.js"></script>
  <script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.core.js"></script>
  <script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.widget.js"></script>
  <script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.tabs.js"></script>
  <script src="/shares/jquery-ui-1.8.24.custom/development-bundle/ui/jquery.ui.accordion.js"></script>	
  <script type="text/javascript" src="/shares/js/ui/ui.datepicker.js"></script>
  <script type="text/javascript" src="/shares/js/ui/i18n/ui.datepicker-zh-TW.js"></script>
  <SCRIPT LANGUAGE="JavaScript"  SRC="/shares/js/form.js"></SCRIPT>  
  <SCRIPT LANGUAGE="JavaScript"  SRC="/shares/js/form-zh-CN.js"></SCRIPT>  
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.form.js"></SCRIPT>  
  <SCRIPT LANGUAGE="JavaScript"  SRC="/shares/js/upload.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript"  SRC="/shares/fckeditor/fckeditor.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.table.js"></SCRIPT>  
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/util.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/strUtil.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/chkutil.js"></SCRIPT>    
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/menu.css">
  <script type="text/javascript"	src="/shares/js/plug-in/jquery.typemenu.js"></script>
  <SCRIPT src="/shares/js/list.js" type="text/javascript"></SCRIPT> 
  <link rel="stylesheet" type="text/css" href="/shares/js/jquerylayout/complex.css" />  
  <script type="text/javascript" src="/shares/js/jquerylayout/jquery.layout-latest.js"></script>
  <script type="text/javascript" src="/shares/js/jquerylayout/jcomplex.js"></script>
  <script type="text/javascript" src="/shares/js/jquerylayout/debug.js"></script>  
  <script type="text/javascript"  src="/shares/js/jquerylayout/jquery.layout.crm.js"></script>	
</head>
<body>

<!-- manually attach allowOverflow method to pane -->
<!--
<div class="ui-layout-north">
<div style="height:24px;background:url(/hytc/resources/images/topbg.jpg)  left bottom  "></div>
</div>
-->
<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
<div class="ui-layout-west  ui-widget-content">    
    <div class="ui-widget-header ui-corner-all header" style="text-align:center;">配置</div>
    <div class="content">		
    <h3 class="ui-widget-header ui-corner-all menutitle">基础</h3>	
<!--	
	<div class="menuitem" cmd="li1.do?v=fandishtype&type=菜品类别&li=li1.do">菜品类别</div>
			<div class="menuitem" cmd="li1.do?v=fandishtype&type=fandishwayofcooking&li=li1.do">菜品口味</div>
			<div class="menuitem" cmd="li1.do?v=fandishtype&type=fandishtaste&li=li1.do">烹调方式</div>			
			<div class="menuitem" cmd="li1.do?v=fandishmaterial&li=li1.do">食材</div>						
			<div class="menuitem" cmd="li1.do?v=fandishsuittype&li=li.do">菜品套餐类别</div>			
			<div class="menuitem" cmd="li1.do?v=fandishtype&type=fantabletype&li=li1.do">台桌类别</div>
			<div class="menuitem" cmd="li1.do?v=FANDININGTABLE&li=li1.do">台桌维护</div>
			-->
			<div class="menuitem" cmd="li1.do?v=fanconfig&li=li1.do">配置</div>		        
	        <div class="menuitem" cmd="li1.do?v=fanconfigprint&li=li.do">维护打印配置</div>	
			<div class="menuitem" cmd="li1.do?v=fanorderbuytypeset_1&li=li1.do">交易类别（启用）</div>			
	        <div class="menuitem" cmd="li1.do?v=fanorderbuytypeset_0&li=li1.do">交易类别（取消）</div>
			<div class="menuitem" cmd="li1.do?v=fanorderbuydiscountset_1&li=li1.do">折扣方案（启用）</div>			
	        <div class="menuitem" cmd="li1.do?v=fanorderbuydiscountset_0&li=li1.do">折扣方案（取消）</div>
			<div class="menuitem" cmd="li1.do?v=FANMEMBER&li=li1.do">会员维护</div>
			<div class="menuitem" cmd="li1.do?v=fanpartner&li=li.do">合作管理</div>
			<div class="menuitem" cmd="exportIndexPages_m.do">生成Weixin首页</div>
	<h3 class="ui-widget-header ui-corner-all menutitle">创建</h3>
	    		<div class="menuitem" cmd="loadFanConfigPrint.do">新建打印配置</div>	
				<div class="menuitem" cmd="loadFanorderbuytypeset.do">交易类别（新建）</div>
				<div class="menuitem" cmd="loadFanorderbuydiscountset.do">折扣方案（新建）</div>
    <h3 class="ui-widget-header ui-corner-all menutitle">更多</h3>		
	<div class="menuitem" cmd="li1.do?v=T_USERForFan1&li=li1.do">操作员启用</div>
	<div class="menuitem" cmd="li1.do?v=T_USERForFan0&li=li1.do">操作员禁止</div>					
	<div class="menuitem" cmd="readPageFlow.do">配置</div>		
	<div class="menuitem" cmd="li-info.do?v=dishinfo&li=li-info.do">美食知识</div>
	<div class="menuitem" cmd="li1.do?v=dishinfo&li=li1.do">美食知识(list)</div>
			<!-- <div class="menuitem" cmd="li1.do?v=info1&li=li.do">govinfo</div> -->
    <div class="menuitem" cmd="li1.do?v=district&li=li.do">商圈</div>			
</div>
</div>
<!--
<div class="ui-layout-south" style="background: #259AC6;">
	Copyright ? 2003-2009 Inc. All Rights Reserved. 北京华宇天创科技有限公司 版权所有 
</div>
-->
<div class="ui-layout-east">
	<div class="ui-widget-header ui-corner-all header" style="text-align:center;">欢迎使用</div>
</div>
<div class="ui-layout-center" id="center">
	<!-- <iframe scrolling="auto" frameborder="0" width="100%" height=\"100%\" src="main.jsp"></iframe> -->
</div>
	<script type="text/javascript">
function go_(id,path){
  var a="<%=request.getContextPath()%>/"+id;
  //var a="";
  //alert(a);
  if (a=="/sampleforf1j/#") return ;  
  //alert("hi");
  $("#center").html("正在加载...")
  $("#center").html("<iframe scrolling=\"auto\" frameborder=\"0\" width=\"100%\" height=\"100%\" src=\""+a+"\"></iframe>");      
  //alert(a);  
  //var $divs=$("#center").prev().children();
  //var title=$divs[0];
  //$(title).html("当前位置>>"+path);
  }    
  $(function() { 
  $(".menuitem").click(function() {
  //alert($(this).attr("cmd"));
  go_($(this).attr("cmd"),"OA门户111");  
  });
		});	
go_('li_shop.do?v=fancompany&li=li_shop.do',"easypos+");  

//var $parent=$("#west").parent();
</script>
</body>
</html>