package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.AttachDTO;
import com.gdu.app14.domain.UploadDTO;
import com.gdu.app14.mapper.UploadMapper;
import com.gdu.app14.util.MyFileUtil;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadMapper uploadMapper;
	
	@Autowired
	private MyFileUtil myFileUtil;
	
	@Override
	public List<UploadDTO> getUploadList() {
		return uploadMapper.selectUploadList();
	}
	
	@Transactional
	@Override
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		UploadDTO upload = UploadDTO.builder()
				.title(title)
				.content(content)
				.build();
		
		int uploadResult = uploadMapper.insertUpload(upload);
		
		List<MultipartFile> files = multipartRequest.getFiles("files");
		int attachResult;
		if(files.get(0).getSize() == 0) {
			attachResult = 1;
		} else {
			attachResult = 0;
		};
		
		
		for(MultipartFile multipartFile : files) {
			try {
				if(multipartFile != null && multipartFile.isEmpty() == false) {
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1);	// IE는 origin에 전체 경로가 붙어서 파일명만 사용해야 함
					
					String filesystem = myFileUtil.getFilename(origin);
					String path = myFileUtil.getTodayPath();
					
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					File file = new File(dir, filesystem);
					
					multipartFile.transferTo(file);
					
					AttachDTO attach = AttachDTO.builder()
							.origin(origin)
							.path(path)
							.filesystem(filesystem)
							.uploadNo(upload.getUploadNo())
							.build();
					
					attachResult += uploadMapper.insertAttach(attach);
					
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(uploadResult > 0 && attachResult == files.size()) {
				out.println("<script>");
				out.println("alert('업로드 되었습니다.')");
				out.println("location.href='"+ multipartRequest.getContextPath() +"/upload/list'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('업로드가 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getUploadByNo(int uploadNo, Model model) {
		model.addAttribute("upload", uploadMapper.selectUploadByNo(uploadNo));
		model.addAttribute("attachList", uploadMapper.selectAttachList(uploadNo));
	}
	
	@Override
	public ResponseEntity<Resource> download(String userAgent, int attachNo) {
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);
		File file = new File(attach.getPath(), attach.getFilesystem());
		
		Resource resource = new FileSystemResource(file);
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		uploadMapper.updateDownloadCnt(attachNo);
		
		String origin = attach.getOrigin();
		try {
			// IE (userAgent에 "Trident"가 포함되어 있음)
			if(userAgent.contains("Trident")) {
				origin = URLEncoder.encode(origin, "UTF-8").replaceAll("\\+", " ");
			} else if(userAgent.contains("Edg")) {
				// Edge (userAgent에 "Edg"가 포함되어 있음)
				origin = URLEncoder.encode(origin, "UTF-8");
			} else {
				origin = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Disposition", "attachment; filename=" + origin);
		header.add("Content-Length", file.length() + "");
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	@Override
	public void removeAttachByAttachNo(int attachNo) {
		
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);
		
		int result = uploadMapper.deleteAttachByNo(attachNo);
		if(result > 0) {
			File file = new File(attach.getPath(), attach.getFilesystem());
			
			if(file.exists()) {
				file.delete();
			}
		}
	}
}
