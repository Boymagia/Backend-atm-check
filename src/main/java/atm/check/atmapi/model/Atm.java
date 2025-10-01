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
    private int dinheiro;

    @Column(name = "papel")
    private int papel;

    @Column(name = "levantamento_sem_cartao")
    private int levantamentoSemCartao;

    @Column(name = "sistema")
    private int sistema;

    @ManyToOne
    @JoinColumn(name = "criado_por", referencedColumnName = "id")
    private Admin criadoPor;

    @ManyToOne
    @JoinColumn(name = "agente_id", referencedColumnName = "id")
    @JsonBackReference
    private Agente agente;

    // Construtor padrão
    public Atm() {
        this.dinheiro = 0;
        this.papel = 0;
        this.levantamentoSemCartao = 0;
        this.sistema = 0;
    }

    // Construtor com campos
    public Atm(String localizacao, Double latitude, Double longitude, int dinheiro, int papel, int levantamentoSemCartao, int sistema, Admin criadoPor, Agente agente) {
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
