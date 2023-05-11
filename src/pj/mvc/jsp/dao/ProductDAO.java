package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.ProductDTO;

public interface ProductDAO {


	// 상품등록
	public int productInsert(ProductDTO dto);
	
	// 상품 갯수
	public int productCnt();
	
	// 상품목록
	public List<ProductDTO> productList(int start, int end);
	
	// 상품상세페이지
	public ProductDTO productDetail(int pdNo);
	
	//카테고리
	public List<ProductDTO> category();
	
	// 상품수정
	public int productUpdate(ProductDTO dto);
	
	// 상품삭제
	public int productDelete(int pdNo);
	
	
		
		
	
}
