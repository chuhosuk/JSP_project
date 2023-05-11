package pj.mvc.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pj.mvc.jsp.dto.ProductDTO;

public class ProductDAOImpl implements ProductDAO{
	
	DataSource dataSource;
	
	//싱글톤방식
	static ProductDAOImpl instance = new ProductDAOImpl();
	public  static ProductDAOImpl getInstance() {
		if(instance== null) {
			instance= new ProductDAOImpl();
		}
		return instance;
	}
	
	private ProductDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/jsp_pj_126_chs");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	// 상품추가
	@Override
	public int productInsert(ProductDTO dto) {
		System.out.println("DAO - productInsert");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql ="INSERT INTO mvc_product_tbl(pdNo, pdName, pdBrand, pdImg, pdCategory, pdContent, pdPrice, pdQuantity, pdStatus, pdIndate) " 
					+ " VALUES((SELECT NVL(MAX(pdNo)+1,1) FROM mvc_product_tbl), ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPdName());
			pstmt.setString(2, dto.getPdBrand());
			pstmt.setString(3, dto.getPdImg());
			pstmt.setString(4, dto.getPdCategory());
			pstmt.setString(5, dto.getPdContent());
			pstmt.setInt(6, dto.getPdPrice());
			pstmt.setInt(7, dto.getPdQuantity());
			pstmt.setString(8, dto.getPdStatus());
			
			insertCnt = pstmt.executeUpdate();
			
			System.out.println("SQL insertCnt => " + insertCnt);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return insertCnt;
	}
	
	// 상품 갯수
	@Override
	public int productCnt() {
		System.out.println("DAO - productCnt");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		int selectCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql ="SELECT COUNT(*) as cnt FROM mvc_product_tbl";
			
			pstmt= conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				selectCnt=rs.getInt("cnt");
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return selectCnt;
	}
	
	// 상품목록
	@Override
	public List<ProductDTO> productList(int start, int end) {
		System.out.println("DAO - productList");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		List<ProductDTO> list = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT  * " + 
					"   FROM( " + 
					"	SELECT A.*, rownum AS rn " +  //rn : 일련번호 메기기
					"  	  FROM (SELECT * FROM mvc_product_tbl " + 
					"  			ORDER BY pdNo DESC) A " + 
					"   ) " + 
					"  WHERE  rn BETWEEN ? AND ?";
			System.out.println("sql:" + sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//1. list 생성
				list = new ArrayList<ProductDTO>();
				do {
					//2.dto 생성
					ProductDTO dto = new ProductDTO();
					
					//3.dto에 1건의 rs 게시글 정보를 담는다
					dto.setPdNo(rs.getInt("pdNo"));
					dto.setPdName(rs.getString("pdName"));
					dto.setPdImg(rs.getString("pdImg"));
					dto.setPdBrand(rs.getString("pdBrand"));
					dto.setPdCategory(rs.getString("pdCategory"));
					dto.setPdContent(rs.getString("pdContent"));
					dto.setPdPrice(rs.getInt("pdPrice"));
					dto.setPdQuantity(rs.getInt("pdQuantity"));
					dto.setPdStatus(rs.getString("pdStatus"));
					dto.setPdIndate(rs.getDate("pdIndate"));
					
					//4.list에 dto를 추가한다.
					list.add(dto);
					
				}while(rs.next());
				
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null)pstmt.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 상품상세페이지
	@Override
	public ProductDTO productDetail(int pdNo) {
		System.out.println("DAO - productDetail");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ProductDTO dto = new ProductDTO();
		
		try {
			conn = dataSource.getConnection();
			
			String sql ="SELECT * FROM mvc_product_tbl "
						+"WHERE pdNo = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//3.dto에 1건의 rs 상품정보를 담는다
				dto.setPdNo(rs.getInt("pdNo"));
				dto.setPdName(rs.getString("pdName"));
				dto.setPdBrand(rs.getString("pdBrand"));
				dto.setPdImg(rs.getString("pdImg"));
				dto.setPdCategory(rs.getString("pdCategory"));
				dto.setPdContent(rs.getString("pdContent"));
				dto.setPdPrice(rs.getInt("pdPrice"));
				dto.setPdQuantity(rs.getInt("pdQuantity"));
				dto.setPdStatus(rs.getString("pdStatus"));
				dto.setPdIndate(rs.getDate("pdIndate"));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null)pstmt.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	//카테고리
	@Override
	public List<ProductDTO> category() {
		System.out.println("DAO - category");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		List<ProductDTO> list = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT pdCategory " + 
					"	 , pdImg  " + 
					"	 , pdBrand " + 
					"FROM mvc_product_tbl " + 
					"GROUP BY pdCategory, pdBrand, pdImg " + 
					"ORDER BY pdCategory ";
			System.out.println("sql:" + sql);
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//1. list 생성
				list = new ArrayList<ProductDTO>();
				do {
					//2.dto 생성
					ProductDTO dto = new ProductDTO();
					
					//3.dto에 1건의 rs 게시글 정보를 담는다
					dto.setPdImg(rs.getString("pdImg"));
					dto.setPdBrand(rs.getString("pdBrand"));
					dto.setPdCategory(rs.getString("pdCategory"));
					dto.setPdContent(rs.getString("pdContent"));
					dto.setPdPrice(rs.getInt("pdPrice"));
					dto.setPdQuantity(rs.getInt("pdQuantity"));
					dto.setPdStatus(rs.getString("pdStatus"));
					dto.setPdIndate(rs.getDate("pdIndate"));
					
					//4.list에 dto를 추가한다.
					list.add(dto);
					
				}while(rs.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null)pstmt.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 상품수정
	@Override
	public int productUpdate(ProductDTO dto) {
		
		System.out.println("DAO - productUpdate");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCnt= 0;
		try {
			conn = dataSource.getConnection();
			String sql ="UPDATE mvc_product_tbl SET pdName=?, pdBrand=?, pdImg=?, pdCategory=?, pdContent=?, pdPrice=?, pdQuantity=?, pdStatus=? "
						+ "WHERE pdNo=?";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPdName());
			pstmt.setString(2, dto.getPdBrand());
			pstmt.setString(3, dto.getPdImg());
			pstmt.setString(4, dto.getPdCategory());
			pstmt.setString(5, dto.getPdContent());
			pstmt.setInt(6, dto.getPdPrice() );
			pstmt.setInt(7, dto.getPdQuantity());
			pstmt.setString(8, dto.getPdStatus());
			pstmt.setInt(9, dto.getPdNo());
			
			updateCnt = pstmt.executeUpdate();
			System.out.println("updateCnt" + updateCnt);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
			
			return updateCnt;
		}
	
	// 상품삭제
	@Override
	public int productDelete(int pdNo) {
		System.out.println("DAO - productDelete");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteCnt = 0;
		try {
			conn = dataSource.getConnection();
			String sql ="DELETE mvc_product_tbl "
						+"WHERE pdNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdNo);
			
			deleteCnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteCnt;
	}

	


}
