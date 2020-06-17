package com.uca.capas.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;

@Controller
public class MainController {
	
	@Autowired
	private EstudianteDAO estudianteDAO;
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		
		List<Estudiante> estudiantes = null;
		try {
			
			estudiantes = estudianteDAO.findAll();
			
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
	
	@RequestMapping("/formEstudiante")
	public ModelAndView formProducto(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(!result.hasErrors()) {
			try {
				
				estudianteDAO.insert(estudiante);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			estudiante = new Estudiante();
			mav.addObject("estudiante", estudiante);
			
		}
		
		mav.setViewName("index");
		
		return mav;
		
	}
	
	@RequestMapping("/deleteEstudiante")
	public String delete(@RequestParam Integer codigo) {
		Estudiante estudiante = estudianteDAO.findOne(codigo);
			try {
				
				estudianteDAO.delete(estudiante);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		return "redirect:/listado";
		
	}

}