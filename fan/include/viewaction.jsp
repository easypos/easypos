<%@ page contentType="text/html;charset=gb2312"%>
            <TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <TR align="center">
                    <TD class="td1">
                <div style="float:left;">        
                <% if (keys!=null){ %> 
                分类 
                <select name=CatKey
			class=formselect type=combox onchange="selectCat()">
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
                <% } %>    
                 </div>    
                 <div style="float:right;">     
                  <input type=checkbox  class="checkbox" name= selectCheckBox onclick="selectCheckBox_(this,'<%=recNums%>');refreshButton('<%=recNums%>','<%=iCount%>');">                    
                 <img src="images\list\grid-blue-split.gif" border="0"></img>
		 <input type=text value="" name="SearchInput"> 
  <input type=button value="搜" name="search" onclick="find();">


                 <img src="images\list\grid-blue-split.gif" border="0"></img>                         

                        <%  if (viewAdd.equals("1")) { %> 
                        <input type=button  value="增加" name="Add" id="Add"   onclick="add();">      
                        <% } %>  
                        <%  if (viewDel.equals("1")) { %>    
                        <input type=button  value="删除" name="Del" id="Del" disabled=true  onclick="del();">                        
                        <% 
                         }  
                        for(int i=0 ;i<actionList.size();i++){
                        ViewExpandAction viewAction=(ViewExpandAction)actionList.elementAt(i);
                        String codeName=viewAction.getCodeName();
                        String code=viewAction.getCode();		
                        %>
                        <input type=button  value=<%=codeName%> name=expand<%=i+1%>  id=name=expand<%=i+1%>  disabled=true  onclick="expandAction('<%=code%>')">                  
                        
                        <% } %> 
                    </div>
                    </TD>
                </TR>
            </TABLE>
        