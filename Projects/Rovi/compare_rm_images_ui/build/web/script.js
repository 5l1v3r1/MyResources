/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var URLs, result, cpsHeight, cpsWidth, cfHeight, cfWidth, readindex = 0,currentImageIndex = 0,p_result, cpsSize, cfSize, size, geturl;
//CORS configuration to allow cross-domain access
function createCORSRequest(method, url) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) 
    {
        xhr.open(method, url, false);
    }   else if (typeof XDomainRequest !== "undefined") 
    {
        xhr = new XDomainRequest();
        xhr.open(method, url);
    } else {
        xhr = null;
    }
    return xhr;
}

function httpGet(theUrl)
{ 


    var xhr = createCORSRequest('GET',theUrl);
    if(xhr){
        xhr.onload = function(){
            result = xhr.responseText;
        };
    }

        xhr.send();
}

//Update URLs on the webpage
function updateURL(){
    document.getElementById('cps_urls').innerHTML = URLs.result.CPS[currentImageIndex].URL;
    document.getElementById('cf_urls').innerHTML = URLs.result.CF[currentImageIndex].URL;
}

//Delete existing images on the page
function deleteOldImage(){
	var cpsImage = document.getElementById('cpsImagediv');
	var cloudFrontImage = document.getElementById('cfImagediv');
	while (cpsImage.firstChild){cpsImage.removeChild(cpsImage.firstChild);}
	while (cloudFrontImage.firstChild){cloudFrontImage.removeChild(cloudFrontImage.firstChild);}
}

//Delete Metadata
function deleteOldMetaData(){
	var cpsMetaData = document.getElementById('cps_metadata');
	var cfMetaData = document.getElementById('cf_metadata');
	while (cpsMetaData.firstChild){cpsMetaData.removeChild(cpsMetaData.firstChild);}
	while (cfMetaData.firstChild){cfMetaData.removeChild(cfMetaData.firstChild);}
}

/*
function displaycpsMetaData(){
        var cps_meta  = document.createElement('p');
        var id = URLs.result.CPS[currentImageIndex].ID;
        var date = URLs.result.CPS[currentImageIndex].DATE.toString().split(" ")[0]; 
	cps_meta.id = "cps_image_metadata";
	cps_meta.innerHTML = "<div class=\"container-fluid\"> <div class=\"col-lg-6\">Height: <em>"+ cpsHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
						cpsWidth+"px</em>"+"</br>"+"Bytes: "+cpsSize +"</br></div> <div class=\"col-lg-6\">"+"Id: "+id +"</br>"+"Date: "+date+"</div></div></br>";
	document.getElementById('cps_metadata').appendChild(cps_meta);
}

function displaycfMetaData(){
        var date = URLs.result.CF[currentImageIndex].DATE.toString().split(" ")[0]; 
        var id = URLs.result.CF[currentImageIndex].ID;
        var cf_meta  = document.createElement('p');
	cf_meta.id = "cf_image_metadata";
        cf_meta.innerHTML = "<div class=\"container-fluid\"> <div class=\"col-lg-6\">Height: <em>"+ cfHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
					cfWidth+"px</em>"+"</br>"+"Bytes: "+cfSize +"</br></div> <div class=\"col-lg-6\">"+"Id: "+id +"</br>"+"Date: "+date+"</div></div></br>";                          
        document.getElementById('cf_metadata').appendChild(cf_meta);
    
}
 */

function displaycpsMetaData(){
        var cps_meta  = document.createElement('p');
        var id = URLs.result.CPS[currentImageIndex].ID;
        var date = URLs.result.CPS[currentImageIndex].DATE.toString().split(" ")[0]; 
	cps_meta.id = "cps_image_metadata";
	cps_meta.innerHTML = "Height: <em>"+ cpsHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
						cpsWidth+"px</em>"+"</br>"+/*"Bytes: "+cpsSize +"</br>"+*/"Id: "+id +"</br>"+"date: "+date+"</br>";
	document.getElementById('cps_metadata').appendChild(cps_meta);
}

function displaycfMetaData(){
        var date = URLs.result.CF[currentImageIndex].DATE.toString().split(" ")[0]; 
        var id = URLs.result.CF[currentImageIndex].ID;
        var cf_meta  = document.createElement('p');
	cf_meta.id = "cf_image_metadata";
	cf_meta.innerHTML = "Height: <em>"+ cfHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
						cfWidth+"px</em>"+"</br>"+/*"Bytes: "+cfSize +"</br>"+*/"Id: "+id +"</br>"+"date: "+date +"</br>";                               
        document.getElementById('cf_metadata').appendChild(cf_meta);
    
}

function str2ab(str) {
  var buf = new ArrayBuffer(str.length*2); // 2 bytes for each char
  var bufView = new Uint16Array(buf);
  for (var i=0, strLen=str.length; i<strLen; i++) {
    bufView[i] = str.charCodeAt(i);
  }
  return buf;
}


function httpGetByteData(imageURL){

    
    /*$.get({url:imageURL,
        success:function(imageArray){
            var byteArray = new Uint8Array(imageArray);
            return byteArray.byteLength;
        }});*/
    

    var xhr = createCORSRequest('GET',imageURL);
    if(xhr){
        xhr.onload = function(){
            var imageArray = str2ab(xhr.response);
            var byteArray = new Uint8Array(imageArray);
            size = Math.ceil(byteArray.length/1.925);
           
        };
    }

    xhr.send();
        
        
}

function getMetaData(){
    //obtain image dimensions
    //httpGetByteData(URLs.result.CPS[currentImageIndex].URL);
    cpsSize = "null";
    //httpGetByteData(URLs.result.CF[currentImageIndex].URL);
    cfSize = "null";
    
    var img = $("#cpsAppendedImages")[0]; 
    $("<img/>").attr("src", $(img).attr("src")).load(function() 
    {
        cpsWidth = this.width;  
        cpsHeight = this.height;

        displaycpsMetaData();
    });
        
        
    var img = $("#cfAppendedImages")[0];

    $("<img/>").attr("src", $(img).attr("src")).load(function() 
    {
        cfWidth = this.width;  
        cfHeight = this.height; 

        displaycfMetaData();
    });
    updateURL();
    
    /*
    if($("#cf_image_metadata").length === 0){displaycfMetaData();}
    if($("#cps_image_metadata").length === 0){displaycpsMetaData();}
    */

}

//View next image
function next(){
        //increment to next image
	currentImageIndex += 1;
        
        //delete old data
	deleteOldImage();
	deleteOldMetaData();
        
        //create new image
	var cps_img = document.createElement('img');
	cps_img.src = URLs.result.CPS[currentImageIndex].URL;
        cps_img.id = "cpsAppendedImages";
	document.getElementById('cpsImagediv').appendChild(cps_img);
        
        var cf_img = document.createElement('img');
	cf_img.src = URLs.result.CF[currentImageIndex].URL;
	cf_img.id = "cfAppendedImages";
	document.getElementById('cfImagediv').appendChild(cf_img);
        
	getMetaData();   
}

//View Previous image
function previous(){
        if(currentImageIndex===0)
            {
                currentImageIndex = 0;
            }else{
                currentImageIndex -= 1;
        }
	//delete old data
	deleteOldImage();
	deleteOldMetaData();
        
        //create new image
	var cps_img = document.createElement('img');
	cps_img.src = URLs.result.CPS[currentImageIndex].URL;
        cps_img.id = "cpsAppendedImages";
	document.getElementById('cpsImagediv').appendChild(cps_img);
        
        var cf_img = document.createElement('img');
	cf_img.src = URLs.result.CF[currentImageIndex].URL;
	cf_img.id = "cfAppendedImages";
	document.getElementById('cfImagediv').appendChild(cf_img);
        
	getMetaData();  
}


function selectimage(){
    document.getElementById('actualselectbutton').click();
}

$(document).ready(function(){
document.querySelector('#actualselectbutton').addEventListener('change', function(){
    document.getElementById("selectfile").innerHTML="fetching file...";
    var reader = new FileReader();
    reader.onload = function(){
        var binaryString = this.result;
        p_result = binaryString;
        
        document.getElementById("uploadfilebutton").style.visibility = "visible";
        document.getElementById("uploadfilebutton").className = "col-lg-12";
        
        document.getElementById("selectfilebutton").className = "col-lg-12 hidden";
        document.getElementById("selectfilebutton").style.visibility ="hidden";
        

        
        };
    reader.readAsBinaryString(this.files[0]);
  }, false);  
}); 

function uncheckRadioButtons(){
    document.getElementById("recent").checked = false;
    document.getElementById("random").checked = false;
    document.getElementById("specific").checked = false;
    $("#specific_div").css("border-bottom"," none");
    $("#recent_div").css("border-bottom"," none");
    $("#random_div").css("border-bottom"," none");
    document.getElementById("specific_image_div").className =" ";
    document.getElementById("specific_image_id_div").className ="hidden";
}

$(document).ready(function(){
    $('input[name="specific_image"]').click(function(){
        uncheckRadioButtons();
        document.getElementById("specific").checked = true;
        document.getElementById("title").innerHTML = "<strong>SELECT IMAGES WITH ID</strong>";  
        document.getElementById("specific_image_div").className ="hidden";
        document.getElementById("specific_image_id_div").className =" ";
    });
    $('input[name="recent_image"]').click(function(){
        uncheckRadioButtons();
        document.getElementById("recent").checked = true;
        $("#recent_div").css("border-bottom"," 2px solid #757575");
        document.getElementById("title").innerHTML = "<strong>RECENTLY ADDED IMAGES</strong>"; 
        geturl=recent_image_url;
        regenerate();
         
    });
    $('input[name="random_image"]').click(function(){
        uncheckRadioButtons();
        document.getElementById("random").checked = true;
        $("#random_div").css("border-bottom"," 2px solid #757575");
        document.getElementById("title").innerHTML = "<strong>RANDOMLY SELECTED IMAGES</strong>"; 
        geturl=random_image_url;
        regenerate();
    });
});

function specificSearch(){
    document.getElementById("title").innerHTML = "<strong>IMAGE ID:"+$("#specific_image_id").val()+"</strong>"; 
    geturl=(specific_image_url.toString() + $("#specific_image_id").val());
    console.log(geturl);
    regenerate();
}

function regenerate(){
    httpGet(geturl);
    URLs =JSON.parse(result);
    next();
}
function httpPost(theUrl)
{
    var xhr = createCORSRequest('POST',theUrl);
    if(xhr){
        xhr.onload = function(){
            location.reload();
        };
    }
        xhr.send( p_result);
}

