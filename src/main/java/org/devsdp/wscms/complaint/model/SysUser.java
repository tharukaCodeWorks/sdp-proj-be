package org.devsdp.wscms.complaint.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sys_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysUser.findAll", query = "SELECT s FROM SysUser s"),
    @NamedQuery(name = "SysUser.findById", query = "SELECT s FROM SysUser s WHERE s.id = :id"),
    @NamedQuery(name = "SysUser.findByFirstName", query = "SELECT s FROM SysUser s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "SysUser.findByUsertype", query = "SELECT s FROM SysUser s WHERE s.usertype = :usertype")})
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "USERTYPE")
    private String usertype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actionTakenBy")
    private List<ComplaintProgress> complaintProgressList;
    @OneToMany(mappedBy = "complaintOwner")
    private List<ComplaintProgress> complaintProgressList1;
    @JoinColumn(name = "DIVISION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Division divisionId;
    
}
