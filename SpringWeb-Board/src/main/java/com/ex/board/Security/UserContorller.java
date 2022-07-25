package com.ex.board.Security;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserContorller {
	
	private final UserService userService;
	
	@GetMapping("/signup")
	public String CreateUser(UserCreateForm userCreateForm) {
		
		
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String CreateUser(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordIncorrect","2개의 패스워드가 일치하지 않습니다.");
			return "signup_form";
		}
		
		try {
			this.userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		}catch(Exception e) {
			e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
		}
		
		return "redirect:/";
	}
}
