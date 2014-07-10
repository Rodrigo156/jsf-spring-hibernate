package com.adwareresearch.service.impl;

import com.adwareresearch.domain.EmailSablon;
import com.adwareresearch.service.EmailSenderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmailSenderServiceImpl implements EmailSenderService { 
    
    @Override
    public void sendSystemErrors(EmailSablon sablon) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
