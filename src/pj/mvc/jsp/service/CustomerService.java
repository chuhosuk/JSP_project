package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CustomerService {

	// ID 중복확인 처리
	public void idConfirmAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException;
	
	
	// 회원가입 처리
	public void signInAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException;
	
	
	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
	public void loginAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException;
	
	
	// 회원정보 인증 및 탈퇴처리
	public void deleteCustomerAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException;
	
	
	// 회원정보 인증 및 상세페이지
	public void modifyDetailAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException;
	
	
	// 회원정보 수정 처리
	public void modifyCustomerAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException;
	
	// 카트추가
	public void CartAddAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 카트목록
	public void CartListAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 카트삭제
	public void CartDeleteAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 고객용 회원정보
	public void customerInfo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 관리자용 회원정보
	public void adminCustomerInfo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 상품구매(바로구매)
	public void BuyAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 상품구매(바로구매)
	public void BuyCartAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 구매삭제(환불요청)
	public void refund(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	// 주문목록
	public void orderList(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;

	//나의 쇼핑정보
	public void myOrder(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException ;
	
	//주문승인
	public void orderConfirm(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException ;
	
	//주문승인목록
	public void orderConfirmList(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException ;
	
	//주문취소
	public void orderCancel(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException ;
	
	//결산
	public void sale(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException ;
	
	//결산취소
	public void saleRefundAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException ;
	

	
	
		
	
	
}
