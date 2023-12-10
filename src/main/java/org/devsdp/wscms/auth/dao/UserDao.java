package org.devsdp.wscms.auth.dao;

import org.devsdp.wscms.auth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByPermissionsName(String permissionName);
    List<User> findByRoleId(long roleId);
    boolean existsByEmail(String email);
}
