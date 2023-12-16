package org.devsdp.wscms.complaint.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aerok
 */
@Entity
@Table(name = "complaint_img")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplaintImg.findAll", query = "SELECT c FROM ComplaintImg c"),
    @NamedQuery(name = "ComplaintImg.findById", query = "SELECT c FROM ComplaintImg c WHERE c.id = :id"),
    @NamedQuery(name = "ComplaintImg.findByImgPath", query = "SELECT c FROM ComplaintImg c WHERE c.imgPath = :imgPath")})
public class ComplaintImg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "IMG_PATH")
    private String imgPath;
    @JoinColumn(name = "COMPLAINT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Complaint complaintId;

    public ComplaintImg() {
        super();
    }

    public ComplaintImg(String imgPath, Complaint complaintId) {
        this.imgPath = imgPath;
        this.complaintId = complaintId;
    }

    public ComplaintImg(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Complaint getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Complaint complaintId) {
        this.complaintId = complaintId;
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
        if (!(object instanceof ComplaintImg)) {
            return false;
        }
        ComplaintImg other = (ComplaintImg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.devsdp.wscms.complaint.model.ComplaintImg[ id=" + id + " ]";
    }

}
