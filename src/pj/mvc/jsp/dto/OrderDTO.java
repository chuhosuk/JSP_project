package pj.mvc.jsp.dto;

import java.sql.Date;

public class OrderDTO {
	
	private String id;
	private int shopNo;
	private int pdNo;
	private String pdName;
	private String pdBrand;
	private int salePrice;
	private int buyQuantity;
	private String deliverySt;
	private Date orderDate;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public String getDeliverySt() {
		return deliverySt;
	}
	public void setDeliverySt(String deliverySt) {
		this.deliverySt = deliverySt;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	
	public int getShopNo() {
		return shopNo;
	}
	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}
	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", shopNo=" + shopNo + ", pdNo=" + pdNo + ", pdName=" + pdName + ", pdBrand="
				+ pdBrand + ", salePrice=" + salePrice + ", buyQuantity=" + buyQuantity + ", deliverySt=" + deliverySt
				+ ", orderDate=" + orderDate + "]";
	}
	

	
	
	
}	
	
	



