package atm.check.atmapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import atm.check.atmapi.model.Agente;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Integer> {

    // Método que o utilizador forneceu (findByUsuario)
    Optional<Agente> findByUsuario(String usuario);

    // Método reintroduzido para o login (findByUsuarioAndSenha)
    Optional<Agente> findByUsuarioAndSenha(String usuario, String senha);

    // NOVO MÉTODO: Conta o número de ATMs associados a um agente específico.
    // O filtro "WHERE a.agente.id = :agenteId" garante que a contagem é individual,
    // apenas para o agente com o ID fornecido.
    @Query("SELECT COUNT(a) FROM Atm a WHERE a.agente.id = :agenteId")
    Long countAtmsByAgenteId(@Param("agenteId") Integer agenteId);
}