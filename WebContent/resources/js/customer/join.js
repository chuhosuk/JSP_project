/**
 * 회원가입 validation check
 */

//주의사항 => 회원가입페이지JSP, 중복확인 페이지JSP에 추가 
//<script src="${path}/resources/js/customer/join.js"defer></script>
//아이디 중복화인 버튼 클릭시
//1. 중복확인 페이지 open

// 이메일 
function selectEmailChk(){
	if(document.joinform.email3.value =="0"){ //직접입력
		document.joinform.email2.value = "";
		document.joinform.email2.focus();
		return false;
	}else{
		document.joinform.email2.value = document.joinform.email3.value;
		return false;
	}
}

function confirmId(){
	if(!document.joinform.id.value){
		alert("아이디를 입력하세요.")
		document.joinform.id.focus();
		return false;
	}
	
	let url = "/jsp_pj_126/idConfirmAction.do?id="+ document.joinform.id.value;
	window.open(url, "confirm" ,"menubar=no, width=600, height=500");
	
	//컨트롤러로 url을 넘기므로 컨트롤러에서 추가
}

//회원가입 페이지 필수 체크
function signInCheck(){
	
//	2-1. 중복확인
//  <input type="hidden" name="hiddenId" value="0"> => join.form의 form아래 추가
//	hiddenId : 중복확인 버튼 클릭 여부 체크 (0:클릭안함 | 1:클릭함)
	
	// 2-2. 중복확인 버튼을 클릭하지 않는 경우 "중복확인 해주세요." 메시지 띄운다.
	if(document.joinform.hiddenId.value == "0"){
		alert("중복확인 해주세요.");
		document.joinform.dupChk.focus();
		return false;
	}
}

// ------------[중복확인]------------
// 3. 중복확인 페이지 - id에 포커스
function idConfirmFocus(){
	document.confirmform.id.focus();
}

function idConfirmCheck(){
	if(!document.confirmform.id.value){
		alert("아이디를 입력하세요.");
		document.confirmform.id.focus();
		return false;
	}
}

/*function adminCheck(){

		alert("관리자 ㅎㅇ");
		
	
}*/


// 4. 자식 창에서 부모 창으로 id값을 전달
/*
 * opener : window 객채의 open() 메서드로 열린 새창(=중복확인창)에서 부모창(=회원가입폼)에 접근할 때 사용
 * join.jsp -hiddenId : 중복확인 버튼 클릭여부 체크(0:클릭안함,1:클릭함)
 * self.close();  내창닫기
 * 
 */
function setId(id){
	opener.document.joinform.id.value = id;
	opener.document.joinform.hiddenId.value ="1";
	self.close();
}

// ---------------------------------