package kr.itedu.boardmvc.action;

public class BoardVO {
	private int bid, btype;
	private String btitle, bcontent, bregdate, bchgdate;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getBtype() {
		return btype;
	}
	public void setBtype(int btype) {
		this.btype = btype;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBregdate() {
		return bregdate;
	}
	public void setBregdate(String bregdate) {
		this.bregdate = bregdate;
	}
	public String getBchgdate() {
		return bchgdate;
	}
	public void setBchgdate(String bchgdate) {
		this.bchgdate = bchgdate;
	}
	
}
