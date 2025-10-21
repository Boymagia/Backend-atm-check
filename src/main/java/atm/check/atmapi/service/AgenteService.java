package atm.check.atmapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import atm.check.atmapi.dto.AgenteAtmCadastroDTO;
import atm.check.atmapi.model.Admin;
import atm.check.atmapi.model.Agente;
import atm.check.atmapi.model.Atm;
import atm.check.atmapi.repository.AdminRepository;
import atm.check.atmapi.repository.AgenteRepository;
import atm.check.atmapi.repository.AtmRepository;

@Service
public class AgenteService {

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private AtmRepository atmRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Agente cadastrarAgenteComAtms(AgenteAtmCadastroDTO dto, Integer adminId) {
        // Obter o objeto Agente e o número de unidades (ATMs) diretamente do DTO
        Agente agente = dto.getAgente();
        // ALTERADO: Usando getUnidades() em vez de getNumeroDeAtms()
        

        // Verificações
        // ALTERADO: Usando 'unidades'
        
        
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado."));

        // Criptografar a senha do agente antes de salvar
        String senhaCriptografada = passwordEncoder.encode(agente.getSenha());
        agente.setSenha(senhaCriptografada);
        agente.setCriadoPor(admin);
        agenteRepository.save(agente);

        // Criar a lista de ATMs com base no número de unidades fornecido
        List<Atm> atmsParaSalvar = new ArrayList<>();
        // ALTERADO: Usando 'unidades'
        

        atmRepository.saveAll(atmsParaSalvar);

        return agente;
    }

    @Transactional
    public Optional<Agente> updateAgente(Integer id, Agente agenteAtualizado) {
        return agenteRepository.findById(id).map(agenteExistente -> {
            // Adicionado verificação de nulidade para cada campo a ser atualizado
            if (agenteAtualizado.getNome() != null) {
                agenteExistente.setNome(agenteAtualizado.getNome());
            }
            if (agenteAtualizado.getUsuario() != null) {
                agenteExistente.setUsuario(agenteAtualizado.getUsuario());
            }
            if (agenteAtualizado.getLocalizacao() != null) {
                agenteExistente.setLocalizacao(agenteAtualizado.getLocalizacao());
            }
            if (agenteAtualizado.getLatitude() != null) {
                agenteExistente.setLatitude(agenteAtualizado.getLatitude());
            }
            if (agenteAtualizado.getLongitude() != null) {
                agenteExistente.setLongitude(agenteAtualizado.getLongitude());
            }
            if (agenteAtualizado.getSenha() != null && !agenteAtualizado.getSenha().isEmpty()) {
                agenteExistente.setSenha(passwordEncoder.encode(agenteAtualizado.getSenha()));
            }
            if (agenteAtualizado.getDinheiro() != null) {
                agenteExistente.setDinheiro(agenteAtualizado.getDinheiro());
            }
              if (agenteAtualizado.getPapel() != null) {
                agenteExistente.setPapel(agenteAtualizado.getPapel());
            }
            if (agenteAtualizado.getSistema() != null) {
                agenteExistente.setSistema(agenteAtualizado.getSistema());
            }
               if (agenteAtualizado.getLevantamento() != null) {
                agenteExistente.setLevantamento(agenteAtualizado.getLevantamento());
            }
            if (agenteAtualizado.getUnidades() != null) {
                agenteExistente.setUnidades(agenteAtualizado.getUnidades());
            }
            return agenteRepository.save(agenteExistente);
        });
    }

    /**
     * Retorna o número de ATMs associados a um agente específico.
     * @param agenteId O ID do Agente.
     * @return O número de ATMs para aquele agente.
     * @throws IllegalArgumentException Se o Agente com o ID fornecido não for encontrado.
     */
    public Long countAtmsForAgente(Integer agenteId) {
        if (!agenteRepository.existsById(agenteId)) {
            // É melhor lançar uma exceção de que o recurso não existe. O Controller deve
            // converter isso num HTTP 404 Not Found.
            throw new IllegalArgumentException("Agente com ID " + agenteId + " não encontrado.");
        }
        return agenteRepository.countAtmsByAgenteId(agenteId);
    }
    
    @Transactional
    public Optional<Agente> findAgenteById(Integer id) {
        return agenteRepository.findById(id);
    }
    
    public Optional<Agente> findByUsuarioAndSenha(String usuario, String senha) {
        Optional<Agente> agenteOptional = agenteRepository.findByUsuario(usuario);
        if (agenteOptional.isPresent()) {
            Agente agente = agenteOptional.get();
            if (passwordEncoder.matches(senha, agente.getSenha())) {
                return Optional.of(agente);
            }
        }
        return Optional.empty();
    }
    
    public List<Agente> findAllAgentes() {
        return agenteRepository.findAll();
    }
    
    public void deleteAgenteById(Integer id) {
        agenteRepository.deleteById(id);
    }
}
