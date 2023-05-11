package pj.mvc.jsp.dto;

import java.sql.Date;

public class CartDTO {
	
	private int cartNo;
	private String id;
	private int pdNo; 			//상품번호
	private String pdName;		//상품명
	private String pdBrand;		//상품브랜드
	private String pdImg;		//상품이미지
	private String pdCategory;	//상품카테고리
	private String pdContent;	//상품설명
	private int pdPrice;		//상품가격
	private int pdQuantity;		//상품등록수량
	private String pdStatus;	//상품상태코드
	private Date pdIndate;		//상품등록일
	
	public CartDTO() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	
	public int getPdNo() {
		return pdNo;
	}
	
	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}
	
	public String getPdName() {
		return pdName;
	}
	
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	
	public String getPdBrand() {
		return pdBrand;
	}
	
	public void setPdBrand(String pdBrand) {
		this.pdBrand = pdBrand;
	}
	public String getPdImg() {
		return pdImg;
	}
	
	public void setPdImg(String pdImg) {
		this.pdImg = pdImg;
	}
	
	public String getPdCategory() {
		return pdCategory;
	}
	
	public void setPdCategory(String pdCategory) {
		this.pdCategory = pdCategory;
	}
	
	public String getPdContent() {
		return pdContent;
	}
	
	public void setPdContent(String pdContent) {
		this.pdContent = pdContent;
	}
	
	public int getPdPrice() {
		return pdPrice;
	}
	
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	
	public int getPdQuantity() {
		return pdQuantity;
	}
	
	public void setPdQuantity(int pdQuantity) {
		this.pdQuantity = pdQuantity;
	}
	
	public String getPdStatus() {
		return pdStatus;
	}
	
	public void setPdStatus(String pdStatus) {
		this.pdStatus = pdStatus;
	}
	
	public Date getPdIndate() {
		return pdIndate;
	}
	
	public void setPdIndate(Date date) {
		this.pdIndate = date;
	}
	
	@Override
	public String toString() {
		return "CartDTO [cartNo=" + cartNo + ", id=" + id + ", pdNo=" + pdNo + ", pdName=" + pdName + ", pdBrand="
				+ pdBrand + ", pdImg=" + pdImg + ", pdCategory=" + pdCategory + ", pdContent=" + pdContent
				+ ", pdPrice=" + pdPrice + ", pdQuantity=" + pdQuantity + ", pdStatus=" + pdStatus + ", pdIndate="
				+ pdIndate + "]";
	}
	
	
}	
	
	



