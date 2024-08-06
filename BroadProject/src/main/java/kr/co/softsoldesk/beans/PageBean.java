package kr.co.softsoldesk.beans;

public class PageBean {
	
	private int min;//최소페이지 번호
	private int max;//최대페이지 번호
	private int prevPage;//이전버튼의 페이지 번호
	private int nextPage;//다음버튼의 페이지 번호
	private int pageCnt;//전체 페이지 개수
	private int currentPage;//현재 페이지 번호
	
	public PageBean(int contentCnt, int currentPage, int ContentPageCnt, int paginationCnt) {
		
		/*
		  contentCnt: 전체글 개수
		  currentPage: 혅재 페이지 번호
		  ContentPageCnt: 페이지당 글의 개수(10)
		  paginationCnt: 한번에 표시할 페이지 버튼의 개수(예컨데 게시글이 21개면, 3개의 링크 버튼)
		 */
		
		this.currentPage = currentPage;
		
		pageCnt = contentCnt/ContentPageCnt; //게시들 수에 따른 페이지의 수
		//게시글이 1개: 0페이지, +1 필요
		//게시글이 9개: 1페이지, +1 필요
		//게시글이 10개: 1페이지
		//게시글이 11개: 1페이지, +1 필요
		//게시글이 20개: 2페이지
		
		if(contentCnt % ContentPageCnt > 0) {//pageCnt가 10의 단위가 아니면
			
			pageCnt++;
		}
		min = ((currentPage-1)/ContentPageCnt)* ContentPageCnt + 1;
		//1페이지: ((1-1)/10) + 1 => 1
		//2페이지: ((2-1)/10) + 1 => 1
		//11페이지: ((11-1)/10) + 1 => 2
		//1~10 페이지의 최소 페이지는 1
		//11~19 페이지의 최소 페이지는 11
		
		max = min + paginationCnt -1;//최대페이지
		//1페이지: 1+10-1 => 10 [1]이 보이는 화면에서는 [10]이 최대 페이지
		//11페이지: 11 + 10 - 1 => 20 [11]이 보이는 화면에서는 [20]이 최대 페이지
		
		if(max > pageCnt) {
			
			max = pageCnt;
		}
		//전체 페이지가 3페이지까지 있으면 max 페이지는 3페이지로 제한([1] [2] [3])
		//전체 페이지가 11페이지까지 있으면 max 페이지는  11페이지로 제한([11])
		
		prevPage = min -1;
		//이전 페이지는 푀소 페이지에서 -1/ <이전 [11] => [10]
		nextPage = max +1;
		
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
		//전체 페이지가 15(pageCnt)페이지일 때, nextPage는 16페이지여야
	}
	
	
	
	public int getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	
	
	

}
