<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<%@ page import="com.f1jeeframework.http.AppSession"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.f1jeeframework.view.service.ReportService"%>
<%@ page import="com.f1jeeframework.view.ReportSession"%>
<%@ page import="com.f1jeeframework.model.FormItemBean"%>
<%@ page import="com.f1jeeframework.view.ReportSession"%>
<%@ page import="com.f1jeeframework.model.ViewBean"%>
<% 
	HashMap hashMap = (HashMap) request.getAttribute("hashMap");	 
   String appName=(String)hashMap.get("viewId");   
   String engineName=(String)hashMap.get("engineName");
	ApplicationContext cxt = AppSession.getApplicationContext();
    ReportService reportService = (ReportService) cxt.getBean("reportService"); 
    String showName=(String)hashMap.get("showName");        
	String viewId = (String) hashMap.get("viewId");
	ReportSession reportSession = reportService.getReportSession(viewId);
	ViewBean viewBean = reportSession.getViewBean();		
	String addApp = (String) hashMap.get("addAppName");
	String delApp = (String) hashMap.get("delAppName");
	String editApp = (String) hashMap.get("editAppName");
	String seeApp = (String) hashMap.get("seeAppName");
	Vector indexs = (Vector) hashMap.get("indexList");
	Vector roles = (Vector) hashMap.get("roles");
	String[][] viewDatas = (String[][]) hashMap.get("viewDatas");
	String[] ids = (String[]) hashMap.get("ids");
	String colCounts = (String) hashMap.get("colCounts");
%>
<html>
<head>
<title><%=showName%></title>
<link rel="stylesheet" href="/shares/resources/css/themes/base/ui.all.css">
<style>
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan.css"	TITLE="SID OA">
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan-list-buy.css">
<link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-fan.css">
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.table.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.panel.js"></SCRIPT>
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

<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/util.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/strUtil.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/chkutil.js"></SCRIPT>
<SCRIPT src="/shares/js/list.js" type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.form.js"></SCRIPT>
<script type="text/javascript"> 
$(document).ready(function(){
var id,title,count,price;
// 提交前
	function showRequest(formData, jqForm, options) { 			  
				  return true;				  
				  }                  				
	function showResponse(responseText, statusText)  {  
			  a=responseText;
			  a=a.substring(0,1);
              //alert(a);			  
              //var a=responseText;  
			  if (a=="s"){
			  alert("操作成功!");
			   window.parent.frames["right"].add(id,title,$("#dishcount").val(),price);
			  }
			  else
			  {
			  alert("操作失败!");
			  }
             }   
	$("a.switch_thumb").toggle(function(){
	  $(this).addClass("swap"); 
	  $("#list").fadeOut("fast", function() {
	  	$(this).fadeIn("fast").addClass("thumb_view").removeClass("display"); 
		 });
	  }, function () {
      $(this).removeClass("swap");
	  $("#list").fadeOut("fast", function() {
	  	$(this).fadeIn("fast").removeClass("thumb_view").addClass("display");
		});
	});  	
    $(".btn-add").click(function() {
	            id=$(this).attr("id");
				title=$(this).attr("title");
				price=$(this).attr("price");				
				$("#price").val(price);
				$("#dishid").val(id);
				//count=$(this).attr("count");				
			  $.ajax({
              type:'GET',
			  dataType:'json',
              url:'listSuitTypeDishs.do?id='+id,			
              cache:false,   			  
			  error:function(data){						  
			  $("#dishsuit").html("-");
			  },			  
			  success:function(data){
			    $("#dishsuit").html("");
				$("#count1").val("");
				for ( var i = 0; i < data.length; i++){				  
				  var type = data[i].type;
				  //alert(type);
				  var child = data[i].child;
				  $panel=$("<div class='panel' title='"+type+"'></div>");				 
				  
				  $("#dishsuit").append($panel)
				    var colmuns=
                   [{title:'菜品',id:'dishid',type:'dlgValue',view:'dish',viewIndex:"2",copy:'dishs',format:'1',css:"a"},
                   {title:'数量',id:'count1',type:'text',format:'1',css:{width:"50px"},value:"1"}  
                   ]; 				  
                  $table=$("<table width=\"100%\" id=\"\"></table>")
				  $table.attr("id",i)
				  $table.addRow({colmuns:colmuns,data:child,count:'count1',msgRender:"#msg",showDelBtn:false});  
				  $panel.append($table);
                  $panel.create();				  				  
				  for ( var j = 0; j < child.length; j++){				  				  
				  
				  //alert(child[j].name);
				  }				  
				}				
			}			
			})				
	            $("#dialog-form").dialog("open");
	            //alert($(this).attr("id"));				
				//$right=$("#right",window.parent.document);
				//$menu=$(window.parent.frames["right"].document).find("#order");								
				//window.parent.frames["right"].add($(this).attr("id"),$(this).attr("title"),$(this).attr("price"));
				//$menu.append("<li style=\"height:24px;line-height:24px;background:#f1f1f1;border:1px solid #000;\"><input type=hidden value=\"a\">"+$(this).attr("title")+"<span style=\"float:right;\"><input type=button value=\"x\"></span></li>");
	});
	$("#dishcount").bind('keyup',function(){
	   var bValid = true;				
						bValid=bValid&&(ChkUtil.isInteger($("#dishcount").val()));
						bValid=bValid&&($("#dishcount").val()>0);
						bValid=bValid&&($("#dishcount").val()<10);						
						if (!bValid){
						 $("#errmsg").text("需要数字格式,1-10");						
						  $("#errmsg").attr("class","error");
						}
						else
						{
						$("#errmsg").text("对了");	
                        $("#errmsg").attr("class","success");
						}
	});
    $("#dialog-form").dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
				"确定": function() {	
                        var bValid = true;				
						bValid=bValid&&(ChkUtil.isInteger($("#dishcount").val()));
						bValid=bValid&&($("#dishcount").val()>0);
						bValid=bValid&&($("#dishcount").val()<10);						
						if (!bValid){
						  $("#errmsg").text("需要数字格式,1-10");						
						  $("#errmsg").attr("class","error");
						  return;
						}
						else
						{
						$("#errmsg").text("对了");	
                        $("#errmsg").attr("class","success");
						}
						//==============判断是不是套餐==============
						//=============================
						var view=$("#appName").val();						
						if (view=="Dishsuit2"){
                         $("#ViewForm").attr("action","createOrderItem.do");
                        //if (!validForm()){return("");}   	
	                    var options = {
             		     //target:        '#output1',   // 用服务器返回的数据 更新 id为output1的内容.	       
					     beforeSubmit:  showRequest,  // 提交前		
					     url:"createOrderItem.do",
				         success:       showResponse,  // 提交后 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#form1').ajaxForm(options); 		 	 
	                    $("#form1").submit(); 						
						//=============================						
						}
						else
						{
						$( this ).dialog( "close" );
						$menu=$(window.parent.frames["right"].document).find("#order");								
				                    window.parent.frames["right"].add(id,title,$("#dishcount").val(),price);
						}
				},
				取消: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				
			}
		});
		
		
	
	$(".btn-see").click(function() {
				popup("seeDish.do?id="+$(this).attr("id"));				
	});	   
	$(".list-right").width(document.documentElement.clientWidth-170);		
    //$("dd a:odd").attr("class","");	
    //$("dd a:odd").attr("class","menu02");		
	//alert("");
	//alert($("#type-menu").height());
});
</script>
<style type="text/css"> 

</style>
</head>
<body>
<form name="ViewForm" id="ViewForm" method="post">
  <input name="appName" id="appName"  type="hidden" value=<%=appName%>>
  </input>
  <input name="viewName" id="viewName"  type="hidden" value=<%=appName%>>
  </input>
  <input id="li-name" type="hidden" value=<%=engineName%>>
  </input>  
  
  <%
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
%>
    <div id="type-menu">
      <dl class=choose>
        <dt>类别</dt>
        <dd>
          <html:viewDefaultType />
        </dd>
        </dt>
      </dl>
    </div>  
  <div class="list-right1"> <a href="li-dish-buy.do?v=Dish">单品</a>|<a href="li-dish-buy.do?&v=Dishsuit1">固定套餐</a>|<a href="li-dish-buy.do?v=Dishsuit2">组合套餐</a>
    <div class="pagebar-container">
      <html:viewDefaultPager />
      <div class="sou-bar">
        <input type="text" id="keyWord"  value="<%=findKey%>"/>
        <img onclick="find();" src="/shares/resources/images/sou-btn.png" /></div>
    </div>
    <ul class="thumb_view"  id="list">
      <%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>
      <li>
        <div class="content_block">
          <center>
            <a href="#"><img src="/ysk/attachfiles/dish/<%=viewDatas[i][0]%>/<%=viewDatas[i][1]%>" width="80" height="60"></a>
            <h2><%=viewDatas[i][2]%> <img src="/shares/images/menu/money.png"><%=viewDatas[i][3]%></h2>
            
			<br>
			<input type="button" class="btn-add" type="button" value="购买" id="<%=viewDatas[i][0]%>" title="<%=viewDatas[i][2]%>" price="<%=viewDatas[i][3]%>">
            <input class="btn-see" type="button" value="查看" id="<%=viewDatas[i][0]%>">
            <p><%=viewDatas[i][6]%></p>
          </center>
        </div>
      </li>
      <% } %>
    </ul>
    <!-- end data  -->
    <div class="pagebar-container">
      <html:viewDefaultPager />
    </div>
  </div>
  <script type="text/javascript">
//var layer1=window.parent.document.getElementById("layer1");
//layer1.style.display="none";
//var layer2=window.parent.document.getElementById("layer2");
$layer1=$("#layer1",window.parent.document);
$layer1.fadeOut();
$layer1.hide();
$layer2=$("#layer2",window.parent.document);
//layer2.style.display="block";

//$layer2.css("opacity","0.1")
//$layer2.animate({opacity:"1"},200)
//$layer2.fadeIn();
//$layer2.fadeIn("slow");
$layer2.show();

</script>
  <script type="text/javascript"> 
$(document).ready(function(){ 
	$(".list-right").width(document.documentElement.clientWidth-170);		
    $("dd a:odd").attr("class","");	
    $("dd a:odd").attr("class","menu02");		
	//alert("");
	//alert($("#type-menu").height());
});
</script>  
</form>
<div id="dialog-form" title="请数入菜品数量和做法">
    <form id="form1">
	<input type="hidden" id="dishid" name="dishid">
    </input>
    <input type="hidden" id="price" name="price">
    </input>
    <input type="hidden" id="count1" name="count1" value="0">
    </input>
  <input type="hidden" id="status" name="status" value="0">
  </input>
    <fieldset>
    <div id="errmsg"></div>
    <label for="count">菜品数量</label>
    <input type="text" name="dishcount" id="dishcount" class="text ui-widget-content ui-corner-all" />
    <label for="content">菜品做法</label>
    <input type="text" name="content" id="content" value="" class="text ui-widget-content ui-corner-all" />
    <div id="dishsuit"></div>
    </fieldset>
	</form>
  </div>
</body>
</html>
