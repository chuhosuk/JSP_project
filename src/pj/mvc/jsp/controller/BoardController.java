package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.BoardServiceImpl;

@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  //1단계. 웹브라우저가 전송한 HTTP 요청 받음   
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
		BoardServiceImpl service = new BoardServiceImpl();
		
		// 2단계. 요청분석
		
		String uri = req.getRequestURI(); 		   // /jsp_pj_126/*.do
		String contextPath = req.getContextPath(); // /jsp_pj_126
		String url = uri.substring(contextPath.length());  //    /*.do
		
		// -------게시판-------
		// 게시글목록
		if(url.equals("/*.bo") || url.equals("/board_list.bo")) {
			System.out.println("[url => /board_list.bo]");
			service.boardListAction(req, res);
			
			viewPage = "admin/csCenter/board_list.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res);
		}
		
		// 게시글 상세페이지
		else if(url.equals("/board_detailAction.bo")) {
			System.out.println("[url => /board_detailAction.bo]");
			service.boardDetailAction(req, res);
			
			viewPage = "admin/csCenter/board_detailAction.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res);
			
		}
		
		// 게시글 수정 삭제시 비밀번호 인증
		else if(url.equals("/password_chkAction.bo")) {
			System.out.println("[url => /password_chkAction.bo]");
			String result =service.password_chkAction(req, res);
			
			
			if(result != null) {
				viewPage = "admin/csCenter/board_edit.jsp";
				
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, res);
			}
			else {
				System.out.println("<<비밀번호 불일치>>");
				int num = Integer.parseInt(req.getParameter("num"));
				viewPage =req.getContextPath() + "/board_detailAction.bo?num=" + num + "&message=error";
				res.sendRedirect(viewPage);
			}
			
			
		}
		
		// 게시판 수정처리
		else if(url.equals("/board_updateAction.bo")) {
			System.out.println("[url => /board_updateAction.bo]");
			
			service.boardUpdateAction(req, res);
			viewPage = req.getContextPath() + "/board_list.bo";
			res.sendRedirect(viewPage);
		}
		
		// 게시판 삭제처리
		else if(url.equals("/board_deleteAction.bo")) {
			System.out.println("[url => /board_deleteAction.bo]");
			
			service.boardDeleteAction(req, res);
			viewPage = req.getContextPath() + "/board_list.bo";
			res.sendRedirect(viewPage);
		
		}
		
		// 글쓰기 화면
		else if(url.equals("/board_insert.bo")) {
			System.out.println("[url => /board_insert.bo]");
			
			viewPage = "admin/csCenter/board_insert.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res);
			
		}	
		
		
		// 글쓰기 처리
		//board_insertAction.bo
		else if(url.equals("/board_insertAction.bo")) {
			System.out.println("[url => /board_insertAction.bo]");
			
			service.boardInsertAction(req, res);
			viewPage = req.getContextPath() + "/board_list.bo";
			
			res.sendRedirect(viewPage);
			
		}	
		
		
		// 댓글작성 처리
		else if(url.equals("/comment_insert.bo")) {
			System.out.println("[url => /comment_insert.bo]");
					
			service.commentAddAction(req, res);
			// board_detail.jsp의 comment_add => $.ajax()의 콜백함수(success)로 넘어감 => comment_list로 넘어감
					
		}
				
		// 댓글목록 처리
		else if(url.equals("/comment_list.bo")) {
			System.out.println("[url => /comment_list.bo]");
					
			service.commentListAction(req, res);
			viewPage = "admin/csCenter/comment_list.jsp";  // 결과페이지이며, 콜백함수의 result에 
							
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req,res);
			// board_detail.jsp의 comment_add => $.ajax()의 콜백함수(success)로 넘어감
					
		}
		
		
		
	}

}
