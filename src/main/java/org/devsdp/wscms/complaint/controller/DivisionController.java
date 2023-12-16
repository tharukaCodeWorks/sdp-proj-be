package org.devsdp.wscms.complaint.controller;

import org.devsdp.wscms.sys.dto.ResponseWrapper;
import org.devsdp.wscms.complaint.dao.projection.DivisionDataProjection;
import org.devsdp.wscms.complaint.service.DivisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/divisions")
public class DivisionController {
    private DivisionService divisionService;
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseWrapper> getAllDivisions() {
        try{
            List<DivisionDataProjection> divisions =  divisionService.getAllDivisions();
            return ResponseEntity.ok(new ResponseWrapper<>(divisions, "success", "success"));
        }catch (Exception e) {
            return ResponseEntity.ok(new ResponseWrapper<>(null, "failed", "Something went wrong! Please contact developer"));
        }
    }
}
