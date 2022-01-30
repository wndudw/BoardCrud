package com.board.service;

import java.util.List;

import com.board.domain.BoardVo;

public interface BoardService {

	public List<BoardVo> list() throws Exception;
}
