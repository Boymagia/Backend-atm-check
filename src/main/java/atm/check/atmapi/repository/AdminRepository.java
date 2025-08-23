package atm.check.atmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atm.check.atmapi.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
   
}

