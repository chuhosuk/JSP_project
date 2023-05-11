package pj.mvc.jsp.dto;

import java.sql.Date;

public class BuyDTO {
	
	private String id;
	private int shopNo;
	private int pdNo; 			//상품번호
	private String pdName;
	private String pdBrand;		//상품브랜드
	private int pdPrice;		//상품가격
	private int buyQuantity;		//상품등록수량
	private Date orderDate;
	private String deliverySt;
	
	public BuyDTO() {}
	
	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliverySt() {
		return deliverySt;
	}

	public void setDeliverySt(String deliverySt) {
		this.deliverySt = deliverySt;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public int getPdNo() {
		return pdNo;
	}
	
	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}
	
	public String getPdBrand() {
		return pdBrand;
	}
	
	public void setPdBrand(String pdBrand) {
		this.pdBrand = pdBrand;
	}
	
	public int getPdPrice() {
		return pdPrice;
	}
	
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	
	public int getBuyQuantity() {
		return buyQuantity;
	}
	
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BuyDTO [id=" + id + ", shopNo=" + shopNo + ", pdNo=" + pdNo + ", pdName=" + pdName + ", pdBrand="
				+ pdBrand + ", pdPrice=" + pdPrice + ", buyQuantity=" + buyQuantity + ", orderDate=" + orderDate
				+ ", deliverySt=" + deliverySt + "]";
	}
	
	
}	
	
	



