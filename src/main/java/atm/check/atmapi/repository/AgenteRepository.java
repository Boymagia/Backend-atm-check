package atm.check.atmapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atm.check.atmapi.model.Agente;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Integer> {
    
    Optional<Agente> findByUsuario(String usuario);
}
