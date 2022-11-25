package com.gdu.app15.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app15.domain.BlogDTO;
import com.gdu.app15.mapper.BlogMapper;
import com.gdu.app15.util.MyFileUtil;
import com.gdu.app15.util.PageUtil;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogMapper blogMapper;
	private PageUtil pageUtil;
	private MyFileUtil myFileUtil;
	
	@Autowired
	public void set(BlogMapper blogMapper, PageUtil pageUtil, MyFileUtil myFileUtil) {
		this.blogMapper = blogMapper;
		this.pageUtil = pageUtil;
		this.myFileUtil = myFileUtil;
	}
	
	@Override
	public void getBlogList(Model model) {
		
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = blogMapper.selectBlogListCount();
		pageUtil.setPageUtil(page, totalRecord);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("blogList", blogMapper.selectBlogListByMap(map));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/blog/list"));
	}
	
	@Override
	public void saveBlog(HttpServletRequest request, HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 작성된 내용이 어딘가를 경유해서 도착하면 원래 ip가 X-Forwarded-For라는 요청헤더에 저장된다
		// 출발			경유		도착
		// 1.1.1.1		2.2.2.2		2.2.2.2 : request.getRemoteAddr()
		//							1.1.1.1 : request.getHeader("X-Forwarded-For")
		// 출발						도착
		// 1.1.1.1					1.1.1.1 : request.getRemoteAddr()
		//							null    : request.getHeader("X-Forwarded-For")
		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());
		
		BlogDTO blog = BlogDTO.builder()
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		int result = blogMapper.insertBlog(blog);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				out.println("alert('삽입 성공');");
				out.println("location.href='" + request.getContextPath() + "/blog/list';");
			} else {
				out.println("alert('삽입 실패');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		
		// 파라미터 file
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// 저장할 파일명
		String filesystem = myFileUtil.getFilename(multipartFile.getOriginalFilename());
		
		// 저장 경로
		String path = "C:\\upload";
		
		// 저장 경로가 없으면 만들기
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 저장할 File 객체
		File file = new File(path, filesystem);  // new File(dir, filesystem)도 가능
		
		// HDD에 File 객체 저장하기
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 저장된 파일을 확인할 수 있는 매핑을 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("src", multipartRequest.getContextPath() + "/load/image/" + filesystem);
		return map;
		
		// 저장된 파일이 aaa.jpg라고 가정하면
		// src=${contextPath}/load/image/aaa.jpg 이다. 
		
	}
	
	@Override
	public int increseBlogHit(int blogNo) {
		return blogMapper.updateHit(blogNo);
	}
	
	@Override
	public BlogDTO getBlogByNo(int blogNo) {
		return blogMapper.selectBlogByNo(blogNo);
	}
	
	@Override
	public void modifyBlog(HttpServletRequest request, HttpServletResponse response) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("blogNo"));
		int blogNo = Integer.parseInt(opt.orElse("0"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BlogDTO Blog = BlogDTO.builder()
				.blogNo(blogNo)
				.title(title)
				.content(content)
				.build();
		
		int result = blogMapper.updateBlog(Blog);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {
				out.println("alert('수정 성공');");
				out.println("location.href='" + request.getContextPath() + "/blog/detail?blogNo=" + blogNo + "';");
			} else {
				out.println("alert('수정 실패');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeBlog(HttpServletRequest request, HttpServletResponse response) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("blogNo"));
		int blogNo = Integer.parseInt(opt.orElse("0"));
		
		int result = blogMapper.deleteBlog(blogNo);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {
				out.println("alert('삭제 성공');");
				out.println("location.href='" + request.getContextPath() + "/blog/list';");
			} else {
				out.println("alert('삭제 실패');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
