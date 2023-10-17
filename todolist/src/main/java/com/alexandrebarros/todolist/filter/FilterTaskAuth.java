package com.alexandrebarros.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alexandrebarros.todolist.user.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//Toda esta parte é testada com o Postman, Filtro para validar usuário e senha do "task"
//Pode ser Component ou RestController
// aqui primeiro para serem filtradas
// Anotação mais genérica do Spring, onde indica que as infos tem que passar
@Component
public class FilterTaskAuth extends OncePerRequestFilter {

	@Autowired
	private IUserRepository userRepository;

	// Filtra as informações do user, usadas no HttpServelet
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var servletPath = request.getServletPath();
		
		//vai verificar se a rota começa com: "/task/" senão a lógica não funciona
		if(servletPath.startsWith("/tasks/")) {

		// Pegar a autenticação (usuário e senha) no Postman no Auth(melhor) ou Header
		var authorization = request.getHeader("Authorization");

		// Vai contar os caracteres e retirar os espaços da senha Criptografada
		var authEncoded = authorization.substring("Basic".length()).trim();
		
		//Gera um array de bytes, decodificando a senha e usuário
		byte[] authDecode = Base64.getDecoder().decode(authEncoded);
		
		//Transforma em String o authDecode
		var authString = new String(authDecode);
		
		System.out.println("Authorization");
		System.out.println(authString);
		
		//Faz um array para fazer a separação do usuário e senha no print de: (alx123) em ("alx, 123")
		String[] credentials = authString.split(":");
		String username = credentials[0];
		String password = credentials[1];
		

		// Validar usuário, se está no banco de dados, se tiver valida a senha
		var user = this.userRepository.findByUserName(username);
		if(user == null) {
			response.sendError(401);// Erro - ("Usuário sem autorização");
		} else {
		// validar senha, comparando a enviada com a do banco de dados
			var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
			//Se for igual a true, avança para o filterChain
			if(passwordVerify.verified) {
				request.setAttribute("idUser", user.getId());
				filterChain.doFilter(request, response);
			} else {
				response.sendError(401);
			}
		
		    }
      } else { 
	  			filterChain.doFilter(request, response);
    }

  }
}
