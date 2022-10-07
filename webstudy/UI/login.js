// 비밀번호 입력 없이 Sign In 버튼 누르면 경고창 띄우기

document.getElementById("btn_signin").onclick = function (event) {
  var id = document.getElementById("userId");
  var pw = document.getElementById("pw");
  if (pw.value == "") {
    alert("비밀번호를 입력해주세요");
    event.preventDefault();
    return;
  } else if (id.value.length < 4) {
    alert("아이디는 4글자 이상입니다.");
    event.preventDefault();
    return;
  }
};
document.getElementById("userId").onkeyup = function (event) {
  var id = document.getElementById("userId");
  var idMsg = document.getElementById("id_msg");
  if (id.value.length == 0) {
    idMsg.textContent = "";
  } else if (id.value.length < 4) {
    idMsg.textContent = "아이디는 4자 이상입니다.";
    event.preventDefault();
  } else if (id.value.length > 4) {
    id_msg.textContent = "정상적인 아이디입니다.";
  }
  document.getElementsByName;
};
/*
$("#btn_sigin").on("click", function (event) {
  if (pw.value == "") {
    alert("비밀번호를 입력해주세요");
    event.preventDefault();
    return;
  }
});
*/
