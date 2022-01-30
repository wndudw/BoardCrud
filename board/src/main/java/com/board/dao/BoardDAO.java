package com.board.dao;

import java.util.List;

import com.board.domain.BoardVo;

public interface BoardDAO {

	public List<BoardVo> list() throws Exception;
	
}
