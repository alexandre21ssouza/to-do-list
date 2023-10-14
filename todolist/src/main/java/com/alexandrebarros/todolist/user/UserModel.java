package com.alexandrebarros.todolist.user;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

	@Id
	@GeneratedValue(generator = "UUID")//"UUID" Tipo de Geração de número seguro e único
	private UUID id;
	
	//@Column(name= "usuario") essa anotação define o nome da coluna de "username" p/ "usuario" caso queira personalizar o nome
	private String userName;
	private String name;
	private String password;
	
	
		
	
}
