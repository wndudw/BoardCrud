package com.board.dao;

import java.util.List;

import com.board.domain.BoardVo;

public interface BoardDAO {

	//게시글 목록
	public List<BoardVo> list() throws Exception;
	
	//게시글 작성
	public void write(BoardVo vo) throws Exception;
	
	//게시글 조회
	public BoardVo view(int bno) throws Exception;
	
	//게시글 수정
	public void modify(BoardVo vo) throws Exception;
	
	//게시글 삭제
	public void delete(int bno) throws Exception;
	
	//페이징 처리
	//public List<BoardVo> listPage(Criteria cri) throws Exception;
	
	//게시글 총 갯수
	public int count() throws Exception;
	
	//목록 +페이징 처리
	public List listPage(int displayPost, int postNum) throws Exception;
	
}
