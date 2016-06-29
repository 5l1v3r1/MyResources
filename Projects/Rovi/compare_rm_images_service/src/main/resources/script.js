/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var URLs, result, cpsHeight, cpsWidth, cfHeight, cfWidth, readindex = 0,currentImageIndex = 0,p_result, cpsSize, cfSize, size, geturl,arr,cpsloadtime,cfloadtime, ENV="UAT";
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
    document.getElementById('cps_urls').innerHTML = "<strong><a target=\"_blank\" href="+URLs.result.CPS[currentImageIndex].URL+ ">"+URLs.result.CPS[currentImageIndex].URL+"</a></strong>";
    document.getElementById('cf_urls').innerHTML = "<strong><a target=\"_blank\" href="+ URLs.result.CF[currentImageIndex].URL+ ">"+URLs.result.CF[currentImageIndex].URL+"</a></strong>";
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
						cpsWidth+"px</em>"+"</br>"+"Bytes: "+cpsSize +"</br></div> <div class=\"col-lg-6\">"+"Id: "+id +"</br>"+"Date: "+date+"</br>"+"Load Time: "+cpsloadtime+ "ms</br></div></div></br>";
	document.getElementById('cps_metadata').appendChild(cps_meta);
}

function displaycfMetaData(){
        var date = URLs.result.CF[currentImageIndex].DATE.toString().split(" ")[0]; 
        var id = URLs.result.CF[currentImageIndex].ID;
        var cf_meta  = document.createElement('p');
	cf_meta.id = "cf_image_metadata";
        cf_meta.innerHTML = "<div class=\"container-fluid\"> <div class=\"col-lg-6\">Height: <em>"+ cfHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
					cfWidth+"px</em>"+"</br>"+"Bytes: "+cfSize +"</br></div> <div class=\"col-lg-6\">"+"Id: "+id +"</br>"+"Date: "+date+"</br>"+"Load Time: "+cfloadtime+ "ms</br></div></div></br>";                          
        document.getElementById('cf_metadata').appendChild(cf_meta);
    
}
 */

function changeEnvironment(env){
    ENV = env;
    geturl=random_image_url+ENV;
    uncheckRadioButtons();
            document.getElementById("random").checked = true;
    setTitle("random");
    regenerate();
}

function displaycpsMetaData(){
        var cps_meta  = document.createElement('p');
        var id = URLs.result.CPS[currentImageIndex].ID;
        var date = URLs.result.CPS[currentImageIndex].DATE.toString().split(" ")[0]; 
	cps_meta.id = "cps_image_metadata";
	cps_meta.innerHTML = "Height: <em>"+ cpsHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
						cpsWidth+"px</em>"+"</br>"+"Bytes: "+cpsSize +"</br>"+"Id: "+id +"</br>"+/*"Date: "+date+"</br>"+*/"Load Time: "+cpsloadtime+ "ms</br>";
	document.getElementById('cps_metadata').appendChild(cps_meta);
}

function displaycfMetaData(){
        var date = URLs.result.CF[currentImageIndex].DATE.toString().split(" ")[0]; 
        var id = URLs.result.CF[currentImageIndex].ID;
        var cf_meta  = document.createElement('p');
	cf_meta.id = "cf_image_metadata";
	cf_meta.innerHTML = "Height: <em>"+ cfHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
						cfWidth+"px</em>"+"</br>"+"Bytes: "+cfSize +"</br>"+"Id: "+id +"</br>"+/*"Date: "+date +"</br>"+*/"Load Time: "+cfloadtime+ "ms</br>";                               
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
    var xhr = createCORSRequest('GET',imageURL);
    if(xhr){
        xhr.onload = function(){
            size = xhr.responseText;        
        };
    }
    xhr.send();       
}

function getMetaData(){
    
    httpGetByteData("/compare_rm_images_service/getsize?url=" + encodeURI(URLs.result.CPS[currentImageIndex].URL))+"&env="+ENV;
    arr = size.split("///");
    
    try{
    cpsSize = arr[0].toString();
        } catch (e){
        console.log(e);
    }
    
    try{
    cpsloadtime = arr[1].toString();
    } catch (e){
        console.log(e);
    }
    //obtain image dimensions
    httpGetByteData("/compare_rm_images_service/getsize?url=" + encodeURI(URLs.result.CF[currentImageIndex].URL))+"&env="+ENV;
    arr = size.split("///");
        try{
    cfSize = arr[0];
        } catch (e){
        console.log(e);
    }
    
        try{
    cfloadtime = arr[1].toString();
        } catch (e){
        console.log(e);
    }



    
    var img = $("#cpsAppendedImages")[0]; 
    $("<img/>").attr("src", $(img).attr("src")).load(function() 
    {
        cpsWidth = this.width;  
        cpsHeight = this.height;
        if(document.getElementById("cps_metadata").innerHTML === ""){
        displaycpsMetaData();
    }
    });
        
        
    var img = $("#cfAppendedImages")[0];

    $("<img/>").attr("src", $(img).attr("src")).load(function() 
    {
        cfWidth = this.width;  
        cfHeight = this.height; 
        
        if(document.getElementById("cf_metadata").children.length === 0){
        displaycfMetaData();
    }
    });
    updateURL();
    
    /*
    if($("#cf_image_metadata").length === 0){displaycfMetaData();}
    if($("#cps_image_metadata").length === 0){displaycpsMetaData();}
    */

}

//View next image
function next(){
        if(Object.keys(URLs.result.CPS).length-1 > currentImageIndex){
            currentImageIndex +=1; 
        }else{
            currentImageIndex =currentImageIndex ; 
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

    $('#actualselectbutton').change(function(){
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

function setTitle(view){
    if(view === "recent"){
        document.getElementById("title").innerHTML = "<strong>RECENTLY ADDED IMAGES</strong>"; 
        document.getElementById("cf_headers").innerHTML = "AWS SERVER("+ENV+")";
    }else if(view === "specific"){
        document.getElementById("title").innerHTML = "<strong>SELECT IMAGES WITH ID(</strong>";  
              document.getElementById("cf_headers").innerHTML = "AWS SERVER("+ENV+")";
    }else if(view === "random"){
         document.getElementById("title").innerHTML = "<strong>RANDOMLY SELECTED IMAGES</strong>"; 
             document.getElementById("cf_headers").innerHTML = "AWS SERVER("+ENV+")";
    }
    
}

$(document).ready(function(){
    $('input[name="specific_image"]').click(function(){
        uncheckRadioButtons();
        document.getElementById("specific").checked = true;
        setTitle("specific");
        document.getElementById("specific_image_div").className ="hidden";
        document.getElementById("specific_image_id_div").className =" ";
    });
    $('input[name="recent_image"]').click(function(){
        uncheckRadioButtons();
        document.getElementById("recent").checked = true;
        $("#recent_div").css("border-bottom"," 2px solid #757575");
        setTitle("recent");
        geturl=recent_image_url+ENV;
        regenerate();
         
    });
    $('input[name="random_image"]').click(function(){
        uncheckRadioButtons();
        document.getElementById("random").checked = true;
        $("#random_div").css("border-bottom"," 2px solid #757575");
        setTitle("random");
        geturl=random_image_url+ENV;
        regenerate();
    });
});

function specificSearch(){
    if($("#specific_image_id").val().trim().length === 8 ){
        document.getElementById("title").innerHTML = "<strong>IMAGE ID:"+$("#specific_image_id").val().trim()+"</strong>"; 
        geturl=(specific_image_url.toString() + $("#specific_image_id").val().trim()+"&env=" + ENV);
        console.log(geturl);
        regenerate();
    }else{
        document.getElementById("specific_image_id").value = "";
        alert('Invalid Image ID');
         
    }
}

function regenerate(){
    httpGet(geturl);
    console.log(geturl);
    URLs =JSON.parse(result);
    try{
        URLs.result.CPS[currentImageIndex].URL;
        next();
    }catch(Exception){
        alert('Failed to Load Image. Please Ensure that the ID provided is correct.');
    }

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



