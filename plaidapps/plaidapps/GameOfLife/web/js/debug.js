var DebugWriter = {
		write: function(str, clear){
				if(clear == undefined) { clear = true; }
				
				html = $("#debug").html();
				if(clear){
						html =  "";
				}
				else{
						html += "<br />";
				}
				$("#debug").html(html + str);
		},
		clear: function(){
				$("#debug").html("");
		},
		init: function(){
				var area = '<div id="debug" style="margin-top:30px;height:300px;background-color:#EEEEEE;"></div>';
				$("body").append(area);
		}
};

$(DebugWriter.init);