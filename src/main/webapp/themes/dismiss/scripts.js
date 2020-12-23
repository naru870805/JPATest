jQuery(function($){
	
	//$('.result p span b').text($('section.info .recommen>li').length); // 홀 개수 반환
	
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
		
	
	$('.info-btn').click(function(e){
		if($(this).siblings('.info-view').hasClass('active')){ // 정보 더보기
			$(this).siblings('.info-view').removeClass('active');	
		} else{
			$(this).siblings('.info-view').addClass('active');
		}
		
		if($(this).siblings('.info-view').find('.hall-table').length == 1){ // 정보가 한개면 width 값 100%
			$(this).siblings('.info-view').addClass('length');
		}
	});
	
	$('.hall-btn').click(function(e){ // 비교하기 클릭
		$('body').toggleClass('active');
	});
	
	$('.back').click(function(e){ // 뒤로가기
		$('.hall-btn.compare').trigger('click');
	});
	
	$('.reservation').click(function(e){ // 웨딩홀 방문 상담 예약 클릭
		$('.reservation-popup').addClass('active');
		$('body').css('overflow', 'hidden');
	});
	
	$('.shadow-bg').click(function(e){ // 웨딩홀 방문 상담 예약 닫기
		$('.reservation-popup').removeClass('active');
		$('body').css('overflow', 'visible');
	});
	
	$('.close').click(function(e){
		$('.shadow-bg').trigger('click');
	});
	
	$('#datepicker').datepicker({
		changeMonth: true,
		changeYear: true,
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토']
	});
	
	function hidemsg($form) {
		clearInterval($("#beauty-alert").data("iv"));
		$("#beauty-alert").clearQueue().fadeOut(1000);
		setTimeout(() => {
			$('.reservation-popup').removeClass('active');
			$('body').css('overflow', 'visible');
		}, 800);
	}
	
	$("#contacts_form").submit(function (e) {
		e.preventDefault();
		if (!$(this).parsley().validate()) return false;
		var $form = this;
		$("button", this).hide();
		$.ajax({
			url : context_path + "/subcontacts",
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
				$("#beauty-alert").data("iv", setInterval(hidemsg($form), 2000));
				$("button", $form).show();
				$("input.form-control, input", $form).val("");
				$("input[type='checkbox']", $form).prop("checked", false);
				
			},
			error: function () {
				alert("서버와 연결할 수 없습니다.");
				$("button", $form).show();
			}
		});
		
		return false;
	});
	
})