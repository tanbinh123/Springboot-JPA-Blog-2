﻿let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		//console.log(data);

		// ajax 호출시 default 는 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경, insert 요청 
		// ajax가 통신 성공 후 서버가 json 리턴하면 자동으로 자바 오브젝트로 변환
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset=utf-8", // body data type(MIME)
			dataType: "json" // 요청에 대한 응답이 왔을 때 기본적으로 모든 것이 문자열 // 생긴 게 json이라면 js오브젝트로 변경
		}).done(function(resp) {
			// 성공했을 때
			if(resp.status === 500) {
				alert("Registration failed.");
			}else {
				alert("Registration complete.");
			location.href = "/";
			}
		}).fail(function(error) {
			//실패했을 때
			alert(JSON.stringify(error));
		});
	},
	
		update: function() {
			
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", 
			dataType: "json"
		}).done(function(resp) {
			alert("User info updated.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();