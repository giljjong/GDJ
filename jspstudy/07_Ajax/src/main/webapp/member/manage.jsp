<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String contextPath = request.getContextPath();
	pageContext.setAttribute("contextPath", contextPath); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		fn_getAllMembers();
		fn_getMember();
		fn_registeration();
		fn_modify();
		fn_remove();
	});
	
	function fn_init() {
		$('#id').val('').prop('readonly',false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#grade').val('');
		$('#address').val('');
	}
	
	function fn_getAllMembers() {
		$.ajax({
			type : 'get',
			url : '${contextPath}/member/list.do',
			dataType : 'json',
			success : function(resData){
				$('#count').text(resData.count);
				
				// member_list 초기화
				$('#member_list').empty();
				
				$.each(resData.members, function(i, member){
					var tr = '<tr>';
					tr += '<td>' + member.memberNo + '</td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.grade + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="button" value="조회" class="btn_detail"><input type="hidden" value="'+member.memberNo+'"> <input type="button" value="삭제" class="btn_remove"></td>';
					tr += '</tr>';
					$('#member_list').append(tr);
				});
			}
		});
	};
	function fn_getMember() {
		$('#member_list').on('click', '.btn_detail', function(){
			$.ajax({
				type : 'get',
				url : '${contextPath}/member/detail.do',
				data : 'memberNo=' + $(this).next().val(),
				dataType : 'json',
				success : function(resData){
					if(resData.exists) {
						$('#id').val(resData.member.id).prop('readonly', true);
						$('#name').val(resData.member.name);
						$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true);
						$('#grade').val(resData.member.grade);
						$('#address').val(resData.member.address);
						$('#memberNo').val(resData.member.memberNo);
					} else {
						alert('조회된 회원 정보가 없습니다.');
					}
				}
			});
			
		});
	};
	function fn_registeration(){
		$('#btn_add').click(function() {
			$.ajax({
				type : 'post',
				url : '${contextPath}/member/add.do',
				data : $('#frm_member').serialize(),
				dataType : 'json',
				success : function(resData){
					if(resData.isSuccess) {
						alert('신규 회원이 등록되었습니다.');
						fn_getAllMembers();
						fn_init();
					} else {
						alert('신규 회원 등록이 실패하였습니다.');
					}
				},
				error : function(jqXHR) {
					alert(jqXHR.responseText);
				}
			});
		});
	};	// function

	function fn_modify() {
		$('#btn_modify').click(function() {
			$.ajax({
				type:'post',
				url:'${contextPath}/member/modify.do',
				data : $('#frm_member').serialize(),
				dataType : 'json',
				success : function(resData) {
					if(resData.isSuccess){
					alert('회원 정보가 수정되었습니다.');
					fn_getAllMembers();
					} else {
						alert('회원 정보 수정이 실패했습니다.');
					}
				}, error : function(jqXHR) {
					alert(jqXHR.responseText);
				}
			})
		})
	}	// function

function fn_remove(){

		$('body').on('click', '.btn_remove', function(){

			if(confirm('삭제할까요?') == false){
				return;
			}
				$.ajax({
					/* 요청 */
					type: 'get',
					url: '${contextPath}/member/remove.do',
					data: 'memberNo=' + $(this).prev().val(),
					/* 응답 */
					dataType: 'json',
					success: function(resData){  // resData : {"isSuccess": true}
						if(resData.isSuccess){
							alert('회원 정보가 삭제되었습니다.');
							fn_getAllMembers();
							fn_init();
						} else {
							alert('회원 정보 삭제가 실패했습니다.');
						}
					},
					error: function(jqXHR){
						alert(jqXHR.responseText);
					}
				}); 
		});
	}  // function
	
</script>
</head>
<body>
	 <div class="wrap">
		<h1 class="title">회원관리</h1>	
	 	<form id="frm_member" action="${contextPath}/member/add.do">
	 		<div>
		 		<label for="id">아이디</label>
		 		<input type="text" id="id" name="id">
	 		</div>
	 		<div>
		 		<label for="name">이름</label>
		 		<input type="text" id="name" name="name">
	 		</div>
	 		<div>
	 			<label for="male">남자</label>
	 			<input type="radio" id="male" name="gender" value="M">
	 			<label for="female">여자</label>
	 			<input type="radio" id="female" name="gender" value="F">
	 		</div>
	 		<div>
	 			<label for="grade">회원등급</label>
	 			<select id="grade" name="grade">
	 				<option value="">등급선택</option>
	 				<option value="gold">골드</option>
	 				<option value="silver">실버</option>
	 				<option value="bronze">브론즈</option>
	 			</select>
	 		</div>
 			<div>
 				<label for="address">주소</label>
 				<input type="text" id="address" name="address">
 			</div>
	 			<div>
	 				<input type="button" value="초기화" onclick="fn_init();">
	 				<input type="button" value="신규등록" id="btn_add">
	 				<input type="button" value="변경내용저장" id="btn_modify">
	 				<input type="hidden" id="memberNo">
	 				<input type="button" value="회원삭제" class="btn_remove">
	 			</div>
	 	</form>
		<hr>
		<table class="member_table">
			<caption>전체 회원 수 : <span id="count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>등급</td>
					<td>주소</td>
					<td></td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
		</table>
	 </div>

</body>
</html>