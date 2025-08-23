package atm.check.atmapi.dto;

import java.util.List;

public class AgenteAtmCadastroDTO {

  
    private String nomeAgente;
    private String usuario;
    private String senhaAgente;
    private Integer criadoPor; 

   
    private String localizacao;
    private Double latitude;
    private Double longitude;

    
    private List<AtmDTO> atms;

   
    public AgenteAtmCadastroDTO() {
    }

    
    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenhaAgente() {
        return senhaAgente;
    }

    public void setSenhaAgente(String senhaAgente) {
        this.senhaAgente = senhaAgente;
    }

    public Integer getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Integer criadoPor) {
        this.criadoPor = criadoPor;
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

    public List<AtmDTO> getAtms() {
        return atms;
    }

    public void setAtms(List<AtmDTO> atms) {
        this.atms = atms;
    }

    
    public static class AtmDTO {
        
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
