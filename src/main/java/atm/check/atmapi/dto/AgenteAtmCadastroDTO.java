package atm.check.atmapi.dto;
import atm.check.atmapi.model.AgenteStatus;
import atm.check.atmapi.model.Agente;

/**
 * DTO usado para o cadastro/atualização de um Agente, substituindo o campo
 * 'numeroDeAtms' (que estava envolvido na multiplicação) por 'unidades',
 * que representa o valor consolidado a ser usado.
 */
public class AgenteAtmCadastroDTO {

    // Nota: Por boas práticas de DTO, muitas vezes é melhor usar apenas o ID (String)
    // do agente em vez do objeto Agente completo, mas mantive o objeto
    // para não quebrar a estrutura existente do seu código.
    private Agente agente;


    public AgenteAtmCadastroDTO() {
    }

    // Getters e Setters
    
    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

}
