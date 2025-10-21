package atm.check.atmapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atm.check.atmapi.dto.AgenteStatusUpdateDTO;
import atm.check.atmapi.exception.ResourceNotFoundException;
import atm.check.atmapi.model.AgenteStatus;
import atm.check.atmapi.repository.AgenteStatusRepository;
import jakarta.transaction.Transactional;

/**
 * Serviço responsável pela lógica de negócio da contagem e estado do Caixa Eletrônico (ATM).
 * Inclui o método principal para atualizar o estado de um agente (ATM) existente.
 */
@Service
public class AgenteStatusService {

    private final AgenteStatusRepository repository;

    @Autowired
    public AgenteStatusService(AgenteStatusRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Salva um novo estado de Agente (ATM) na base de dados.
     * @param agenteStatus O objeto AgenteStatus a ser salvo.
     * @return O objeto AgenteStatus salvo.
     */
    public AgenteStatus salvarAgenteStatus(AgenteStatus agenteStatus) {
        return repository.save(agenteStatus);
    }
    
    /**
     * Busca um AgenteStatus pelo ID.
     * @param id O ID do agente (ATM).
     * @return O objeto AgenteStatus.
     * @throws ResourceNotFoundException Se o ATM com o ID fornecido não for encontrado.
     */
    public AgenteStatus buscarAgenteStatus(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agente (ATM) não encontrado com o ID: " + id));
    }
    
    /**
     * Retorna uma lista de todos os AgenteStatus existentes na base de dados.
     * @return Uma lista de AgenteStatus.
     */
    public List<AgenteStatus> listarTodos() {
        return repository.findAll();
    }
    
    /**
     * Remove um AgenteStatus pelo ID.
     * @param id O ID do agente (ATM) a ser removido.
     * @throws ResourceNotFoundException Se o ATM com o ID fornecido não for encontrado.
     */
    @Transactional
    public void deletarAgenteStatus(Long id) {
        // Verifica se o ATM existe antes de tentar deletar
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agente (ATM) não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }


    /**
     * Atualiza o estado de um Caixa Eletrônico na base de dados.
     *
     * @param id O ID do agente (ATM) a ser atualizado.
     * @param dto O DTO contendo os novos dados a serem aplicados.
     * @return A entidade AgenteStatus atualizada.
     * @throws ResourceNotFoundException Se o ATM com o ID fornecido não for encontrado.
     */
    @Transactional
    public AgenteStatus atualizarEstado(Long id, AgenteStatusUpdateDTO dto) {
        // Tenta encontrar a entidade pelo ID. Se não encontrar, lança a exceção 404.
        AgenteStatus agenteExistente = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agente (ATM) não encontrado com o ID: " + id));

        // Aplica as atualizações do DTO à entidade existente
        if (dto.getDinheiro() != null) {
            agenteExistente.setDinheiro(dto.getDinheiro());
        }
        if (dto.getPapel() != null) {
            agenteExistente.setPapel(dto.getPapel());
        }
        if (dto.getLevantamentoSemCartao() != null) {
            agenteExistente.setLevantamentoSemCartao(dto.getLevantamentoSemCartao());
        }
       
        // Salva e retorna a entidade atualizada
        return repository.save(agenteExistente);
    }
}
