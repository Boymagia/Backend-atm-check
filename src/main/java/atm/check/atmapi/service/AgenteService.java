package atm.check.atmapi.service;

import atm.check.atmapi.dto.AgenteAtmCadastroDTO;
import atm.check.atmapi.model.Admin;
import atm.check.atmapi.model.Agente;
import atm.check.atmapi.model.Atm;
import atm.check.atmapi.repository.AdminRepository;
import atm.check.atmapi.repository.AgenteRepository;
import atm.check.atmapi.repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

@Service
public class AgenteService {

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AtmRepository atmRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Métodos para Agente
    public List<Agente> findAllAgentes() {
        return agenteRepository.findAll();
    }

    public Optional<Agente> findAgenteById(Integer id) {
        return agenteRepository.findById(id);
    }

    public Agente saveAgente(Agente agente) {
        agente.setSenha(passwordEncoder.encode(agente.getSenha()));
        return agenteRepository.save(agente);
    }

    public void deleteAgenteById(Integer id) {
        agenteRepository.deleteById(id);
    }

    public Optional<Agente> findByUsuarioAndSenha(String usuario, String senha) {
        Optional<Agente> agenteOptional = agenteRepository.findByUsuario(usuario);
        if (agenteOptional.isPresent() && passwordEncoder.matches(senha, agenteOptional.get().getSenha())) {
            return agenteOptional;
        }
        return Optional.empty();
    }

    public Agente createAgente(Agente agente, Integer adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isPresent()) {
            agente.setCriadoPor(admin.get());
            agente.setSenha(passwordEncoder.encode(agente.getSenha()));
            return agenteRepository.save(agente);
        } else {
            throw new RuntimeException("Admin não encontrado com o ID: " + adminId);
        }
    }

    public Atm createAtm(Atm atm, Integer agenteId, Integer adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);
        Optional<Agente> agente = agenteRepository.findById(agenteId);

        if (admin.isPresent() && agente.isPresent()) {
            atm.setCriadoPor(admin.get());
            atm.setAgente(agente.get());
            return atmRepository.save(atm);
        } else {
            throw new RuntimeException("Admin ou Agente não encontrados.");
        }
    }

    // NOVO MÉTODO: Cadastra um novo agente com ATMs
    public Agente cadastrarAgenteComAtms(AgenteAtmCadastroDTO dto, Integer adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isEmpty()) {
            throw new RuntimeException("Admin não encontrado com o ID: " + adminId);
        }

        // 1. Salva o Agente
        Agente novoAgente = dto.getAgente();
        novoAgente.setCriadoPor(admin.get());
        novoAgente.setSenha(passwordEncoder.encode(novoAgente.getSenha()));
        agenteRepository.save(novoAgente);

        // 2. Cria e salva os ATMs baseados na localização do agente
        Set<Atm> atms = new HashSet<>();
        for (int i = 0; i < dto.getNumeroDeAtms(); i++) {
            Atm novoAtm = new Atm();
            novoAtm.setLocalizacao(novoAgente.getLocalizacao());
            novoAtm.setLatitude(novoAgente.getLatitude());
            novoAtm.setLongitude(novoAgente.getLongitude());
            
            novoAtm.setAgente(novoAgente);
            novoAtm.setCriadoPor(admin.get());
            atms.add(novoAtm);
            atmRepository.save(novoAtm); 
        }
        
        // 3. Associa os ATMs criados ao agente e atualiza-o
        novoAgente.setAtms(atms);
        return agenteRepository.save(novoAgente);
    }
}
