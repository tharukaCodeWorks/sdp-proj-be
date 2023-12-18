package org.devsdp.wscms.complaint.dao;

import org.devsdp.wscms.complaint.dao.projection.DepartmentDataProjection;
import org.devsdp.wscms.complaint.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author aerok
 */
public interface DepartmentDao extends CrudRepository<Department, Integer> {
    @Query(value = "SELECT d.id, d.name FROM department d", nativeQuery = true)
    List<DepartmentDataProjection> findIdAndName();
}
