    $(document).ready(function(){  
     //��������
	$("#btn-check1").click(function(){	
    tmp=window.confirm("�������̣�Ҫ������");						
    if (tmp ==false)
    {
        return;
    }		
		//var str="��ѡ�е��ǣ�\r\n";		
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
			  //$("#errInfo").info("����û�����!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("�������!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
	
	//ȡ������
	$("#btn-check0").click(function(){	
    tmp=window.confirm("ȡ�����̣�Ҫ������");						
    if (tmp ==false)
    {
        return;
    }		
		//var str="��ѡ�е��ǣ�\r\n";		
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
			  //$("#errInfo").info("����û�����!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("�������!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
	
	//ȡ������
	$("#btn-check2").click(function(){	
    tmp=window.confirm("�����̼����������Ҫ������");						
    if (tmp ==false)
    {
        return;
    }		
		//var str="��ѡ�е��ǣ�\r\n";		
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
			  //$("#errInfo").info("����û�����!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("�������!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
	//����ȫ����Ʒ	
	$("#btn-sell-all").click(function(){	
	 var $tmp=$('[name=items]:checkbox:checked');	
	 
	 if ($tmp.length>1 || $tmp.length==0){
	     window.alert("���ҽ���ѡ��һ������");						
	   return;
	 }
     tmp=window.confirm("����ȫ����Ʒ��Ҫ������");						
     if (tmp ==false)
     {
        return;
     }
     tmp=window.confirm("����ȫ����Ʒ��Ҫ��������Ҫ�ٴ�ȷ�ϣ�");						
     if (tmp ==false)
     {
        return;
     }
     tmp=window.confirm("����ȫ����Ʒ��Ҫ��������Ҫ���ȷ�ϣ�");						
     if (tmp ==false)
     {
        return;
     }	
		//var str="��ѡ�е��ǣ�\r\n";		
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
			  //$("#errInfo").info("����û�����!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("�������!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});
  });
  