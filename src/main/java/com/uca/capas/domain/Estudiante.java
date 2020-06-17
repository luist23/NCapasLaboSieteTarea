package com.uca.capas.domain;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="estudiante")
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="c_usuario")
	private Integer codigo;
	
	@Column(name="nombre")
	@Size(message = "El nombre no debe tener mas de 50 caracteres", max = 50)
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String nombre;
	
	@Column(name="apellido")
	@Size(message = "El apellido no debe tener mas de 50 caracteres", max = 50)
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String apellido;
	
	@Column(name="carne")
	@Pattern(regexp = "^[0-9]{8}$", message = "El carne debe tener exactamente 8 digitos")
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String carne;
	
	@Column(name="carrera")
	@Size(message = "La carrera no debe tener mas de 100 caracteres", max = 100)
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String carrera;


	public Estudiante() {
		
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCarne() {
		return carne;
	}


	public void setCarne(String carne) {
		this.carne = carne;
	}


	public String getCarrera() {
		return carrera;
	}


	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	
}