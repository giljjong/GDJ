package com.gdu.app11.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpMapper empMapper;

	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = empMapper.selectAllEmployeesCount();
		
		int recordPerPage = 10;
		int begin = (page - 1) * recordPerPage + 1;
		int end = begin + recordPerPage - 1;
		
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(begin, end);
		
		System.out.println(employees);
		
		model.addAttribute("page", page);
		model.addAttribute("employees", employees);
	}

}
