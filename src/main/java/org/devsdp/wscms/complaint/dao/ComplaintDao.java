package org.devsdp.wscms.complaint.dao;

import org.devsdp.wscms.complaint.model.Complaint;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author aerok
 */
public interface ComplaintDao extends CrudRepository<Complaint, Integer> {

}
