<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="oasession" class="com.f1jeeframework.util.OASession" scope="session" />
<%@ page import="com.f1jeeframework.util.*"%>
<%@ page import="java.util.*"%>
<%
String[][]  menus=(String[][])request.getAttribute("list");
Vector roles =(Vector)request.getAttribute("roles");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>快船-餐饮ERP商务系统-后台系统</title>
<link rel="StyleSheet" href="/shares/js/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="/shares/js/dtree/dtree.js"></script>
<link rel="stylesheet" type="text/css" href="/shares/resources/css/easyui/themes/orange/easyui.css">
<link rel="stylesheet" type="text/css" href="/shares/resources/css/easyui/themes/icon.css">	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/portal1.css">	
<LINK REL="stylesheet" TYPE="text/css" HREF="<%=request.getContextPath()%>/resources/css/OA.css" TITLE="SID OA">
<script type="text/javascript" src="/shares/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/shares/js/plug-in/jquery.easyui.min.js"></script>
<script type="text/javascript">
var layer1=window.parent.document.getElementById("layer1");
layer1.style.display="none";
var layer2=window.parent.document.getElementById("layer2");
layer2.style.display="block";
alert("hi");
function menu(id){
//alert(id);
		$.ajax({
              type:'GET',
			  dataType:'xml',
              url:'AjaxTree.do?id='+id,			
              cache:false,   			  
			  error:function(data){						  
			  $("#tree-div").html("加载错误!");			  
			  },			  
			  success:function(data){			  
			  d = new dTree('d');         
			  d.add(0,-1,'');
			  $(data).find("menu").each(function(i){               
			  var id=$(this).attr("id");
			  var pid=$(this).attr("pid");			  
			  var name=$(this).attr("name");
			  var url=$(this).attr("url");
			  var tip=$(this).attr("tip");
			  var img=$(this).attr("img");
			  if (img!="") img="/shares/"+img;
			  d.add(id,pid,name,url,name,'doc1',img);				  
			  //d.add(id,pid,name,url,'','',img);	
			  });
			   $("#tree-div").html(""+d);
			  }
			  })
		}
menu('fan');
function addBookmark(title,url) {
if (window.sidebar) { 
window.sidebar.addPanel(title, url,""); 
} else if( document.all ) {
window.external.AddFavorite( url, title);
} else if( window.opera && window.print ) {
return true;
}
}

function find_(){   
 url=$("#finds").val()+"&k="+$("#find_").val();  
 //alert(url);
 go_(url,"搜索");
 return;
}
</script>

<script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script> 
</head>
<body onload="MM_preloadImages('resources/img/zdh_tb22.gif','resources/img/zdh_tb23.gif','resources/img/zdh_tb24.gif','resources/img/zdh_tb25.gif','resources/img/zdh_tb26.gif','resources/img/zdh_tb27.gif','resources/img/zdh_tb21.gif','resources/img/tubiao21.gif','resources/img/tubiao22.gif','resources/img/tubiao23.gif','resources/img/tubiao24.gif','resources/img/tubiao25.gif','resources/img/tubiao26.gif','resources/img/tubiao27.gif','resources/img/tubiao28.gif')">
<div class="easyui-layout" style="width:100%;height:100%;">
		<div region="south"  style="background:url(/shares/resources/css/easyui/themes/orange/images/panel_title.png);height:16px;line-height:16px;color:#000;">
		<span style="font-size:9px;">快船出品,Based by F1jee|f1jee.com.cn,copyright all</span>
		</div>		
		<div region="west" id="west" style="background-color: #fff;" split="true" title="目录" style="width:180px;">
			<div id="tree-div" >		   
				正在加载...
		 </div>
		</div>
		<div region="center"   id="center" title1="当前位置>>ERP>>门户" style="background:#fff;" style="overflow:hidden;">		
		<!--
		<div id="layer1" width="100%">
        
        <div id="wait-info" style="width:100%;height:24px;background:#fff ;margin:auto;border:2px solid #f1f1f1" >
        <img src="008.gif" />处理数据中,请稍后...
        </div>		
        
        </div>
		<div id="layer2"  style="display:none" width="100%">	</div>
		-->
		</div>
	</div>
<script type="text/javascript">
function go_(id,path){
if (id=="#") return;
  var a="<%=request.getContextPath()%>/"+id;
 // alert(a);
  if (a=="/fan/#") return ;  
  //$("#center").html("正在加载...");  
  //$("#layer1").show();
  $("#center").html("<iframe scrolling=\"auto\" frameborder=\"0\" width=\"100%\" height=\"100%\" src=\""+a+"\"></iframe>");       
  //var $divs=$("#center").prev().children();
  //var title=$divs[0];
  //$(title).html("当前位置>>"+path);
  }
//go_('main.jsp',"欢迎");
$("#west").width("180px");  
menu('fan-report');
</script>
</body>
</html>
