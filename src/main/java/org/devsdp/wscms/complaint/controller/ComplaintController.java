package org.devsdp.wscms.complaint.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.devsdp.wscms.complaint.dto.responses.ComplaintProgressResDto;
import org.devsdp.wscms.complaint.dto.responses.ComplaintViewResponseDto;
import org.devsdp.wscms.complaint.model.ComplaintProgress;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    private ComplaintService complaintService;
    private TokenProvider tokenProvider;

    public ComplaintController(ComplaintService complaintService, TokenProvider tokenProvider) {
        this.complaintService = complaintService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseWrapper> makeComplaint(@RequestBody ComplaintDto complaint, HttpServletRequest request) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            User user = tokenProvider.getAuthUser(request);

            Complaint savedComplaint = complaintService.makeComplaint(complaint, user.getId());
            return ResponseEntity.ok(new ResponseWrapper<>(savedComplaint, "success", "success"));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseWrapper<>(null, "failed", "Something went wrong! Please contact developer"));
        }
    }

    // added by Prasad
    @GetMapping(value = "/view-all")
    public ResponseEntity<?> viewComplaints(int userId, HttpServletRequest request) {
//        User user = tokenProvider.getAuthUser(request);
        List<Complaint> complaints = this.complaintService.getComplaints(userId);
        List<ComplaintViewResponseDto> resComplaintList = new ArrayList<>();
        if (complaints.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (Complaint complaint : complaints) {
            ComplaintViewResponseDto responseDto = new ComplaintViewResponseDto();
            responseDto.setId(complaint.getId());
            responseDto.setTitle(complaint.getComplaintTitle());
            responseDto.setDescription(complaint.getComplaintDesc());
            responseDto.setDivision(complaint.getDivision().getName());
            responseDto.setReportedDate(complaint.getReportedDate());
            responseDto.setStatus(complaint.getStatus());
            resComplaintList.add(responseDto);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(resComplaintList, "success", "success"));
    }

    @GetMapping(value = "/get-progress")
    public ResponseEntity<?> viewComplaintProgress(int complaintId) {
        List<ComplaintProgress> complainProgressList = this.complaintService.getComplainProgressList(complaintId);
        if (complainProgressList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ComplaintProgressResDto> resCompProList = new ArrayList<>();
        for (ComplaintProgress cp : complainProgressList) {
            ComplaintProgressResDto cprd = new ComplaintProgressResDto();
            cprd.setId(cp.getId());
            cprd.setTitle(cp.getActionTitle());
            cprd.setDescription(cp.getActionDesc());
            cprd.setActionTypeId(cp.getActionType());
            cprd.setActionTakenBy(cp.getActionTakenBy().getFirstName());
            cprd.setComplaintId(complaintId);
            cprd.setDate(cp.getRecordedDate());
            resCompProList.add(cprd);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(resCompProList, "success", "success"));

    }

}
