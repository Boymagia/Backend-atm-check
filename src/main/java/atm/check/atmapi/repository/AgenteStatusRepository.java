package atm.check.atmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atm.check.atmapi.model.AgenteStatus;

/**
 * Repositório para a entidade AgenteStatus.
 * O Long como segundo parâmetro define o tipo da chave primária (ID),
 * garantindo consistência com a entidade AgenteStatus e o serviço.
 */
@Repository
public interface AgenteStatusRepository extends JpaRepository<AgenteStatus, Long> {

    // Spring Data JPA fornece automaticamente implementações para:
    // save(), findById(), findAll(), deleteById(), existsById(), etc.

}