package org.devsdp.wscms.complaint.service;

import org.devsdp.wscms.complaint.dao.PublicUserDao;
import org.springframework.stereotype.Service;

@Service
public class PublicUserService {
    private PublicUserDao publicUserDao;

    public PublicUserService(PublicUserDao publicUserDao) {
        this.publicUserDao = publicUserDao;
    }


}
