$(".complete").click(function(){
	$.ajax({
		url : context_path + $(this).attr("data-ajax"),
		type : "POST",
		success: function (data) {
			if (!data.success) {
				this.error();
				return;
			}
			location.href="/";
		},
		error: function () {
			alert("서버와 연결할 수 없습니다.");
		}
	});
});