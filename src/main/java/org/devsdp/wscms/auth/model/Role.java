package org.devsdp.wscms.auth.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions;

}
