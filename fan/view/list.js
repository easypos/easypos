$(document).ready(function(){
$("input[type='checkbox']").css("width","24px");
a=$("#li-name").val();
if (a=="li1.do"){
    a="li2.do";
}
else
{
   a="li4.do";
}
var c = $("form").serialize();
       //  alert(c);
        // alert(decodeURIComponent(c));
	$("#jsddm1").typemenu();
	$(".typemenu").menu();	
	//$( "input:submit, a, button").button();
	//$( "input:submit, a, button").with(200);
	//$( "input:submit, a, button").height(20);	
	//$( "a", ".demo" ).click(function() { return false; });
	function showRequest(formData, jqForm, options) { 			  
				  return true;				  
				  }                  				
	function showResponse(responseText, statusText)  {  			  
			  $("#content").html(responseText);
			  $("#errInfo").hide();
			  $( "#dialog-form" ).dialog( "close" );
			  $("input").each(function(){		this.value=decodeURIComponent(this.value); 										} ); 
			  $(".liforajax").click(function() {
	             alert("===========================2"); 
				 $("input").each(function(){				this.value=encodeURIComponent(this.value); 							} ); 
				 var options = {
             		     //target:     '#output1',    // �÷��������ص����� ���� idΪoutput1������.	       
					     beforeSubmit:  showRequest,  // �ύǰ		
					     url:a+"?p="+$(this).attr("p"),
				         success:       showResponse,  // �ύ�� 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm").submit(); 
			});		
             } 
	$("#liajax,.liforajax").click(function() {
	             alert("===========================1"); 
				 $("input").each(function(){				this.value=encodeURIComponent(this.value); 							} ); 
				 var options = {
             		     //target:     '#output1',    // �÷��������ص����� ���� idΪoutput1������.	       
					     beforeSubmit:  showRequest,  // �ύǰ		
					     url:a,
				         success:       showResponse,  // �ύ�� 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm").submit(); 
			});	
			
			$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 400,
			width: 600,
			show: "blind",			
			hide: "explode",
			modal: true,
			buttons: {
				"����": function() {			
				$("input").each(function(){				this.value=encodeURIComponent(this.value); 							} ); 
				 var options = {
             		     //target:     '#output1',    // �÷��������ص����� ���� idΪoutput1������.	       
					     beforeSubmit:  showRequest,  // �ύǰ		
					     url:a,
				         success:       showResponse,  // �ύ�� 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm_ad').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm_ad").submit(); 
				
						//$( this ).dialog( "close" );					
				},
				"ȡ��": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				//allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#findforad" )			
			.click(function() {
			//alert("111");
				$( "#dialog-form" ).dialog( "open" );
			});
			
		//$('#switcher').themeswitcher();
});


