package org.devsdp.wscms.complaint.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aerok
 */
@Entity
@Table(name = "complaint_progress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplaintProgress.findAll", query = "SELECT c FROM ComplaintProgress c"),
    @NamedQuery(name = "ComplaintProgress.findById", query = "SELECT c FROM ComplaintProgress c WHERE c.id = :id"),
    @NamedQuery(name = "ComplaintProgress.findByActionTitle", query = "SELECT c FROM ComplaintProgress c WHERE c.actionTitle = :actionTitle"),
    @NamedQuery(name = "ComplaintProgress.findByActionType", query = "SELECT c FROM ComplaintProgress c WHERE c.actionType = :actionType"),
    @NamedQuery(name = "ComplaintProgress.findByRecordedDate", query = "SELECT c FROM ComplaintProgress c WHERE c.recordedDate = :recordedDate")})
public class ComplaintProgress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ACTION_TITLE")
    private String actionTitle;
    @Lob
    @Column(name = "ACTION_DESC")
    private String actionDesc;
    @Column(name = "ACTION_TYPE")
    private Integer actionType;
    @Column(name = "RECORDED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;
    @JoinColumn(name = "COMPLAINT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Complaint complaintId;
    @JoinColumn(name = "ACTION_TAKEN_BY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SysUser actionTakenBy;
    @JoinColumn(name = "COMPLAINT_OWNER", referencedColumnName = "ID")
    @ManyToOne
    private SysUser complaintOwner;

    public ComplaintProgress() {
    }

    public ComplaintProgress(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }

    public Complaint getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Complaint complaintId) {
        this.complaintId = complaintId;
    }

    public SysUser getActionTakenBy() {
        return actionTakenBy;
    }

    public void setActionTakenBy(SysUser actionTakenBy) {
        this.actionTakenBy = actionTakenBy;
    }

    public SysUser getComplaintOwner() {
        return complaintOwner;
    }

    public void setComplaintOwner(SysUser complaintOwner) {
        this.complaintOwner = complaintOwner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplaintProgress)) {
            return false;
        }
        ComplaintProgress other = (ComplaintProgress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.devsdp.wscms.complaint.model.ComplaintProgress[ id=" + id + " ]";
    }

}
