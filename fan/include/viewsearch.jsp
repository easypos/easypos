<%@ page contentType="text/html;charset=gb2312"%>
<% if (keys!=null){ %>
<div class="select-container">
  <span>
    分类
  </span>
  <li>
  <select name=CatKey
			 type=combox onchange="selectCat()" style="width:120px">
      <option selected value=<%=catKey%>><%=catCode%></option>
      <%  		
				for (int i = 0; i < keys.size(); i++) {
					TypeBean typeBean = (TypeBean) keys.elementAt(i);
			%>
      <option value=<%=typeBean.getId()%>><%=typeBean.getKey()%></option>
      <%
			     }
			%>
    </select>
  </li>
</div>	
<% } %>



<div class="select-container">
  <span>
    行数
  </span>
  <li>
  <select name="Rows"  type="combox" style="width:40px;" onchange="selectRow()">
      <option selected><%=rows%></option>
      <option>15</option>
      <option>20</option>
      <option>40</option>
    </select>
  </li>
</div>
<div class="toolbar-sep"></div>

   
<div class="select-container">
  <span>
    转到
  </span>
  <li>
   <select name=jump  type=combox style="width:40px"
			onchange="jumpPage()">
      <%
				for (int i = 0; i < new Integer(pages).intValue(); i++) {
			%>
      <option><%=i%></option>
      <%
				}
			%>
    </select>
  </li>
</div>
<div class="toolbar-sep"></div>


<div class="search" style="margin-top:0px; margin-left:-2px; margin-right:3px;">
  <li>
    <input type=text value="" name="SearchInput" >
  </li>
  <li><img onclick="find();" src="resources/images/fw/form/search.gif" /></li>  
</div>
