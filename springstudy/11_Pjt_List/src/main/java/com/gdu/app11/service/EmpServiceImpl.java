package com.gdu.app11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;
import com.gdu.app11.util.PageUtil;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private PageUtil pageUtil;

	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = empMapper.selectAllEmployeesCount();
		
		pageUtil.setPageUtil(page, totalRecord);
		
		Map<String, Object> map = new HashMap<>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());

		List<EmpDTO> employees = empMapper.selectEmployeesByPage(map);
		
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/emp/list"));
		model.addAttribute("employees", employees);
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("totalRecord", totalRecord);
	}
	
	@Override
	public void findEmployees(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("column", request.getParameter("column"));
		map.put("query", request.getParameter("query"));
		map.put("start", request.getParameter("start"));
		map.put("stop", request.getParameter("stop"));
		
		
		int totalRecord = empMapper.selectFindEmployeesCount(map);
		
		pageUtil.setPageUtil(page, totalRecord);
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		List<EmpDTO> employees = empMapper.selectFindEmployees(map);
		
		model.addAttribute("employees", employees);
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/emp/search"));
		model.addAttribute("totalRecord", totalRecord);
	}
}
