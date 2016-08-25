$(document).ready(function() {
	
	$("#java-deploy-search").change(function() {
		var val = $(this).val().toUpperCase();
		$("#java-deploy tbody tr").each(function() {
			var name = $(this).children().get(0).innerHTML.toUpperCase();
			var uuid = $(this).children().get(1).innerHTML.toUpperCase();
			if(name.indexOf(val) != -1 || uuid.indexOf(val) != -1) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
	});

	$("#java-web-deploy-search").change(function() {
		var val = $(this).val().toUpperCase();
		$("#java-web-deploy tbody tr").each(function() {
			var name = $(this).children().get(0).innerHTML.toUpperCase();
			var uuid = $(this).children().get(1).innerHTML.toUpperCase();
			var contextPath = $(this).children().get(2).innerHTML.toUpperCase();
			var port = $(this).children().get(3).innerHTML;
			if(name.indexOf(val) != -1 || uuid.indexOf(val) != -1
				|| contextPath.indexOf(val) != -1 || port.indexOf(val) != -1) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
	});
	
	$("form").submit(function() {
		return false;
	});
	
});