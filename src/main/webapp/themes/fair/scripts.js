
var sections;
var xhr;

function hidemsg() {
	clearInterval($("#beauty-alert").data("iv"));
	$("#beauty-alert").clearQueue().fadeOut(300);
}

$(window).resize(function () {
	$("section#reason .other-background>img").css("width",$("body").width() - 80 + "px");
});

$(document).ready(function() {
	
	$(window).resize();
	
	$("section#intro .background").eq(0).fadeIn(0);
	$("section#intro .background").eq(0).addClass("active");
	
	setInterval(function(e) {
		if($("section#intro .background.active").next().length == 0) {
			$("section#intro .background.active").fadeOut(300);
			$("section#intro .background.active").addClass("deactive").removeClass("active");
			$("section#intro .background").eq(0).fadeIn(300).addClass("active");
			$("section#intro .background.deactive").removeClass("deactive");			
		}else {
			$("section#intro .background.active").fadeOut(300);
			$("section#intro .background.active").addClass("deactive").removeClass("active");
			$("section#intro .background.deactive").next().fadeIn(300).addClass("active");
			$("section#intro .background.deactive").removeClass("deactive");			
		}
	}, 5000);
	
	if ($(".side-i").size() > 1) $(".side-i:not(:eq(0))").remove();
	$(".side-i").css("width", $(".side-i").width() + "px");
	
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
	
	$(document).on("click","[data-loc]", function(e) {
		var loc = "#" + $(this).attr("data-loc");
		scrollTo(0,$(loc).offset().top);
	})

	$("#sdmb .rolling .owl-theme").owlCarousel({ 	
		items:1,
		nav: false,
		loop: true,
		center: true, 
		dots: true,
		autoplay:true,
		autoplayTimeout:3000,		
		height:329
	});	
	
	$("#reason-owl").owlCarousel({ 	
		items:1,
		nav: false,
		loop: true,
		center: true, 
		dots: true,
		autoplay:true,
		autoplayTimeout:10000
	});		

	$("#honeymoon>.background[data-idx='0']").css("opacity","1");
	$("#honeymoon .menu li[data-idx='0']").addClass("active");
	$("#honeymoon-owl").owlCarousel({ 	
		items:1,
		nav: false,
		loop: true,
		center: true, 
		dots: false,
		autoplay:true,
		autoplayTimeout:8000
	}).on('changed.owl.carousel', function(e) {
		var t = this;

		setTimeout(function () {
			var idx = $(".owl-item.active [data-idx]", t).attr("data-idx");
			$("#honeymoon>.background").css("opacity","0");
			$("#honeymoon>.background[data-idx='"+idx+"']").css("opacity","1");
			
			$("#honeymoon .menu li").removeClass("active");
			$("#honeymoon .menu li[data-idx='"+idx+"']").addClass("active");
			
		}, 100);
	});
	
	$(document).on("click","#honeymoon .menu li", function(e) {
		var ix = parseInt($(this).attr("data-idx"));
		$('#honeymoon-owl.owl-carousel').trigger('to.owl.carousel', ix);
		$("#honeymoon .menu li").removeClass("active");
		$(this).addClass("active");
	})
	
});



