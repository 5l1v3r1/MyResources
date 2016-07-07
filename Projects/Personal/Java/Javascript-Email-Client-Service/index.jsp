<%-- 
    Document   : index
    Created on : May 23, 2016, 1:17:44 PM
    Author     : jappeah
--%>

<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<head>
<!-- jquery -->

<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>

<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/themes/ui-lightness/jquery-ui.css" type="text/css" media="all" />

<script src="https://www.google.com/jsapi" type="text/javascript"></script>



<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<script type="text/javascript" src="script.js"></script>
</head>

<% 
Properties props=new Properties();
String Path = (System.getenv("ROVI_BASE") + File.separator+ "conf" +File.separator + "compare_rm_images.properties");
InputStream in = getServletContext().getResourceAsStream("/config.properties");
System.out.println(Path);
props.load(in);//new FileInputStream(new File(Path)));
String posturl = props.get("post_image_url").toString();

 %>
 
<script type="text/javascript">
    
var recent_image_url = "<%=props.get("recent_image_url").toString()%>";
var specific_image_url = "<%=props.get("specific_image_url").toString()%>";
var random_image_url = "<%=props.get("random_image_url").toString()%>";
//Get JSON data on Window load
window.onload = function()
{
	httpGet("<%=props.get("random_image_url").toString()%>");
	URLs =JSON.parse(result);
	next();
};

</script>

<body>
<!-- nav -->
<nav id="navbar" class="navbar default" style="width:100%; background:red; margin-bottom: 3px; box-shadow: 2px 2px 3px #a6a6a6">
    <p class="lead" id="title" style="text-align: center; margin-top: 13px; color:white; text-shadow: 2px 2px 3px #a6a6a6;"><strong>RANDOMLY ADDED IMAGES</strong></p>
</nav>

<!--Radio Buttons for format selection-->
<section style=" background: #e5e5e5; margin-top: 0px;border-bottom: 1px solid #bdbdbd;box-shadow: 1px 1px 3px #bdbdbd">
    <div class="container-fluid" style="text-align: center;">
        <div class="col-lg-4" id="recent_div">
            <input type="radio" name="recent_image" id="recent"><br> Recently Added Images
        </div>
        <div class="col-lg-4" id="random_div">
            <input type="radio" name="random_image" id="random"><br> Random Images
        </div>
        <div class="col-lg-4" id="specific_div">
            <div id="specific_image_div"><input type="radio" name="specific_image" id="specific"><br> Specific Image ID</div>
            <div class="hidden" id="specific_image_id_div"><input placeholder="Enter image id" id="specific_image_id" style="margin-top:5px; margin-top: 5px;" type="text"/> <button onclick="specificSearch();" >go</button></div>
        </div>
    </div>         
</section>    

<br>
<br>

<!--images and metadata-->
<section id="images" style="height:80%;">
	<!-- data container -->
	<div class="container-fluid">
	
		<div class="col-sm-12" style="width:100%;">
			<div class="col-lg-6 " >
				<p class="lead" id="headers">TULSA SERVER</p>
				<div id="cps_metadata"></div>				
				<div  id="cpsStatic" >
					<p id="cpsImagediv" ></p>				
				</div>
				<p id="cps_urls"></p>
			</div>
			<div class="col-lg-6 " >
				<p class="lead" id="headers">AWS SERVER</p>
				<div  id="cf_metadata"></div>				
				<div  id="cloudFront" >
					<p id="cfImagediv" ></p>
				</div>
				<p id="cf_urls"></p>
			</div>
		</div>
		
	<!-- data container -->	
	</div>
	
<!--images and metadata-->
</section>



<!--buttons-->
<section style="height:10%;">
	<div class="col-lg-12">
		<div class="col-lg-6"><button  id="buttons" onclick="previous()">< Previous</button></div>
		<div class="col-lg-6"><button  id="buttons" onclick="next()">Next ></button></div>
	</div>
<section>
    
    
    <br>
    <br>
    <br>

<!--visible file upload buttons-->
<section style="height:10%;">
    <div class="container-fluid col-lg-12">
        <div class="col-lg-12" id="selectfilebutton">
            <button id="selectfile" onclick="selectimage()">Select File</button>
        </div>
        <div class="col-lg-12 hidden" id="uploadfilebutton" style="visibility:hidden;">
            <button id="uploadfile" onclick="httpPost('<%= posturl%>');">Upload File</button>
        </div>
    </div>
</section>   

            


            

        
<!--hidden file upload data-->
<section class="hidden">
    <input id="actualselectbutton" type="file" name="attachment"/>
</section>
    
    
    
    
</body>
