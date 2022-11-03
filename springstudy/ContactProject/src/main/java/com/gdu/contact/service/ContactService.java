package com.gdu.contact.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.gdu.contact.domain.ContactDTO;

public interface ContactService {
	public List<ContactDTO> findAllContact();
	public int countAllContact();
	public ContactDTO findContactByNo(int no);
	public int addContact(ContactDTO contact);
	public int modifyContact(ContactDTO contact);
	public int removeContact(int no);
}
