package com.board.domain;

public class Page {

	// 현재 페이지 번호
	private int num;

	// 게시글 총 갯수
	private int count;

	// 한 페이지에 출력할 게시글 갯수
	private int postNum = 10;

	// 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	private int pageNum;

	// 출력할 게시글
	private int displayPost;

	// 한번에 표시할 페이징 번호의 갯수
	private int pageNumCnt = 10;

	// 표시되는 페이지 번호 중 마지막 번호
	private int endPageNum;

	// 표시되는 페이지 번호 중 첫번째 번호
	private int startPageNum;

	// 다음 이전 표시 여부
	private boolean prev;
	private boolean next;

	public void setNum(int num) {
		this.num = num;
	}

	public void setCount(int count) {
		this.count = count;
		
		dateCalc();
	}

	public int getCount() {
		return count;
	}

	public int getPostNum() {
		return postNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getDisplayPost() {
		return displayPost;
	}

	public int getPageNumCnt() {
		return pageNumCnt;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public boolean getPrev() {
		return prev;
	}

	public boolean getNext() {
		return next;
	}

	private void dateCalc() {

		// 10개씩 하단에 표시되는 페이지 번호 중 마지막 번호 (*** 1차 계산 ***)
		// 마지막 페이징 번호 = ((올림)(현재 페이지 번호 / 한번에 표시할 페이지 번호의 갯수)) * 한번에 표시할 페이지 번호의 갯수
		endPageNum = (int) (Math.ceil((double) num / (double) pageNumCnt) * pageNumCnt);

		// 표시되는 페이징 번호중 첫번째 번호
		startPageNum = endPageNum - (pageNumCnt - 1);

		// 하단 페이징 마지막 번호 재계산 (*** 2차 계산 ***)
		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNumCnt));

		// 현재 페이징 번호(1차계산)가 하단에 표시될 마지막번호(2차계산)보다 크면 2차로 계산한 값을 넣어준다.
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		// 이전버튼을 만들기 위한..
		prev = startPageNum == 1 ? false : true;
		// 다음버튼 만들기 위한..
		next = endPageNum * pageNumCnt >= count ? false : true;
		
		 //현재 페이지 기준으로 10개의 게시글 구하기.
		 // ((현재페이지번호 - 1) * 출력할 게시글 갯수 ) + 1
		 displayPost = ((num -1) * postNum) + 1;
		 // 화면 이동마다 10개씩 데이터 출력을 위해 다시 postNum을 초기화 후 페이징번호 * 10을 구함.
		 postNum = 0;
		 postNum = 10*(num);
	}

}
