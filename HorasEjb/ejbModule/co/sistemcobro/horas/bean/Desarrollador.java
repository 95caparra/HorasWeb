package co.sistemcobro.horas.bean;

import java.sql.Timestamp;

public class Desarrollador {
	
	private Integer iddesarrollador; 
	private String nombre; 
	private Integer idusuariocrea; 
	private Timestamp fechacrea; 
	private Integer idusuariomod; 
	private Timestamp fechamod; 
	private Integer estado;
	private String codigoUsuario;
	
	public Integer getIddesarrollador() {
		return iddesarrollador;
	}
	public void setIddesarrollador(Integer iddesarrollador) {
		this.iddesarrollador = iddesarrollador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdusuariocrea() {
		return idusuariocrea;
	}
	public void setIdusuariocrea(Integer idusuariocrea) {
		this.idusuariocrea = idusuariocrea;
	}
	public Timestamp getFechacrea() {
		return fechacrea;
	}
	public void setFechacrea(Timestamp fechacrea) {
		this.fechacrea = fechacrea;
	}
	public Integer getIdusuariomod() {
		return idusuariomod;
	}
	public void setIdusuariomod(Integer idusuariomod) {
		this.idusuariomod = idusuariomod;
	}
	public Timestamp getFechamod() {
		return fechamod;
	}
	public void setFechamod(Timestamp fechamod) {
		this.fechamod = fechamod;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	

}
