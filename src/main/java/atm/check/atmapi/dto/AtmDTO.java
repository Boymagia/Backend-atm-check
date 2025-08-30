package atm.check.atmapi.dto;

import atm.check.atmapi.enums.EstadoAtm;
import java.util.List;

public class AtmDTO {
    private String nrSerie;
    private String modelo;
    private String localizacao;
    private EstadoAtm estado;
    private List<String> bancos;

    // Construtor padrão
    public AtmDTO() {}

    // Construtor com todos os campos
    public AtmDTO(String nrSerie, String modelo, String localizacao, EstadoAtm estado, List<String> bancos) {
        this.nrSerie = nrSerie;
        this.modelo = modelo;
        this.localizacao = localizacao;
        this.estado = estado;
        this.bancos = bancos;
    }

    // Getters e Setters
    public String getNrSerie() {
        return nrSerie;
    }

    public void setNrSerie(String nrSerie) {
        this.nrSerie = nrSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public EstadoAtm getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtm estado) {
        this.estado = estado;
    }

    public List<String> getBancos() {
        return bancos;
    }

    public void setBancos(List<String> bancos) {
        this.bancos = bancos;
    }
}

