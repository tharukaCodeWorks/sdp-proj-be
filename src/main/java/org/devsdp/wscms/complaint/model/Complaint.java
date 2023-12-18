package org.devsdp.wscms.complaint.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "complaint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complaint.findAll", query = "SELECT c FROM Complaint c"),
    @NamedQuery(name = "Complaint.findById", query = "SELECT c FROM Complaint c WHERE c.id = :id"),
    @NamedQuery(name = "Complaint.findByComplaintTitle", query = "SELECT c FROM Complaint c WHERE c.complaintTitle = :complaintTitle"),
    @NamedQuery(name = "Complaint.findByReportedDate", query = "SELECT c FROM Complaint c WHERE c.reportedDate = :reportedDate"),
    @NamedQuery(name = "Complaint.findByStatus", query = "SELECT c FROM Complaint c WHERE c.status = :status"),
    @NamedQuery(name = "Complaint.findByPriority", query = "SELECT c FROM Complaint c WHERE c.priority = :priority")})
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "COMPLAINT_TITLE")
    private String complaintTitle;
    @Lob
    @Column(name = "COMPLAINT_DESC")
    private String complaintDesc;
    @Column(name = "REPORTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date reportedDate;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "PRIORITY")
    private Integer priority;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "complaintId")
    private List<ComplaintProgress> complaintProgressList;
    @JoinColumn(name = "DIVISION", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Division division;
    @JoinColumn(name = "REPORTED_BY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PublicUser reportedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "complaintId")
    private List<ComplaintImg> complaintImgList;
}
