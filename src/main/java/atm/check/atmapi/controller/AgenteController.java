package atm.check.atmapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import atm.check.atmapi.dto.AgenteAtmCadastroDTO;
import atm.check.atmapi.model.Agente;
import atm.check.atmapi.service.AgenteService;

@RestController
@RequestMapping("/agentes")
public class AgenteController {

    @Autowired
    private AgenteService agenteService;

    // Endpoint para cadastro de novo agente com seus ATMs
    // Exemplo: POST /agentes/cadastro?adminId=1
    @PostMapping("/cadastro")
    public ResponseEntity<Agente> cadastrarAgenteComAtms(@RequestBody AgenteAtmCadastroDTO dto,
                                                        @RequestParam("adminId") Integer adminId) {
        try {
            Agente novoAgente = agenteService.cadastrarAgenteComAtms(dto, adminId);
            return new ResponseEntity<>(novoAgente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para login de Agente
   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Agente agente) {
        Optional<Agente> foundAgente = agenteService.findByUsuarioAndSenha(agente.getUsuario(), agente.getSenha());
        
        if (foundAgente.isPresent()) {
            return ResponseEntity.ok(foundAgente.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        }
    }
}
