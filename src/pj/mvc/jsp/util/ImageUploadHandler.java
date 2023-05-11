package pj.mvc.jsp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// 전체 이미지 경로에서 파일명을 잘라와서 setAttribute로 전달
public class ImageUploadHandler {
	
	private String uploadPath; //이미지 경로
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	public void imageUpload(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		File uploadDir = new File(uploadPath);
		
		//업로드할 경로에 폴더가 없는경우 폴더 생성
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			uploadDir.setWritable(true);
			uploadDir.setReadable(true);
			uploadDir.setExecutable(true);
			
			String fileName="";
			for(Part part: req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part); //아래 메서드 생성 - 선택한 이미지(트롬세탁기.jpg)를 리턴
				if(fileName != null && !"".contentEquals(fileName)) {
					part.write(uploadPath + File.separator + fileName); 
					
					System.out.println("fileName : " + fileName);
					req.setAttribute("fileName", fileName );
				}
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().println("==업로드 완료!!==");
	}
	
		private String getFileName(Part part) {
			
			for(String content:part.getHeader("content-disposition").split(";")) {
				if(content.trim().startsWith("filename")) {
					System.out.println("content : " + content);
					
					//content : filename="트롬세탁기.jpg" => 트롬세탁기.jpg를 가져와서 리턴
					return content.substring(content.indexOf("=") + 2, content.length()-1);//트롬세탁기.jpg
				}
			}
			return null; //filename이 없는 경우(폼필드가 데이터인 경우)
		}
	
	
	

}
