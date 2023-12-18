package org.devsdp.wscms.complaint.dao;

import org.devsdp.wscms.complaint.dao.projection.DivisionDataProjection;
import org.devsdp.wscms.complaint.model.Division;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author aerok
 */
public interface DivisionDao extends CrudRepository<Division, Integer> {
    @Query(value = "SELECT d.id, d.name as divisionName, d.province, dep.id as departmentId, dep.name as departmentName " +
            "FROM division d " +
            "JOIN department dep ON d.category_id = dep.id", nativeQuery = true)
    List<DivisionDataProjection> findAllDivisionWithDepartment();
}
