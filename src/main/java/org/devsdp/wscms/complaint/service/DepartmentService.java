package org.devsdp.wscms.complaint.service;

import org.devsdp.wscms.complaint.dao.DepartmentDao;
import org.devsdp.wscms.complaint.dao.projection.DepartmentDataProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<DepartmentDataProjection> getAllDepartments(){
        return departmentDao.findIdAndName();
    }

}
