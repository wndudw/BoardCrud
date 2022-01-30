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
		  
		  return sql.selectList(namespace + ".list");
		 }

}
