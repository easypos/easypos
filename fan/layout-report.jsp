<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
  <title>YSK餐饮运营管理平台</title>    
  <LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/type1.css"/>  
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
    <div class="ui-widget-header ui-corner-all header" style="text-align:center;">报表</div>
    <div class="content">	
	<h3 class="ui-widget-header ui-corner-all menutitle">定单</h3>		
	<table class="f1-ui-table-1" border="0" cellspacing="2" cellpadding="0" width="100%">			
	<tr>
		<td ><label>选餐厅</label></td>
		<td class="detail"><div id="companyId-info">请选择</div><input type="hidden" name="companyId" id="companyId" value=""><input type="button" onclick="dListForId('fancompany','2','companyId')" value="." class="eform-btn-select"> </td>
	</tr>
	</table>
	<h3 class="ui-widget-header ui-corner-all menutitle">报表</h3>    
	<div class="menuitem" cmd="li-buy.do?li=li-buy.do&v=FanOrderBuy&o_=crt">交易流水记录</div>			
	<div  class="menuitem"cmd="reportForBuyType.do">统计（交易类别）</div>
	<div  class="menuitem"cmd="reportByOperator.do">统计（按工号）</div>
    <h3 class="ui-widget-header ui-corner-all menutitle">定单</h3>
    <div class="menuitem" cmd="li-order1.do?o_=crt">待支付定单</div> 	
	<div class="menuitem" cmd="li-order2.do?li=li-order2.do&v=fanOrder2&o_=crt">已支付定单</div>			
	<div class="menuitem" cmd="li1.do?v=fanOrder2&o_=crt">已退定单</div>	
	<div class="menuitem" cmd="li-order.do?li=li-order.do&v=fanOrder2&o_=crt">最近3天</div>	
	<h3 class="ui-widget-header ui-corner-all menutitle">更多</h3>   
    <div  class="menuitem" cmd="li1.do?v=fanhandover">交班记录</div> 	 	
	<div  class="menuitem" cmd="li-handover.do">交班记录（查询）</div> 	 	
	<div class="menuitem" cmd="li1.do?v=fanOrderBuyItemReport">营业明细</div>    
	<!--
	<div class="menuitem" cmd="li1.do?v=fanOrder2">营业查询</div>	
	<div class="menuitem" cmd="report/chart.jsp">营业统计</div>
	<div class="menuitem" cmd="report/reportOrderbuyType.jsp">销售排行</div>		
	<div class="menuitem" cmd="report/reportDay.jsp?type=1">交易当天统计</div>	
	<div class="menuitem" cmd="report/reportDay.jsp?type=2">交易当月统计</div>	
	<div class="menuitem" cmd="report/reportDay.jsp">交易任意统计</div>		
	-->
	<div  class="menuitem" cmd="li1.do?v=fanorderlog">退菜记录</div> 		
	<!--
	<div class="menuitem" cmd="#">收银统计</div>    
	<div class="menuitem" cmd="#">会员卡消费查询</div>    
	<div class="menuitem" cmd="#">退菜记录</div>    
	<div class="menuitem" cmd="#">厨师备膳记录</div>    
	<div class="menuitem" cmd="#">台桌使用记录</div>
	-->
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
  a=$(this).text();  
  if (a=="已支付定单" || a=="交易记录" || a=="交班记录") {  
    pid=$("#companyId").val();
    go_($(this).attr("cmd")+"&cid="+pid,"列表");  
  }
  else
  {
  go_($(this).attr("cmd"),"列表");  
  }  
  });
		});	
go_('li-buy.do?li=li-buy.do&v=FanOrderBuy&o_=crt',"easypos+");  
//var $parent=$("#west").parent();
</script>
</body>
</html>