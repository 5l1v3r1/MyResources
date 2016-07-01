 /*Font Resize on Window Resize*/
 function resizeFontById(div_id){
	$(document).ready(function(){
		var initialFontSize = getFontSize(div_id);
		$(window).resize(function(){
			resizeFont(initialFontSize ,div_id);
		});
		function getFontSize(id){        
			var initialFontSize = parseInt($("#" + id).css("font-size".replace("px","")));
			return initialFontSize;
		}
		function resizeFont(fontSize,id){
			var currentWindowSize = parseInt(Math.sqrt(Math.pow($(window).height(),2) + Math.pow($(window).width(),2)));
			var newFontSize = (fontSize*(currentWindowSize/1000));
			if(newFontSize >fontSize){newFontSize = newFontSize - (newFontSize-fontSize);}
			$("#" + id).css("font-size",newFontSize);
		}
	});
}