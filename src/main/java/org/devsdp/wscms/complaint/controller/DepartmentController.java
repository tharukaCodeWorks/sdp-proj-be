package org.devsdp.wscms.complaint.controller;

import org.devsdp.wscms.sys.dto.ResponseWrapper;
import org.devsdp.wscms.complaint.dao.projection.DepartmentDataProjection;
import org.devsdp.wscms.complaint.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseWrapper> getAllDepartments() {
        try{
            List<DepartmentDataProjection> divisions =  departmentService.getAllDepartments();
            return ResponseEntity.ok(new ResponseWrapper<>(divisions, "success", "success"));
        }catch (Exception e) {
            return ResponseEntity.ok(new ResponseWrapper<>(null, "failed", "Something went wrong! Please contact developer"));
        }
    }
}
