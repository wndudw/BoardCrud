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

}
