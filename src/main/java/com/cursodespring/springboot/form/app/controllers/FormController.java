package com.cursodespring.springboot.form.app.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cursodespring.springboot.form.app.models.domain.Pais;
import com.cursodespring.springboot.form.app.models.domain.Usuario;
import com.cursodespring.springboot.form.app.validadores.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	@Autowired
	private UsuarioValidador validador;
	
	@GetMapping("/form")
	public String form(Model model){
		Usuario usuario = new Usuario();
		usuario.setNombre("James");
		usuario.setApellido("McGill");
		usuario.setIdentificador("123-456-789-K");
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status){
		//validador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado formulario");
		if(result.hasErrors()){
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach( error ->{
				errores.put(error.getField(), "El campo ".concat(error.getField().concat(" ").concat(error.getDefaultMessage()) ));
			});
			model.addAttribute("error", errores);
			return "form";
		}
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}
	
	//Este metodo recupera los parametros y mapea un objeto usuario
	// @PostMapping("/form")
	public String procesar2(Model model, @RequestParam 
			String username, @RequestParam String password, 
			@RequestParam String email){
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setEmail(email);
		usuario.setPassword(password);
		model.addAttribute("titulo", "Resultado formulario");
		model.addAttribute("usuario", usuario);
		return "resultado";
	}
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("España", "México", "Argentina", "Chile", "Colombia", "Perú", "Venezuela");
	}
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return Arrays.asList(new Pais(34, "ESP", "España"), new Pais(52, "MEX", "México"), new Pais(54, "ARG", "Argentina"), 
				new Pais(56, "COL", "Colombia"), new Pais(51, "PER", "Perú"), new Pais(58, "VZL", "Venezuela"));
	}
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ESP", "España");
		paises.put("MX", "México");
		paises.put("ARG", "Argentina");
		paises.put("CHL", "Chile");
		paises.put("COL", "Colombia");
		paises.put("PER", "Perú");
		paises.put("VZL", "Venezuela");
		return paises;
	}
}
