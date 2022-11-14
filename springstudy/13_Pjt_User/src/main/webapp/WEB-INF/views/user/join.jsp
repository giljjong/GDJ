<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	$(function(){
		fn_idCheck();
		fn_pwCheck();
		fn_mobileCheck();
		fn_emailCheck();
		fn_birthyear();
		fn_birthmonth();
		fn_birthdate();
	});
	
	function fn_idCheck(){
		$('#id').keyup(function(){
			
			
			$.ajax({
				type : 'get',
				url : '${contextPath}/user/checkSameId',
				data : 'id=' + $(this).val(),
				dataType : 'json',
				success : function(resData){
					if(resData.isUser || resData.isRetireUser) {
						$('#msg_id').text('이미 사용중이거나 탈퇴한 아이디입니다.');
					} else {
						$('#msg_id').text('사용 가능한 아이디입니다.');
					}
				}
			});
		});
	};
	function fn_pwCheck() {
		
	}
	function fn_mobileCheck() {
		
	}
	function fn_emailCheck() {
		$('#btn_getAuthCode').click(function(){
			new Promise(function(resolve, reject){
				$.ajax({
					type : 'get',
					url : '${contextPath}/user/checkEmail',
					data : 'email=' + $('#email').val(),
					dataType : 'json',
					success : function(resData) {
						if(resData.isUser) {
							reject();	// Promise 객체의 catch 메소드에 바인딩되는 함수
						} else {
							resolve();	// Promise 객체의 then 메소드에 바인딩되는 함수
						}
					}
				});	// ajax
			}).then(function(){
					
				// 인증번호 보내는 ajax
				$.ajax({
					type : 'get',
					url : '${contextPath}/user/sendAuthCode',
					data : 'email=' + $('#email').val(),
					dataType : 'json',
					success : function(resData) {
						alert('인증 코드를 발송했습니다. 이메일을 확인하세요.');
						$('#btn_verifyAuthCode').click(function(){
							if(resData.authCode == $('#authCode').val()){
								alert('인증되었습니다.')
							} else {
								alert('인증에 실패했습니다.');
							};
						});
					},
					error : function(jqXHR) {
						alert('인증번호 발송이 실패했습니다.');
					}
				});
				
			}).catch(function(){
				// 사용할 수 없는 이메일이 입력된 경우(다른 회원이 등록한 이메일을 입력)
				$('#msg_email').text('이미 사용중인 이메일입니다.');
				$('#authCode').prop('readonly', true);
			});
		});
	}
	function fn_birthyear() {
		
	}
	function fn_birthmonth() {
		
	}
	function fn_birthdate(){
		var month = $('#birthmonth');
		var arr = [1, 3, 5, 7, 8, 10, 12];
		month.change(function() {
			$('#birthdate').empty();
			var end = 0;
			var birthdate = '';
			
			for(let m = 0; m < arr.length; m++) {
				if(month.val() == arr[m]) {
					end = 31;
					break;
				} else if(month.val() == 2) {
					end = 28;
				} else {
					end = 30;
				}
			}

			for(let i = 1; i <= end; i++) {
				$('#birthdate').append("<option value='"+ i + "'>"+ i +"</option>");
			}

		});
	}
</script>
</head>
<body>
	
	<div>
		<h1>회원 가입</h1>
		<div>* 표시는 필수 입력사항입니다.</div>
		
		<hr>
		
		<form id="frm_join" action="${contextPath}/user/join" method="post">
				<input type="hidden" name="location" value="${location}">
				<input type="hidden" name="promotion" value="${promotion}">
			<div>
				<label for="id">아이디*</label>
				<input type="text" name="id" id="id">
				<span id="msg_id"></span>
			</div>
			<div>
				<label for="pw">패스워드*</label>
				<input type="password" name="pw" id="pw">
				<span id="msg_pw"></span>
			</div>
			<div>
				<label for="re_pw">패스워드 재확인*</label>
				<input type="password" id="re_pw">
				<span id="msg_re_pw"></span>
			</div>
			<div>
				<label for="name">이름*</label>
				<input type="text" name="name" id="name">
			</div>
			<div>
				<label for="none">선택안함</label>
				<input type="radio" name="gender" id="none" value="N" checked="checked">
				<label for="male">남자</label>
				<input type="radio" name="gender" id="male" value="M">
				<label for="female">여자</label>
				<input type="radio" name="gender" id="female" value="F">
			</div>
			<div>
				<label for="mobile">전화번호*</label>
				<input type="text" name="mobile" id="mobile">
				<span id="msg_mobile"></span>
			</div>
			<div>
				<label for="birthyear">생년월일*</label>
				<select name="birthyear" id="birthyear">
					<c:forEach var="i" begin="1920" end="2022">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<select name="birthmonth" id="birthmonth">
					<c:forEach var="i" begin="1" end="12">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<select name="birthdate" id="birthdate">
					<c:forEach var="i" begin="1" end="31">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<input type="text" id="postcode" name="postcode" placeholder="우편번호">
				<input type="button" onclick="fn_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="roadAddress" name="roadAddress" placeholder="도로명주소">
				<input type="text" id="jibunAddress" name="jibunAddress" placeholder="지번주소">
				<span id="guide" style="color:#999;display:none"></span><br>
				<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">
				<input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목">
				
				<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				<script>
				    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
				    function fn_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
				                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				                var roadAddr = data.roadAddress; // 도로명 주소 변수
				                var extraRoadAddr = ''; // 참고 항목 변수
				
				                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				                    extraRoadAddr += data.bname;
				                }
				                // 건물명이 있고, 공동주택일 경우 추가한다.
				                if(data.buildingName !== '' && data.apartment === 'Y'){
				                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                }
				                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				                if(extraRoadAddr !== ''){
				                    extraRoadAddr = ' (' + extraRoadAddr + ')';
				                }
				
				                // 우편번호와 주소 정보를 해당 필드에 넣는다.
				                document.getElementById('postcode').value = data.zonecode;
				                document.getElementById("roadAddress").value = roadAddr;
				                document.getElementById("jibunAddress").value = data.jibunAddress;
				                
				                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
				                if(roadAddr !== ''){
				                    document.getElementById("extraAddress").value = extraRoadAddr;
				                } else {
				                    document.getElementById("extraAddress").value = '';
				                }
				
				                var guideTextBox = document.getElementById("guide");
				                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
				                if(data.autoRoadAddress) {
				                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
				                    guideTextBox.style.display = 'block';
				
				                } else if(data.autoJibunAddress) {
				                    var expJibunAddr = data.autoJibunAddress;
				                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
				                    guideTextBox.style.display = 'block';
				                } else {
				                    guideTextBox.innerHTML = '';
				                    guideTextBox.style.display = 'none';
				                }
				            }
				        }).open();
				    }
				</script>
			</div>
			<div>
				<label for="email">이메일*</label>
				<input type="text" name="email" id="email">
				<input type="button" id="btn_getAuthCode" value="인증번호받기">
				<span id="msg_email"></span><br>
				<input type="text" name="authCode" id="authCode" placeholder="인증코드 입력">
				<input type="button" value="인증하기" id="btn_verifyAuthCode">
			</div>
			
			<hr>
			
			<div>
				<button>가입하기</button>
				<input type="button" value="취소하기" onclick="location.href='/'">
			</div>
		</form>
	</div>
	
</body>
</html>