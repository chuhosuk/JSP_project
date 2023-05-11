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

import pj.mvc.jsp.dto.BuyDTO;
import pj.mvc.jsp.dto.CartDTO;
import pj.mvc.jsp.dto.CustomerDTO;
import pj.mvc.jsp.dto.OrderDTO;
import pj.mvc.jsp.dto.SaleDTO;

public class CustomerDAOImpl implements CustomerDAO{
	
	// 커넥션 풀 객체를 보관
	DataSource dataSource;
	
	//싱글톤 생성
	static CustomerDAOImpl instance = new CustomerDAOImpl();
	public static CustomerDAOImpl getInstance() {
		if(instance == null) {
			instance = new CustomerDAOImpl();
		}
		return instance;
	}
	//디폴트 생성자
	private CustomerDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/jsp_pj_126_chs");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	// 중복확인처리
	@Override
	public int idCheck(String strId) {
		System.out.println("dao - 아이디 중복확인 처리");
		int selectCnt =0;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_customer_tbl WHERE id=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				selectCnt= 1;
			}
			
			System.out.println("selectCnt" + selectCnt);
			
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
	
	
	// 회원가입처리
	@Override
	public int insertCustomer(CustomerDTO dto) {
		System.out.println("dao - 회원가입처리");
		
		int insertCnt =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_customer_tbl(id,password,name,birthday,address,hp,email,regDate)"
					+"values(?,?,?,?,?,?,?,?)";
			
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setDate(4, dto.getBirthday());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getHp());
			pstmt.setString(7, dto.getEmail());
			pstmt.setTimestamp(8, dto.getRegDate());
			
			insertCnt =pstmt.executeUpdate(); //성공:1 실패:0
			
			System.out.println("insertCnt" + insertCnt);
			
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

	@Override
	public int idPasswordChk(String strId, String strPassword) {
		int selectCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="SELECT * FROM mvc_customer_tbl "
					+ "WHERE id =? "
					+ "AND password=?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			pstmt.setString(2,strPassword);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				selectCnt =1;
			}
			
			System.out.println("selectCnt :" + selectCnt);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return selectCnt;
		
	}

	@Override
	public int deleteCustomer(String strId) {
		System.out.println("dao - 회원탈퇴처리");
		int deleteCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="delete FROM mvc_customer_tbl WHERE id =?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			
			deleteCnt =pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteCnt;
	}

	//회원정보 상세페이지
	@Override
	public CustomerDTO getCustomerDetail(String strId) {
		System.out.println("dao - 회원정보 상세페이지");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//1 CustomerDTO  바구니 생성
		CustomerDTO dto = new CustomerDTO();
		
		try {
			conn = dataSource.getConnection();
			
			//2. strId(로그인 화면에서 입력받은 세션id)와 일치하는 데이터가 있는지 조회
			String sql ="SELECT * FROM mvc_customer_tbl WHERE id =?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			
			rs =pstmt.executeQuery();
			
			//3 ResultSet에 id와 일치하는 한사람의 회원정보가 존재하면
			if(rs.next()) {
				// ResultSet을 읽어서 CustomerDTO에 setter로 담는다.
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getDate("birthday"));
				dto.setAddress(rs.getString("address"));
				dto.setHp(rs.getString("hp"));
				dto.setEmail(rs.getString("email"));
				dto.setRegDate(rs.getTimestamp("regDate"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}

	@Override
	public int updateCustomer(CustomerDTO dto) {
		
		System.out.println("dao - 회원수정처리");
		int updateCnt=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="UPDATE mvc_customer_tbl "
					+"SET password=?, name=?, birthday=?, address=?, hp=?, email=?, regDate=? "
					+ "WHERE id=?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,dto.getPassword());
			pstmt.setString(2,dto.getName());
			pstmt.setDate(3,dto.getBirthday());
			pstmt.setString(4,dto.getAddress());
			pstmt.setString(5,dto.getHp());
			pstmt.setString(6,dto.getEmail());
			pstmt.setTimestamp(7, dto.getRegDate() );
			pstmt.setString(8,dto.getId());
			
			updateCnt =pstmt.executeUpdate(); //성공=1,실패=0
			System.out.println("updateCnt" +updateCnt);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}
	
		// 카트추가
		@Override
		public int cartAdd(CartDTO dto) {
        System.out.println("dao - 카트추가");
	         
	         int insertCart = 0;
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         
	         try {
	            conn = dataSource.getConnection();
	            String sql = "INSERT INTO mvc_cart_tbl(cartNo,id,pdNo,pdName,pdBrand,pdImg,pdCategory,pdContent,pdPrice,pdStatus,ctQuantity) "
	                      + "VALUES ((SELECT NVL(MAX(cartNo)+1,1) FROM mvc_cart_tbl),?,?,?,?,?,?,?,?,?,?)";
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, dto.getId());
	            pstmt.setInt(2,dto.getPdNo());
	            pstmt.setString(3, dto.getPdName());
	            pstmt.setString(4,dto.getPdBrand());
	            pstmt.setString(5, dto.getPdImg());
	            pstmt.setString(6, dto.getPdCategory());
	            pstmt.setString(7, dto.getPdContent());
	            pstmt.setInt(8, dto.getPdPrice());
	            pstmt.setString(9, dto.getPdStatus());
	            pstmt.setInt(10, dto.getPdQuantity());
	            
	            
	            insertCart = pstmt.executeUpdate();  // 성공 : 1; 실패 : 0;
	            
	            System.out.println("insertCnt : " + insertCart);
	            
	         }catch(SQLException e) {
	            e.printStackTrace();
	         }finally {
	            try {
	               if(pstmt != null)pstmt.close();
	               if(conn != null) conn.close();
	            }catch(SQLException e) {
	               e.printStackTrace();
	            }
	         }
	         return insertCart;
		}

	// 카트목록
	@Override
	public List<CartDTO> cartList(int start, int end, String id) {
	     System.out.println("DAO - CartList");
	     
	     Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     List<CartDTO>list = null;
	     
	     try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT * " + 
	              "  FROM( " + 
	              "   SELECT A.*, rownum AS rn " + // rn : 일련번호 매기기
	              "      FROM (SELECT * FROM mvc_cart_tbl " + 
	              "           WHERE id = ? ORDER BY cartNo DESC) A   " + 
	              "  ) " + 
	              " WHERE rn BETWEEN ? AND ?";
	        System.out.println("sql: " + sql);
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setInt(2, start);
	        pstmt.setInt(3, end);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	           
	           // 1. list 생성
	           list = new ArrayList<CartDTO>();
	            
	           do {
	              // 2. dto 생성
	        	   CartDTO dto = new CartDTO();
	              
	              // 3. dto에 1건의 rs 게시글 정보를 담는다.
	              dto.setCartNo(rs.getInt("cartNo"));
	              dto.setId(rs.getString("id"));
	              dto.setPdNo(rs.getInt("pdNo"));
	              dto.setPdName(rs.getString("pdName"));
	              dto.setPdBrand(rs.getString("pdBrand"));
	              dto.setPdImg(rs.getString("pdImg"));
	              dto.setPdCategory(rs.getString("pdCategory"));
	              dto.setPdContent(rs.getString("pdContent"));
	              dto.setPdPrice(rs.getInt("pdPrice"));
	              dto.setPdQuantity(rs.getInt("ctQuantity"));
	              dto.setPdStatus(rs.getString("pdStatus"));
	              dto.setPdIndate(rs.getDate("pdIndate"));
	              
	              // 4. list에 dto를 추가한다.
	              list.add(dto);
	              
	           }while(rs.next());
	        }
	        
	     }catch(SQLException e) {
	        e.printStackTrace();
	     }finally {
	        try {
	           if(rs != null)rs.close();
	           if(pstmt != null)pstmt.close();
	           if(conn != null) conn.close();
	        }catch(SQLException e) {
	           e.printStackTrace();
	        }
	     }
	     
	     // 5. list 리턴
	     return list;
	}

	@Override
	public CartDTO productConfirm(int pdNo) {
		 System.out.println("dao - 상품 확인 후 담기 ");
         Connection conn = null;
         PreparedStatement pstmt = null;         
         ResultSet rs = null;                // SELECT문일때 반드시 작성
         CartDTO dto = new CartDTO();  // 바구니 생성
         
         try {
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM mvc_product_tbl WHERE pdNo = ?";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1,pdNo);
            
            rs = pstmt.executeQuery();  
            
            if(rs.next()) {
               dto.setPdNo(rs.getInt("pdNo"));
               dto.setPdName(rs.getString("pdName"));
               dto.setPdBrand(rs.getString("pdBrand"));
               dto.setPdImg(rs.getString("pdImg"));
               dto.setPdCategory(rs.getString("pdCategory"));
               dto.setPdContent(rs.getString("pdContent"));
               dto.setPdPrice(rs.getInt("pdPrice"));
               dto.setPdStatus(rs.getString("pdStatus"));
            }
            
         }catch(SQLException e) {
            e.printStackTrace();
         }finally {
            try {
               if(rs != null)rs.close();
               if(pstmt != null)pstmt.close();
               if(conn != null) conn.close();
            }catch(SQLException e) {
               e.printStackTrace();
            }
         }
         return dto;
	}
	
	//카트 갯수
	@Override
	public int cartCnt() {
		System.out.println("DAO - cartCnt");
         
         int cartCnt = 0;
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         
         try {
            conn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) as cnt FROM mvc_cart_tbl";
            
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
               cartCnt = rs.getInt("cnt");
            }
         }catch(SQLException e) {
            e.printStackTrace();
         }finally {
            try {
               if(rs != null)rs.close();
               if(pstmt != null)pstmt.close();
               if(conn != null) conn.close();
            }catch(SQLException e) {
               e.printStackTrace();
            }
         }
         return cartCnt;
	}

	// 카트삭제
	@Override
	public int cartDelete(int cartNo) {
		System.out.println("DAO - cartDelete");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteCart = 0;
		try {
			conn = dataSource.getConnection();
			String sql ="DELETE mvc_cart_tbl "
						+"WHERE cartNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			
			deleteCart = pstmt.executeUpdate();
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
		return deleteCart;
	}
	
	@Override
	//고객용 회원정보
	public List<CustomerDTO> customerInfo(String strId) {
		System.out.println("dao - 고객용회원정보");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomerDTO> list =null;
		
		try {
			conn = dataSource.getConnection();
			
			//2. strId(로그인 화면에서 입력받은 세션id)와 일치하는 데이터가 있는지 조회
			String sql ="SELECT * FROM mvc_customer_tbl WHERE id =?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			
			rs =pstmt.executeQuery();
			
			//3 ResultSet에 id와 일치하는 한사람의 회원정보가 존재하면
			if(rs.next()) {
				// ResultSet을 읽어서 CustomerDTO에 setter로 담는다.
				list = new ArrayList<CustomerDTO>();
				do {
					CustomerDTO dto = new CustomerDTO(); 
					dto.setId(rs.getString("id"));
					dto.setPassword(rs.getString("password"));
					dto.setName(rs.getString("name"));
					dto.setBirthday(rs.getDate("birthday"));
					dto.setAddress(rs.getString("address"));
					dto.setHp(rs.getString("hp"));
					dto.setEmail(rs.getString("email"));
					dto.setRegDate(rs.getTimestamp("regDate"));
					
					list.add(dto);
					
				}while(rs.next());
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	//관리자용 회원정보
	public List<CustomerDTO> adminCustomerInfo() {
		System.out.println("dao - 관리자용회원정보");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomerDTO> list =null;
		
		try {
			conn = dataSource.getConnection();
			
			//2. strId(로그인 화면에서 입력받은 세션id)와 일치하는 데이터가 있는지 조회
			String sql ="SELECT * FROM mvc_customer_tbl";
			
			pstmt= conn.prepareStatement(sql);
			
			rs =pstmt.executeQuery();
			
			//3 ResultSet에 id와 일치하는 한사람의 회원정보가 존재하면
			if(rs.next()) {
				// ResultSet을 읽어서 CustomerDTO에 setter로 담는다.
				list = new ArrayList<CustomerDTO>();
				do {
					CustomerDTO dto = new CustomerDTO(); 
					dto.setId(rs.getString("id"));
					dto.setPassword(rs.getString("password"));
					dto.setName(rs.getString("name"));
					dto.setBirthday(rs.getDate("birthday"));
					dto.setAddress(rs.getString("address"));
					dto.setHp(rs.getString("hp"));
					dto.setEmail(rs.getString("email"));
					dto.setRegDate(rs.getTimestamp("regDate"));
					
					list.add(dto);
					
				}while(rs.next());
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	//구매확인(바로)
	public BuyDTO shopConfirm(int cartNo) {
		System.out.println("dao - 상품 확인 후 담기 ");
        Connection conn = null;
        PreparedStatement pstmt = null;         
        ResultSet rs = null;                // SELECT문일때 반드시 작성
        BuyDTO dto = new BuyDTO();  // 바구니 생성
        
        try {
           conn = dataSource.getConnection();
           String sql = "SELECT * FROM mvc_product_tbl WHERE pdNo = ?";
           
           pstmt = conn.prepareStatement(sql);
           
           pstmt.setInt(1,cartNo);
           
           rs = pstmt.executeQuery();  
           
           if(rs.next()) {
              dto.setPdNo(rs.getInt("pdNo"));
              dto.setPdName(rs.getString("pdName"));
              dto.setPdBrand(rs.getString("pdBrand"));
              dto.setPdPrice(rs.getInt("pdPrice"));
              dto.setBuyQuantity(rs.getInt("pdQuantity"));
           }
           
        }catch(SQLException e) {
           e.printStackTrace();
        }finally {
           try {
              if(rs != null)rs.close();
              if(pstmt != null)pstmt.close();
              if(conn != null) conn.close();
           }catch(SQLException e) {
              e.printStackTrace();
           }
        }
        return dto;
		
	}
	
	@Override
	//구매확인(카트)
	public BuyDTO cartConfirm(int cartNo, String id) {
		System.out.println("dao - 카트 확인 후 담기 ");
        Connection conn = null;
        PreparedStatement pstmt = null;         
        ResultSet rs = null;                // SELECT문일때 반드시 작성
        BuyDTO dto = new BuyDTO();  // 바구니 생성
        
        try {
           conn = dataSource.getConnection();
           String sql = "SELECT * FROM mvc_cart_tbl WHERE cartNo = ? "
           		+ "AND id = ? ";
           
           pstmt = conn.prepareStatement(sql);
           
           pstmt.setInt(1, cartNo);
           pstmt.setString(1, id);
           
           rs = pstmt.executeQuery();  
           
           if(rs.next()) {
              dto.setPdNo(rs.getInt("pdNo"));
              dto.setPdName(rs.getString("pdName"));
              dto.setPdBrand(rs.getString("pdBrand"));
              dto.setPdPrice(rs.getInt("pdPrice"));
              dto.setBuyQuantity(rs.getInt("pdQuantity"));
           }
           
        }catch(SQLException e) {
           e.printStackTrace();
        }finally {
           try {
              if(rs != null)rs.close();
              if(pstmt != null)pstmt.close();
              if(conn != null) conn.close();
           }catch(SQLException e) {
              e.printStackTrace();
           }
        }
        return dto;
		
	}
	
	//상품구매(바로구매)
	@Override
	public int buy(BuyDTO dto) {
		
		 System.out.println("dao - 바로구매");
         
         int insertShop = 0;
         Connection conn = null;
         PreparedStatement pstmt = null;
         
         try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO mvc_buy_tbl(shopNo,id,pdNo,pdName,pdBrand,pdPrice,buyQuantity,deliverySt,orderDate) "
                      + "VALUES ((SELECT NVL(MAX(shopNo)+1,1) FROM mvc_buy_tbl),?,?,?,?,?,?,'주문완료',sysdate)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getId());
            pstmt.setInt(2,dto.getPdNo());
            pstmt.setString(3, dto.getPdName());
            pstmt.setString(4,dto.getPdBrand());
            pstmt.setInt(5, dto.getPdPrice());
            pstmt.setInt(6, dto.getBuyQuantity());
            
            
            insertShop = pstmt.executeUpdate();  // 성공 : 1; 실패 : 0;
            
            System.out.println("insertShop : " + insertShop);
            
         }catch(SQLException e) {
            e.printStackTrace();
         }finally {
            try {
               if(pstmt != null)pstmt.close();
               if(conn != null) conn.close();
            }catch(SQLException e) {
               e.printStackTrace();
            }
         }
         return insertShop;
	}
	
	//장바구니구매 
	@Override
	public int buyCart(int cartNo, String strId) {
		
		 System.out.println("dao - 카트구매");
         System.out.println("cartNo" + cartNo);
         System.out.println("strId" + strId);
         int insertShop = 0;
         Connection conn = null;
         PreparedStatement pstmt = null;
         
         try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO mvc_buy_tbl(shopNo, id, pdNo, pdName, pdBrand, pdPrice, buyQuantity, deliverySt, orderDate) " + 
            		"VALUES ( " + 
            		"(SELECT NVL(MAX(shopNo)+1,1) FROM mvc_buy_tbl), " + 
            		"(SELECT DISTINCT id FROM mvc_cart_tbl WHERE id = ? ), " + 
            		"(SELECT pdNo FROM mvc_cart_tbl WHERE cartNo = ? ), " + 
            		"(SELECT pdName FROM mvc_cart_tbl WHERE cartNo = ? ), " + 
            		"(SELECT pdBrand FROM mvc_cart_tbl WHERE cartNo = ? ), " + 
            		"(SELECT pdPrice FROM mvc_cart_tbl WHERE cartNo = ? ), " + 
            		"(SELECT ctQuantity FROM mvc_cart_tbl WHERE cartNo = ? ), " + 
            		"'주문완료', " + 
            		"sysdate )";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, strId);
            pstmt.setInt(2, cartNo);
            pstmt.setInt(3, cartNo);
            pstmt.setInt(4, cartNo);
            pstmt.setInt(5, cartNo);
            pstmt.setInt(6, cartNo);
            
            
            insertShop = pstmt.executeUpdate();  // 성공 : 1; 실패 : 0;
            
            System.out.println("insertShop : " + insertShop);
            
         }catch(SQLException e) {
            e.printStackTrace();
         }finally {
            try {
               if(pstmt != null)pstmt.close();
               if(conn != null) conn.close();
            }catch(SQLException e) {
               e.printStackTrace();
            }
         }
         return insertShop;
	}
	
	//구매카운트
	@Override
	public int orderCnt() {
		System.out.println("DAO - orderCnt");
	    
	    int orderCnt = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	       conn = dataSource.getConnection();
	       String sql = "SELECT COUNT(*) as cnt FROM mvc_buy_tbl ";
	       
	       pstmt = conn.prepareStatement(sql);
	       
	       rs = pstmt.executeQuery();
	       
	       if(rs.next()) {
	    	   orderCnt = rs.getInt("cnt");
	       }
	    }catch(SQLException e) {
	       e.printStackTrace();
	    }finally {
	       try {
	          if(rs != null)rs.close();
	          if(pstmt != null)pstmt.close();
	          if(conn != null) conn.close();
	       }catch(SQLException e) {
	          e.printStackTrace();
	       }
	    }
	    return orderCnt;
	}
	
	//나의 쇼핑정보
	public List<BuyDTO> myOrder(int start, int end, String id) {
		Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     List<BuyDTO> list = null;
	     
	     try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT * FROM(SELECT A.*, rownum AS rn FROM(SELECT * FROM mvc_buy_tbl WHERE id=? ORDER BY shopNo DESC) A )WHERE rn BETWEEN ? AND ?";
	        System.out.println("sql: " + sql);
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setInt(2, start);
	        pstmt.setInt(3, end);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	           
	           // 1. list 생성
	           list = new ArrayList<BuyDTO>();
	            
	           do {
	              // 2. dto 생성
	        	   BuyDTO dto = new BuyDTO();
	              
	              // 3. dto에 1건의 rs 게시글 정보를 담는다.
	        	  dto.setShopNo(rs.getInt("shopNo"));
	              dto.setId(rs.getString("id"));
	              dto.setPdNo(rs.getInt("pdNo"));
	              dto.setPdName(rs.getString("pdName"));
	              dto.setPdBrand(rs.getString("pdBrand"));
	              dto.setPdPrice(rs.getInt("pdPrice"));
	              dto.setBuyQuantity(rs.getInt("buyQuantity"));
	              dto.setDeliverySt(rs.getString("deliverySt"));
	              dto.setOrderDate(rs.getDate("orderDate"));
	              
	              // 4. list에 dto를 추가한다.
	              list.add(dto);
	              
	           }while(rs.next());
	        }
	        
	     }catch(SQLException e) {
	        e.printStackTrace();
	     }finally {
	        try {
	           if(rs != null)rs.close();
	           if(pstmt != null)pstmt.close();
	           if(conn != null) conn.close();
	        }catch(SQLException e) {
	           e.printStackTrace();
	        }
	     }
	     
	     // 5. list 리턴
	     return list;
	}
	
	
	//주문목록
	@Override
	public List<BuyDTO> orderList(int start, int end) {
		Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     List<BuyDTO>list = null;
	     
	     try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT * " + 
	              "  FROM( " + 
	              "   SELECT A.*, rownum AS rn " + // rn : 일련번호 매기기
	              "      FROM (SELECT * FROM mvc_buy_tbl " + 
	              "   ORDER BY shopNo DESC) A)  " + 
	              " WHERE rn BETWEEN ? AND ?";
	        System.out.println("sql: " + sql);
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, start);
	        pstmt.setInt(2, end);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	           
	           // 1. list 생성
	           list = new ArrayList<BuyDTO>();
	            
	           do {
	              // 2. dto 생성
	        	   BuyDTO dto = new BuyDTO();
	              
	              // 3. dto에 1건의 rs 게시글 정보를 담는다.
	              dto.setShopNo(rs.getInt("shopNo"));
	              dto.setId(rs.getString("id"));
	              dto.setPdNo(rs.getInt("pdNo"));
	              dto.setPdName(rs.getString("pdName"));
	              dto.setPdBrand(rs.getString("pdBrand"));
	              dto.setPdPrice(rs.getInt("pdPrice"));
	              dto.setBuyQuantity(rs.getInt("buyQuantity"));
	              dto.setDeliverySt(rs.getString("deliverySt"));
	              dto.setOrderDate(rs.getDate("orderDate"));
	              
	              // 4. list에 dto를 추가한다.
	              list.add(dto);
	              
	           }while(rs.next());
	        }
	        
	     }catch(SQLException e) {
	        e.printStackTrace();
	     }finally {
	        try {
	           if(rs != null)rs.close();
	           if(pstmt != null)pstmt.close();
	           if(conn != null) conn.close();
	        }catch(SQLException e) {
	           e.printStackTrace();
	        }
	     }
	     
	     // 5. list 리턴
	     return list;
	}
	
	//구매취소(환불요청)
	@Override
	public int refund(int shopNo) {
		System.out.println("dao - refund");
		int refundCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="UPDATE mvc_buy_tbl "
					+"SET deliverySt= '주문취소' "
					+ "WHERE shopNo=?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1,shopNo);
			
			refundCnt =pstmt.executeUpdate(); //성공=1,실패=0
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return refundCnt;
	}
	
	
	
	//주문승인 
	@Override
	public int orderConfirm(int shopNo) {
			
		System.out.println("dao - 주문승인");
        System.out.println("shopNo" + shopNo);
        int orderConfirm = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
           conn = dataSource.getConnection();
           String sql = "INSERT INTO mvc_order_tbl (shopNo, id, pdNo, pdName, pdBrand, salePrice, buyQuantity, deliverySt, orderDate) " + 
           		"VALUES( ?, " +
           		"      (SELECT id FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		"	   (SELECT pdNo FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		"	   (SELECT pdName FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		"	   (SELECT pdBrand FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		"	   (SELECT pdPrice * buyQuantity FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		"	   (SELECT buyQuantity FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		"	   ('배송준비중'), " + 
           		"	   (SYSDATE))" ; 
           pstmt = conn.prepareStatement(sql);
           
           pstmt.setInt(1, shopNo);
           pstmt.setInt(2, shopNo);
           pstmt.setInt(3, shopNo);
           pstmt.setInt(4, shopNo);
           pstmt.setInt(5, shopNo);
           pstmt.setInt(6, shopNo);
           pstmt.setInt(7, shopNo);
           
           
           orderConfirm = pstmt.executeUpdate();  // 성공 : 1; 실패 : 0;
           
           System.out.println("orderConfirm : " + orderConfirm);
           
        }catch(SQLException e) {
           e.printStackTrace();
        }finally {
           try {
              if(pstmt != null)pstmt.close();
              if(conn != null) conn.close();
           }catch(SQLException e) {
              e.printStackTrace();
           }
        }
        return orderConfirm;
	}
	
	//주문승인목록
	public List<OrderDTO> orderConfirmList(int start, int end){
		System.out.println("dao - orderConfirmList");
		
		Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     List<OrderDTO>list = null;
	     
	     try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT * " + 
	              " FROM( " + 
	              " SELECT A.*, rownum AS rn " + 
	              "   FROM (SELECT * FROM mvc_order_tbl " + 
	              "   ORDER BY id DESC) A)  " + 
	              " WHERE rn BETWEEN ? AND ?";
	        System.out.println("sql: " + sql);
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, start);
	        pstmt.setInt(2, end);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	           
	           // 1. list 생성
	           list = new ArrayList<OrderDTO>();
	            
	           do {
	              // 2. dto 생성
	        	   OrderDTO dto = new OrderDTO();
	              
	              // 3. dto에 1건의 rs 게시글 정보를 담는다.
	        	  dto.setShopNo(rs.getInt("shopNo"));
	              dto.setId(rs.getString("id"));
	              dto.setPdNo(rs.getInt("pdNo"));
	              dto.setPdName(rs.getString("pdName"));
	              dto.setPdBrand(rs.getString("pdBrand"));
	        	  dto.setSalePrice(rs.getInt("salePrice"));
	        	  dto.setBuyQuantity(rs.getInt("buyQuantity"));
	        	  dto.setDeliverySt(rs.getString("deliverySt"));
	        	  dto.setOrderDate(rs.getDate("orderDate"));
	        	   
	              // 4. list에 dto를 추가한다.
	              list.add(dto);
	              
	           }while(rs.next());
	        }
	        
	     }catch(SQLException e) {
	        e.printStackTrace();
	     }finally {
	        try {
	           if(rs != null)rs.close();
	           if(pstmt != null)pstmt.close();
	           if(conn != null) conn.close();
	        }catch(SQLException e) {
	           e.printStackTrace();
	        }
	     }
	     
	     // 5. list 리턴
	     return list;
	}
	
	//주문취소
	@Override
	public int orderCancel(int shopNo) {
		System.out.println("dao - 주문취소");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int orderCancel = 0;
		try {
			conn = dataSource.getConnection();
			String sql ="DELETE mvc_buy_tbl "
						+"WHERE shopNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shopNo);
			
			orderCancel = pstmt.executeUpdate();
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
		return orderCancel;
	}
	
	//결산승인 
	@Override
	public void saleConfirm(int shopNo) {
		
		System.out.println("dao - 결산승인");
        System.out.println("shopNo" + shopNo);
        int orderConfirm = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
           conn = dataSource.getConnection();
           String sql = "INSERT INTO mvc_sale_tbl (id, pdNo, pdName, saleAmount, saleTotal, shopNo) " + 
           		"	VALUES( " +
           		"(SELECT id FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		" (SELECT pdNo FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		" (SELECT pdName FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		" (SELECT buyQuantity FROM mvc_buy_tbl WHERE shopNo=?), " + 
           		" (SELECT pdPrice * buyQuantity FROM mvc_buy_tbl WHERE shopNo=?), "+
           		" ? )";
           pstmt = conn.prepareStatement(sql);
           
           pstmt.setInt(1, shopNo);
           pstmt.setInt(2, shopNo);
           pstmt.setInt(3, shopNo);
           pstmt.setInt(4, shopNo);
           pstmt.setInt(5, shopNo);
           pstmt.setInt(6, shopNo);
           
           
           orderConfirm = pstmt.executeUpdate();  // 성공 : 1; 실패 : 0;
           
           System.out.println("orderConfirm : " + orderConfirm);
           
        }catch(SQLException e) {
           e.printStackTrace();
        }finally {
           try {
              if(pstmt != null)pstmt.close();
              if(conn != null) conn.close();
           }catch(SQLException e) {
              e.printStackTrace();
           }
        }
	}
	
	//결산
	@Override
	public List<SaleDTO> sale() {
		
		Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     List<SaleDTO>sale = null;
	     
	     try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT pdName " + 
	        			",sum(saleTotal) AS saleTotal " + 
	        			"FROM mvc_sale_tbl " + 
	        			"GROUP BY pdName ";
	        System.out.println("sql: " + sql);
	        
	        pstmt = conn.prepareStatement(sql);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	           
	           // 1. list 생성
	        	sale = new ArrayList<SaleDTO>();
	            
	           do {
	              // 2. dto 생성
	        	   SaleDTO dto = new SaleDTO();

	              // 3. dto에 1건의 rs 게시글 정보를 담는다.
	        	   
	        	  dto.setPdName(rs.getString("pdName")); 
	        	  dto.setSaleTotal(rs.getInt("saleTotal")); 
	              
	              // 4. list에 dto를 추가한다.
	              sale.add(dto);
	              
	           }while(rs.next());
	        }
	        
	     }catch(SQLException e) {
	        e.printStackTrace();
	     }finally {
	        try {
	           if(rs != null)rs.close();
	           if(pstmt != null)pstmt.close();
	           if(conn != null) conn.close();
	        }catch(SQLException e) {
	           e.printStackTrace();
	        }
	     }
	     
	     // 5. list 리턴
	     return sale;
	}
	
	//주문승인취소
	@Override
	public int orderConfirmCancel(int shopNo) {
		System.out.println("dao - 주문취소");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int orderConfirmCancel = 0;
		try {
			conn = dataSource.getConnection();
			String sql ="DELETE mvc_order_tbl "
						+"WHERE shopNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shopNo);
			
			orderConfirmCancel = pstmt.executeUpdate();
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
		return orderConfirmCancel;
	}
	
	//결산취소
	public void saleRefund(int shopNo) {
		
		System.out.println("dao - saleRefund");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="DELETE mvc_sale_tbl WHERE shopNo=? ";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1,shopNo);
			
			pstmt.executeUpdate(); //성공=1,실패=0
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

}
