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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import atm.check.atmapi.dto.AgenteAtmCadastroDTO;
import atm.check.atmapi.model.Agente;
import atm.check.atmapi.service.AgenteService;

@RestController
@RequestMapping("/agentes")
public class AgenteController {

    @Autowired
    private AgenteService agenteService;

    // Endpoint para buscar todos os agentes
    @GetMapping
    public ResponseEntity<List<Agente>> getAllAgentes() {
        List<Agente> agentes = agenteService.findAllAgentes();
        return ResponseEntity.ok(agentes);
    }
    
    // Endpoint para buscar um agente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Agente> getAgenteById(@PathVariable Integer id) {
        Optional<Agente> agente = agenteService.findAgenteById(id);
        return agente.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para retornar a contagem de ATMs para um agente específico.
    // Exemplo: GET /agentes/1/atm-count
    @GetMapping("/{id}/cliente-")
    public ResponseEntity<Long> getAtmCountForAgente(@PathVariable Integer id) {
        try {
            Long atmCount = agenteService.countAtmsForAgente(id);
            // Retorna o número de ATMs com status 200 OK
            return ResponseEntity.ok(atmCount);
        } catch (IllegalArgumentException e) {
            // Se o Agente não for encontrado no serviço, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para cadastro de novo agente com seus ATMs
    // Exemplo: POST /agentes/cadastro?adminId=1
    @PostMapping("/cadastro")
    public ResponseEntity<Agente> cadastrarAgenteComAtms(@RequestBody AgenteAtmCadastroDTO dto,
                                                         @RequestParam("adminId") Integer adminId) {
        try {
            // Se o serviço for bem-sucedido, retorna 201 Created
            Agente novoAgente = agenteService.cadastrarAgenteComAtms(dto, adminId);
            return new ResponseEntity<>(novoAgente, HttpStatus.CREATED);
            
        } catch (IllegalArgumentException e) {
            // Captura erros de validação (Admin não encontrado, DTO inválido, etc.)
            // Retorna um status HTTP 400 Bad Request com a mensagem de erro
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            
        } catch (Exception e) {
            // Captura qualquer outro erro inesperado e retorna 500 Internal Server Error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno ao cadastrar Agente e ATMs.", e);
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

    // Endpoint para atualizar um agente existente
    // Rota: PUT /agentes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Agente> updateAgente(@PathVariable Integer id, @RequestBody Agente agenteAtualizado) {
        Optional<Agente> agente = agenteService.updateAgente(id, agenteAtualizado);
        
        // Retorna 200 OK se atualizado, ou 404 Not Found se o agente não existir.
        return agente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenteById(@PathVariable Integer id) {
        // Assume-se que o serviço irá lançar uma exceção (ou você pode adicionar
        // uma verificação de existência aqui) se o ID não for encontrado.
        agenteService.deleteAgenteById(id);
        return ResponseEntity.noContent().build();
    }
}
