package atm.check.atmapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import atm.check.atmapi.model.Agente;
import atm.check.atmapi.service.AgenteService;

@RestController
@RequestMapping("/agentes")
public class AgenteController {

    @Autowired
    private AgenteService agenteService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Agente agente) {
      
        Optional<Agente> foundAgente = agenteService.findByUsuarioAndSenha(agente.getUsuario(), agente.getSenha());
        
        if (foundAgente.isPresent()) {
            return ResponseEntity.ok("Login de agente bem-sucedido.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        }
    }

    @PostMapping
    public ResponseEntity<Agente> createAgente(@RequestBody Agente agente) {
        Agente novoAgente = agenteService.saveAgente(agente);
        return new ResponseEntity<>(novoAgente, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Agente> getAllAgentes() {
        return agenteService.findAllAgentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agente> getAgenteById(@PathVariable Integer id) {
        Optional<Agente> agente = agenteService.findAgenteById(id);
        return agente.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenteById(@PathVariable Integer id) {
        agenteService.deleteAgenteById(id);
        return ResponseEntity.noContent().build();
    }
}
