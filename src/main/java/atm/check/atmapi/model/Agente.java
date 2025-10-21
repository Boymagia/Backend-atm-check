package atm.check.atmapi.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // IMPORTANTE!
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "agentes")
@EntityListeners(AuditingEntityListener.class)
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String usuario;

    // A senha será criptografada no Service
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false) 
    private int unidades;
    
    private String localizacao;
    private Double latitude;
    private Double longitude;
    @Column(name = "dinheiro", nullable = false)
    private Integer dinheiro;
    @Column(name = "papel", nullable = false)
    private Integer papel;
    @Column(name = "levantamento_sem_cartao", nullable = false)
    private Integer levantamentoSemCartao;
    @Column(name = "sistema", nullable = false)
    private Integer sistema;

    // --- CORREÇÃO AQUI ---
    // Hibernate estava a usar 'admin_id', mas a DB espera 'criado_por_admin_id'.
    // Usamos @JoinColumn para forçar o nome da coluna correto.
    @ManyToOne
    @JoinColumn(name = "criado_por_admin_id", nullable = false) 
    private Admin criadoPor;
    // -----------------------

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    //------
 
    
    
    // Construtores, Getters e Setters (Certifique-se de manter os seus originais)
    
    public Agente() {}

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Admin getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Admin criadoPor) {
        this.criadoPor = criadoPor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDinheiro(Integer dinheiro) {
        this.dinheiro = dinheiro;
    }
      public Integer getDinheiro() {
        return dinheiro;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
      public void setPapel(Integer papel) {
        this.papel = papel;
    }
      public Integer getPapel() {
        return papel;
    }
      public void setSistema(Integer sistema) {
        this.sistema = sistema ;
    }
      public Integer getSistema() {
        return sistema;
    }
      public void setLevantamento(Integer levantamentoSemCartao ) {
        this.levantamentoSemCartao = levantamentoSemCartao ;
    }
      public Integer getLevantamento() {
        return levantamentoSemCartao ;
    }
    public void setUnidades(Integer unidades ) {
        this.unidades = unidades;
    }
      public Integer getUnidades() {
        return unidades;
    }
}
