
        var qaHeight, qaWidth, qaSize, qaloadtime,size;
        currentImageIndex = 0;
        window.onload = function()
        {
                httpGet("/compare_rm_images_service/specific?id=17125602&env=UAT&isqa=true");
                URLs =JSON.parse(result);
                qa_next();
        };
        
        
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
    
    httpGetByteData("/compare_rm_images_service/getsize?url=" + encodeURI(URLs.result.CPS[currentImageIndex].URL))+"&env=UAT";
    arr = size.split("///");
    
    try{
    qaSize = arr[0].toString();
        } catch (e){
        console.log(e);
    }
    
    try{
    qaloadtime = arr[1].toString();
    } catch (e){
        console.log(e);
    }


    
    var img = $("#cpsAppendedImages")[0]; 
    $("<img/>").attr("src", $(img).attr("src")).load(function() 
    {
        qaWidth = this.width;  
        qaHeight = this.height;
        console.log(document.getElementById("qa_metadata").innerHTML);
        
        if(document.getElementById("qa_metadata").innerHTML === ""){
        var qa_meta  = document.createElement('p');
        var id = URLs.result.CPS[currentImageIndex].ID;
        var date = URLs.result.CPS[currentImageIndex].DATE.toString().split(" ")[0]; 
	qa_meta.id = "cps_image_metadata";
	qa_meta.innerHTML = "Height: <em>"+ qaHeight+"px</em>"+"</br>"+ "Width: <em>"+ 
						qaWidth+"px</em>"+"</br>"+"Bytes: "+qaSize +"</br>"+"Id: "+id +"</br>"+/*"Date: "+date+"</br>"+*/"Load Time: "+qaloadtime+ "ms</br>";
	document.getElementById('qa_metadata').appendChild(qa_meta);
    }
    });
        


}   


function qa_next(){
    var qa_image_div = document.getElementById("image_div");
    	while (qa_image_div.firstChild){qa_image_div.removeChild(qa_image_div.firstChild);}


    var qa_test_page_Image = document.getElementById('image_div');
    while (qa_test_page_Image.firstChild){qa_test_page_Image.removeChild(qa_test_page_Image.firstChild);}
    
    var qaMetaData = document.getElementById('qa_metadata');
    while (qaMetaData.firstChild){qaMetaData.removeChild(qaMetaData.firstChild);}
        try{
            document.getElementById('qa_url').innerHTML = "<strong><a target=\"_blank\" href="+URLs.result.CPS[currentImageIndex].URL+ ">"+URLs.result.CPS[currentImageIndex].URL+"</a></strong>";
        }catch(Exception){
            document.getElementById('qa_url').innerHTML = "<strong><a target=\"_blank\" href=''></a></strong>";
    }
        var qa_img = document.createElement('img');

	qa_img.src = URLs.result.CPS[currentImageIndex].URL;

        qa_img.id = "cpsAppendedImages";
	document.getElementById('image_div').appendChild(qa_img);
        getMetaData();
        
        if(Object.keys(URLs.result.CPS).length-1 > currentImageIndex){
            currentImageIndex +=1; 
        }else{
            currentImageIndex = currentImageIndex ; 
        }
          

}

function qa_previous(){

        var qa_image_div = document.getElementById("image_div");
    	while (qa_image_div.firstChild){qa_image_div.removeChild(qa_image_div.firstChild);}

    var qa_test_page_Image = document.getElementById('image_div');
    while (qa_test_page_Image.firstChild){qa_test_page_Image.removeChild(qa_test_page_Image.firstChild);}
        
    var qaMetaData = document.getElementById('qa_metadata');
    while (qaMetaData.firstChild){qaMetaData.removeChild(qaMetaData.firstChild);}
    
    	var qa_img = document.createElement('img');
	qa_img.src = URLs.result.CPS[currentImageIndex].URL;
        qa_img.id = "cpsAppendedImages";
	document.getElementById('image_div').appendChild(qa_img);
        getMetaData();

        
        document.getElementById('qa_url').innerHTML = "<strong><a target=\"_blank\" href="+URLs.result.CPS[currentImageIndex].URL+ ">"+URLs.result.CPS[currentImageIndex].URL+"</a></strong>";
        if(currentImageIndex===0)
            {
                currentImageIndex = 0;
            }else{
                currentImageIndex -= 1;
        }
}


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

function regenerate(){
        if($("#image_id").val().trim().length === 8 ){
            currentImageIndex = 0;
            qa_geturl = "/compare_rm_images_service/specific?id=" + $("#image_id").val().trim() +"&env=UAT&isqa=true"; 
            httpGet(qa_geturl);
            URLs =JSON.parse(result);
            try{
                URLs.result.CPS[currentImageIndex].URL;
                qa_next();
            }catch(Exception){
                alert('Failed to Load Image. Please Ensure that the ID provided is correct.');
                httpGet("/compare_rm_images_service/specific?id=17125602&env=UAT&isqa=true");
                URLs =JSON.parse(result);
                qa_next();
            }

        }else{
            alert('Invalid Image ID');
            document.getElementById("image_id").value = "";
        }
}

