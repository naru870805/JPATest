
var sections;
var xhr;

function hidemsg() {
	clearInterval($("#beauty-alert").data("iv"));
	$("#beauty-alert").clearQueue().fadeOut(300);
}

function shuffle(a) { 
	var j, x, i;
	for (i = a.length; i; i -= 1) { 
		j = Math.floor(Math.random() * i);
		x = a[i - 1]; a[i - 1] = a[j];
		a[j] = x;
	}
	return a;
}

$(window).scroll(function () {
	var _st = $(this).scrollTop();
	if(_st > $("#main").height() || _st) {
		$(".animate-btn").addClass("active");
	}else {
		$(".animate-btn").removeClass("active");
	}
	var ch = $("#contact").offset().top + $("#contact").height() - 30
	if(_st > $("#contact").offset().top && _st < ch) {
		$(".animate-btn").removeClass("active");
	}

});

$(window).resize(function () {
	$("section#reason .other-background>img").css("width",$("body").width() - 80 + "px");
});

$(document).ready(function() {
	
	
	// 허니문 array;
	var marray = new Array();
	var active;
	var idx = 0;
	$("#honeymoon>.menu>li").each(function(ev){
		if(idx != $(this).attr("data-idx")) {
			marray.push($(this));
		}else {
			active = $(this);
		}
	});
	var newArray = new Array();
	/*shuffle(marray);*/

	for(var i =0;i <= 4;i++) {
		if(i == 2) {
			newArray.push(active);
		}else {
			if(i > 2) {
				newArray.push(marray[i - 1]);
			}else {
				newArray.push(marray[i]);
			}
		}
	}

	$("#honeymoon>.menu").html("");
	for(var i =0;i <= 4;i++) {
		$("#honeymoon>.menu").append(newArray[i]);
	}
	
	// 허니문 array 종료		
	
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
		
		if(getCookie("ref") != "") {
			$("input[name='mktCookie']").val(getCookie("ref"));
		}
		
		var $form = $(this);
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
		var marray = new Array();
		var active;
		$("#honeymoon>.menu").css("opacity","0");
		setTimeout(function () {
			var idx = $(".owl-item.active [data-idx]", t).attr("data-idx");
			$("#honeymoon>.menu>li").each(function(ev){
				if(idx != $(this).attr("data-idx")) {
					marray.push($(this));
				}else {
					active = $(this);
				}
			});
			var newArray = new Array();
			/*shuffle(marray);*/
			
			for(var i =0;i <= 4;i++) {
				if(i == 2) {
					newArray.push(active);
				}else {
					if(i > 2) {
						newArray.push(marray[i - 1]);
					}else {
						newArray.push(marray[i]);
					}
				}
			}
			$("#honeymoon>.menu").html("");
			for(var i =0;i <= 4;i++) {
				$("#honeymoon>.menu").append(newArray[i]);
			}
			$("#honeymoon>.menu").css("opacity","1");
		}, 100);
	});
	
	$(document).on("click","#honeymoon .menu>li", function (e) {
		var ix = parseInt($(this).attr("data-idx"));
		$('#honeymoon-owl.owl-carousel').trigger('to.owl.carousel', ix);
		e.preventDefault();
		return false;
	});
	
	$(document).on("keyup keypress keydown", "input[name='tel1']", function (e){
		if(e.keyCode == 189 || e.keyCode == 109) {
			e.preventDefault();
			return false;
		}
	});
	
});



