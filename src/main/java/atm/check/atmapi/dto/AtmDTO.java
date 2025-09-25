package atm.check.atmapi.dto;

import atm.check.atmapi.model.Atm;

public class AtmDTO {

    private Integer id;
    private String localizacao;
    private Double latitude;
    private Double longitude;
    private Boolean dinheiro;
    private Boolean papel;
    private Boolean levantamentoSemCartao;
    private Boolean sistema;

    // Construtor padrão
    public AtmDTO() {
    }

    // Construtor a partir da entidade Atm
    public AtmDTO(Atm atm) {
        this.id = atm.getId();
        this.localizacao = atm.getLocalizacao();
        this.latitude = atm.getLatitude();
        this.longitude = atm.getLongitude();
        this.dinheiro = atm.getDinheiro();
        this.papel = atm.getPapel();
        this.levantamentoSemCartao = atm.getLevantamentoSemCartao();
        this.sistema = atm.getSistema();
    }

    // Getters e Setters
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

    public Boolean getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Boolean dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Boolean getPapel() {
        return papel;
    }

    public void setPapel(Boolean papel) {
        this.papel = papel;
    }

    public Boolean getLevantamentoSemCartao() {
        return levantamentoSemCartao;
    }

    public void setLevantamentoSemCartao(Boolean levantamentoSemCartao) {
        this.levantamentoSemCartao = levantamentoSemCartao;
    }

    public Boolean getSistema() {
        return sistema;
    }

    public void setSistema(Boolean sistema) {
        this.sistema = sistema;
    }
}
