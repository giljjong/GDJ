	$(function(){ 
		fn_checkAll();
		fn_checkOne();
		fn_submitFrm();
	})
	
	// 모두 동의 (모두 동의의 체크 상태 = 개별 선택들의 체크 상태)
	function fn_checkAll(){
		$('#check_all').click(function(){
			// 체크 상태 변경
			$('.check_one').prop('checked', $(this).prop('checked'));
			$('.lbl_all, .lbl_one').toggleClass('lbl_checked');
		});
	};
	
	// 개별 선택 (항상 개별 선택 4개를 모두 순회하면서 어떤 상태인지 체크해야 한다)
	function fn_checkOne(){
		// 체크 상태 변경
		$('.check_one').click(function(){
			$(this).next().toggleClass('lbl_checked');
			// 체크 상태 변경
			let checkCount = 0;
			for(let i = 0; i < $('.check_one').length; i++) {
				checkCount += $($('.check_one')[i]).prop('checked');
			};
			// 체크박스 개수 vs 체크된 박스 개수
			$('#check_all').prop('checked', $('.check_one').length == checkCount);
			if($('#check_all').prop('checked')){
				$('.lbl_all').addClass('lbl_checked');
			} else {
				$('.lbl_all').removeClass('lbl_checked');
			}
		});
	};
	function fn_submitFrm(){
		$('#frm_agree').submit(function(){
			if($('#service').is(':checked') == false || $('#privacy').is(':checked') == false){
				alert('필수 동의를 하셔야합니다.');
				event.preventDefault();
				return;
			}
		})
	};