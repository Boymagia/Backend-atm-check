package atm.check.atmapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "agentes")
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String usuario;

    private String senha;

    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "criado_por", referencedColumnName = "id")
    private Admin criadoPor;

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

    public Admin getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Admin criadoPor) {
        this.criadoPor = criadoPor;
    }
}
