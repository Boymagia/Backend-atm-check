package atm.check.atmapi.dto;

import atm.check.atmapi.model.Agente;


public class AgenteAtmsDTO {
    private Agente agente;
    private Integer numeroDeAtms;

  
    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Integer getNumeroDeAtms() {
        return numeroDeAtms;
    }

    public void setNumeroDeAtms(Integer numeroDeAtms) {
        this.numeroDeAtms = numeroDeAtms;
    }
}
