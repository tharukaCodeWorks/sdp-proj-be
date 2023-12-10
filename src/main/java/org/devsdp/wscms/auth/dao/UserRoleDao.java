package org.devsdp.wscms.auth.dao;

import org.devsdp.wscms.auth.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends CrudRepository<Role, Long> {
}
