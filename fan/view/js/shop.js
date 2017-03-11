    $(document).ready(function(){  
     //发布店铺
	$("#btn-check1").click(function(){	
    tmp=window.confirm("发布店铺，要继续吗？");						
    if (tmp ==false)
    {
        return;
    }		
		//var str="你选中的是：\r\n";		
		var str="";		
		$('[name=items]:checkbox:checked').each(function(){
			//str+=$(this).val()+"\r\n";
			str+=$(this).val()+",";
		})
		$( "#loading").dialog( "open" );
		//alert(str);
		jQuery.ajax({
              type:'GET',
			  dataType:'string',
              url:"pubShopBySelect_m.do?id="+str,			
              cache:false,   			  
			  error:function(data){	              			  
			  //$("#errInfo").info("操作没有完成!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("操作完成!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
	
	//取消店铺
	$("#btn-check0").click(function(){	
    tmp=window.confirm("取消店铺，要继续吗？");						
    if (tmp ==false)
    {
        return;
    }		
		//var str="你选中的是：\r\n";		
		var str="";		
		$('[name=items]:checkbox:checked').each(function(){
			//str+=$(this).val()+"\r\n";
			str+=$(this).val()+",";
		})
		//alert(str);
		$( "#loading").dialog( "open" );
		jQuery.ajax({
              type:'GET',
			  dataType:'string',
              url:"cancelShopBySelect.do?id="+str,			
              cache:false,   			  
			  error:function(data){	              			  
			  //$("#errInfo").info("操作没有完成!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("操作完成!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
	
	//取消店铺
	$("#btn-check2").click(function(){	
    tmp=window.confirm("将店铺加入黑名单，要继续吗？");						
    if (tmp ==false)
    {
        return;
    }		
		//var str="你选中的是：\r\n";		
		var str="";		
		$('[name=items]:checkbox:checked').each(function(){
			//str+=$(this).val()+"\r\n";
			str+=$(this).val()+",";
		})
		//alert(str);
		$( "#loading").dialog( "open" );
		jQuery.ajax({
              type:'GET',
			  dataType:'string',
              url:"addBlackShopBySelect.do?id="+str,			
              cache:false,   			  
			  error:function(data){	              			  
			  //$("#errInfo").info("操作没有完成!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("操作完成!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
	//代理全部产品	
	$("#btn-sell-all").click(function(){	
	 var $tmp=$('[name=items]:checkbox:checked');	
	 
	 if ($tmp.length>1 || $tmp.length==0){
	     window.alert("当且仅当选择一个店铺");						
	   return;
	 }
     tmp=window.confirm("代理全部产品，要继续吗？");						
     if (tmp ==false)
     {
        return;
     }
     tmp=window.confirm("代理全部产品，要继续吗？需要再次确认！");						
     if (tmp ==false)
     {
        return;
     }
     tmp=window.confirm("代理全部产品，要继续吗？需要最后确认！");						
     if (tmp ==false)
     {
        return;
     }	
		//var str="你选中的是：\r\n";		
		var str="";		
		$('[name=items]:checkbox:checked').each(function(){
			//str+=$(this).val()+"\r\n";
			str+=$(this).val()+",";
		})
		//alert(str);
		$( "#loading").dialog( "open" );
		jQuery.ajax({
              type:'GET',
			  dataType:'string',
              url:"addShopAllProduct.do?id="+str,		
              cache:false,   			  
			  error:function(data){	              			  
			  //$("#errInfo").info("操作没有完成!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("操作完成!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
  });
  