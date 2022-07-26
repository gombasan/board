package com.ex.board.service;

import java.security.Principal;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ex.board.entity.Comment;
import com.ex.board.entity.Message;
import com.ex.board.entity.SiteUser;
import com.ex.board.repository.UserRepository;
import com.ex.board.service.form.DataNotFoundException;
import com.ex.board.service.form.PwCompareForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	
	public SiteUser create(String username, String email, String password) {
		
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		this.userRepository.save(user);
		
		return user;
		
	}
	
	public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
        	throw new DataNotFoundException("siteuser not found");
        }
    }
	
	// 회원정보 변경
	public void modify(Long id, String username, String email, String password) {
		
		@SuppressWarnings("deprecation")
		SiteUser siteuser=this.userRepository.getById(id);
		if(email!="") siteuser.setEmail(email);//받은 email값이 ""면 변경하지않는다.
		
		siteuser.setPassword(passwordEncoder.encode(password));
		
		
		this.userRepository.save(siteuser);
	}
	
	

	
}
