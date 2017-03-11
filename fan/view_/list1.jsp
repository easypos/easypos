<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
#set($formFile=${htmlFile})
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>"列表"</title>
<style type="text/css">
td{height:24px;}
</style>
<link rel="stylesheet" href="/shares/jquery-ui-1.9.0/themes/hot-sneaks/jquery.ui.all.css">
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/type1.css"/>  
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-crm.css">
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/table.css">    
  <link rel="stylesheet" href="/shares/js/plug-in/jRating/jRating.jquery.css" type="text/css" />   
  <script src="/shares/jquery-ui-1.9.0/jquery-1.8.2.js"></script>
    <script src="/shares/jquery-ui-1.9.0/external/jquery.bgiframe-2.1.2.js"></script>
    <script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.core.js"></script>
    <script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.widget.js"></script>
 	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.mouse.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.button.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.draggable.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.position.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.resizable.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.dialog.js"></script>
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
  <script type="text/javascript"	src="/shares/js/list.js"></script>
  <SCRIPT src="/shares/js/list.js" type="text/javascript"></SCRIPT>       
  <script type="text/javascript"  src="/shares/js/themeswitchertool.js"></script>
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.form.js"></SCRIPT>
</head>
<body>
<div class="ui-widget-header ui-corner-all" style="padding-left:5px;"><h2>${hashMap.showName}</h2></div>
<ul id="jsddm1" class="typemenu">
<li><a href="#">类别</a>
<div></div>
</li>
</ul>
<div class="search" style="margin-top:0px; margin-left:-2px; margin-right:3px;">
  <li>
    <input type="text" id="keyWord"  value="${hashMap.findKey}" />
  </li>
  <li title="find"><img onclick="find();" src="resources/images/form/search.gif" /></li>
  <li id="liajax" title="ajax"><img src="resources/images/form/search.gif" /></li>
  <li id="findforad" title="findforad"><img src="resources/images/form/search.gif" /></li>  
</div>
<div style="clear:both;">#parse($formFile)</div>
<center><div id="errInfo" class="errInfo" style="display:none;"><img src="/shares/images/loading.gif"></img>loading</div></center>
<div id="content"> 
<div class="pagebar-container ui-widget-content ui-corner-all">${pager}</div>
${viewBody}
<div class="pagebar-container ui-widget-content ui-corner-all">${pager}</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $('#switcher').themeswitcher();
	$("#jsddm1").typemenu();
	$(".typemenu").menu();	
	//$( "input:submit, a, button").button();
	//$( "input:submit, a, button").with(200);
	//$( "input:submit, a, button").height(20);	
	//$( "a", ".demo" ).click(function() { return false; });
	function showRequest(formData, jqForm, options) { 			  
				  return true;				  
				  }                  				
	function showResponse(responseText, statusText)  {  			  
			  $("#content").html(responseText);
			  $("#errInfo").hide();
			  $( "#dialog-form" ).dialog( "close" );
			  $(".liforajax").click(function() {
	             alert("==========================="); 
				 var options = {
             		     //target:     '#output1',    // 用服务器返回的数据 更新 id为output1的内容.	       
					     beforeSubmit:  showRequest,  // 提交前		
					     url:"li2.do?p="+$(this).attr("p"),
				         success:       showResponse,  // 提交后 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm").submit(); 
			});		
             } 
	$("#liajax,.liforajax").click(function() {
	             alert("==========================="); 
				 var options = {
             		     //target:     '#output1',    // 用服务器返回的数据 更新 id为output1的内容.	       
					     beforeSubmit:  showRequest,  // 提交前		
					     url:"li2.do",
				         success:       showResponse,  // 提交后 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm").submit(); 
			});	
			
			$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
				"搜索": function() {			
				 var options = {
             		     //target:     '#output1',    // 用服务器返回的数据 更新 id为output1的内容.	       
					     beforeSubmit:  showRequest,  // 提交前		
					     url:"li2.do",
				         success:       showResponse,  // 提交后 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm").submit(); 
				
						//$( this ).dialog( "close" );					
				},
				"取消": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				//allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#findforad" )			
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
		
});
</script> 
</body>
</html>
