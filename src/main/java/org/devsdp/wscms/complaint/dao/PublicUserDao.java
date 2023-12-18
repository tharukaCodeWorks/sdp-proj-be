package org.devsdp.wscms.complaint.dao;

import org.devsdp.wscms.complaint.model.PublicUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author aerok
 */
public interface PublicUserDao extends CrudRepository<PublicUser, Integer> {
    @Query(value = "SELECT * FROM public_user WHERE user_id = ?1", nativeQuery = true)
    Optional<PublicUser> findByUserId(long userId);
}
