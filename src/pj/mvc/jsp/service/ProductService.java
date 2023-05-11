package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProductService {

	//상품등록
	public void ProductAddAction(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException;
	
	
	//상품목록
	public void ProductListAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	//카테고리
	public void category(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException;
	
	//상품상세페이지
	public void ProductDetailAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	
	//상품수정
	public void ProductUpdateAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	
	//상품삭제
	public void ProductDeleteAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	 
	

}
