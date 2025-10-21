package atm.check.atmapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class AgenteStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ✓ Long

    @NotNull(message = "O status 'dinheiro' é obrigatório.")
    private Integer dinheiro;

    @NotNull(message = "O status 'papel' é obrigatório.")
    private Integer papel;

    @NotNull(message = "O status 'levantamentoSemCartao' é obrigatório.")
    private Integer levantamentoSemCartao;

    @NotNull(message = "O status 'sistema' é obrigatório.")
    private Integer sistema;

    // Construtor vazio
    public AgenteStatus() {
    }

    // ✓ CORRETO: Getters e Setters usando Long
    public Long getId() {  // ← Mudou de Integer para Long
        return id;
    }

    public void setId(Long id) {  // ← Mudou de Integer para Long
        this.id = id;
    }

    public Integer getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Integer dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Integer getPapel() {
        return papel;
    }

    public void setPapel(Integer papel) {
        this.papel = papel;
    }

    public Integer getLevantamentoSemCartao() {
        return levantamentoSemCartao;
    }

    public void setLevantamentoSemCartao(Integer levantamentoSemCartao) {
        this.levantamentoSemCartao = levantamentoSemCartao;
    }

    public Integer getSistema() {
        return sistema;
    }

    public void setSistema(Integer sistema) {
        this.sistema = sistema;
    }
}