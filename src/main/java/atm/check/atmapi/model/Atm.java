package atm.check.atmapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "atms")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String localizacao;

    private Double latitude;

    private Double longitude;

    @Column(name = "dinheiro")
    private Boolean dinheiro;

    @Column(name = "papel")
    private Boolean papel;

    @Column(name = "levantamento_sem_cartao")
    private Boolean levantamentoSemCartao;

    @Column(name = "sistema")
    private Boolean sistema;

    @ManyToOne
    @JoinColumn(name = "criado_por", referencedColumnName = "id")
    private Admin criadoPor;

    @ManyToOne
    @JoinColumn(name = "agente_id", referencedColumnName = "id")
    @JsonBackReference
    private Agente agente;

    // Construtor padrão
    public Atm() {
        this.dinheiro = false;
        this.papel = false;
        this.levantamentoSemCartao = false;
        this.sistema = false;
    }

    // Construtor com campos
    public Atm(String localizacao, Double latitude, Double longitude, Boolean dinheiro, Boolean papel, Boolean levantamentoSemCartao, Boolean sistema, Admin criadoPor, Agente agente) {
        this.localizacao = localizacao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dinheiro = dinheiro;
        this.papel = papel;
        this.levantamentoSemCartao = levantamentoSemCartao;
        this.sistema = sistema;
        this.criadoPor = criadoPor;
        this.agente = agente;
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

    public Admin getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Admin criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }
}
