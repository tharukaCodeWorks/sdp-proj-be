package org.devsdp.wscms.complaint.controller;

import org.devsdp.wscms.sys.dto.ResponseWrapper;
import org.devsdp.wscms.auth.model.User;
import org.devsdp.wscms.complaint.dto.requests.ComplaintDto;
import org.devsdp.wscms.complaint.model.Complaint;
import org.devsdp.wscms.complaint.service.ComplaintService;
import org.devsdp.wscms.sys.util.TokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    private ComplaintService complaintService;
    private TokenProvider tokenProvider;

    public ComplaintController(ComplaintService complaintService, TokenProvider tokenProvider){
        this.complaintService = complaintService;
        this.tokenProvider = tokenProvider;
    }
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseWrapper> makeComplaint(@RequestBody ComplaintDto complaint, HttpServletRequest request) {
        try{
            String authorizationHeader = request.getHeader("Authorization");
            User user = tokenProvider.getAuthUser(request);

            Complaint savedComplaint =  complaintService.makeComplaint(complaint, user.getId());
            return ResponseEntity.ok(new ResponseWrapper<>(savedComplaint, "success", "success"));

        }catch (Exception e) {
            return ResponseEntity.ok(new ResponseWrapper<>(null, "failed", "Something went wrong! Please contact developer"));
        }
    }
}
