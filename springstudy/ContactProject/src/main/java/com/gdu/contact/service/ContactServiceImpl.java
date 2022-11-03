package com.gdu.contact.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.contact.domain.ContactDTO;
import com.gdu.contact.repository.ContactDAO;

public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDao;
	
	@Override
	public List<ContactDTO> findAllContact() {
		return contactDao.selectAllContacts();
	}
	
	@Override
	public int countAllContact() {
		return contactDao.countAllContact();
	}
	
	@Override
	public ContactDTO findContactByNo(int no) {
		return contactDao.selectContactByNo(no);
	}

	@Override
	public int addContact(ContactDTO contact) {
		return contactDao.insertContact(contact);
	}

	@Override
	public int modifyContact(ContactDTO contact) {
		return contactDao.modifyContact(contact);
	}

	@Override
	public int removeContact(int no) {
		// TODO Auto-generated method stub
		return contactDao.removeContact(no);
	}

}
