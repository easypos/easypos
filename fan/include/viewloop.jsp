<%@ page contentType="text/html;charset=gb2312"%>
<div class="pagebar-container">
 <div class="pagebar-info">每页<%=rows%>条,<%=iNowPage%>/<%=pages%>页,共<%=rowCounts%>条</div>
<li><input name=iRows type=hidden value=<%=rowCounts%>>  <input name=iPage type=hidden> </li>
<li><a href="#" name="home" onclick="homePage();"><img src="resources/images/pagebar/pagebar-first-normal.gif" alt="上一页"/></a></li>
<li> <a href="#" name="last" onclick="lastPage();"><img src="resources/images/pagebar/pagebar-prev-normal.gif" alt="上一页"/></a></li>
	 
	
 <div class="pagebar-sep"></div>
<li><a href="#" name="next" onclick="nextPage();"><img src="resources/images/pagebar/pagebar-next-normal.gif" alt="下一页"/></a> </li>
<li><a href="#" name="end" onclick="endPage();"><img src="resources/images/pagebar/pagebar-last-normal.gif" alt="末页" /></a></li>
</div>
