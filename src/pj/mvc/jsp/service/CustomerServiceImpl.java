package pj.mvc.jsp.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import page.Paging;
import pj.mvc.jsp.dao.CustomerDAO;
import pj.mvc.jsp.dao.CustomerDAOImpl;
import pj.mvc.jsp.dao.ProductDAO;
import pj.mvc.jsp.dao.ProductDAOImpl;
import pj.mvc.jsp.dto.BuyDTO;
import pj.mvc.jsp.dto.CartDTO;
import pj.mvc.jsp.dto.CustomerDTO;
import pj.mvc.jsp.dto.OrderDTO;
import pj.mvc.jsp.dto.ProductDTO;
import pj.mvc.jsp.dto.SaleDTO;

public class CustomerServiceImpl implements CustomerService{
	
	// ID 중복확인 처리
	@Override
	public void idConfirmAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("서비스 - ID 중복확인 처리");
		
		//3단계. 화면으로부터 입력받은 값을 받는다.
		String strId = req.getParameter("id");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		//5단계. 중복확인 처리
		int selectCnt = dao.idCheck(strId);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("id",strId);
		req.setAttribute("selectCnt", selectCnt);
		
	}

	// 회원가입 처리
	@Override
	public void signInAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		System.out.println("서비스 -회원가입처리");
		
		//3단계. 화면으로부터 입력받은 값을 받는다.
		// DTO 생성
		CustomerDTO dto =new CustomerDTO();
		dto.setId(req.getParameter("id"));
		dto.setPassword(req.getParameter("password"));
		dto.setName(req.getParameter("name"));
		dto.setBirthday(Date.valueOf(req.getParameter("birthday")));
		dto.setAddress(req.getParameter("address"));
		
		
		
		//hp은 필수가 아니므로 null 값이 들어올 수 있으므로 값이 존재할때만 받아온다.
		String hp = "";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		
		if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 +"-"+ hp2 + "-" + hp3;
		}
		dto.setHp(hp);
		
		String email="";
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		email = email1 + "@" + email2 ; 
		dto.setEmail(email);
		
		//regDate는 입력값이 없으면 default로 sysdate를 설정했음, 아래문장은 직접 값을 설정하는 경우
		dto.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao  = CustomerDAOImpl.getInstance();
		
		//5단계. 회원가입 처리
		int insertCnt = dao.insertCustomer(dto);
		
		System.out.println("insertCnt" + insertCnt);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("insertCnt", insertCnt);
		
	}

	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
	@Override
	public void loginAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		System.out.println("서비스 - 로그인 처리");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		String strId  = req.getParameter("id");
		String strPassword = req.getParameter("password");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		//5단계. 로그인 처리
		int selectCnt = dao.idPasswordChk(strId, strPassword);
		
		//로그인 성공시 세션ID를 설정
		if (selectCnt ==1) {	
			
			//6단계. jsp로 처리 결과 전달
//			HttpSession session = req.getSession();
//			session.setAttribute("sessionID", strId);
			req.getSession().setAttribute("sessionID", strId);
		}
		
		//6단계. jsp로 처리 결과 전달
		//req.setAttribute("selectCnt", selectCnt); 세션으로 로그인 여부판단
	}
	

	// 회원정보 인증 및 탈퇴처리
	@Override
	public void deleteCustomerAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - 회원탈퇴 처리");
		
		//3단계. 화면, 세션으로부터 입력받은 값을 받는다.
		String strId = (String)req.getSession().getAttribute("sessionID");
		String strPassword = req.getParameter("password");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();

		//5단계. 회원정보 인증 및 탈퇴처리
		//5-1단계. 회원정보 인증 
		int selectCnt = dao.idPasswordChk(strId, strPassword);
		
		int deleteCnt = 0;
		//5-2단계. 탈퇴처리 selectCnt = 1이면 탈퇴 -> deleteCustomer
		if(selectCnt == 1) {
			deleteCnt =dao.deleteCustomer(strId);
		}	
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("deleteCnt", deleteCnt);
		//req.setAttribute("selectCnt", selectCnt);

	}

	// 회원정보 인증 및 상세페이지
	@Override
	public void modifyDetailAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		System.out.println("서비스 - 회원정보 인증 및 상세페이지");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		
		String strId = (String)req.getSession().getAttribute("sessionID");
		String strPassword = req.getParameter("password");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();

		//5단계. 회원정보 인증 및 상세페이지
		//5-1단계. 회원정보 인증 
		int selectCnt = dao.idPasswordChk(strId, strPassword);
		
		CustomerDTO dto = null;
		//5-2단계. 상세페이지
		if(selectCnt == 1) {
			dto = dao.getCustomerDetail(strId);
			
		}
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("dto", dto);
		req.setAttribute("selectCnt", selectCnt);
		
	}

	// 회원정보 수정 처리
	@Override
	public void modifyCustomerAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - 회원정보 수정 처리");
		
		//3-1단계. dto생성
		CustomerDTO dto = new CustomerDTO();
		
		//3-2단계. 화면으로부터 입력받은 값을 받아서 dto에 담는다
		dto.setId((String)req.getSession().getAttribute("sessionID"));
		dto.setPassword(req.getParameter("password"));
		dto.setName(req.getParameter("name"));
		dto.setBirthday(Date.valueOf(req.getParameter("birthday")));
		dto.setAddress(req.getParameter("address"));
		
		String hp = "";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		
		if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 +"-"+ hp2 + "-" + hp3;
		}
		dto.setHp(hp);
		
		String email="";
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		email = email1 + "@" + email2 ; 
		dto.setEmail(email);
		
	
		dto.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		//5단계. 수정처리
		int updateCnt = dao.updateCustomer(dto);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("updateCnt", updateCnt);
	}

	//카트추가
	@Override
	public void CartAddAction(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("서비스 - 카트 추가");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		int pdNo= Integer.parseInt(req.getParameter("pdNo"));
		System.out.println("pdno" +pdNo);
		int ctQuantity= Integer.parseInt(req.getParameter("ctQuantity"));
		System.out.println("ctQuantity" +ctQuantity);		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();
				
		// 5-1 단계. 상품정보 DTO에 담기
		CartDTO dto = dao.productConfirm(pdNo);
				
		// 5-2 단계. 상품정보 계정 dto에 담기
		dto.setId((String)(req.getSession().getAttribute("sessionID")));
		dto.setPdQuantity(ctQuantity);
	    // 5-2 단계. 담은 상품 장바구니에 추가하기
		 int insertCart = dao.cartAdd(dto);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("insertCart", insertCart);
		
	}

	//카트목록
	@Override
	public void CartListAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		  System.out.println("서비스 - CartList");
	      
	      // 3단계. 화면으로부터 입력받은 값을 받는다.
	      String pageNum = req.getParameter("pageNum");
	      System.out.println(pageNum);
	      String id = (String)req.getSession().getAttribute("sessionID");
	      System.out.println(id);
	      
	      // 4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
	      CustomerDAO dao = CustomerDAOImpl.getInstance();
	      
	      // 5-1단계. 상품카운트
	      int total = dao.cartCnt();
	      System.out.println("total : " + total);
	      
	      Paging paging = new Paging(pageNum);
	      paging.setTotatlCount(total);
	      
	      int start = paging.getStartRow();
	      int end = paging.getEndRow();
	      
	      System.out.println("start : " + start);
	      System.out.println("end : " + end);
	      
	      // 5-2단계. 상품목록
	      List<CartDTO>list = new ArrayList<CartDTO>();
	      list = dao.cartList(start, end, id);
	      
	      // 6단계. jsp로 처리 결과 전달
	      req.setAttribute("list", list);
	      req.setAttribute("paging", paging);
		
	}
	

	//카트삭제
	@Override
	public void CartDeleteAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("서비스 - CartDelete");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		String stCartNo = req.getParameter("cartNo");
		
		String[] arrCartNo = stCartNo.split(",");
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		//5단계. 상세페이지
		int deleteCart =0;
		
		for(int i = 0 ; i<arrCartNo.length; i++) {
	         int cartNo = Integer.parseInt(arrCartNo[i]);
	         System.out.println(cartNo);
	         deleteCart = dao.cartDelete(cartNo);
	     }
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("deleteCart", deleteCart);
		
	}

	// 고객용 회원정보
	@Override
	public void customerInfo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//3단계. 화면, 세션으로부터 입력받은 값을 받는다.
		String strId = (String) req.getSession().getAttribute("sessionID");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();

		//5단계. 회원정보 인증 및 상세페이지
		List<CustomerDTO>list = dao.customerInfo(strId);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("list", list);
				
	}
	//관리자용 회원정보
	@Override
	public void adminCustomerInfo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//3단계. 화면, 세션으로부터 입력받은 값을 받는다.
		String strId = (String) req.getSession().getAttribute("sessionID");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();

		//5단계. 회원정보 인증 및 상세페이지
		List<CustomerDTO>list = dao.adminCustomerInfo();
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("list", list);
				
	}
	
	// 상품구매(바로구매)
	@Override
	public void BuyAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		//3단계. 화면, 세션으로부터 입력받은 값을 받는다.
		System.out.println();
		int ctQuantity =Integer.parseInt(req.getParameter("ctQuantity"));
		int pdNo = Integer.parseInt(req.getParameter("pdNo"));
		
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		//int deleteCart = dao.cartDelete(pdNo);
		BuyDTO dto = dao.shopConfirm(pdNo);
						
		// 5-2 단계. 상품정보 계정 dto에 담기
		dto.setBuyQuantity(ctQuantity);
		dto.setId((String)(req.getSession().getAttribute("sessionID")));
	    // 5-2 단계. 담은 상품 장바구니에 추가하기
		int insertShop = dao.buy(dto);
				
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("insertShop", insertShop);
		
		
	}
	
	//장바구니 구매
	@Override
	public void BuyCartAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		//3단계. 화면, 세션으로부터 입력받은 값을 받는다.
		String stCartNo = req.getParameter("cartNo");
		String[] arrcartNo = stCartNo.split(",");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		
	
		
		int insertShop = 0;
		String strId = (String) req.getSession().getAttribute("sessionID");
		for(int i = 0 ; i<arrcartNo.length; i++) {
	         int cartNo = Integer.parseInt(arrcartNo[i]);
	         System.out.println(cartNo);
	         insertShop = dao.buyCart(cartNo, strId);
	     }
		
		int deleteCart = 0;
		for(int i = 0 ; i<arrcartNo.length; i++) {
	         int cartNo = Integer.parseInt(arrcartNo[i]);
	         System.out.println(cartNo);
	         deleteCart = dao.cartDelete(cartNo);
	     }
		
		
						
		// 5-2 단계. 상품정보 계정 dto에 담기
		
	    // 5-2 단계. 담은 상품 장바구니에 추가하기
		//int insertShop = dao.buyCart(dto);
				
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("insertShop", insertShop);
		
		
	}

	// 주문목록(관리자)
	@Override
	public void orderList(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
			System.out.println("서비스 - orderList");
	      
	      // 3단계. 화면으로부터 입력받은 값을 받는다.
	      String pageNum = req.getParameter("pageNum");
	      System.out.println(pageNum);
	      // 4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
	      CustomerDAO dao = CustomerDAOImpl.getInstance();
	      
	      // 5-1단계. 상품카운트
	      int total = dao.orderCnt();
	      System.out.println("total : " + total);
	      
	      Paging paging = new Paging(pageNum);
	      paging.setTotatlCount(total);
	      
	      int start = paging.getStartRow();
	      int end = paging.getEndRow();
	      
	      System.out.println("start : " + start);
	      System.out.println("end : " + end);
	      
	      // 5-2단계. 상품목록
	      List<BuyDTO>list = new ArrayList<BuyDTO>();
	      list = dao.orderList(start, end);
	      
	      // 6단계. jsp로 처리 결과 전달
	      req.setAttribute("list", list);
	      req.setAttribute("paging", paging);
		
	}

	// 구매삭제(취소요청)
	@Override
	public void refund(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - refund");
		int shopNo =Integer.parseInt(req.getParameter("shopNo"));
		System.out.println("shopNo" + shopNo);
		String strId = (String) req.getSession().getAttribute("sessionID");
		
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		int refundCnt =dao.refund(shopNo);
		
		req.setAttribute("refundCnt", refundCnt);
		
		
		
	}
	//나의 쇼핑정보
	@Override
	public void myOrder(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		 System.out.println("서비스 - myOrder");
	     
		 // 3단계. 화면으로부터 입력받은 값을 받는다.
	      String pageNum = req.getParameter("pageNum");
	      String id = (String)req.getSession().getAttribute("sessionID");
	      
	      // 4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
	      CustomerDAO dao = CustomerDAOImpl.getInstance();
	      
	      // 5-1단계. 상품카운트
	      int total = dao.orderCnt();
	      System.out.println("total : " + total);
	      
	      Paging paging = new Paging(pageNum);
	      paging.setTotatlCount(total);
	      
	      int start = paging.getStartRow();
	      int end = paging.getEndRow();
	      
	      System.out.println("start : " + start);
	      System.out.println("end : " + end);
	      
	      // 5-2단계. 상품목록
	      List<BuyDTO>list = new ArrayList<BuyDTO>();
	      list = dao.myOrder(start, end, id);
	      
	      // 6단계. jsp로 처리 결과 전달
	      req.setAttribute("list", list);
	      req.setAttribute("paging", paging);
     
	}

	//주문승인
	@Override
	public void orderConfirm(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		System.out.println("서비스 - 주문승인");
		
		String stShopNo = (String)req.getParameter("shopNo");
		String[] arrShopNo = stShopNo.split(",");
		
		int orderConfirm = 0;
		for(int i = 0 ; i<arrShopNo.length; i++) {
	         int shopNo = Integer.parseInt(arrShopNo[i]);
	         System.out.println(shopNo);
	         
	         CustomerDAO dao = CustomerDAOImpl.getInstance();
	         
	         
	         dao.saleConfirm(shopNo);
	         orderConfirm = dao.orderConfirm(shopNo);
	         dao.orderCancel(shopNo);
	         
	         //승인후 주문목록에서 삭제
	         
	         
	         req.setAttribute("orderConfirm", orderConfirm);
	     }
		
	}
	
	// 주문승인목록(관리자)
	@Override
	public void orderConfirmList(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		System.out.println("서비스 - orderConfirmList");
	      
	      // 3단계. 화면으로부터 입력받은 값을 받는다.
	      String pageNum = req.getParameter("pageNum");
	      System.out.println(pageNum);
	      // 4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
	      CustomerDAO dao = CustomerDAOImpl.getInstance();
	      
	      // 5-1단계. 상품카운트
	      int total = dao.orderCnt();
	      System.out.println("total : " + total);
	      
	      Paging paging = new Paging(pageNum);
	      paging.setTotatlCount(total);
	      
	      int start = paging.getStartRow();
	      int end = paging.getEndRow();
	      
	      System.out.println("start : " + start);
	      System.out.println("end : " + end);
	      
	      // 5-2단계. 상품목록
	      List<OrderDTO>list = new ArrayList<OrderDTO>();
	      list = dao.orderConfirmList(start, end);
	      
	      // 6단계. jsp로 처리 결과 전달
	      req.setAttribute("list", list);
	      req.setAttribute("paging", paging);
		
	}

	//주문취소
	@Override
	public void orderCancel(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - 주문취소");
		
		String stShopNo = (String)req.getParameter("shopNo");
		String[] arrShopNo = stShopNo.split(",");
		
		int orderCancel = 0;
		for(int i = 0 ; i<arrShopNo.length; i++) {
	         int shopNo = Integer.parseInt(arrShopNo[i]);
	         System.out.println(shopNo);
	         
	         CustomerDAO dao = CustomerDAOImpl.getInstance();
	         
	         orderCancel = dao.orderCancel(shopNo);
	         
	         req.setAttribute("orderCancel", orderCancel);
	     }
		
	}

	//결산
	@Override
	public void sale(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("서비스 - 결산");
		
		CustomerDAO dao = CustomerDAOImpl.getInstance();
		List<SaleDTO>sale = new ArrayList<SaleDTO>();
		
		sale =dao.sale();
		
		System.out.println(sale);
		req.setAttribute("sale", sale);
	}
	
	//결산취소
	@Override
	public void saleRefundAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		System.out.println("서비스 - 결산취소");
		

		String stShopNo = (String)req.getParameter("shopNo");
		String[] arrShopNo = stShopNo.split(",");
		
		int orderConfirmCancel = 0;
		for(int i = 0 ; i<arrShopNo.length; i++) {
	         int shopNo = Integer.parseInt(arrShopNo[i]);
	         System.out.println(shopNo);
	         
	         CustomerDAO dao = CustomerDAOImpl.getInstance();
	         
	         
	         dao.saleRefund(shopNo);
	         
	         //결산취소후 주문승인 취소
	         orderConfirmCancel = dao.orderConfirmCancel(shopNo);
	         
	         req.setAttribute("orderConfirmCancel", orderConfirmCancel);
	     }
		
	}

}
