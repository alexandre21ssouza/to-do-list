package com.alexandrebarros.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.print.attribute.standard.DateTimeAtCreation;

import org.hibernate.annotations.CreationTimestamp;

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
	@Column(unique = true)// Determina que os dados sejam únicos no banco(constrain)
	private String userName;
	private String name;
	private String password;
	
	@CreationTimestamp //Essa anotação salva a data da criação do User
	private LocalDateTime createdAt;
	
	
		
	
}
