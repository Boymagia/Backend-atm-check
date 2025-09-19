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
        // Obter o objeto Agente e o número de ATMs diretamente do DTO
        Agente agente = dto.getAgente();
        Integer numeroDeAtms = dto.getNumeroDeAtms();

        // Verificações
        if (agente == null || numeroDeAtms == null || numeroDeAtms <= 0) {
            throw new IllegalArgumentException("O agente e o número de ATMs devem ser válidos.");
        }
        
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado."));

        // Criptografar a senha do agente antes de salvar
        String senhaCriptografada = passwordEncoder.encode(agente.getSenha());
        agente.setSenha(senhaCriptografada);
        agente.setCriadoPor(admin);
        agenteRepository.save(agente);

        // Criar a lista de ATMs com base no número fornecido
        List<Atm> atmsParaSalvar = new ArrayList<>();
        for (int i = 0; i < numeroDeAtms; i++) {
            Atm atm = new Atm();
            atm.setLocalizacao(agente.getLocalizacao());
            atm.setLatitude(agente.getLatitude());
            atm.setLongitude(agente.getLongitude());
            atm.setCriadoPor(admin);
            atm.setAgente(agente);
            atmsParaSalvar.add(atm);
        }

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
            return agenteRepository.save(agenteExistente);
        });
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
