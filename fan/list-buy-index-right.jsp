<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/type1.css"	TITLE="SID OA">
<script type="text/javascript"	src="/shares/js/jquery-1.3.2.min.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.table.js"></SCRIPT> 
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.form.js"></SCRIPT>
<script src="/shares/js/ui/ui.core.js"></script>
<script src="/shares/js/ui/ui.widget.js"></script>
<script src="/shares/js/ui/ui.mouse.js"></script>
<script src="/shares/js/ui/ui.draggable.js"></script>
<script src="/shares/js/ui/ui.resizable.js"></script>
<SCRIPT src="/shares/js/list.js" type="text/javascript"></SCRIPT>
<title>下单</title>
<SCRIPT LANGUAGE="JavaScript">
function add(id,title){
alert(id+title);
  $("#tableData").addTr({colmuns:colmuns,data:[{id_:id,title_:title},"1"],count:'Count'}); 
}
  var colmuns=
  [{title:'项目',id:'dishid',type:'combox',copy:'type',format:'1',css:"a"},
  {title:'数量',id:'dishcount',type:'text',format:'1',css:{width:"50px"},value:"1"}  
  ];
$(document).ready(function(){ 
  $("#tableData").addRow(
  {colmuns:colmuns,data:[],count:'Count',msgRender:"#msg"}); 
  //$("#tableData").sum({colmuns:[{index:5,type:"value"},{index:4,type:"count"},{index:6,type:"percent"}],
  //render: "#sumprice2",edit:true});
  //$("#tableData").sumRow({colmuns:[{index:3,type:"count"},{index:4,type:"value"}],index:5,edit:true});   
  //$("#tableData").sum({colmuns:[{index:3,type:"count"},{index:4,type:"value"}],render: "#sumprice1",edit:true});   
  //$("#tableData").sum({colmuns:[{index:3,type:"value"}],render: "#sumtest1",edit:true});   
  $('#menu-form').ajaxForm(
	                {					   
             		   //target:        '#output1',   // 用服务器返回的数据 更新 id为output1的内容.	       
					    beforeSubmit:  showRequest,  // 提交前
				        dataType:'json',
						success:       processJson,  // 提交后 				        						
				        resetForm: false       				       
				    }
	); 	
	 // 提交前
	function showRequest(formData, jqForm, options) { 			  
	              $("#list").html("loading...")
				  return true;				  
				  }                  				
	function processJson(list){ } 
	$("#btn-save").click(function() { alert("====================");$("#menu-form").submit();}); 	
});
</SCRIPT>
</head>
<body>
<form id="menu-form" action="aa.do" method="post">
 <div style="margin:0px auto;background:#ff7400;line-height:24px" id="menu">菜单列表<div>
 <li style="background:#f1f1f1;line-height:24px;"><input type="button" value="台桌">
 <input type="button" id="btn-save" value="下单">
 </li>
 <input type="hidden" value="0"  name="Count"  id="Count"> 
 <div class="forum-container">
 <table id="tableData" style="background:#fff;width:100%" ></table>
 </div>
</form >
</body>
</html>
