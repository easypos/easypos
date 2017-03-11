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
    $('#switcher').themeswitcher();
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
	             alert("==========================="); 
				 $("input").each(function(){				this.value=encodeURIComponent(this.value); 							} ); 
				 var options = {
             		     //target:     '#output1',    // 用服务器返回的数据 更新 id为output1的内容.	       
					     beforeSubmit:  showRequest,  // 提交前		
					     url:a+"?p="+$(this).attr("p"),
				         success:       showResponse,  // 提交后 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm").submit(); 
			});		
             } 
	$("#liajax,.liforajax").click(function() {
	             alert("==========================="); 
				 $("input").each(function(){				this.value=encodeURIComponent(this.value); 							} ); 
				 var options = {
             		     //target:     '#output1',    // 用服务器返回的数据 更新 id为output1的内容.	       
					     beforeSubmit:  showRequest,  // 提交前		
					     url:a,
				         success:       showResponse,  // 提交后 				        
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
			modal: true,
			buttons: {
				"搜索": function() {			
				$("input").each(function(){				this.value=encodeURIComponent(this.value); 							} ); 
				 var options = {
             		     //target:     '#output1',    // 用服务器返回的数据 更新 id为output1的内容.	       
					     beforeSubmit:  showRequest,  // 提交前		
					     url:a,
				         success:       showResponse,  // 提交后 				        
				         resetForm: false       				       
				        };				        	  
	                    $('#ViewForm').ajaxForm(options); 		 	 
						//$("#errInfo").text("loading...");
						$("#errInfo").show();						
	                    $("#ViewForm").submit(); 
				
						//$( this ).dialog( "close" );					
				},
				"取消": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				//allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#findforad" )			
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
		
});


