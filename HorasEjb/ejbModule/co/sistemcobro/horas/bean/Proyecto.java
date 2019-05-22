package co.sistemcobro.horas.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Proyecto {

	private Integer idproyecto; 
	private String priorizacioncomite; 
	private String nombresolicitante; 
	private TipoProyecto tipoProyecto; 
	private Campana campana; 
	private LineaNegocio lineaNegocio; 
	private String nombreproyecto; 
	private String detalleproyecto; 
	private Desarrollador desarrollador; 
	private EstadoProyecto estadoProyecto; 
	private Date fechainicioproyecto; 
	private Date fechafinproyecto; 
	private Integer idusuariocrea; 
	private Timestamp fechacrea; 
	private Integer idusuariomod; 
	private Timestamp fechamod; 
	private Integer estado;
	
	public Proyecto(){
		tipoProyecto = new TipoProyecto();
		campana = new Campana();
		lineaNegocio = new LineaNegocio();
		desarrollador = new Desarrollador();
		estadoProyecto = new EstadoProyecto();
	}
	
	
	public Integer getIdproyecto() {
		return idproyecto;
	}
	public void setIdproyecto(Integer idproyecto) {
		this.idproyecto = idproyecto;
	}
	public String getPriorizacioncomite() {
		return priorizacioncomite;
	}
	public void setPriorizacioncomite(String priorizacioncomite) {
		this.priorizacioncomite = priorizacioncomite;
	}
	public String getNombresolicitante() {
		return nombresolicitante;
	}
	public void setNombresolicitante(String nombresolicitante) {
		this.nombresolicitante = nombresolicitante;
	}
	public TipoProyecto getTipoProyecto() {
		return tipoProyecto;
	}
	public void setTipoProyecto(TipoProyecto tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}
	public Campana getCampana() {
		return campana;
	}
	public void setCampana(Campana campana) {
		this.campana = campana;
	}
	public LineaNegocio getLineaNegocio() {
		return lineaNegocio;
	}
	public void setLineaNegocio(LineaNegocio lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}
	public String getNombreproyecto() {
		return nombreproyecto;
	}
	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}
	public String getDetalleproyecto() {
		return detalleproyecto;
	}
	public void setDetalleproyecto(String detalleproyecto) {
		this.detalleproyecto = detalleproyecto;
	}
	public Desarrollador getDesarrollador() {
		return desarrollador;
	}
	public void setDesarrollador(Desarrollador desarrollador) {
		this.desarrollador = desarrollador;
	}
	public EstadoProyecto getEstadoProyecto() {
		return estadoProyecto;
	}
	public void setEstadoProyecto(EstadoProyecto estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
	}
	public Date getFechainicioproyecto() {
		return fechainicioproyecto;
	}
	public void setFechainicioproyecto(Date fechainicioproyecto) {
		this.fechainicioproyecto = fechainicioproyecto;
	}
	public Date getFechafinproyecto() {
		return fechafinproyecto;
	}
	public void setFechafinproyecto(Date fechafinproyecto) {
		this.fechafinproyecto = fechafinproyecto;
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
