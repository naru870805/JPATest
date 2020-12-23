
var sections;

$(window).scroll(function () {
	var _st = $(this).scrollTop();
	var _t1 = $("#contents").offset().top - _st;
	(_t1 <= 80) ? $("#contents .page-header").addClass("fixed") : $("#contents .page-header").removeClass("fixed");
	
	var _t2 = $("#contents section:eq(0)").offset().top - _st + 80;
	(_t2 <= 170) ? $(".side-i").addClass("fixed") : $(".side-i").removeClass("fixed");

	if (sections) {
		$("#gnb li").removeClass("active");
		var _el = undefined;
		sections.each(function () {
			var $t = $(this);
			var _n = $t.offset().top - _st;
			if (_n + $t.outerHeight() < 0 || _n > $(window).outerHeight()) return;
			if (_n - $(window).outerHeight() < -130) {
				_el = "/" + $t.attr("id");
			}
		});
		$("#gnb li:has(a[href='" + _el + "'])").addClass("active");
	}
});

$(window).resize(function () {
	$(".side-i").css("width", "auto");
	$(".side-i").css("width", $(".side-i").width() + "px");
});

$(document).ready(function() {
	
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

	$("#main-owl").owlCarousel({ 	
		items:1,
		nav: true,
		loop: true,
		center: true, 
		dots: false,
		navText: [ '<i class="fal fa-angle-left"></i>', '<i class="fal fa-angle-right"></i>' ]
	}).on('changed.owl.carousel', function(e) {
		var t = this;
		$(".thumbs .thumb[data-idx]").removeClass("active");
		setTimeout(function () {
			var idx = $(".owl-item.active [data-idx]", t).attr("data-idx");
			$(".thumbs .thumb[data-idx='" + idx + "']").addClass("active");
		}, 200);
	});
	$(".thumbs .thumb[data-idx='0']").addClass("active");
	$(".thumbs .thumb[data-idx]").click(function () {
		var ix = parseInt($(this).attr("data-idx"));
		$('.main-div .owl-carousel').trigger('to.owl.carousel', ix);
		e.preventDefault();
		return false;
	});
	
	var base_w = $(".thumb-box .thumbs>section").width() - $(".thumb-box .thumbs").width();
	$(".thumb-box .carousel").on("mousemove", function (e) {
		var ox = e.pageX - $(this).offset().left;
		var r = 1.2 * (ox / $(this).width()) - 0.1;
		if (r < 0) r = 0;
		if (r > 1) r = 1;
		$(".thumb-box .thumbs>section").css("left", (0 - base_w * r) + "px");
	});
	
	$('[data-src]').each(function () {
		$(this).css("background-image", "url(" + $(this).attr("data-src") + ")");
	});
	
	
	
	
	$('#option-owl').owlCarousel({ 	
		items:2,
		nav: true,
		navText: [ '<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>' ],
		responsiveClass:true,
		responsive: { 0:{ items:1 }, 600:{ items:2 } }
	});

	var _y = $("#contents").prev().outerHeight();
	$("#contents").css("top", _y + "px");
	
	$("#gnb li").removeClass("active");
	sections = $("#contents>section");
	if (sections.size() == 1) {
		var href = "/" + sections.attr("id");
		$("#gnb li:has(a[href='" + href + "'])").addClass("active");
		sections = undefined;
	}
	
	$(window).scroll();
	
	$(document).on("click", "#gnb li>a", function (e) {
		var href = $(this).attr("href").substring(1);
		if ($("section#" + href).size() > 0) {
			var t = $("section#" + href).position().top + $("#background").height() - 80;
			$("html,body").animate({ scrollTop : t }, 500);
			e.preventDefault();
		}
	});
	
	$(document).on("click", ".pagemore a", function (e) {
		
		var $btn = $(this);
		if ($btn.hasClass("loading")) return;
		
		$btn.addClass("loading");
		
		$.ajax({
			url : $(this).attr("href"),
			success: function (html) {
				var $el = $(html).find("#reviews");
				if ($el.size() > 0) {
					$("#reviews .pagemore").remove();
					$("#reviews").append($el.html());
					$(window).scroll();
				}
				else {
					this.error();
				}
			},
			error: function () {
				alert("서버와 연결이 올바르지 않습니다.");
				$btn.removeClass("loading");
			}
		});
		
		e.preventDefault();
		return false;
	});
	
});