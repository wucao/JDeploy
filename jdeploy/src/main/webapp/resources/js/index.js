$(document).ready(function() {
	
	$("#java-deploy-search").change(function() {
		$("#java-deploy tbody tr").each(function() {
			var val = $("#live-search").val();
			var name = $(this).children().get(0).innerHTML;
			var uuid = $(this).children().get(1).innerHTML;
			var finalName = $(this).children().get(2).innerHTML;
			if(name.indexOf(val) != -1 || uuid.indexOf(val) != -1
					|| finalName.indexOf(val) != -1) {
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