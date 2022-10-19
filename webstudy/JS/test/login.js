document.getElementById('btn_signin').onclick = function (event) {
  var id = document.getElementById('userId');
  var pw = document.getElementById('pw');
  if (pw.value == '') {
    alert('비밀번호를 입력하세요');
    event.preventDefault();
    return;
  } else if (id.value.length < 4) {
    event.preventDefault();
    return;
  }
};
document.getElementById('userId').onkeyup = function (event) {
  var id = document.getElementById('userId');
  var idMsg = document.getElementById('id_msg');
  if (id.value.length == 0) {
    idMsg.textContent = '';
  } else if (id.value.length < 4) {
    idMsg.style.color = '#FF0000';
    idMsg.style.fontSize = '14px';
    idMsg.textContent = '아이디는 4자 이상입니다.';
    event.preventDefault();
  } else if (id.value.length >= 4) {
    idMsg.style.color = '#0000FF';
    idMsg.style.fontSize = '14px';
    id_msg.textContent = '정상적인 아이디입니다.';
  }
  document.getElementsByName;
};
