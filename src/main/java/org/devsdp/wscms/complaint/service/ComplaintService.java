package org.devsdp.wscms.complaint.service;

import java.util.List;
import org.devsdp.wscms.complaint.dao.ComplaintDao;
import org.devsdp.wscms.complaint.dao.ComplaintProgressDao;
import org.devsdp.wscms.complaint.dao.DivisionDao;
import org.devsdp.wscms.complaint.dao.PublicUserDao;
import org.devsdp.wscms.complaint.dto.requests.ComplaintDto;
import org.devsdp.wscms.complaint.model.Complaint;
import org.devsdp.wscms.complaint.model.ComplaintProgress;
import org.devsdp.wscms.complaint.model.Division;

import org.devsdp.wscms.complaint.model.PublicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    ComplaintDao complaintDao;
    DivisionDao divisionDao;
    PublicUserDao publicUserDao;
    // added by Prasad
    ComplaintProgressDao complainProgressDao;

    // constructor modified by Prasad
    @Autowired
    public ComplaintService(ComplaintDao complaintDao, DivisionDao divisionDao, PublicUserDao publicUserDao, ComplaintProgressDao complainProgressDao) {
        this.complaintDao = complaintDao;
        this.divisionDao = divisionDao;
        this.publicUserDao = publicUserDao;
        this.complainProgressDao = complainProgressDao;
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

//    added by Prasad
    public List<Complaint> getComplaints(int userId) {
//        PublicUser publicUser = publicUserDao.findByUserId(1).get();
        return this.complaintDao.findByUserId(userId);
    }

    // added by Prasad
    public List<ComplaintProgress> getComplainProgressList(int complaintId) {
        return this.complainProgressDao.getComplaintProgressByCompId(complaintId);
    }
}
