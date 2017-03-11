<% String v=request.getParameter("v"); 
   String type=request.getParameter("type");   
   if (type==null)  type="";
   String o_=request.getParameter("o_");      
   String li=((request.getParameter("li"))!=null)?(request.getParameter("li")):"li.do";
   System.out.println("================="+li);
%>
<html>
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>-</title>
</head>
 
 <frameset border="2px" frameborder="2px" framespacing="10px" marginwidth="0" marginheight="0" leftmargin="0" topmargin="0" cols="*,400">
  <frame borde="0" scrolling="yes" frameborde="no" style="border-style:solid;border-width:0px;border-color: #0066CC;" name="left" src="<%=li%>?v=<%=v%>&type=<%=type%>&o_=<%=o_%>" scrolling="auto" target="right">  
  <frame borde="0" scrolling="yes"  frameborde="no" style="border-style:solid;border-width:0px;border-color: #0066CC;" name="right"  id="right" src="list-right.jsp" noresize1>
  <noframes>
  <body>
  <p>此网页使用了框架，但您的浏览器不支持框架。</p>
  </body>
  </noframes>
</frameset>

</html>
