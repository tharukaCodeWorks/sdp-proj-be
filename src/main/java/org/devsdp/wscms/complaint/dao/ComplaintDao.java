package org.devsdp.wscms.complaint.dao;

import java.util.List;
import org.devsdp.wscms.complaint.model.Complaint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author aerok
 */
public interface ComplaintDao extends CrudRepository<Complaint, Integer> {

    @Query("SELECT c FROM Complaint c WHERE c.reportedBy.id = :userId")
    List<Complaint> findByUserId(int userId);
}
