package atm.check.atmapi.dto;

import atm.check.atmapi.model.Atm;

public class AtmDTO {

    private Integer id;
    private String localizacao;
    private Double latitude;
    private Double longitude;
    
    // CORREÇÃO: Alterado de Boolean para Integer para aceitar 0/1 do modelo Atm.
    private Integer dinheiro;
    private Integer papel;
    private Integer levantamentoSemCartao;
    private Integer sistema;

    // Construtor padrão
    public AtmDTO() {
    }

    // Construtor a partir da entidade Atm
    public AtmDTO(Atm atm) {
        this.id = atm.getId();
        this.localizacao = atm.getLocalizacao();
        this.latitude = atm.getLatitude();
        this.longitude = atm.getLongitude();
        
        // Esta atribuição agora funciona, pois ambos são Integer.
        this.dinheiro = atm.getDinheiro();
        this.papel = atm.getPapel();
        this.levantamentoSemCartao = atm.getLevantamentoSemCartao();
        this.sistema = atm.getSistema();
    }

    // Getters e Setters (ajustados para Integer)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
