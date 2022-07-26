package com.ex.board.service.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min = 3, max = 25 ,message = "ID는 3~25자여야 합니다.")
	@NotEmpty(message = "ID를 입력하십시오.")
	private String username;
	
	@NotEmpty(message = "Password를 입력하십시오.")
	private String password1;
	
	@NotEmpty(message = "Password 확인을 입력하십시오.")
	private String password2;
	
	@NotEmpty(message = "Email을 입력하십시오.")
	@Email
	private String email;
	
}
