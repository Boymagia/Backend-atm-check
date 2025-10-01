package atm.check.atmapi.dto;

import atm.check.atmapi.model.Atm;

public class AtmDTO {

    private Integer id;
    private String localizacao;
    private Double latitude;
    private Double longitude;
    private int dinheiro;
    private int papel;
    private int levantamentoSemCartao;
    private int sistema;

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

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public int getPapel() {
        return papel;
    }

    public void setPapel(int papel) {
        this.papel = papel;
    }

    public int getLevantamentoSemCartao() {
        return levantamentoSemCartao;
    }

    public void setLevantamentoSemCartao(int levantamentoSemCartao) {
        this.levantamentoSemCartao = levantamentoSemCartao;
    }

    public int getSistema() {
        return sistema;
    }

    public void setSistema(int sistema) {
        this.sistema = sistema;
    }
}
