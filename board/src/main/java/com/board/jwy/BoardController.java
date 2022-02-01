package com.board.jwy;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVo;
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
	
	//게시글 작성 구현
	@PostMapping("/write")
	public String postWirte(BoardVo vo) throws Exception {
		service.write(vo);
		
		//redirect : 모든 작업을 마치고 /board/list로 바로 이동하겠다.
		return "redirect:/board/list";
	}
	
	//게시글 조회
	@GetMapping("/view")
	/*
	 * 글번호를 받아서 게시글을 조회하기 때문에
	 * @RequestParam을 이용하면 특정값을 가져올수 있다
	 * 그 특정값을 찾아 int bno에 넣어주고 
	 * Model을 이용하여 뷰(view)에 데이터를 넘겨준다.*/
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception{
		
		BoardVo vo = service.view(bno);
		
		//model의 이름은 "view"로 지정함.
		model.addAttribute("view", vo);
		
	}
	
	//게시글 수정 화면 이동 ( 데이터 내용들도 같이 나오게 처리)
	@GetMapping("/modify")
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
		
		BoardVo vo = service.view(bno);
		
		model.addAttribute("view", vo);
	}
	
	//게시글 수정 구현 
	@PostMapping("/modify")
	public String postModify(BoardVo vo) throws Exception {
		
		service.modify(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();
		
	}
	
	
	//게시글 삭제
	@GetMapping("/delete")
	public String getDelete(@RequestParam("bno") int bno) throws Exception{
		service.delete(bno);
		
		return "redirect:/board/list";
	}
	
	//페이징처리
	@GetMapping("/listPage")
	public void getListPage(Model model, @RequestParam(value = "num",defaultValue = "1") int num) throws Exception {
		 
		 // 게시물 총 갯수
		 int count = service.count();
		  
		 // 한 페이지에 출력할 게시물 갯수
		 int postNum = 10;
		  
		 // 게시글 시작 번호 구하기. ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
		 int pageNum = (int)Math.ceil((double)count/postNum);
		  
		 // 출력할 게시물
		 //현재 페이지의 게시글 시작번호 = (현재 페이지번호 - 1) * 페이지당 보여줄 게시글 갯수
		 int displayPost = (num-1) * postNum;
		    
		 List list = null; 
		 list = service.listPage(displayPost, postNum);
		 model.addAttribute("list", list);   
		 model.addAttribute("pageNum", pageNum);
		}

}
