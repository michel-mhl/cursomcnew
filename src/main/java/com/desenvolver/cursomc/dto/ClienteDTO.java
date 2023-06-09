package com.desenvolver.cursomc.dto;

import com.desenvolver.cursomc.domain.Cliente;
import com.desenvolver.cursomc.services.validation.ClienteInsert;
import com.desenvolver.cursomc.services.validation.ClienteUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull(message = "Preenchimento Obrigatório")
    @Size(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;
    @NotNull(message = "Preenchimento Obrigatório")
    @Email(message = "Email invalido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
