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
<div class="selectall">
    <li>
    <input type=checkbox  class="checkbox" name="CheckedAll" id="CheckedAll">  
    </li>
    <li>
          <div class="btn-container2">		
  
  <%  if (viewAdd.equals("1")) { %> 
                        
           <input type=button   value="增 加" name="Add" id="Add"   onclick="add();"/>  

                        <% } %>  
                        <%  if (viewDel.equals("1")) { %>    
			
                         <input type=button   value="删 除" name="Del" id="Del" disabled=true/>  
				                 
                        <% 
                         }  
                        for(int i=0 ;i<actionList.size();i++){
                        ViewExpandAction viewAction=(ViewExpandAction)actionList.elementAt(i);
                        String codeName=viewAction.getCodeName();
                        String code=viewAction.getCode();		
                        %>					
                        <input type=button   value=<%=codeName%> name=expand<%=i+1%>  id=expand<%=i+1%>    onclick="expandAction('<%=code%>')"/>                  
                        
                        <% } %> 
</div>
  </li>
</div>
<div class="search" style="margin-top:0px; margin-left:-2px; margin-right:3px;">
  <li>
    <input type=text name="SearchInput" value="<%=findKey%>">
  </li>
  <li><img onclick="find();" src="resources/images/form/search.gif" /></li>  
</div>

