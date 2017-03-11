<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<title>欢迎使用网络多媒体信息发布系统</title>
    
	<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/type1.css"/>  
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-fan-menu.css">
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/table.css">  
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/themes/base/ui.all.css">
  <link rel="stylesheet" href="/shares/js/plug-in/jRating/jRating.jquery.css" type="text/css" />   
  <!-- <script type="text/javascript" src="/shares/js/jquerylayout/jquery.js"></script>	-->
  <script type="text/javascript" src="/shares/js/jquery-1.5.1.min.js"></script>	  
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
	
	<script type="text/javascript" src="/shares/js/jquerylayout/jquery.layout.js"></script>
	<script type="text/javascript" src="/shares/js/jquerylayout/complex.js"></script>

	<script type="text/javascript">

	var myLayout; // a var is required because this page utilizes: myLayout.allowOverflow() method

	$(document).ready(function () {
		myLayout = $('body').layout({
			// enable showOverflow on west-pane so popups will overlap north pane
			west__showOverflowOnHover: true

		//,	west__fxSettings_open: { easing: "easeOutBounce", duration: 750 }
		});
 	});

	</script>
	<script type="text/javascript">
function go_(id,path){
  var a="<%=request.getContextPath()%>/"+id;
  //var a="";
  //alert(a);
  if (a=="/oa/#") return ;  
  //alert("hi");
  $("#center").html("正在加载...")
  $("#center").html("<iframe scrolling=\"auto\" frameborder=\"0\" width=\"100%\" height=\"100%\" src=\""+a+"\"></iframe>");      
  //alert(a);  
  //var $divs=$("#center").prev().children();
  //var title=$divs[0];
  //$(title).html("当前位置>>"+path);
  }  
  
  $(function() { 
  $(".menu").click(function() {
  //alert($(this).attr("cmd"));
  go_($(this).attr("cmd"),"OA门户111");  
  });
		});	
//go_('a.jsp',"OA门户111");  
//var $parent=$("#west").parent();
</script>
	<style type="text/css">
	/**
	 *	Basic Layout Theme
	 * 
	 *	This theme uses the default layout class-names for all classes
	 *	Add any 'custom class-names', from options: paneClass, resizerClass, togglerClass
	 */

	.ui-layout-pane { /* all 'panes' */ 
		background: #FFF; 
		border: 1px solid #BBB; 
		padding: 0px; 
		overflow: hidden;
	} 

	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: #ff7400; 
	} 

	.ui-layout-toggler { /* all 'toggler-buttons' */ 
		background: #AAA; 
	} 


	</style>


	<style type="text/css">
	/**
	 *	ALL CSS below is only for cosmetic and demo purposes
	 *	Nothing here affects the appearance of the layout
	 */

	body {
		font-family: Arial, sans-serif;
		font-size: 0.85em;
	}
	p {
		margin: 1em 0;
	}

	/*
	 *	Rules below are for simulated drop-down/pop-up lists
	 */

	ul {
		/* rules common to BOTH inner and outer UL */
		z-index:	100000;
		margin:		1ex 0;
		padding:	0;
		list-style:	none;
		cursor:		pointer;
		border:		1px solid Black;
		/* rules for outer UL only */
		width:		15ex;
		position:	relative;
	}
	ul li {
		background-color: #EEE;
		padding: 0.15em 1em 0.3em 5px;
	}
	ul ul {
		display:	none;
		position:	absolute;
		width:		100%;
		left:		-1px;
		/* Pop-Up */
		bottom:		0;
		margin:		0;
		margin-bottom: 1.55em;
	}
	.ui-layout-north ul ul {
		/* Drop-Down */
		bottom:		auto;
		margin:		0;
		margin-top:	1.45em;
	}
	ul ul li		{ padding: 3px 1em 3px 5px; }
	ul ul li:hover	{ background-color: #FF9; }
	ul li:hover ul	{ display:	block; background-color: #EEE; }
	
	
	.menu {
	font-size: 13px;
	font-weight:300;
	width: 170px;
	margin-left: 1px;
	height: 30px;
	line-height: 30px;
	list-style-type:none;	
	color: #000;
  }
  
 

.menu:hover{
	background:url(/hytc/resources/images/a_active_bg.jpg) repeat-y;
	color:#dd680c;
}

	</style>

</head>
<body>

<!-- manually attach allowOverflow method to pane -->
<!--
<div class="ui-layout-north">
<div style="height:24px;background:url(/hytc/resources/images/topbg.jpg)  left bottom  "></div>
</div>
-->
<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
<div class="ui-layout-west">    
	<div class="panel" title="定单">	
	<div class="menu" cmd="list-index.jsp?v=FANORDER&li=li-order.do">定单预定池</div>
			<div class="menu" cmd="list-index.jsp?v=FANORDER1&li=li-order.do">定单结算池</div>
			<div class="menu">撤消定单</div>			
			<div class="menu" cmd="list-index.jsp?v=FANORDER2&li=li-order.do">已结算定单</div>
			<div  class="menu"cmd="list-index.jsp?v=FanOrderBuy">交易记录</div>
			<div  class="menu" cmd="list-index.jsp?v=fanorderlog">退菜记录</div>	
	</div>

	<div class="panel" title="更多">	
	<div  class="menu" cmd="list-index.jsp?v=fanhandover">交班记录</div>
	<div  class="menu" cmd="list-index.jsp?v=fanhandover">交班</div>
	</div>
</div>
<!--
<div class="ui-layout-south" style="background: #259AC6;">
	Copyright ? 2003-2009 Inc. All Rights Reserved. 北京华宇天创科技有限公司 版权所有 
</div>
-->
<!--
<div class="ui-layout-east">
	欢迎使用!!!
</div>
-->
<div class="ui-layout-center" id="center">
	<!-- <iframe scrolling="auto" frameborder="0" width="100%" height=\"100%\" src="main.jsp"></iframe> -->
</div>

</body>
</html>