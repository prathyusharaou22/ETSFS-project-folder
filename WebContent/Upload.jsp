<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
      <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
    <style>
	        div.container {
	            width: 100%;
	            border: 1px solid cadetblue;
	            border-radius: 5px;
	        }
	
	        header, footer {
	            padding: 1em;
	            color: white;
	            background-color:cadetblue;
	            clear: left;
	            text-align: center;
	        }
	
	        nav {
	            float: left;
	            max-width: 160px;
	            margin: 0;
	            padding: 1em;
	        }
	
	        nav ul {
	            list-style-type: none;
	            padding: 0;
	        }
	
	        nav ul a {
	            text-decoration: none;
	        }
	
	        article {
	            margin-top: 250px;
	            border-left: 1px solid cadetblue;
	            padding: 1em;
	            overflow: hidden;
	        }
	</style>
  	
    
</head>   

	<script>
	      $(document).ready(function(){
	    	  
	    	  //Radio button change validation 
	    	  
	              $('input.test1').click(function() {
	  	    	    if ($("input[id='test1']").prop("checked", true)) {
	  	    	    	
	  	    	    	$("#singleDataset").css("display", "block");
	  	    	    	$("#fileUplodDiv").css("display", "none");
					//	$("#upload").css("display", "none");
	  	    	    	
	  	    	    }
	              });
	              $('input.test2').click(function() {
	  	    	    if ($("input[id='test2']").prop("checked", true)) {
	  	    	  		
	  	    	    	$("#singleDataset").css("display", "none");
	  	    	    	$("#fileUplodDiv").css("display", "block");
	  	    	   // 	$("#upload").css("display", "block");
	  	    	   		
	  	    	    	
	  	    	    }
	              }); 
	              
	         // Submit button validation
	         
	         
	              $('#upload').click(function() {
	            	  
	            	 if($("input[id='test1']").prop("checked")){
		            	  if($("#name").val().length === 0 || $("#email").val().length === 0 || $("#address").val().length === 0 || 
		            			  $("#department").val().length === 0 || $("#social").val().length === 0 || $("#password").val().length === 0 ){
			    	   			alert("All the data must be entered");
			    	   			return false;
			    	   		}
	            		 
	            	 } 
	            	 else if($("input[id='test2']").prop("checked")){
	            		 var file = $('input[type=file]').val();       

	            		   if ( ! file) {
	            		       alert('The file is required.');
	            		       event.preventDefault();
	            		       return;
	            		   }
	            	 }
	            	  
	              });
	              
	             
	    	});
     </script> 
	

<body>
    
    
<form method="POST" action="uploadDataCtrl" enctype="multipart/form-data">

<div class="container">

    <header>
       <h1>ENCRYPTION </h1>
    </header>
    <div id="nav" class = "nav" style= "line-height:30px;height:700px;width:30%;float:left;padding:5px;border: 1px solid cadetblue;"> 
	    <div class="col s12 m4 l3">
	        <div>
	            <label for="Type">Type </label>	
	            <div>
	                <div class="input-field col s3" style= "margin-left:10px;margin-top:-10px;" >
		              
		                <p>
					      <input name="group1" type="radio" id="test1" class="test1" checked="checked"/>
					      <label for="test1">Want to encrypt a single data set</label>
					    </p>
					    <p>
					      <input name="group1" type="radio" id="test2" class="test2" />
					      <label for="test2">Want to encrypt a file</label>
					    </p>
	                    
					   
	                <br>
	                </div> <br>
	            </div>
	            
	        </div>
		</div> 
	</div>
  
    
    <div id="section" style="width:70%;float:left;padding:10px;border-bottom: 1px solid cadetblue;display: block;">
         	<div class="singleDataset" id="singleDataset" style= "display: block;" >
            	
                	<div class="col s12">
	                  <div class="row">
	                    <div class="input-field col s6">
	                      <input id="name" type=text class="validate" name="name" >
	                      <label for="name">Name</label>
	                    </div>
	                     <div class="input-field col s6">
	                      <input id="email" name="email" type="email" class="validate">
	                      <label for="email">Email</label>
	                    </div>
	                  </div>
	                    
	                    <div class="row" style="height: 65px; ">
	                    <div class="input-field col s6">
	                      <input id="department" name="department" type="text" class="validate">
	                      <label for="department">Department</label>
	                    </div> 
	                    <div class="input-field col s6">
	                      <input id="password" name="password" type="password" class="validate">
	                      <label for="password">Password</label>
	                    </div>
	                  </div>
	                    <div class="row">
	                    <div class="input-field col s6">
	                      <input id="address" name="address" type="text" class="validate">
	                      <label for="address">Address</label>
	                    </div> 
	                    <div class="input-field col s6">
	                      <input id="social" name="social" type="text" class="validate">
	                      <label for="social">Social</label>
	                    </div> 
	                   </div>
                    </div>
             
        	</div>
        
		    <div class="file-field input-field" id="fileUplodDiv" style= "display: none;">
		      <div class="btn">
			      <span>File</span>
		      </div>
		       <div class="file-path-wrapper">
		      
		       		<input class="file-path validate" type="text" style="color:gray;"> 	
		       </div>
		      
		      <input type="text" name="description" style= "display: none;" />
    			<input type="file" name="file" />
    			
		    </div>
           
  </div>
  
           <input type="submit" value="Submit" name="upload" id="upload"  style="margin-left: 300px; margin-top: 100px"  />

	          
	 
    <footer>Copyright © Sam Houston State University</footer>

</div>

</form>
</body>
</html>