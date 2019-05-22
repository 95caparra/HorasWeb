package co.sistemcobro.horas.bean;

import java.sql.Timestamp;

public class HoraProyecto {
	
	private Integer idhoraproyecto; 
	private Proyecto proyecto; 
	private String nombrePersona; 
	private String actividad; 
	private String fecha; 
	private Integer horas; 
	private EstadoProyecto estadoProyecto; 
	private Integer idusuariocrea; 
	private Timestamp fechacrea; 
	private Integer idusuariomod; 
	private Timestamp fechamod; 
	private Integer estado;
	private Desarrollador desarrollador;
	
	private String nombreEmpleado;
	private String nombreProyecto;
	private Integer horasTrabajadas;
	
	public HoraProyecto(){
		proyecto = new Proyecto();
		estadoProyecto = new EstadoProyecto();
		desarrollador = new Desarrollador();
	}
	
	public Integer getIdhoraproyecto() {
		return idhoraproyecto;
	}
	public void setIdhoraproyecto(Integer idhoraproyecto) {
		this.idhoraproyecto = idhoraproyecto;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getHoras() {
		return horas;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	public EstadoProyecto getEstadoProyecto() {
		return estadoProyecto;
	}
	public void setEstadoProyecto(EstadoProyecto estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
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

	public Desarrollador getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(Desarrollador desarrollador) {
		this.desarrollador = desarrollador;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public Integer getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(Integer horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}
	
	
	

}
