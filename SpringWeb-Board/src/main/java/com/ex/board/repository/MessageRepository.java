package com.ex.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ex.board.entity.message.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	 Page<Message> findAll(Pageable pageable);
	 Page<Message> findAll(Specification<Message> spec, Pageable pageable);
	 
}
