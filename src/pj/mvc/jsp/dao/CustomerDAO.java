package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.BuyDTO;
import pj.mvc.jsp.dto.CartDTO;
import pj.mvc.jsp.dto.CustomerDTO;
import pj.mvc.jsp.dto.OrderDTO;
import pj.mvc.jsp.dto.ProductDTO;
import pj.mvc.jsp.dto.SaleDTO;

public interface CustomerDAO {

	
	// ID 중복확인 처리
	public int idCheck(String strId);

	// 회원가입 처리
	public int insertCustomer(CustomerDTO dto);
	
	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
	public int idPasswordChk(String strId, String strPassword);
	
	// 회원정보 인증 및 탈퇴처리
	public int deleteCustomer(String strId);
	
	// 회원정보 인증 및 상세페이지
	public CustomerDTO getCustomerDetail(String strId);
	
	// 회원정보 수정 처리
	public int updateCustomer(CustomerDTO dto);
	
	// 카트추가
	public int cartAdd(CartDTO dto);
	
	// 카트목록
	public List<CartDTO> cartList(int start, int end, String id);
	
	// 카트삭제
	public int cartDelete(int cartNo);
	
	//상품확인
	public CartDTO productConfirm(int pdNo);
	
	//카트갯수
	public int cartCnt();
	
	//고객용 회원정보
	public List<CustomerDTO> customerInfo(String strId);
	
	//관리자용 회원정보
	public List<CustomerDTO> adminCustomerInfo();
	
	//상품구매(바로구매)
	public int buy(BuyDTO dto);
	
	//상품구매 (카트구매)
	public int buyCart(int cartNo, String strId);
	
	//구매확인 (바로)
	public BuyDTO shopConfirm(int cartNo);
	
	//카트구매확인
	public BuyDTO cartConfirm(int cartNo, String id);
	
	//구매카운트
	public int orderCnt();
	
	//주문목록
	public List<BuyDTO> orderList(int start, int end);
	
	//구매취소(환불요청)
	public int refund(int shopNo);
	
	//나의쇼핑정보
	public List<BuyDTO> myOrder(int start, int end, String id);
	
	//주문승인
	public int orderConfirm(int shopNo);
	
	//주문승인목록
	public List<OrderDTO> orderConfirmList(int start, int end);
	
	//주문취소
	public int orderCancel(int shopNo);
	
	//결산
	public List<SaleDTO> sale ();
	
	//결산승인
	public void saleConfirm(int shopNo);
	
	//결산취소
	public void saleRefund(int shopNo);
	
	//주문승인취소
	public int orderConfirmCancel(int shopNo);
	
			
		
	


}
