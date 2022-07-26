package com.ex.board.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ex.board.entity.SiteUser;
import com.ex.board.repository.MessageRepository;
import com.ex.board.service.UserService;
import com.ex.board.service.form.PwCompareForm;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class HomeController {
	private final MessageRepository messageRepository;
	private final UserService userService;
	
	@RequestMapping("/")
	    public String root() {
	        return "redirect:/message/list";
	}
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage")
		public String mypage(Model model, Principal  principal) {
		
		SiteUser siteUser = this.userService.getUser(principal.getName());
		model.addAttribute("siteUser", siteUser);
		
		return "mypage";
	}
	
	@GetMapping("/modify")
	public String mypagemodify(Model model, PwCompareForm userModifyform) {
		
		model.addAttribute("userModifyform",userModifyform);
		return "modify";
	}
	
	
	
//	@PreAuthorize("isAuthenticated()")
//	@RequestMapping("/mypage")
//		public String mypage(Model model, Principal  principal) {
//		
//		SiteUser siteUser = this.userService.getUser(principal.getName());
//		model.addAttribute("siteUser", siteUser);
//		
//		return "mypage";
//	}
	
}
