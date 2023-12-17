package org.devsdp.wscms.complaint.service;

import org.devsdp.wscms.complaint.dao.DivisionDao;
import org.devsdp.wscms.complaint.dao.projection.DivisionDataProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {
    DivisionDao divisionDao;
    public DivisionService(DivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

    public List<DivisionDataProjection> getAllDivisions () {
        return divisionDao.findAllDivisionWithDepartment();
    }
}
