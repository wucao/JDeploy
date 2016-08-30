$(document).ready(function() {

	$("#form-new").submit(function() {
		var name = $("#input-name").val();
		var url = $("#input-url").val();
		var port = $("#input-port").val();

		if(name.length === 0) {
			layerAlert("请填写项目名称！")
			return false;
		}
		if(url.length === 0) {
			layerAlert("请填写SVN地址！")
			return false;
		}
		if(parseInt(port).toString() !== port || parseInt(port) < 0 || parseInt(port) > 65536) {
			layerAlert("请填写正确的端口号！")
			return false;
		}

	});

	$("input[name='type']").change(function () {
		if(this.value == 2) {
			$(".input-field-branch").fadeIn();
		} else {
			$(".input-field-branch").fadeOut();
		}
	});

	/**
	 * 用于代替alert
	 * @param text
	 */
	function layerAlert(text) {
		$("#alert-modal .text-alert").html(text);
		$("#alert-modal").openModal({dismissible: false});
	}

});