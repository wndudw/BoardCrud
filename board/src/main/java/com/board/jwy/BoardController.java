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
import com.board.domain.Page;
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
		 
		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.count());
		
	    List<BoardVo> list = null;
	    list = service.listPage(page.getDisplayPost(), page.getPostNum());
	    
	    model.addAttribute("list", list);  
	    
	    /* Page 클래스가 있으므로 하나로 모델처리함.
		model.addAttribute("pageNum", page.getPageNum());
		
		model.addAttribute("startPageNum", page.getStartPageNum());
		model.addAttribute("endPageNum", page.getEndPageNum());
		 
		model.addAttribute("prev", page.getPrev());
		model.addAttribute("next", page.getNext());  
		*/
	    
	    model.addAttribute("page", page);
		model.addAttribute("select", num);
		
		
		/* Page 클래스를 만들지 않고  페이징 처리 하는 방법.
		 // 게시물 총 갯수
		 int count = service.count();
		  
		 // 한 페이지에 출력할 게시물 갯수
		 int postNum = 10;
		  
		 // 화면 화단에 표시할 페이징 번호 구하기 .  ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
		 int pageNum = (int)Math.ceil((double)count/postNum);
		  
		 //현재 페이지 기준으로 10개의 게시글 구하기.
		 // ((현재페이지번호 - 1) * 출력할 게시글 갯수 ) + 1
		 int displayPost = ((num -1) * postNum) + 1;
		 
		 // 화면 이동마다 10개씩 데이터 출력을 위해 다시 postNum을 초기화 후 페이징번호 * 10을 구함.
		 postNum = 0;
		 postNum = 10*(num);
		 
		 ///////////////// 하단 페이징 번호 10개씩만 출력 처리 .////////////////////
		 
		 // 한번에 표시할 페이징 번호의 갯수
		 int pageNum_cnt = 10;
		 
		 // 10개씩 하단에 표시되는 페이지 번호 중 마지막 번호 (*** 1차 계산 ***)
		 // 마지막 페이징 번호 = ((올림)(현재 페이지 번호 / 한번에 표시할 페이지 번호의 갯수)) * 한번에 표시할 페이지 번호의 갯수
		 int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
		 
		 // 표시되는 페이징 번호중 첫번째 번호
		 int startPageNum = endPageNum - (pageNum_cnt - 1);
		 
		 //하단 페이징 마지막 번호 재계산  (*** 2차 계산 ***)
		 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
		 	// 현재 페이징 번호(1차계산)가  하단에 표시될 마지막번호(2차계산)보다 크면 2차로 계산한 값을 넣어준다.
		 if(endPageNum > endPageNum_tmp) {
		  endPageNum = endPageNum_tmp;
		 }
		 
		 //이전버튼을 만들기 위한.. 
		 boolean prev = startPageNum == 1 ? false : true;
		 //다음버튼 만들기 위한..
		 boolean next = endPageNum * pageNum_cnt >= count ? false : true;
		 
		 List list = null; 
		 list = service.listPage(displayPost, postNum);
		 model.addAttribute("list", list);   
		 model.addAttribute("pageNum", pageNum);
		 
		 // 시작 및 끝 번호
		 model.addAttribute("startPageNum", startPageNum);
		 model.addAttribute("endPageNum", endPageNum);
		 
		 // 이전 및 다음
		 model.addAttribute("prev", prev);
		 model.addAttribute("next", next);
		 
		 // 현재페이지
		 model.addAttribute("select", num);
		 */
		 }

}
