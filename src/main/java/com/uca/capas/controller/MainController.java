package com.uca.capas.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		
		List<Estudiante> estudiantes = null;
		try {
			estudiantes = estudianteService.findAll();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
	
	@RequestMapping("/inicio")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();
		
		Estudiante estudiante = new Estudiante();
		
		mav.addObject("estudiante", estudiante);
		mav.setViewName("index");
		
		return mav;
	}
	
	@PostMapping(value="/filtrar")
	public ModelAndView filtrar(@RequestParam(value="nombre") String cadena) {
		
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			
			estudiantes = estudianteService.filtrarPor(cadena);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
		
	}
	
	@RequestMapping("/formEstudiante")
	public ModelAndView formProducto(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(!result.hasErrors()) {
			try {
				
				estudianteService.insert(estudiante);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			estudiante = new Estudiante();
			mav.addObject("estudiante", estudiante);
			
		}
		
		mav.setViewName("index");
		
		return mav;
		
	}
	
	@PostMapping(value="/buscar",params = "action=eliminar")
	public String delete(@RequestParam(value = "codigo") Integer codigo) {
		//Estudiante estudiante = estudianteService.findOne(codigo);
			try {
				
				estudianteService.delete(codigo);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		return "redirect:/listado";
	}
	
	@PostMapping(value="/buscar",params = "action=buscar")
	public ModelAndView findOneE(@RequestParam(value = "codigo") Integer codigo) {
		ModelAndView mav = new ModelAndView();
		Estudiante es= null;
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		try {
			
			es = estudianteService.findOne(codigo);
			estudiantes.add(es);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
	
	@PostMapping(value="/buscar",params = "action=editar")
	public ModelAndView modificarEstudianteB(@RequestParam(value = "codigo") Integer codigo) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;

		try {

			estudiante = estudianteService.findOne(codigo);
			mav.addObject("estudiante", estudiante);
			mav.setViewName("editar");
			
		}catch (Exception e) {
			e.printStackTrace();
			estudiante = new Estudiante();			
			mav.addObject("estudiante", estudiante);
			mav.setViewName("index");
		}

		
		return mav;
		
	}
	
	@RequestMapping("/editarEstudiante")
	public ModelAndView modificarEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		if(!result.hasErrors()) {
			try {
				estudianteService.insert(estudiante);
				estudiantes = estudianteService.findAll();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			mav.addObject("estudiantes", estudiantes);
			mav.setViewName("listado");
			
			
		}else {
			estudiante = new Estudiante();
			mav.addObject("estudiante", estudiante);
			mav.setViewName("index");
		}
		
		
		
		return mav;
		
	}

}