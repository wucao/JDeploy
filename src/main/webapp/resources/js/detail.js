$(document).ready(function() {

	var uuid = $("#text-uuid").html();

	// 初始化刷新运行状态
	refreshStatus();

	// 部署按钮
	$("#btn-deploy").click(function () {
		ajaxShell("../deploy", {uuid: uuid}, function() {
			refreshStatus();
		});
	});

	// 重启按钮
	$("#btn-restart").click(function () {
		ajaxShell("../restart", {uuid: uuid}, function() {
			refreshStatus();
		});
	});

	// 停止按钮
	$("#btn-stop").click(function () {
		ajaxShell("../stop", {uuid: uuid}, function() {
			refreshStatus();
		});
	});

	// 查看日志
	$(".btn-showlog").click(function () {

		var url = $(this).attr("data-wsurl");
		var websocket = new WebSocket(url);
		websocket.onmessage = function(event) {
			var msg = event.data;
			$("#layer-modal .modal-content>div").append(msg);
			$("#layer-modal .modal-content").scrollTop($("#layer-modal .modal-content>div").height() - $("#layer-modal .modal-content").height());
		};

		$("#layer-modal .modal-content").html("<div></div>");
		$("#layer-modal").openModal({
			dismissible: false,
			complete: function () {
				websocket.close();
			}
		});

	});


	/**
	 * ajax请求后台运行脚本
	 */
	function ajaxShell(url, postData, successCallback) {
		$("#loader-modal").openModal({dismissible: false});
		$.ajax({
			url: url,
			type: "POST",
			data: postData,
			cache: false,
			dataType: "text",
			success: function (data) {
				$("#loader-modal").closeModal();
				$("#layer-modal .modal-content").html(data.replace(/\n/g,"<br>"));
				$("#layer-modal").openModal({dismissible: false});
				if(successCallback) {
					successCallback();
				}
			},
			error: function () {
				$("#loader-modal").closeModal();
				layerAlert("发生异常，请重试！");
			}
		});
	}

	/**
	 * 刷新服务器运行状态
	 */
	function refreshStatus() {
		$.ajax({
			url: "../status",
			type: "POST",
			data: {uuid: uuid},
			cache: false,
			dataType: "text",
			success: function (data) {
				$(".service-status").children("span").hide();
				if(data === "true") {
					$(".service-status").find(".green-text").show();
				} else {
					$(".service-status").find(".red-text").show();
				}
			},
			error: function () {
				layerAlert("发生异常，请重试！");
			}
		});
	}

	/**
	 * 用于代替alert
	 * @param text
	 */
	function layerAlert(text) {
		$("#alert-modal .text-alert").html(text);
		$("#alert-modal").openModal({dismissible: false});
	}
	
});