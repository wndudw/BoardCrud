package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	
	//게시글 목록
	@Override
	public List<BoardVo> list() throws Exception {
		
		return dao.list();
	}
	
	//게시글 작성
	@Override
	public void write(BoardVo vo) throws Exception {
		dao.write(vo);
	
	}

	//게시글 조회
	@Override
	public BoardVo view(int bno) throws Exception {
		
		return dao.view(bno);
	}

	//게시글 수정
	@Override
	public void modify(BoardVo vo) throws Exception {
		
		dao.modify(vo);
		
	}

	//게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		
		dao.delete(bno);
	}

//	@Override
//	public List<BoardVo> listPage(Criteria cri) throws Exception {
//		
//		return dao.listPage(cri);
//	}

	@Override
	public int count() throws Exception {
		return dao.count();
	}

	@Override
	public List listPage(int displayPost, int postNum) throws Exception {
		return dao.listPage(displayPost, postNum);
	}

}
