package com.localMysql.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.localMysql.constants.EazySchoolConstants;
import com.localMysql.model.Contact;
import com.localMysql.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ContactService {
	
	
	
	//private static Logger log =  LoggerFactory.getLogger(ContactService.class);
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	public ContactService() {
		System.out.println("Contact bean initialized");
	}
	
	public boolean saveMessageDetails(Contact contact) {
		boolean isSaved = false;
		contact.setStatus(EazySchoolConstants.OPEN);
		contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
		contact.setCreatedAt(LocalDateTime.now());
		int result = contactRepository.saveContactMsg(contact);
		if(result>0) {
			isSaved = true;
		}
		return isSaved;
		
	
	}
	

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    
    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE, updatedBy);
        if(result>0) {
            isUpdated = true;
        }
        return isUpdated;
    }
    

}
