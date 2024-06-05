function check_ok() {
	if(reg_frm.b_name.value.length==0) {
		alert("이름을 써주세요.")
		reg_frm.b_name.focus();
		return;
	}
	if(reg_frm.b_email.value.length==0) {
		alert("Email을 써주세요.")
		reg_frm.b_email.focus();
		return;
	}
	if(reg_frm.b_title.value.length==0) {
		alert("제목을 써주세요.")
		reg_frm.b_title.focus();
		return;
	}
	if(reg_frm.b_content.value.length==0) {
		alert("내용을 써주세요.")
		reg_frm.b_content.focus();
		return;
	}

	reg_frm.submit();
}

function delete_ok() {
	if(del_frm.b_pwd.value.length==0) {
		alert("암호를 써주세요.")
		del_frm.b_pwd.focus();
		return;
	}
	del_frm.submit();
}

