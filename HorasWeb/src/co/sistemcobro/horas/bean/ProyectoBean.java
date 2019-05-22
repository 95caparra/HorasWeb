package co.sistemcobro.horas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

import co.sistemcobro.horas.ejb.ICampanaEJBLocal;
import co.sistemcobro.horas.ejb.IDesarrolladorEJBLocal;
import co.sistemcobro.horas.ejb.IEstadoProyectoEJBLocal;
import co.sistemcobro.horas.ejb.ILineaNegocioEJBLocal;
import co.sistemcobro.horas.ejb.IProyectoEJBLocal;
import co.sistemcobro.horas.ejb.ITipoProyectoEJBLocal;
import co.sistemcobro.horas.session.LoginBean;

@ManagedBean(name = "proyectoBean")
@ViewScoped
public class ProyectoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(ProyectoBean.class);
	
	private LoginBean loginBean;
	
	private Proyecto proyecto;
	
	private Integer selectedTipoProyecto;
	private List<TipoProyecto> tiposProyecto;
	
	private Integer selectedCampana;
	private List<Campana> campanas;
	
	private Integer selectedLineaNegocio;
	private List<LineaNegocio> lineasNegocio;
	
	private Integer selectedDesarrollador;
	private List<Desarrollador> desarrolladores;
	
	private Integer selectedEstadoProyecto;
	private List<EstadoProyecto> estadosProyecto;
	
	private Date fechaIni;
	private Date fechaFin;
	
	@EJB
	private IProyectoEJBLocal proyectoEJB;
	@EJB
	private ITipoProyectoEJBLocal tipoProyectoEJB;
	@EJB
	private ICampanaEJBLocal campanaEJB;
	@EJB
	private ILineaNegocioEJBLocal lineaNegocioEJB;
	@EJB
	private IDesarrolladorEJBLocal desarrolladorEJB;
	@EJB
	private IEstadoProyectoEJBLocal estadoProyectoEJB;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			try {
				proyecto = new Proyecto();	
				
				tiposProyecto = new ArrayList<>();
				campanas = new ArrayList<>();
				lineasNegocio = new ArrayList<>();
				desarrolladores = new ArrayList<>();
				estadosProyecto = new ArrayList<>();
				
				tiposProyecto = tipoProyectoEJB.listaTiposProyecto();
				campanas = campanaEJB.listaCampanas();
				lineasNegocio = lineaNegocioEJB.listaLineasNegocio();
				desarrolladores = desarrolladorEJB.listaDesarrolladores();
				estadosProyecto = estadoProyectoEJB.listaEstadosProyecto();
			} catch (Exception e) {
				logger.error("ocurrio un error ", e);
			}
			
		}
		
	}
	
	public void insertar(){
		try {
			proyecto.getTipoProyecto().setIdTipoProyecto(selectedTipoProyecto);
			proyecto.getCampana().setIdcampana(selectedCampana);
			proyecto.getLineaNegocio().setIdlineanegocio(selectedLineaNegocio);
			proyecto.getDesarrollador().setIddesarrollador(selectedDesarrollador);
			proyecto.getEstadoProyecto().setIdestadoproyecto(selectedEstadoProyecto);
			proyecto.setIdusuariocrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));
			proyecto.setFechainicioproyecto(new java.sql.Date(fechaIni.getTime()));
			proyecto.setFechafinproyecto(new java.sql.Date(fechaFin.getTime()));
			if(proyectoEJB.insertarProyecto(proyecto)> 0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se guardada éxitosamente "));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
						"No se guardo "));
			}
		} catch (Exception e) {
			logger.error("ocurrio un error ", e);
		} finally {
			proyecto = new Proyecto();
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public IProyectoEJBLocal getProyectoEJB() {
		return proyectoEJB;
	}

	public void setProyectoEJB(IProyectoEJBLocal proyectoEJB) {
		this.proyectoEJB = proyectoEJB;
	}

	public Integer getSelectedTipoProyecto() {
		return selectedTipoProyecto;
	}

	public void setSelectedTipoProyecto(Integer selectedTipoProyecto) {
		this.selectedTipoProyecto = selectedTipoProyecto;
	}

	public List<TipoProyecto> getTiposProyecto() {
		return tiposProyecto;
	}

	public void setTiposProyecto(List<TipoProyecto> tiposProyecto) {
		this.tiposProyecto = tiposProyecto;
	}

	public Integer getSelectedCampana() {
		return selectedCampana;
	}

	public void setSelectedCampana(Integer selectedCampana) {
		this.selectedCampana = selectedCampana;
	}

	public List<Campana> getCampanas() {
		return campanas;
	}

	public void setCampanas(List<Campana> campanas) {
		this.campanas = campanas;
	}

	public Integer getSelectedLineaNegocio() {
		return selectedLineaNegocio;
	}

	public void setSelectedLineaNegocio(Integer selectedLineaNegocio) {
		this.selectedLineaNegocio = selectedLineaNegocio;
	}

	public List<LineaNegocio> getLineasNegocio() {
		return lineasNegocio;
	}

	public void setLineasNegocio(List<LineaNegocio> lineasNegocio) {
		this.lineasNegocio = lineasNegocio;
	}

	public Integer getSelectedDesarrollador() {
		return selectedDesarrollador;
	}

	public void setSelectedDesarrollador(Integer selectedDesarrollador) {
		this.selectedDesarrollador = selectedDesarrollador;
	}

	public List<Desarrollador> getDesarrolladores() {
		return desarrolladores;
	}

	public void setDesarrolladores(List<Desarrollador> desarrolladores) {
		this.desarrolladores = desarrolladores;
	}

	public Integer getSelectedEstadoProyecto() {
		return selectedEstadoProyecto;
	}

	public void setSelectedEstadoProyecto(Integer selectedEstadoProyecto) {
		this.selectedEstadoProyecto = selectedEstadoProyecto;
	}

	public List<EstadoProyecto> getEstadosProyecto() {
		return estadosProyecto;
	}

	public void setEstadosProyecto(List<EstadoProyecto> estadosProyecto) {
		this.estadosProyecto = estadosProyecto;
	}

	public ITipoProyectoEJBLocal getTipoProyectoEJB() {
		return tipoProyectoEJB;
	}

	public void setTipoProyectoEJB(ITipoProyectoEJBLocal tipoProyectoEJB) {
		this.tipoProyectoEJB = tipoProyectoEJB;
	}

	public ICampanaEJBLocal getCampanaEJB() {
		return campanaEJB;
	}

	public void setCampanaEJB(ICampanaEJBLocal campanaEJB) {
		this.campanaEJB = campanaEJB;
	}

	public ILineaNegocioEJBLocal getLineaNegocioEJB() {
		return lineaNegocioEJB;
	}

	public void setLineaNegocioEJB(ILineaNegocioEJBLocal lineaNegocioEJB) {
		this.lineaNegocioEJB = lineaNegocioEJB;
	}

	public IDesarrolladorEJBLocal getDesarrolladorEJB() {
		return desarrolladorEJB;
	}

	public void setDesarrolladorEJB(IDesarrolladorEJBLocal desarrolladorEJB) {
		this.desarrolladorEJB = desarrolladorEJB;
	}

	public IEstadoProyectoEJBLocal getEstadoProyectoEJB() {
		return estadoProyectoEJB;
	}

	public void setEstadoProyectoEJB(IEstadoProyectoEJBLocal estadoProyectoEJB) {
		this.estadoProyectoEJB = estadoProyectoEJB;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	
	
}
