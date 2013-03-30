$(function() {
	
	loadTemplate = function(templateURL, callBackMethod) {
		$.ajax({
			type : 'GET',
			url : '/renderTemplate' + templateURL,
			async : false,
			success : function(templateHtml) {
				$("#content").html(templateHtml);

				if (callBackMethod) {
					eval(callBackMethod);
				}
			}
		});
	}
	
});
