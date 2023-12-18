package org.devsdp.wscms.complaint.model;

import lombok.*;
import org.devsdp.wscms.auth.model.User;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "public_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublicUser.findAll", query = "SELECT p FROM PublicUser p"),
    @NamedQuery(name = "PublicUser.findById", query = "SELECT p FROM PublicUser p WHERE p.id = :id"),
    @NamedQuery(name = "PublicUser.findByFirstname", query = "SELECT p FROM PublicUser p WHERE p.firstname = :firstname")})
public class PublicUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedBy")
    private List<Complaint> complaintList;

}
