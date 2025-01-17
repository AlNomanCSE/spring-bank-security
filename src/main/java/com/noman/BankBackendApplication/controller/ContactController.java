package com.noman.BankBackendApplication.controller;

import com.noman.BankBackendApplication.model.Contact;
import com.noman.BankBackendApplication.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);

    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int serviceReqNumber = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + serviceReqNumber;
    }

}
