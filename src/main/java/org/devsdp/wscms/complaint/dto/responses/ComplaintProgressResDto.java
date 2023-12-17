package org.devsdp.wscms.complaint.dto.responses;

import java.util.Date;

/**
 *
 * @author aerok
 */
public class ComplaintProgressResDto {

    public enum ActionType {
        PROGRESS, CHANGED_SYS_USER, CHANGE_CATEGORY, COMPLETED
    }

    public static final int ACTION_TYPE_PROGRESS = 1;
    public static final int ACTION_TYPE_CHANGED_SYS_USER = 2;
    public static final int ACTION_TYPE_CHANGE_CATEGORY = 3;
    public static final int ACTION_TYPE_COMPLETED = 4;

    private int id;
    private int complaintId;
    private String title;
    private String description;
    private String actionTakenBy;
    private int actionTypeId;
    //1=PROGRESS,2=CHANGED_SYS_USER,3=CHANGE_CATEGORY,4=COMPLETED
    private ActionType actionType;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
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

    public int getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(int actionTypeId) {
        this.actionTypeId = actionTypeId;
        switch (actionTypeId) {
            case ACTION_TYPE_PROGRESS:
                this.actionType = ActionType.PROGRESS;
                break;
            case ACTION_TYPE_CHANGED_SYS_USER:
                this.actionType = ActionType.CHANGED_SYS_USER;
                break;
            case ACTION_TYPE_CHANGE_CATEGORY:
                this.actionType = ActionType.CHANGE_CATEGORY;
                break;
            case ACTION_TYPE_COMPLETED:
                this.actionType = ActionType.COMPLETED;
                break;
            default:
                break;
        }
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getActionTakenBy() {
        return actionTakenBy;
    }

    public void setActionTakenBy(String actionTakenBy) {
        this.actionTakenBy = actionTakenBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
