package org.devsdp.wscms.complaint.service;

import org.devsdp.wscms.complaint.dao.ComplaintDao;
import org.devsdp.wscms.complaint.dao.DivisionDao;
import org.devsdp.wscms.complaint.dao.PublicUserDao;
import org.devsdp.wscms.complaint.dto.requests.ComplaintDto;
import org.devsdp.wscms.complaint.model.Complaint;
import org.devsdp.wscms.complaint.model.Division;

import org.devsdp.wscms.complaint.model.PublicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    ComplaintDao complaintDao;
    DivisionDao divisionDao;
    PublicUserDao publicUserDao;

    @Autowired
    public ComplaintService(ComplaintDao complaintDao, DivisionDao divisionDao, PublicUserDao publicUserDao) {
        this.complaintDao = complaintDao;
        this.divisionDao = divisionDao;
        this.publicUserDao = publicUserDao;
    }

    public Complaint makeComplaint(ComplaintDto complaint, long userId) {
        PublicUser publicUser = publicUserDao.findByUserId(userId).get();
        Complaint complaint1 = Complaint
                .builder()
                .complaintDesc(complaint.getDescription())
                .complaintTitle(complaint.getTitle())
                .division(Division.builder().id(complaint.getDivisionId()).build())
                .reportedBy(publicUser)
                .build();
        return complaintDao.save(complaint1);
    }

}
