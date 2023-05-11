package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.CustomerServiceImpl;
import pj.mvc.jsp.service.ProductServiceImpl;
import pj.mvc.jsp.util.ImageUploadHandler;
@WebServlet("*.do")
//@MultipartConfig(location="D:\\Dev126\\workspace\\jsp_pj_126_chs\\WebContent\\resources\\upload",
//fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String IMG_UPLOAD_DIR="D:\\Dev126\\workspace\\jsp_pj_126_chs\\WebContent\\resources\\upload";
	
    //1단계. 웹브라우저가 전송한 HTTP 요청 받음   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("doGet~~");
		action(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}
	
	public void action(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//한글안깨지게처리
		req.setCharacterEncoding("UTF-8");
		String viewPage= ""; 
		ImageUploadHandler uploader; 
		CustomerServiceImpl service = new CustomerServiceImpl();
		ProductServiceImpl product = new ProductServiceImpl();
		
		// 2단계. 요청분석
		//http://localhost/jsp_pj_126/*.do
		
		String uri = req.getRequestURI(); 		   // /jsp_pj_126/*.do
		String contextPath = req.getContextPath(); // /jsp_pj_126
		String url = uri.substring(contextPath.length());  //    /*.do
		
		// 첫페이지
		if(url.equals("/*.do") || url.equals("/main.do")) {
			System.out.println("[url => /main.do]");
			
			product.ProductListAction(req, res);
			viewPage = "common/main.jsp";
			
			
		}
		//---------- [회원가입]----------
		
		// 회원가입 화면
		else if(url.equals("/join.do")) {
			System.out.println("[url => /join.do]");
			
			viewPage = "customer/join/join.jsp";
			
			
		}
		// 아이디 중복확인 처리
		else if(url.equals("/idConfirmAction.do")) {
			System.out.println("[url => /idConfirmAction.do]");
			
			service.idConfirmAction(req, res);
			viewPage = "customer/join/idConfirmAction.jsp";
		}
			
		// 회원가입 처리
		else if(url.equals("/joinAction.do")) {
			System.out.println("[url => /joinAction.do]");
			service.signInAction(req, res);
			
			
			
			viewPage = "customer/join/joinAction.jsp";
			
		}
		
		//-------------[로그인]--------------
		// 로그인 화면
		else if(url.equals("/login.do")) {
			System.out.println("[url => /login.do]");
			
			viewPage = "customer/login/login.jsp";
		}
		
		// 로그인 처리
		else if (url.equals("/loginAction.do")) {
			System.out.println("[url => /loginAction.do]");
			service.loginAction(req, res);
			
			viewPage = "customer/login/loginAction.jsp";
		}
		
		// 로그인 처리
		else if (url.equals("/admin_loginAction.do")) {
			System.out.println("[url => /admin_loginAction.do]");
			service.loginAction(req, res);
			
			viewPage = "customer/login/admin_loginAction.jsp";
		}
		
		// 로그아웃 처리
		else if(url.equals("/logout.do")) {
			System.out.println("[url => /logout.do]");
			
			req.getSession().invalidate();  //세션삭제
			
			product.ProductListAction(req, res);
			viewPage ="common/main.jsp";
		}
		
		
		//-------------[회원탈퇴]--------------
		// 회원탈퇴 화면
		else if(url.equals("/deleteCustomer.do")) {
			System.out.println("[url => /deleteCustomer.do]");
			
			viewPage ="customer/mypage/customerInfo/deleteCustomer.jsp";
		}
		
		// 회원탈퇴 처리
		else if(url.equals("/deleteCustomerAction.do")) {
			System.out.println("[url => /deleteCustomerAction.do]");
			
			service.deleteCustomerAction(req, res);
			viewPage ="customer/mypage/customerInfo/deleteCustomerAction.jsp";
		}
		
		//-------------[회원수정]--------------
		// 회원정보 수정화면
		else if(url.equals("/modifyCustomer.do")) {
			System.out.println("[url => /modifyCustomer.do]");
			
			viewPage ="customer/mypage/customerInfo/modifyCustomer.jsp";
		}
		
		
		// 회원정보 상세페이지
		else if(url.equals("/modifyDetailAction.do")) {
			System.out.println("[url => /modifyDetailAction.do]");
			
			service.modifyDetailAction(req, res);
			viewPage ="customer/mypage/customerInfo/modifyDetailAction.jsp";
		}
		
		
		// 회원정보 수정처리
		else if(url.equals("/modifyCustomerAction.do")) {
			System.out.println("[url => /modifyCustomerAction.do]");
			service.modifyCustomerAction(req, res);
			
			viewPage ="customer/mypage/customerInfo/modifyCustomerAction.jsp";
		}
		
		//제품 상세페이지
		else if(url.equals("/customer_detail.do")) {
			System.out.println("[url => /modifyCustomerAction.do]");
			
			product.ProductDetailAction(req, res);
			viewPage ="customer/product/customer_detail.jsp";
		}
		//카테고리
		else if(url.equals("/category.do")) {
			System.out.println("[url => /category.do]");
			
			product.category(req, res);
			viewPage ="customer/product/category.jsp";
		}
		
		//카트추가
		else if (url.equals("/cartAddAction.do")) {
			System.out.println("[url => /customer_addAction.do]");
			
			/*
			 * //추가 : 서비스 호출전에 추가 String contentType= req.getContentType(); if(contentType
			 * != null && contentType.toLowerCase().startsWith("multipart/")) { uploader =
			 * new ImageUploadHandler(); uploader.setUploadPath(IMG_UPLOAD_DIR); //img 경로
			 * uploader.imageUpload(req, res); }
			 */
			
			service.CartAddAction(req, res);
			
			viewPage ="customer/cart/cartAddAction.jsp";
		}
		
		//카트리스트
		else if (url.equals("/cartList.do")) {
			System.out.println("[url => /customer_cartlist.do]");
			
			
			service.CartListAction(req, res);
			viewPage ="customer/cart/cartList.jsp";
		}
		
		//카트삭제
		else if (url.equals("/cartDeleteAction.do")) {
			System.out.println("[url => /cartDeleteAction.do]");
					
					
			service.CartDeleteAction(req, res);
			viewPage ="customer/cart/cartDeleteAction.jsp";
		}
		
		// 고객용 회원정보
		else if (url.equals("/customerInfo.do")) {
			System.out.println("[url => /customerInfo.do]");
					
					
			service.customerInfo(req, res);
			viewPage ="customer/mypage/customerInfo/customerInfo.jsp";
		}
		// 관리자용 회원정보
		else if (url.equals("/admin_customerInfo.do")) {
			System.out.println("[url => /customerInfo.do]");
					
					
			service.adminCustomerInfo(req, res);
			viewPage ="admin/ad_product/ad_customerInfo.jsp";
		}
		
		// 상품구매(바로구매)
		else if (url.equals("/buyAction.do")) {
			System.out.println("[url => /buyAction.do]");
			
			service.BuyAction(req, res);		
			
			viewPage ="customer/buy/buyAction.jsp";
		}
		
		// 상품구매(카트구매)
		else if (url.equals("/buyCartAction.do")) {
			System.out.println("[url => /buyCartAction.do]");
			
			service.BuyCartAction(req, res);		
			
			viewPage ="customer/buy/buyAction.jsp";
		}
		// 주문목록
		else if (url.equals("/orderList.do")) {
			System.out.println("[url => /orderList.do]");
			
			service.orderList(req, res);		
			
			viewPage ="admin/ad_product/ad_order_list.jsp";
		}
		//나의 쇼핑정보
		else if (url.equals("/myOrder.do")) {
			System.out.println("[url => /myOrder.do]");
			
			service.myOrder(req, res);
			
			viewPage ="customer/buy/myOrder.jsp";
		}
		
		//환불요청
		else if (url.equals("/refund.do")) {
			System.out.println("[url => /refund.do]");
			
			service.refund(req, res);
			
			viewPage ="customer/buy/refundAction.jsp";
		}
		
		//주문승인
		else if (url.equals("/orderConfirmAction.do")) {
			System.out.println("[url => /orderConfirmAction.do]");
			
			service.orderConfirm(req, res);
			
			viewPage ="admin/ad_product/ad_order_cofirmAction.jsp";
		}
		
		//주문승인목록
		else if (url.equals("/orderConfirmList.do")) {
			System.out.println("[url => /orderConfirmList.do]");
			
			service.orderConfirmList(req, res);
			
			viewPage ="admin/ad_product/ad_order_confirmList.jsp";
		}
		
		//주문취소
		else if (url.equals("/orderCancel.do")) {
			System.out.println("[url => /orderCancel.do]");
			
			service.orderCancel(req, res);
			
			viewPage ="admin/ad_product/ad_order_cancel.jsp";
		}
		
		//결산
		else if (url.equals("/sale.do")) {
			System.out.println("[url => /sale.do]");
			
			service.sale(req, res);
			
			viewPage ="admin/ad_product/sale.jsp";
		}
		//결산취소
		else if (url.equals("/saleRefundAction.do")) {
			System.out.println("[url => /saleRefundAction.do]");
			
			service.saleRefundAction(req, res);
			
			viewPage ="admin/ad_product/ad_order_saleRefundAction.jsp";
		}
		
		
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후 , 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, res);
	}

}
