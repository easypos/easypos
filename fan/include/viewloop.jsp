<%@ page contentType="text/html;charset=gb2312"%>
<div class="pagebar-container">
 <div class="pagebar-info">ÿҳ<%=rows%>��,<%=iNowPage%>/<%=pages%>ҳ,��<%=rowCounts%>��</div>
<li><input name=iRows type=hidden value=<%=rowCounts%>>  <input name=iPage type=hidden> </li>
<li><a href="#" name="home" onclick="homePage();"><img src="resources/images/pagebar/pagebar-first-normal.gif" alt="��һҳ"/></a></li>
<li> <a href="#" name="last" onclick="lastPage();"><img src="resources/images/pagebar/pagebar-prev-normal.gif" alt="��һҳ"/></a></li>
	 
	
 <div class="pagebar-sep"></div>
<li><a href="#" name="next" onclick="nextPage();"><img src="resources/images/pagebar/pagebar-next-normal.gif" alt="��һҳ"/></a> </li>
<li><a href="#" name="end" onclick="endPage();"><img src="resources/images/pagebar/pagebar-last-normal.gif" alt="ĩҳ" /></a></li>
</div>
