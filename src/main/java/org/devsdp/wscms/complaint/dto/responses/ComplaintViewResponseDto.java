package org.devsdp.wscms.complaint.dto.responses;

import java.util.Date;

/**
 *
 * @author aerok
 */
public class ComplaintViewResponseDto {

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public enum Status {
        OPEN, IN_PROGRESS, CLOSED
    }

    private int id;
    private String title;
    private String description;
    private Date reportedDate;
    private Status status;
    private String division;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(int status) {
        switch (status) {
            case 1:
                this.status = Status.OPEN;
                break;
            case 2:
                this.status = Status.IN_PROGRESS;
                break;
            case 3:
                this.status = Status.CLOSED;
                break;
            default:
                break;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

}
