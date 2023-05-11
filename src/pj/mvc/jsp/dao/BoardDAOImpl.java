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

import pj.mvc.jsp.dto.BoardCommentDTO;
import pj.mvc.jsp.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO{
	
	//커넥션 풀 객체를 보관
	DataSource dataSource;
	
	//싱글톤 방식: 객체를 한번만 생성
	static BoardDAOImpl instance = new BoardDAOImpl();
	public static BoardDAOImpl getInstance() {
		if(instance == null) {
			instance = new BoardDAOImpl();
		}
		return instance;
	}
	
	//디폴트 생성자
	private BoardDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/jsp_pj_126_chs");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	// 게시글목록
	@Override
	public List<BoardDTO> boardList(int start, int end) {
		System.out.println("DAO - boardLIST");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDTO> list = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT  * " + 
					"   FROM( " + 
					"	SELECT A.*, rownum AS rn " +  //rn : 일련번호 메기기
					"  	  FROM (SELECT * FROM mvc_board_tbl " + 
					"  			ORDER BY num DESC) A " + 
					"   ) " + 
					"  WHERE  rn BETWEEN ? AND ?";
			System.out.println("sql" + sql);
			
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				// 1. list 생성
				 list =new  ArrayList<BoardDTO>();
				
				do {
					// 2. dto 생성
					BoardDTO dto = new BoardDTO();
					
					// 3. dto에 1건의 rs 게시글 정보를 담는다.
					dto.setNum(rs.getInt("num"));	 
					dto.setTitle(rs.getString("title"));	 
					dto.setContent(rs.getString("content"));	 
					dto.setWriter(rs.getString("writer"));	 
					dto.setPassword(rs.getString("password"));;	 
					dto.setReadCnt(rs.getInt("readCnt"));;	 
					dto.setRegDate(rs.getDate("regDate"));;	 
					
					// 4. list에 dto를 추가한다.
					list.add(dto);
					
				}while(rs.next());
				
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		// 5.List 큰바구니에 담는다 
		return list;
		
	}
	
	// 게시글 갯수 구하기
	@Override
	public int boardCnt() {
		System.out.println("DAO - boardCnt");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int selectCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql ="SELECT COUNT(*) as cnt FROM mvc_board_tbl";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				selectCnt = rs.getInt("cnt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return selectCnt;
	}

	// 조회수 증가
	@Override
	public void plusReadCnt(int num) {
		System.out.println("DAO - plusReadCnt");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="UPDATE MVC_BOARD_TBL " + 
					" SET READCNT = READCNT+1 " + 
					" WHERE num=?"; 
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
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
		
	}
	
	// 게시글 상세페이지, 게시글 수정을 위한 상세페이지
	@Override
	public BoardDTO getBoardDetail(int num) {
		System.out.println("DAO - getBoardDetail");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = new BoardDTO();
		
		try {
			conn = dataSource.getConnection();
			String sql ="SELECT * FROM mvc_board_tbl " + 
					"	WHERE num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));	 
				dto.setTitle(rs.getString("title"));	 
				dto.setContent(rs.getString("content"));	 
				dto.setWriter(rs.getString("writer"));	 
				dto.setPassword(rs.getString("password"));;	 
				dto.setReadCnt(rs.getInt("readCnt"));;	 
				dto.setRegDate(rs.getDate("regDate"));;	 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	// 게시글 수정 삭제시 비밀번호 인증
	@Override
	public String password_chk(int num, String password) {
		
		System.out.println("DAO - password_chk");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="SELECT * FROM mvc_board_tbl WHERE num= ? AND password=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				result = rs.getString("password");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 게시판 수정처리
	@Override
	public void updateBoard(BoardDTO dto) {
		
		System.out.println("DAO - updateBoard");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="UPDATE mvc_board_tbl SET password=?, title=?, content=? WHERE num=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNum());
			
			pstmt.executeUpdate();
			
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

	// 게시글 삭제처리
	@Override
	public void deleteBoard(int num) {
		
		System.out.println("DAO - deleteBoard");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="DELETE FROM mvc_board_tbl WHERE num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		
			
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
	}

	// 글쓰기 처리
	@Override
	public void insertBoard(BoardDTO dto) {
		
		System.out.println("DAO - insertBoard");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql ="INSERT INTO mvc_board_tbl (num,title,content,writer,password,readCnt,regDate) " 
						+ "VALUES((SELECT NVL(MAX(num)+1, 1) FROM mvc_board_tbl),? ,? ,? ,? ,0 ,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			pstmt.setString(4, dto.getPassword());
			
			
			pstmt.executeQuery();
		
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
		
	}
	
 @Override
   public void commentInsert(BoardCommentDTO dto) {
      System.out.println("commentInsert - commentInsert");
         
         Connection conn = null;
         PreparedStatement pstmt = null;
         
         try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO mvc_board_comment_tbl(COMMENT_num, board_num, writer, content, regDate) " + 
                    " VALUES((SELECT NVL(MAX(COMMENT_num)+1, 1) FROM mvc_board_comment_tbl), ?,?,?,sysdate)";
                    
            
            pstmt =conn.prepareStatement(sql);
            
            pstmt.setInt(1, dto.getBoard_num());
            pstmt.setString(2, dto.getWriter());
            pstmt.setString(3, dto.getContent());
            
            
            pstmt.executeQuery(); //성공:1 실패:0
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
   }

   @Override
   public List<BoardCommentDTO> commentList(int board_num) {
      System.out.println("dao - commentList");
      
      Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         List<BoardCommentDTO> list = null;
         
         try {
             conn = dataSource.getConnection();
             String sql = "SELECT * FROM mvc_board_comment_tbl WHERE board_num = ?" +
                         " order by comment_num";
             System.out.println("sql : " + sql);
             
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, board_num);
             rs =pstmt.executeQuery();
             
             if(rs.next()) {
                // 1. list 생성
                list = new ArrayList<BoardCommentDTO>();
                do {
                   //2. dto 생성
                   BoardCommentDTO dto = new BoardCommentDTO();
                   
                   // 3.dto에 1건의 rs 게시글 정보를 담는다.
                   //num, title, content, writer, password, readCnt, regDate
                   dto.setComment_num(rs.getInt("comment_num"));
                   dto.setBoard_num(rs.getInt("board_num"));
                   dto.setWriter(rs.getString("writer"));
                   dto.setContent(rs.getString("content"));
                   dto.setRegDate(rs.getDate("regDate"));

                   // 4. list에 dto를 추가한다. 
                   list.add(dto);
                   
                }while(rs.next());
             }

          }catch(SQLException e) {
             e.printStackTrace();
          }finally {
             try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
             }catch(SQLException e) {
                e.printStackTrace();
             }
             
          }
      return list;
         
	   }

}
