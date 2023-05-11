package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import page.Paging;
import pj.mvc.jsp.dao.BoardDAO;
import pj.mvc.jsp.dao.BoardDAOImpl;
import pj.mvc.jsp.dto.BoardCommentDTO;
import pj.mvc.jsp.dto.BoardDTO;

public class BoardServiceImpl implements BoardService{

	// 게시글목록
	@Override
	public void boardListAction(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		System.out.println("서비스 -게시글 목록");
		
		//3단계. 화면으로부터 입력받은 값을 받는다.
		String pageNum = req.getParameter("pageNum");
		
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		Paging paging = new Paging(pageNum);
		
		
		//5-1단계. 전체 게시글 갯수 카운트
		int total = dao.boardCnt();
		System.out.println("total => " + total);
		
		paging.setTotatlCount(total);
		
		//5-2단계. 게시글 목록 조회
		int start = paging.getStartRow(); 	// 페이지별 시작번호
		int end = paging.getEndRow(); 		// 페이지별 끝번호
		
		System.out.println("start => " + start);
		System.out.println("end => " + end);
		
		List<BoardDTO> list = dao.boardList(start, end);
		
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
	}
	
	// 게시글 상세페이지
	@Override
	public void boardDetailAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 -게시글 상세페이지");
		
		//3단계. 화면으로부터 입력받은 값을 받는다.
		int num = Integer.parseInt(req.getParameter("num"));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		//5-1단계. 조회수 증가		
		dao.plusReadCnt(num);
		
		//5-2단계. 게시글 상세페이지
		BoardDTO dto = dao.getBoardDetail(num);
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("dto", dto);
		
		
	}
	
	// 게시글 수정 삭제시 비밀번호 인증
	@Override
	public String password_chkAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 -게시글 수정 삭제시 비밀번호 인증");
		
		//3단계. 화면으로부터 입력받은 값 또는 hidden값 을 받는다.
		int num = Integer.parseInt(req.getParameter("num"));
		String password = req.getParameter("password");
		System.out.println("글번호 : " + num + "비밀번호 : " + password);
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		//5-1단계. 게시글 수정 삭제시 비밀번호 인증
		String result =dao.password_chk(num, password);
		BoardDTO dto = null;
		
		if(result != null) { //인증성공
			//5-2단계. 상세페이지
			dto = dao.getBoardDetail(num);
		}
		
		//6단계. jsp로 처리 결과 전달
		req.setAttribute("dto", dto);
		
		return result;
	}

	// 게시판 수정처리
	@Override
	public void boardUpdateAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 -게시글 수정처리");
		//3단계. 화면으로부터 입력받은 값 또는 hidden값 을 받는다.
		// dto 생성
		BoardDTO dto = new BoardDTO();
		
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setPassword(req.getParameter("password"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		//5단계. 게시글 수정처리후 list로 이동
		dao.updateBoard(dto);
		
	}

	// 게시판 삭제처리
	@Override
	public void boardDeleteAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		//3단계. 화면으로부터 입력받은 값 또는 hidden값 을 받는다.
		int num = Integer.parseInt(req.getParameter("num"));
		
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		//5단계. 게시글 수정처리후 list로 이동
		dao.deleteBoard(num);
		
	}
	

	// 글쓰기 화면
	@Override
	public void boardInsertAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		//3단계. 화면으로부터 입력받은 값 또는 hidden값 을 받는다.
		BoardDTO dto = new BoardDTO();
		dto.setWriter(req.getParameter("writer"));
		dto.setPassword(req.getParameter("password"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		
				
		//4단계. 싱글톤방식으로 dao 객체 생성, 다형성 적용
		BoardDAO dao = BoardDAOImpl.getInstance();
				
		//5단계. 게시글 수정처리후 list로 이동
		dao.insertBoard(dto);
		
	}

	// 글쓰기 처리
	@Override
	public void commentAddAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		 System.out.println("서비스 - 댓글작성 처리");
	      
	      System.out.println("board_num " + req.getParameter("board_num"));
	      System.out.println("writer " + req.getParameter("writer"));
	      System.out.println("content " + req.getParameter("content"));
	      
	      //3단계. 화면으로부터 입력받은 값 또는 hidden값을 받는다.
	      BoardCommentDTO dto = new BoardCommentDTO();
	      dto.setBoard_num(Integer.parseInt(req.getParameter("board_num")));
	      dto.setWriter(req.getParameter("writer"));
	      dto.setContent(req.getParameter("content"));
	      //4단계. 싱글톤 방식으로 dto 객체생성, 다형성 적용
	      BoardDAO dao = BoardDAOImpl.getInstance();
	      
	      //5단계. 댓글 작성 
	      dao.commentInsert(dto);
	      
	      //실행이 끝나면   board_detailAction.jsp의 comment_add() =>  $.ajax()의 콜백함수(success의 result)로 넘어감

	}

	// 댓글작성 처리
	@Override
	public void commentListAction(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//3단계. 화면으로부터 입력받은 값 또는 hidden값을 받는다.
	      int num = Integer.parseInt(req.getParameter("num"));
	      System.out.println("board_num : " + num);
	      
	      //4단계. 싱글톤 방식으로 dto 객체생성, 다형성 적용
	      BoardDAO dao = BoardDAOImpl.getInstance();
	      
	      //5단계. 댓글 목록 처리
	      List<BoardCommentDTO> list = dao.commentList(num);
	      
	      //6단계 . jsp로 처리
	      req.setAttribute("list", list); // $.$.ajax()의 콜백함수(success의 result )로 넘어감

	}

}
