package com.gdu.app12.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.gdu.app12.domain.BbsDTO;
import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;
import com.gdu.app12.util.Securityutil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BbsServiceImpl implements BbsService {

	private BbsMapper bbsMapper;
	private PageUtil pageUtil;
	private Securityutil securityUtil;
	
	@Override
	public void findAllBbsLists(HttpServletRequest request, Model model) {
		
		System.out.println(securityUtil.getAuthCode(6));
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = bbsMapper.selectAllBbsCount();
		opt = Optional.ofNullable(request.getParameter("recordPerPage"));
		int recordPerPage = Integer.parseInt(opt.orElse("10"));
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		Map<String, Object> map = new HashMap<>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		List<BbsDTO> bbsList = bbsMapper.selectAllBbsList(map);
		
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/bbs/list"));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("recordPerPage", recordPerPage);
	}

	@Override
	public int addBbs(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String writer = request.getParameter("writer");
		String title = securityUtil.preventXSS(request.getParameter("title"));
		
		BbsDTO bbsDTO = BbsDTO.builder()
				.ip(ip)
				.writer(writer)
				.title(title)
				.build();
		
		return bbsMapper.insertBbs(bbsDTO);
	}

	/*
	 * @Transactional
	 * 안녕. 나는 트랜잭션을 처리하는 애너테이션이야
	 * INSERT, UPDATE, DELETE 중 2개 이상이 호출되는 서비스에 추가하면 돼
	 */
	
	@Transactional
	@Override
	public int addReply(HttpServletRequest request) {
		
		String writer = request.getParameter("writer");
		String title = securityUtil.preventXSS(request.getParameter("title"));
		String ip = request.getRemoteAddr();
		int depth = Integer.parseInt(request.getParameter("depth"));
		int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		
		BbsDTO bbsDTO = BbsDTO.builder()
				.groupNo(groupNo)
				.groupOrder(groupOrder)
				.build();
		
		bbsMapper.updatePreviousReply(bbsDTO);
		
		BbsDTO reply = BbsDTO.builder()
				.writer(writer)
				.title(title)
				.ip(ip)
				.depth(depth + 1)
				.groupNo(groupNo)
				.groupOrder(groupOrder + 1)
				.build();
		
		bbsMapper.insertReply(reply);
		
		return 0;
	}
	
	@Override
	public int removeBbs(int bbsNo) {
		return bbsMapper.deleteBbs(bbsNo);
	}


}
