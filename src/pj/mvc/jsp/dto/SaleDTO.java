package pj.mvc.jsp.dto;


public class SaleDTO {
	
	private int pdNo;
	private String pdName;
	private int saleAmount;
	private int saleTotal;
	
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
	public int getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}
	public int getSaleTotal() {
		return saleTotal;
	}
	public void setSaleTotal(int saleTotal) {
		this.saleTotal = saleTotal;
	}
	@Override
	public String toString() {
		return "OrderDTO [pdNo=" + pdNo + ", pdName=" + pdName + ", saleAmount=" + saleAmount + ", saleTotal="
				+ saleTotal + "]";
	}

	
	
	
}	
	
	



