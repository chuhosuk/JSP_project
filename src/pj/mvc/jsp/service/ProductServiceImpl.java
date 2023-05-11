package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import page.Paging;
import pj.mvc.jsp.dao.BoardDAOImpl;
import pj.mvc.jsp.dao.ProductDAO;
import pj.mvc.jsp.dao.ProductDAOImpl;
import pj.mvc.jsp.dto.ProductDTO;

public class ProductServiceImpl implements ProductService{

	//상품등록 처리
	@Override
	public void ProductAddAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("서비스 - ProductAddAction()");
		
		//3단계. 화면으로부터 입력받은 값을 받는다.
		ProductDTO dto = new ProductDTO();
		
		dto.setPdName(req.getParameter("pdName"));
		dto.setPdBrand(req.getParameter("pdBrand"));
		
		//플젝명/ upload 경로
		//imageUploadHandler 클래스에서 파일명을 setAttribute()로 넘겼으므로
		String p_img1 = "/jsp_pj_126_chs/resources/upload/" + req.getAttribute("fileName"); //플젝명/upload/파일경로
		System.out.println("dto.getPdImg() : "+ p_img1);
		dto.setPdImg(p_img1);
		dto.setPdCategory(req.getParameter("pdCategory"));
		dto.setPdContent(req.getParameter("pdContent"));
		dto.setPdPrice(Integer.parseInt(req.getParameter("pdPrice")));
		dto.setPdQuantity(Integer.parseInt(req.getParameter("pdQuantity")));
		dto.setPdStatus(req.getParameter("pdStatus"));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		ProductDAO dao = ProductDAOImpl.getInstance();
		
				
		//5단계. 상품정보 insert
		int insertCnt= dao.productInsert(dto);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("insertCnt", insertCnt);
	}
	
	//상품목록
	@Override
	public void ProductListAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - ProductListAction()");
		
		//3단계. 화면으로부터 입력받은 값을 받는다.
		String pageNum = req.getParameter("pageNum");
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		//5-1단계. 상품카운트
		int total = dao.productCnt();
		System.out.println("total : " + total);
		
		Paging paging = new Paging(pageNum);
		paging.setTotatlCount(total);
		
		int start = paging.getStartRow();
		int end =paging.getEndRow();
		
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		//5-2단계. 상품목록
		List<ProductDTO> list =dao.productList(start, end);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
		
	}
	
	//상품상세페이지
	@Override
	public void ProductDetailAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - ProductDetailAction()");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		int pdNo = Integer.parseInt(req.getParameter("pdNo"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		//5단계. 상세페이지
		ProductDTO dto =dao.productDetail(pdNo);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("dto", dto);
		
	}
	
	//카테고리
	@Override
	public void category(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			
		System.out.println("서비스 - category()");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		//5-1단계. 상품카운트
		
		//5-2단계. 상품목록
		List<ProductDTO> list = dao.category();
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("list", list);
		
	}
	
	// 상품수정처리
	@Override
	public void ProductUpdateAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - ProductUpdateAction()");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		//화면값 받아오기(hidden)
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int hiddenPdNo = Integer.parseInt(req.getParameter("hiddenPdNo"));
		String hiddenPdImg = req.getParameter("hiddenPdImg"); 		//기존 이미지
		String uploadPdImg = (String)req.getAttribute("fileName"); 	//업로드 파일명
		
		System.out.println("hiddenPdImg" +hiddenPdImg);
		System.out.println("uploadPdImg" +uploadPdImg);
		
		ProductDTO dto = new ProductDTO();
		dto.setPdNo(hiddenPdNo);
		dto.setPdName(req.getParameter("pdName"));
		dto.setPdBrand(req.getParameter("pdBrand"));

		String strPdImg = "";
		//이미지를 수정하지 않았을 때
		if(uploadPdImg == null) {
			strPdImg = hiddenPdImg;
		}
		//이비지를 수정했을 때
		else{
			strPdImg = "/jsp_pj_126/resources/upload/" + uploadPdImg;
		}
		System.out.println("이미지 =>" +strPdImg);
		dto.setPdImg(strPdImg);
		dto.setPdCategory(req.getParameter("pdCategory"));
		dto.setPdContent(req.getParameter("pdContent"));
		dto.setPdPrice(Integer.parseInt(req.getParameter("pdPrice")));
		dto.setPdQuantity(Integer.parseInt(req.getParameter("pdQuantity")));
		dto.setPdStatus(req.getParameter("pdStatus"));
		
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		
		//5단계. 상품 수정
		int updateCnt = dao.productUpdate(dto);
		
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("updateCnt", updateCnt);
		
		
	}
	
	//상품삭제처리
	@Override
	public void ProductDeleteAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - ProductDeleteAction()");
		//3단계. 화면으로부터 입력받은 값을 받는다.
		int pdNo = Integer.parseInt(req.getParameter("pdNo"));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		//5단계. 상세페이지
		int deleteCnt =dao.productDelete(pdNo);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("deleteCnt", deleteCnt);
		
		
	}

	

}
