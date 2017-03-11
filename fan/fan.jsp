<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<title>易极云尚EASYPOS＋运营管理平台</title>
	<link rel="stylesheet" href="/shares/jquery-ui-1.9.0/themes/ysk-restaurant/jquery.ui.all.css">
	<script src="/shares/jquery-ui-1.9.0/jquery-1.8.2.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.core.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.widget.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.tabs.js"></script>	
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.accordion.js"></script>	
	
<script>
	$(function() {
		$( "#tabs" ).tabs({
			ajaxOptions: {
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"Couldn't load this tab. We'll try to fix this as soon as possible. " +
						"If this wouldn't be a demo." );
				},
				sucess: function( xhr, status, index, anchor ) {
					alert("hi");
				}
			}
		});
		$( "#tabs" ).tabs({
         create: function(event, ui) { alert("hihi"); 
		 }
    });
	$( "#tabs" ).tabs({
	
     load: function(event, ui) {
     //alert("");
	 $(ui.panel).height(document.documentElement.clientHeight-90); }
});
    $(".ui-tabs .ui-tabs-panel").css("padding","1px 1px");
	});
	</script>
	<style type="text/css">	
html,body {background-color:#fff;overflow-y:hidden;overflow-x:hidden; !important;font-family : "Lucida Grande", Verdana, Lucida, Arial, Helvetica, 宋体,sans-serif;font-size:9pt;}
*{margin:0; padding:0;}
img, body, html {border:0pt none;margin:0px;}
address, caption, cite, code, dfn, em, strong, th, var {font-style:normal;font-weight:normal;}
ol, ul,li{list-style-image:none;list-style-position:outside;list-style-type:none;}
caption, th {text-align:left;}
<!-- h1, h2, h3, h4, h5, h6 {font-size:100%;} -->
A:link {font-family: "Verdana, Arial, sans-serif";font-size: 12px;text-decoration: none;color:#000000}
A:visited {font-family: "Verdana, Arial, sans-serif";font-size: 12px;text-decoration: none;color:#BFC1C3}
A:hover {font-family: "Verdana, Arial, sans-serif";font-size: 12px;text-decoration:none;color: #0066CC}
	</style>  
</head>
<body>
<div class="ui-widget-content ui-corner-all" style="height:26px;line-height:26px;">
<h2 style="float:left;margin-left:10px">您好!${oasession.userName},欢迎使用易极云尚餐饮管理系统</h2>
<h2 style="float:right;margin-left:1px;margin-right:10px"><a href="#">注销</a></h2>
<div style="float:right" id="switcher"></div> 
</div>

<div id="tabs">
	<ul>
	    <!--<li><a href="#tabs-2">点菜</a></li>			 -->
		<li><a href="/fan/content-set.html">基础数据</a></li>	        
		<li><a href="/fan/content-dish.html">餐厅和菜品</a></li>	    
		<li><a href="/fan/content-report.html">报表</a></li>
		<!-- <li><a href="#tabs-1">开台</a></li> -->
	</ul>
	<!--
	<div id="tabs-1">     
	<iframe scrolling="no" frameborder="0" width="100%" height="100%" src="/fan/listimplfortable.do?v=FANDININGTABLE"></iframe>	 	  	 
	</div>
	
	<div id="tabs-2">	
	<iframe scrolling="no" frameborder="0" width="100%" height="100%" src="/fan/list-buy-index.jsp"></iframe>			
	</div>
	-->
	
</div>

 <div class="ui-layout-south" style="height:24px;">
	<div id="effect" class="ui-widget-content ui-corner-all">
      <center>	
	    <p>
			Copyright ? 2009-2016 Inc. All Rights Reserved. 易极云尚，保留所有权利 
		</p>
	  </center>	
	</div>
</div>
<script type="text/javascript">
//alert(document.documentElement.clientHeight);
$("#tabs-1").height(document.documentElement.clientHeight-100);
$("#tabs-2").height(document.documentElement.clientHeight-100);
//$("#tabs-4").height(document.documentElement.clientHeight-100);
</script>	
	<script type="text/javascript">
function go_(id,path){
  var a="/fan/"+id;
  //var a="";
  //alert(a);
  if (a=="/fan/#") return ;  
  //alert("hi");
  //$("#test").html("正在加载...")
   $("#tabs-3").html("<iframe scrolling=\"auto\" frameborder=\"0\" width=\"100%\" height=\"100%\" src=\""+a+"\"></iframe>");     
   
   //alert("<iframe scrolling=\"auto\" frameborder=\"0\" width=\"100%\" height=\"100%\" src=\""+a+"\"></iframe>");  
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
go_('layout.jsp',"OA门户111");  
//alert('hi');
//var $parent=$("#west").parent();
</script>
</body>
</html>
