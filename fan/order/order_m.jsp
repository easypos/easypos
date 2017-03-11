<!DOCTYPE html>
<html>
<head>
<title>云尚客餐饮</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/table.css">
<link rel="stylesheet" href="/o2o/m/css/m_esse.css" />
<script src="/shares/jquery-mobile/jquery-1.10.2.min.js"></script>
<script src="/shares/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/shares/o2o/js/jquery.order_m_init.js" type="text/javascript"></script>
<script>
$(function(){
showShoppingCart();
$("#btn-clear").click(function() {
	  r=window.confirm("清空购物车,要继续吗？");
        	  if (r ==false){
			  //alert("-");
        	  return;
        	  }
        localStorage.setItem("dishs",null);			  
        $("#order-list").html("");		
    });
});
function addShoppingCart(id,name,price,pic){
//alert("123_");
var isSave=false;
var goods = localStorage.getItem("dishs");//取回goods变量
goods = JSON.parse(goods);//把字符串转换成JSON对象
if(goods!=null&&goods!="undefined"){ //如果不为空，则判断购物车中是否包含了当前购买的商品
var objs=goods.good;
for(var i=0;i<objs.length;i++){
isSave=false;
if(objs[i].id==id){ //说明该商品已在购物车，则数量加1
objs[i].num+=1;
isSave=true;
break;
}
}
if(!isSave){
objs[objs.length]={id:id,name:name,price:price,pic:pic,num:1};
}
}else{
var goods ={good:[
{id:id,name:name,price:price,pic:pic,num:1}]
}//要存储的JSON对象
}
goods = JSON.stringify(goods);//将JSON对象转化成字符串
localStorage.setItem("dishs",goods);//用localStorage保存转化好的的字符串
//alert("ok");
$("#tishiInfo").fadeIn("show",function(){
$("#tishiInfo").fadeOut(2000);
});
}
function showShoppingCart(){
var info="";
var goods = localStorage.getItem("dishs");//取回goods变量
goods = JSON.parse(goods);//把字符串转换成JSON对象
if(goods!=null){ //如果不为空，则判断购物车中是否包含了当前购买的商品
var objs=goods.good;
for(var i=0;i<objs.length;i++){
info+="<li class='li-order'><div class='content_block'>"+
//"<div class=\"checkbox-order\">"+
"<input style=\"float:left;\" type=\"checkbox\"  class=\"checkbox_\" name=\"items\""
                    		+ " value=\""				
							+ objs[i].id + "\"" 
							+ " id=\"" + objs[i].id + "\""							
							+ "/>"
							+
  //"<label for=\"" + objs[i].id + "\"></label>"
  //+"</div>"+
"<img src='/ysk/attachfiles/dish/"+objs[i].pic+"' width='40px' height='40px' />"
+"<h2>"+objs[i].name+objs[i].id+"</h2>"+
"<p>"+"<span class='btn-add-inc'><span class=\"price\">"+objs[i].price+"</span><a class='righta' href=\"javascript:addNum('good"+objs[i].id+"')\">+</a><input type='text' id='good"+
objs[i].id+"' value='"+objs[i].num+"' /><a class='lefta' href=\"javascript:cutNum('good"+objs[i].id+"')\">-</a></span></p>"
//"<p><a href=\"javascript:delGoodFromCart('"+objs[i].id+"')\">删除</a></p>"+
"</div></li>\n";
}
//alert(info);
//$("#productInfo").append($(info));
$("#order-list").html($(info));
}
}
function addNum(id){
$("#"+id).val( parseInt($("#"+id).val())+1 );
var goods = localStorage.getItem("dishs");//取回goods变量
goods = JSON.parse(goods);//把字符串转换成JSON对象
if(goods!=null&&goods!="undefined"){ //如果不为空，则判断购物车中是否包含了当前购买的商品
var objs=goods.good;
for(var i=0;i<objs.length;i++){
var goodId="good"+objs[i].id;
if(goodId==id){ //说明该商品已在购物车，则数量加1
objs[i].num+=1;
break;
}
}
goods = JSON.stringify(goods);//将JSON对象转化成字符串
localStorage.setItem("dishs",goods);//用localStorage保存转化好的的字符串
showShoppingCart();
}
}
function cutNum(id){
var num=parseInt($("#"+id).val());
if(num>1){
var goods = localStorage.getItem("dishs");//取回goods变量
goods = JSON.parse(goods);//把字符串转换成JSON对象
if(goods!=null&&goods!="undefined"){ //如果不为空，则判断购物车中是否包含了当前购买的商品
var objs=goods.good;
for(var i=0;i<objs.length;i++){
var goodId="good"+objs[i].id;
if(goodId==id){ //说明该商品已在购物车，则数量加1
objs[i].num-=1;
break;
}
}
$("#"+id).val( num-1 );
}
goods = JSON.stringify(goods);//将JSON对象转化成字符串
localStorage.setItem("dishs",goods);//用localStorage保存转化好的的字符串
showShoppingCart();
}
}
function delGoodFromCart(id){
var goods = localStorage.getItem("dishs");//取回goods变量
goods = JSON.parse(goods);//把字符串转换成JSON对象
if(goods!=null){ //如果不为空，则判断购物车中是否包含了当前购买的商品
var objs=goods.good;
for(var i=0;i<objs.length;i++){
if(objs[i].id==id){ //说明该商品已在购物车，则数量加1
objs.splice(i,1); //删除元素
goods = JSON.stringify(goods);//将JSON对象转化成字符串
localStorage.setItem("dishs",goods);//用localStorage保存转化好的的字符串
showShoppingCart();
return;
}
}
}
}
</script>
</head>
<body>
<div class="header_">  
  <a href="javascript:history.back();" rel="nofollow" class="left_">返回</a>
   <h1>我的点菜单</h1>
   <a href="#" id="list-btn-forselect" class="view-btn-del">删除</a>
  </div>
 <div class="divided-10"></div> 
 <!-- <div class="type1-title"><input type="checkbox"  class="checkbox-all" name="CheckedAll" id="CheckedAll">一般贸易</div> -->
 <ul class="display" id="order-list">    
 <li>
 <div class="content_block" >  
 </div>  
</li> 
 </ul>
 <div class="divided-10"></div>
  <div style="height:60px;" class="cls"></div>
	<div data-role="footer" data-position="fixed"  style="border:0px;" class="menu">
    <ul class="menu_">
      <li class="ui-block-a tab-new-bj">
        <div class="tab-new-content"> <a href="/o2o/m" target="_self" class="tab_home_d">
          <div class="tab_cls"></div>
          <div>首页</div>
          </a> </div>
      </li>
      <li class="ui-block-b tab-new-bj">
        <div class="tab-new-content"> <a href="/o2o/li-dish-buy-online-m.f1j?v=dish_dali&id=${id}" target="_self" class="tab_type_d">
          <div class="tab_cls"></div>
          <div>所有菜品</div>
          </a> </div>
      </li>
      <li>
        <div  id="btn-clear"  class="btn-del"  id="view_" target="_self"> 清空</div>
      </li>
      <li>
        <div  id="confirm"  class="btn-confirm"  id="view_" target="_self"> 立即购买</div>
      </li>
    </ul>
  </div>
</body>
</html>
