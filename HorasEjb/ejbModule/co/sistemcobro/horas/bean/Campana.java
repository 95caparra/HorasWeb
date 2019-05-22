package co.sistemcobro.horas.bean;

import java.sql.Timestamp;

public class Campana {
	
	private Integer idcampana; 
	private String detalle; 
	private Integer idusuariocrea; 
	private Timestamp fechacrea; 
	private Integer idusuariomod; 
	private Timestamp fechamod; 
	private Integer estado;
	
	public Integer getIdcampana() {
		return idcampana;
	}
	public void setIdcampana(Integer idcampana) {
		this.idcampana = idcampana;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
	
	

}
