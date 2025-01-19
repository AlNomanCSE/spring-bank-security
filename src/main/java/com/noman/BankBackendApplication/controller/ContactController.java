package com.noman.BankBackendApplication.controller;

import com.noman.BankBackendApplication.model.Contact;
import com.noman.BankBackendApplication.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;

    @PostMapping("/contact")
//    @PreFilter("filterObject.contactName!='Test'")
    @PostAuthorize("filterObject.contactName!='Test'")
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        List<Contact> returnContacts = new ArrayList<>();
        if (contacts.isEmpty()) {
            Contact contact = contacts.getFirst();
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            Contact save = contactRepository.save(contact);
            returnContacts.add(save);
        }
        return returnContacts;
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int serviceReqNumber = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + serviceReqNumber;
    }

}
