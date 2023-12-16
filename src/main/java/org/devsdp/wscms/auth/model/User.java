package org.devsdp.wscms.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.devsdp.wscms.complaint.model.Division;
import org.devsdp.wscms.complaint.model.PublicUser;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String avatar;
    @Column
    private String emailVerifyCode;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @JsonIgnore
    private String passwordResetCode;
    @Column
    private String isEmailVerified;
    @OneToOne
    private Role role;
    private String gender;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PERMISSIONS", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "PERMISSION_ID") })
    @JsonIgnore
    private Set<Permission> permissions;


}
