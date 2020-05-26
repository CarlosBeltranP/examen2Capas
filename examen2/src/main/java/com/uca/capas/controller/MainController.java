package com.uca.capas.controller;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;


@Controller
public class MainController {
	@Autowired //inyectamos objeto
	private CategoriaService categoriaService;
	
	@Autowired //inyectamos objeto
	private LibroService libroService;
	
	
	@GetMapping("/index")	//Pagina inicial
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@GetMapping("/formCategoria")	
	public ModelAndView formCategoria() {
		ModelAndView mav = new ModelAndView();
		Categoria categoria = new Categoria();
		mav.addObject("categoria", categoria);
		mav.setViewName("ingresarCat");
		return mav;
	}
	
	@GetMapping("/formLibro")	
	public ModelAndView formLibro() {
		ModelAndView mav = new ModelAndView();
		List<Categoria> listaCategorias = null;
		listaCategorias = categoriaService.findAll();
		Libro libro = new Libro();
		mav.addObject("categorias", listaCategorias);
		mav.addObject("libro", libro);
		
		mav.setViewName("ingresarLib");
		return mav;
	}
	
	@PostMapping("/guardarCategoria")
	public ModelAndView guardar(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) { 
			try {
				mav.setViewName("ingresarCat");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {	
			try {
			
				categoriaService.save(categoria);
				mav.addObject("message", "Categoría guardada con éxito");
				mav.setViewName("index");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		return mav;
	
	}
	
	@PostMapping("/guardarLibro")
	public ModelAndView guardaLibror(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) { 
			List<Categoria> categorias= null;
			try {
				categorias =  categoriaService.findAll();
				mav.addObject("categorias",categorias);
				mav.setViewName("ingresarLib");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {	
			try {
		
				ZoneId defaultZoneId = ZoneId.systemDefault();
				LocalDate today = LocalDate.now();
				Date date = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
				libro.setFecha(date);
				libro.getFecha();
			
				libroService.save(libro);
				mav.addObject("message2", "Libro guardado con éxito");
				mav.setViewName("index");
			}catch(Exception ex) {
				
			}
			
		}
		return mav;
	
	}
	
	@GetMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Libro> listaLibros = null;
		
		listaLibros = libroService.findAll();
	
		mav.addObject("libros", listaLibros);
		mav.setViewName("listado");
		return mav;
	}
	
	
	

}
