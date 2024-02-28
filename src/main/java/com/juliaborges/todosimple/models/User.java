package com.juliaborges.todosimple.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name=User.TABLE_NAME) // Criando uma tabela e atribuindo User como uma Entidade
public class User {
   public interface CreateUser {}
   public interface UpdateUser {}
    public static final String TABLE_NAME = "user";

    //Atributos do Usuário 
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Estratégia -> AutoIncrement
    @Column(name = "id", unique = true) //Unique para nunca duplicar valor 
    private Long id;  
  
    @Column(name = "username", length = 100,nullable = false, unique = true)//Gera configurações do Banco de Dados
    @NotNull(groups = CreateUser.class)//Quando eu for criar um usuário -> não pode ser nulo 
    @NotEmpty(groups = CreateUser.class) //Nao pode ser vazio
    @Size(groups = CreateUser.class, min=2,max=100)
    private String username; 
   
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", length = 60,nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups ={CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class},min=7,max=20)
    private String password; 
   
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    //Métodos

    public User() {
}
    

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof User)){
            return false;
        }
        User other = (User) obj; 
        if (this.id == null)
        if (other.id !=null)
        return false;
        else if (!this.id.equals(other.id))
        return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username) && Objects.equals(this.password,other.password);

    
    }
}
