package com.uca.capas.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name = "cat_libro")
public class Libro {
	
	@Id
	@Column(name="c_libro")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(generator="cat_libro_c_libro_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cat_libro_c_libro_seq", sequenceName = "public.cat_libro_c_libro_seq", allocationSize = 1)
	private Integer libro_id;
	
	@Size(message="El titulo no debe tener mas de 150 caracteres", max = 150)
	@NotEmpty(message="Este campo no puede estar vacío") 
	@Column(name="s_titulo")
	private String titulo;
	
	@Size(message="El autor no debe tener mas de 500 caracteres", max = 500)
	@NotEmpty(message="Este campo no puede estar vacío") 
	@Column(name="s_autor")
	private String autor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_categoria")
	private Categoria categoria;
	
	@Column(name="f_ingreso")
	private Date fecha;
	
	@Column(name="b_estado")
	private Boolean estado;
	
	@Size(message="El ISBN no debe tener mas de 10 caracteres", max = 10)
	@NotEmpty(message="Este campo no puede estar vacío") 
	@Column(name="s_isbn")
	private String isbn;
	
	
	public Integer getLibro_id() {
		return libro_id;
	}
	public void setLibro_id(Integer libro_id) {
		this.libro_id = libro_id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Libro() {
		super();
	}
	public String getFechaDelegate(){
		if(this.fecha == null){
			return "";
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			String shortdate = sdf.format(this.fecha.getTime());
			return shortdate;
		}
	}
	
	public String getBactivoDelegate(){
		if(this.estado == null){
			return "";
		}
		else{
			if(this.estado) return "ACTIVO";
			else return "INACTIVO";
		}
	}

}
