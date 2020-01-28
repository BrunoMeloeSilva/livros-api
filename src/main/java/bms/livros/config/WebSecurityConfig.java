package bms.livros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth ) throws Exception {
		auth.inMemoryAuthentication().withUser("mestre").password("{noop}123456").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		http.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //Passo1/2: Para permitir chamadas via js no chrome.
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().csrf().disable();
		
	}

}

/*
 * 1. Ao informar a dependencia no POM "spring-boot-starter-security"
 ****** Já obriga a existencia de uma chave para acessar as APIs do WS, mas o erro apresentado será
 ****** generico e sem detalhes no Body do envelope http. Somente será informado 401 Unauthorized.
 *
 * 2. Ao implementar a classe acima somente com o metodo sobrescrito configure(HttpSecurity)
 ****** O erro apresentado ao tentar logar sem a chave de acesso, já possui um body com o detalhe do erro.
 *
 * 3. Ao "implementar" o metodo configureGlobal para acesso ao WS o erro 
 ****** "Using generated security password: 96503968-0f05-4015-b139-bcc7bc65cbf6" deixa de ser apresentado
 ****** ao "subir" o Spring.
 *
 * 4. Quando o Spring se atualizou, tive de adicionar {noop} na frente do valor do password, para poder funcionar.
 ****** Isso se deve porque agora deve-se dizer o tipo de enconder da senha a ser utilizada. Mais detalhes em:
 ****** https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-format
 ****** https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#pe-dpe
 * */