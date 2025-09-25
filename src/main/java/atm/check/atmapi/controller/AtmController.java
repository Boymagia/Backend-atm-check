package atm.check.atmapi.controller;

import atm.check.atmapi.dto.AtmDTO;
import atm.check.atmapi.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atms")
public class AtmController {

    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    /**
     * Cria um novo ATM.
     * Endpoint: POST /api/atms
     *
     * @param atmDto O DTO do ATM a ser criado.
     * @return O DTO do ATM criado, com status 201 Created.
     */
    @PostMapping
    public ResponseEntity<AtmDTO> createAtm(@RequestBody AtmDTO atmDto) {
        AtmDTO createdAtm = atmService.createAtm(atmDto);
        return new ResponseEntity<>(createdAtm, HttpStatus.CREATED);
    }

    /**
     * Retorna uma lista de todos os ATMs.
     * Endpoint: GET /api/atms
     *
     * @return Uma lista de ATMDTOs.
     */
    @GetMapping
    public ResponseEntity<List<AtmDTO>> getAllAtms() {
        List<AtmDTO> atms = atmService.getAllAtms();
        return new ResponseEntity<>(atms, HttpStatus.OK);
    }

    /**
     * Busca um ATM pelo ID.
     * Endpoint: GET /api/atms/{id}
     *
     * @param id O ID do ATM.
     * @return O ATMDTO se encontrado, com status 200 OK, ou 404 Not Found se não.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AtmDTO> getAtmById(@PathVariable Integer id) {
        Optional<AtmDTO> atm = atmService.getAtmById(id);
        return atm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Atualiza um ATM existente.
     * Endpoint: PUT /api/atms/{id}
     *
     * @param id O ID do ATM a ser atualizado.
     * @param atmDto O DTO com os dados de atualização.
     * @return O ATMDTO atualizado se o ID for encontrado, com status 200 OK, ou 404 Not Found se não.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AtmDTO> updateAtm(@PathVariable Integer id, @RequestBody AtmDTO atmDto) {
        Optional<AtmDTO> updatedAtm = atmService.updateAtm(id, atmDto);
        return updatedAtm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Exclui um ATM pelo ID.
     * Endpoint: DELETE /api/atms/{id}
     *
     * @param id O ID do ATM a ser excluído.
     * @return Resposta sem conteúdo, com status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtm(@PathVariable Integer id) {
        atmService.deleteAtm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
