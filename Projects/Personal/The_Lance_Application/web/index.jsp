<!DOCTYPE html>
<html lang="en">
<%@page import="utils.GetImageMetadata,java.lang.String,java.io.File,java.lang.System"%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>welcome | lance</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="css/stylish-portfolio.css" rel="stylesheet">
    <link href="css/stylesheet.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/flaticon.css"> 
    
    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="https://www.google.com/jsapi" type="text/javascript"></script>
</head>
<body>
    <!-- Header
    <header class="header">
        <div class="text-vertical-center">
            <h2 class="lead" id="headerMessage">Get <em>quality</em> product designs in <span id="headerMessage_style"><em>3 easy steps.</em></span></h2>
            <button id="headerButton" onclick="window.location.href='#serviceinfo'">Start Here!</button>
            <br>
            <br>
        </div>
    </header>-->

    <!-- Aside: Here's how-->
    <aside>
            <p class="lead" id="firstAside"> Here's How</p>
    </aside>

    <!--Section: Service Information -->
    <section id="serviceinfo">
        <!-- container-->
        <div class="container fluid" id="serviceinfo_container">
            
            <!-- describe -->
            <div class="col-lg-4 text-center" id="serviceinfo_describe">
                    <p style="font-size:25px;">
                        <strong>Describe</strong>
                    </p>
                <div id="serviceinfo_icon_container"></div>
                <br>
                <p style="font-size:17px;">Describe your vision with keywords and any specific details or preferences.</p>
            <!-- /describe -->
            </div>
            
            <!-- envision -->
            <div class="col-lg-4 text-center" id="serviceinfo_describe">
                <p style="font-size:25px;">
                    <strong>Envision</strong>
                </p>
                <div id="serviceinfo_icon_container"></div>
                <br>
                <p style="font-size:17px;">Select an image that most closely represents your vision.</p>
            <!-- /envision -->
            </div>
            
            <!-- create -->
            <div class="col-lg-4 text-center" id="serviceinfo_describe">
                <p style="font-size:25px;">
                    <strong>Create</strong>
                </p>
                <div id="serviceinfo_icon_container"></div>
                <br>
                <p style="font-size:17px;">Sit back and watch our team of engineers and designers turn your vision into reality.</p>
            <!-- /create -->
            </div>
            
        <!-- /container -->
        </div>
    </section>

    <!-- Aside: Call to Action -->
    <aside>
        <p class="lead" id="secondAside"> Start Now! Your product is literally two button clicks away.</p>
    </aside>

    <!-- Describe Section -->
    <section id="description" class="about" >
    
    <div class="col-lg-12 text-center" id="description_container">
        <div id="topAppendedImages"></div>
        
        <div style= "height:70%; width:100%;">
            <div class="col-lg-4" id="search_results_3"></div>
            <div class="col-lg-4" style=" height:100%;">
                <textarea id="search-form" placeholder="Describe your vision here!" onkeyup="displayGenerateButton();" ></textarea>
            </div>
            <div class="col-lg-4" style=" height:100%;">
            </div>
        </div>
        
        <div id="bottomAppendedImages"></div>
        
        
    <div id="getsearchbutton_container" class="container-fluid hidden" style="height:28%;">
        <button id="getsearchbutton" href="#addimage" onclick="getSearch()">Generate Images</button> 
    </div>

        <div class="container-fluid" style="height:9%;">
        <button id="fileuploadbutton" href="#addimage" onclick="uploadAction()">Upload an image</button> 
        <input type="file" name="file" id="file" ></input>
    </div>
    </div>
     
    </section>


    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                    <h4><strong>Lance</strong>
                    </h4>
                    <p>3200 Race Street<br>Philadelphia, PA 19104</p>
                    <ul class="list-unstyled">
                        <li><i class="fa fa-phone fa-fw"></i> (123) 456-7890</li>
                        <li><i class="fa fa-envelope-o fa-fw"></i>  <a href="mailto:danishd@me.com">danishd@me.com</a>
                        </li>
                    </ul>
                    <br>
                    <ul class="list-inline">
                        <li>
                            <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-dribbble fa-fw fa-3x"></i></a>
                        </li>
                    </ul>
                    <hr class="small">
                    <p class="text-muted">Copyright &copy; Danish Corporation 2016</p>
                </div>
            </div>
        </div>
        
    <!-- /footer -->
    </footer>



    <!-- jQuery && Javascript -->
    <script src="js/jquery.js"></script>
    <script src="js/jquery_2.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/javascript.js"></script>
    
    



<script type="text/javascript">
<%!

public String generateResult(String action,String query, File image){
    String result = null;
   if(action.equals("bingsearch")){
        result = GetImageMetadata.executeImageSearchResult(query);
    }

    if(action.equals("imagesearch")){
        result = GetImageMetadata.executeComputerVision(image);
    }
    return result;
}

String responseMessage = generateResult("bingsearch", "query", null);

%>

    
    JSON.parse(<%= responseMessage %>, function(key,value){
        if(key==="thumbnailUrl"){
            console.log(value);
        }
    });
    for(url = 0; url <= respnsejson.thumnailUrl.length; url ++){
        array.push(respnsejson.thumnailUrl[url]);
    }
   console.log(array);
   
</script>

</body>
</html>
