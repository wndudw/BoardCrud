package com.board.jwy;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.service.BoardService;

@Controller
//RequestMapping("/board/*") : BoardController 클래스에 있는 모든 메소드의 url은 board/* 로 처리하겠다.
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
	//게시글 목록 이동
	@GetMapping("/list") //Model : controller와 view를 연결해주는 역할
	public void getList(Model model) throws Exception {
		
		List list = null;
		list = service.list();
		model.addAttribute("list", list);
	}
	
	//게시글 작성 이동
	@GetMapping("/write")
	public void getWirte() throws Exception{
		
	}
}
