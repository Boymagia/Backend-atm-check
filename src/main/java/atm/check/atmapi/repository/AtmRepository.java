package atm.check.atmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atm.check.atmapi.model.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Integer> {
}