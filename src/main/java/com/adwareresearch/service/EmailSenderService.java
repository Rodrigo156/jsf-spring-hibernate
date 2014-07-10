package com.adwareresearch.service;

import com.adwareresearch.domain.EmailSablon;


public interface EmailSenderService {
    public void sendSystemErrors(EmailSablon sablon);
}
