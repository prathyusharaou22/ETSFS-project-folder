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
<body>
    
    
<form method="POST" action="DecryptionController" enctype="multipart/form-data">

<div class="container">

    <header>
       <h1>DECRYPTION </h1>
    </header>
    
    
    <div id="section" style="width:70%;float:left;padding:10px;border-bottom: 1px solid cadetblue;display: block;">
         	<div class="singleDataset" id="singleDataset" style= "display: block;" >
            	
                	<div class="col s12">
	                  <div class="row">
	                   
	                    <div class="input-field col s6">
	                      <input id="ssn" name="ssn" type="text" class="validate" placeholder="######### (Only Numbers)">
	                      <label for="ssn">Social</label>
	                    </div> 
	                   </div>
                    </div>
             
        	</div>       
   </div>
  
           <input type="submit" value="Submit" name="upload" id="upload"  style="margin-left: 300px; margin-top: 100px"  />

	          
	 
    <footer>Copyright © Sam Houston State University</footer>

</div>

</form>
</body>
</html>