package atm.check.atmapi.dto;

import java.time.LocalDateTime;

/**
 * DTO para retornar o status detalhado dos componentes do ATM:
 * dinheiro, levantamentoSemCartao, papel e sistemas.
 */
public class AgenteStatusUpdateDTO {
    private final Integer dinheiro;
    private final Integer levantamentoSemCartao;
    private final Integer papel;
    private final Integer sistemas;
    private final Integer unidades;
    private final LocalDateTime timestamp;

    public AgenteStatusUpdateDTO(Integer dinheiro, Integer levantamentoSemCartao, Integer papel, Integer sistemas, Integer unidades) {
        this.dinheiro = dinheiro;
        this.levantamentoSemCartao = levantamentoSemCartao;
        this.papel = papel;
        this.sistemas = sistemas;
        this.unidades = unidades;
        this.timestamp = LocalDateTime.now();
    }

    // Getters (mantendo os nomes em português para a resposta final)
    public Integer getDinheiro() {
        return dinheiro;
    }

    public Integer getLevantamentoSemCartao() {
        return levantamentoSemCartao;
    }

    public Integer getPapel() {
        return papel;
    }

    public Integer getSistemas() {
        return sistemas;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public Integer getUnidades() {
        return unidades;
    }
}