package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.ProductServiceImpl;
import pj.mvc.jsp.util.ImageUploadHandler;


@WebServlet("*.pd")
@MultipartConfig(location="D:\\Dev126\\workspace\\jsp_pj_126_chs\\WebContent\\resources\\upload",
fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String IMG_UPLOAD_DIR="D:\\Dev126\\workspace\\jsp_pj_126_chs\\WebContent\\resources\\upload";


	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		action(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}
	
	public void action(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//한글 안께지게 처리
		req.setCharacterEncoding("UTF-8");
		String viewPage= "";
		ImageUploadHandler uploader; 
		ProductServiceImpl service = new ProductServiceImpl();
		
		// 2단계. 요청분석
		String uri = req.getRequestURI(); 		   // /jsp_pj_126/*.do
		String contextPath = req.getContextPath(); // /jsp_pj_126
		String url = uri.substring(contextPath.length());  //    /*.do
		
		// -------------------------상품정보----------------------------
		
		// 상품등록 화면
		if(url.equals("/*.pd") || url.equals("/ad_product_add.pd")) {
			System.out.println("[url => /ad_product_add.pd]");
		
			viewPage= "admin/ad_product/ad_product_add.jsp";
		
		}
		
		//상품등록 처리
		else if(url.equals("/ad_product_addAction.pd")) {
			System.out.println("[url => /ad_product_addAction.pd]");
			
			//추가 : 서비스 호출전에 추가
			String contentType= req.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); //img 경로
				uploader.imageUpload(req, res);
			}
			
			service.ProductAddAction(req, res);
			
			viewPage= "admin/ad_product/ad_product_addAction.jsp";
		}
		
		// 상품목록
		else if(url.equals("/ad_product_list.pd")) {
			System.out.println("[url => /ad_product_list.pd]");
			
			service.ProductListAction(req, res);
			
			viewPage= "admin/ad_product/ad_product_list.jsp";
		}
		
		// 상품상세페이지 (목록의 수정버튼 클릭시)
		else if(url.equals("/ad_product_detailAction.pd")) {
			System.out.println("[url => /ad_product_detailAction.pd]");
			
			service.ProductDetailAction(req, res);
			viewPage= "admin/ad_product/ad_product_detailAction.jsp";
		}
		
		
		// 상품수정처리 
		else if(url.equals("/ad_product_updateAction.pd")) {
			System.out.println("[url => /ad_product_updateAction.pd]");
			
			//추가 : 서비스 호출전에 추가
			String contentType= req.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); //img 경로
				uploader.imageUpload(req, res);
			}
			
			service.ProductUpdateAction(req, res);
			viewPage= "admin/ad_product/ad_product_updateAction.jsp";
		}
		
		
		// 상품삭제 (목록의 삭제버튼 클릭시)
		
		else if(url.equals("/ad_product_deleteAction.pd")) {
			System.out.println("[url => /ad_product_deleteAction.pd]");
			
			service.ProductDeleteAction(req, res);
			viewPage= "admin/ad_product/ad_product_deleteAction.jsp";
		}
		
		// 주문목록
		else if(url.equals("/.pd")) {
			System.out.println("[url => /ad_product_deleteAction.pd]");
			
			service.ProductDeleteAction(req, res);
			viewPage= "admin/ad_product/ad_product_deleteAction.jsp";
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, res);
			
		
	}

}
