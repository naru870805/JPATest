
var sections;
var xhr;

function hidemsg() {
	clearInterval($("#beauty-alert").data("iv"));
	$("#beauty-alert").clearQueue().fadeOut(300);
}

$(document).ready(function() {


	$(".dateinput").attr("readonly", "readonly").datepicker({
		dateFormat: "yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		dayNames: [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ],
		dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ], 
		dayNamesShort: [ "일", "월", "화", "수", "목", "금", "토" ]
	});

	
	
	$('[data-src]').each(function () {
		$(this).css("background-image", "url(" + $(this).attr("data-src") + ")");
	});
	
	
	$("#contacts_form").submit(function (e) {
		
		if (!$(this).parsley().validate()) return false;
		
		if($("#agree:checked").length != 1) {
			alert("개인정보 수집 및 이용안내에 동의해주세요.");
			return false;
		}
			
		var $form = $(this);
		
		if(getCookie("ref") != "") {
			$("input[name='mktCookie']").val(getCookie("ref"));
		}
		
		$("button", this).hide();
		$.ajax({
			url : context_path + "/subevent/contact",
			data : $(this).serialize(),
			type : "POST",
			success: function (data) {
				if (!data.success) {
					this.error();
					return;
				}
				
				clearInterval($("#beauty-alert").data("iv"));
				$("#beauty-alert h3").html(data.title);
				$("#beauty-alert p").html(data.message);
				$("#beauty-alert i").attr("class", "fal fa-heart");
				$("#beauty-alert").clearQueue().show();
				$("#beauty-alert").data("iv", setInterval(hidemsg, 2000));
				
				$("input , textarea", $form).val("");
				$("input[type='checkbox']", $form).prop("checked", false);
				$("button", $form).show();
				
			},
			error: function () {
				alert("서버와 연결할 수 없습니다.");
				$("button", $form).show();
			}
		});
		
		return false;
	});

	
	$(document).on("click","#main .cover", function(e) {
		$(this).fadeOut(400);
		player.playVideo();
	})
	
	var userAgent=navigator.userAgent.toLowerCase();
	if(userAgent.indexOf("kakao") != -1) {
		$("#main .cover").fadeOut(400);
	}

	
	
});



