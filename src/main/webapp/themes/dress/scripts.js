
var sections;
var xhr;

$(window).scroll(function () {
	
});

$(window).resize(function () {
	$(".side-i").css("width", "auto");
	$(".side-i").css("width", $(".side-i").width() + "px");
	
	if ($(this).width() > 768) {
		$("#main-owl img[data-src]").attr("src", "/img/t.png");
	}
	else {
		$("#main-owl img[data-src]").attr("src", "/img/t.png");
		/*$("#main-owl img[data-src]").each(function () {
			$(this).attr("src", $(this).attr("data-src"));
		});*/
	}
});


function hidemsg() {
	clearInterval($("#beauty-alert").data("iv"));
	$("#beauty-alert").clearQueue().fadeOut(300);
}

$(document).ready(function() {
	$(window).resize();
	
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
		autoHeight: true,
		navText: [ '<i class="fal fa-angle-left"></i>', '<i class="fal fa-angle-right"></i>' ],
		responsiveClass:true,
		responsive: { 0:{ autoHeight: true }, 768:{ autoHeight: false} }
	}).on('changed.owl.carousel', function(e) {
		var t = this;
		$(".thumbs .thumb[data-idx]").removeClass("active");
		setTimeout(function () {
			var idx = $(".owl-item.active [data-idx]", t).attr("data-idx");
			$(".thumbs .thumb[data-idx='" + idx + "']").addClass("active");
		}, 100);
	});
	
	$("#main-owl img").one("load", function() {
		$('.main-div .owl-carousel').trigger('refresh.owl.carousel');
	}).each(function() {
		if(this.complete) $(this).load();
	});
	
	$(".thumbs .thumb[data-idx='0']").addClass("active");
	$(".thumbs .thumb[data-idx]").click(function (e) {
		var ix = parseInt($(this).attr("data-idx"));
		$('#main-owl.owl-carousel').trigger('to.owl.carousel', ix);
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
	
	var base_w = $(".thumb-box .thumbs>section").width() - $(".thumb-box .thumbs").width();

	var _y = $("#contents").prev().outerHeight();
	//$("#contents").css("top", _y + "px");
	
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
			if (history.replaceState) history.replaceState({}, href, "/");
			$("body").removeClass("m-open");
			var x = 80;
			if ($(window).width() < 768) x = 0;
			var t = $("section#" + href).position().top + $("#background").height() - x;
			$("html,body").animate({ scrollTop : t }, 500);
			e.preventDefault();
		}
	});

	
	$("[data-autocomplete]").each(function (k, v) {
		$(v).autocomplete({
			source : $(v).attr("data-autocomplete"),
		    minLength: 0,
		    focus : function(event, ui) {
				$(v).val(ui.item.label);
				return false;
			},
			select : function(event, ui) {
				var $el = $(this).parents("li[data-type]");
				$("input.index", $el).val(ui.item.id);
			}
		}).keydown(function(e){
			if(e.keyCode == 13){
				e.preventDefault();
				return false;
			}
		});
	});

	$(".all-check").click(function(){
		if($(this).prop("checked")) {
			$("input[type=checkbox]").prop("checked",true);
		}
		else {
			$("input[type=checkbox]").prop("checked",false);
		}
	});


	$(document).on("click", ".contact-area>button", function (e) {
		var article = "";
		$(".price-area>.company-desc>b").each( function(k,v) {
			if(k > 0) {
				article += ", " + $(this).text();
			}else {
				article += $(this).text(); 
			}
		});
		
		$("textarea[name='content']").val(article);
		
		var offset = $("textarea[name='content']").offset();
		 $('html, body').animate({scrollTop : offset.top - 120}, 400);
		
		e.preventDefault();
		return false;
	});
	
	$("#contacts_form").submit(function (e) {
		e.preventDefault();
		if (!$(this).parsley().validate()) return false;
			
		var $form = $(this);
		$("button", this).hide();
		$.ajax({
			url : context_path + "/contacts",
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
				$("#beauty-alert i").attr("class", "far fa-heart");
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
});



