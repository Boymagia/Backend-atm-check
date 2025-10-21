package atm.check.atmapi.dto;

import atm.check.atmapi.model.Agente;

/**
 * DTO usado para transferência de dados de Agente e suas Unidades (Contagem/Status).
 * O campo 'unidades' substitui 'numeroDeAtms', simplificando a requisição para
 * que o front-end envie o valor final que deve ser persistido ou processado.
 */
public class AgenteAtmsDTO {

    private Agente agente;
    private Integer unidades;
     // Campo consolidado que substitui 'numeroDeAtms'

    public AgenteAtmsDTO() {
    }

    // Getters e Setters
    
    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }
}
