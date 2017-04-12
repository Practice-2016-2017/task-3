package com.roi.dao;
import com.roi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by alexander-k on 12.04.17.
 */
public interface RoleDao extends JpaRepository<Role, Long>{

}
