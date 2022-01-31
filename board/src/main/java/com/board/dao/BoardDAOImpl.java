package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVo;

@Repository
public class BoardDAOImpl implements BoardDAO {
		
		@Inject
		private SqlSession sql;
		
		//boardMapper의 매퍼이름과 같아야함!
		private static String namespace = "com.board.mappers.board";

		 // 게시물 목록
		 @Override
		 public List list() throws Exception { 
		  
		/*
		 * mapper는 각 매퍼를 구분하는 namesapce와 매퍼 하위에 쿼리문을 묶은 id로 구분된다.
		 * dao에서 매퍼를 접근할때 namespace와 id모두 필요 이때, 이 둘을 구분하는게 . 이다
		 */
		  return sql.selectList(namespace + ".list");
		 }

		 
		//게시글 작성
		@Override
		public void write(BoardVo vo) throws Exception {
			sql.insert(namespace + ".write", vo);
		}

		
		//게시글 조회
		public BoardVo view(int bno) throws Exception {
			
			return sql.selectOne(namespace + ".view", bno);
		}

		//게시글 수정
		@Override
		public void modify(BoardVo vo) throws Exception {
			sql.update(namespace + ".modify", vo);
			
		}


		//게시글 삭제
		@Override
		public void delete(int bno) throws Exception {
			sql.delete(namespace + ".delete", bno);
		}

}
