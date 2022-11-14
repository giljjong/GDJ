package com.gdu.app13.service;

import java.util.Map;

import org.springframework.stereotype.Service;

public interface UserService {
	public Map<String, Object> isReduce(String id);
	public Map<String, Object> isReduceEmail(String email);
	public Map<String, Object> sendAuthCode(String email);
}
