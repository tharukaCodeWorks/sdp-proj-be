package org.devsdp.wscms.complaint.dao;

import java.util.List;
import org.devsdp.wscms.complaint.model.ComplaintProgress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author aerok
 */
public interface ComplaintProgressDao extends CrudRepository<ComplaintProgress, Integer> {

    @Query("SELECT c FROM ComplaintProgress c WHERE c.complaintId.id = :complaintId")
    public List<ComplaintProgress> getComplaintProgressByCompId(int complaintId);
}
