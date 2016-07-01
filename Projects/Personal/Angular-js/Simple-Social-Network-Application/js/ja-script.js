/*===========================================================================================================
                               author        :    Joseph Appeah
                               date          :    07/01/2016
                               description   :    Useful functions for all projects
                               framework     :    javascript, jquery
=============================================================================================================

------------------------------------------xx usage xx--------------------------------------------------------

                    <script type="text/javascript" src="../ja-script.js" ></script>
	
-------------------------------------------------------------------------------------------------------------

-------------------------------------xx function directory xx------------------------------------------------
    1.    resizeFontById(selector_id)        :    Resizes font with wndow size
    2.    scrollToDiv(button_id, div_id)     :    Scrolls to specified div when specified button is clicked
    3.    scrollToTop(button_id)             :    Scrolls to top of page when specified button is clicked
-------------------------------------------------------------------------------------------------------------
*/
 
 
//Font Resize on Window Resize
 function resizeFontById(selector_id){
	$(document).ready(function(){
		var initialFontSize = getFontSize(selector_id);
		$(window).resize(function(){
			resizeFont(initialFontSize ,selector_id);
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

//Scroll to specified div
function scrollToDiv(button_id, div_id){
	$(document).ready(function(){
		$("#"+ button_id).click(function(){
			$('html,body').animate({
				scrollTop: $("#" + div_id).offset().top
			});
		}
	}
}

//Scroll to top of page
function scrollToTop(button_id){
	$(document).ready(function(){
		$("#" + button_id).click(function(){
			scrollTop: top
		});
	});
});