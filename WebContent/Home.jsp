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
    

<div class="container">

    <header>
       <h1>ETSFS HOME PAGE </h1>
    </header>
   
    <div id="section" style="width:70%;float:left;padding:10px;border-bottom: 1px solid cadetblue;display: block;">
         	<div class="singleDataset" id="singleDataset" style= "display: block;" >
         		<div class="row">
	         		<div class="col s12 m6">
	         			<h5 class="light">Choose One Option:</h5>
	         		</div>
	         		<div class="col s12 m3">
		         		<div class="collection">
			         	
						    <a href="Upload.jsp" class="collection-item"><span class="badge"></span>Redirect to Encryption </a>
						    <a href="DecryptTypes.jsp" class="collection-item"><span class="badge"></span>Redirect to Decryption</a>
					  	</div>
	         		</div>
	         	</div>
             
        	</div>
        
		    
    </div>
	<div>
	   		<footer>Copyright © Sam Houston State University</footer>
   </div>      
</div>
</body>
</html>