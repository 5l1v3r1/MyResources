var array = [];
var file;

function uploadAction(){
    document.getElementById('file').click(); return false;
    console.log(document.getElementById('file').files[0]);
}

function appendImages(){
    document.getElementById("topAppendedImages").style.height = "30%";
    document.getElementById("bottomAppendedImages").style.height = "30%";
    document.getElementById("topAppendedImages").style.width = "100%";
    document.getElementById("bottomAppendedImages").style.width = "100%";
    var topdivs="<div style=\"width:100%; height:100%;\">+<div class=\"container-fluid\" id=\"search_results\" style=\"width:100%; height:100%;\"></div></div>";
    var bottomdivs="<div style=\"width:100%; height:100%;\">+<div class=\"container-fluid\" id=\"search_results_2\" style=\"width:100%; height:100%;\"></div></div>";
    document.getElementById("topAppendedImages").innerHTML = topdivs;
    document.getElementById("bottomAppendedImages").innerHTML = bottomdivs;
    
}

function deleteOldImages(){
    while (document.getElementById("search_results_3").firstChild) 
    {
        document.getElementById("search_results_3").removeChild(document.getElementById("search_results_3").firstChild);
    }
    while (document.getElementById("topAppendedImages").firstChild) 
    {
        document.getElementById("topAppendedImages").removeChild(document.getElementById("search_results").firstChild);
        document.getElementById("bottomAppendedImages").removeChild(document.getElementById("search_results_2").firstChild);
    }
}

function generateURL(){
    return array[Math.floor((Math.random() * array.length + 1))];
}

function displayGenerateButton(){

    for(i =1;i>=0;i-=0.001){
    document.getElementById("fileuploadbutton").style.opacity = i;
    }
    
    document.getElementById("getsearchbutton_container").className = "container-fluid";
    document.getElementById("fileuploadbutton").style.visibility = "hidden";
    document.getElementById("getsearchbutton").style.visibility = "visible";
    for(i =0;i<=1;i+=0.001){
    document.getElementById("getsearchbutton").style.opacity = i;
    }
}


function getSearch() 
{   
    if(document.getElementById("bottomAppendedImages").children.length > 0){ 
        deleteOldImages();
    }
    
    
    
    for(count =0; count <=6; count++){
            var img = document.createElement('img');
            img.src = generateURL();

            if(count<= 2){
            img.id = "AppendedImages";
            document.getElementById('search_results').appendChild(img);
            }

            if(count> 2 && count <= 5){
            img.id = "AppendedImages";
            document.getElementById('search_results_2').appendChild(img);
            }
            if(count === 6){
            img.id = "AppendedImage";
            document.getElementById('search_results_3').appendChild(img);
            }
        }
document.getElementById("getsearchbutton").innerHTML = "Reload Images";
}

