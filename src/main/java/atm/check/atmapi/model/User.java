package atm.check.atmapi.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admins")

public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name="nome", nullable=false)
private String nome;

@Column(unique = true, nullable=false)
private String email;

@Column(name="senha", nullable=false)
private String senha;

public User(){}

public User(String nome, String email, String senha){
    this.nome = nome;
    this.email = email;
    this.senha = senha;
}

}