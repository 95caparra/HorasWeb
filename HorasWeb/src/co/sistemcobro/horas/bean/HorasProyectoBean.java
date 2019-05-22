package co.sistemcobro.horas.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import co.sistemcobro.horas.ejb.IEstadoProyectoEJBLocal;
import co.sistemcobro.horas.ejb.IHoraProyectoEJBLocal;
import co.sistemcobro.horas.ejb.IProyectoEJBLocal;
import co.sistemcobro.horas.session.LoginBean;

@ManagedBean(name = "horasProyectoBean")
@ViewScoped
public class HorasProyectoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(HorasProyectoBean.class);
	
	private LoginBean loginBean;
	
	private HoraProyecto horaProyecto;
	
	private Integer selectedProyecto;
	private List<Proyecto> proyectos;
	
	private Integer selectedEstadoProyecto;
	private List<EstadoProyecto> estadosProyecto;
	
	private Date fechaReporte;
	
	@EJB
	private IProyectoEJBLocal proyectoEJB;
	@EJB
	private IHoraProyectoEJBLocal horaProyectoEJB;
	@EJB
	private IEstadoProyectoEJBLocal estadoProyectoEJB;
	
	
	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			try {
				horaProyecto = new HoraProyecto();				
				proyectos = new ArrayList<>();
				estadosProyecto = new ArrayList<>();
				
				String codigoUsuario = loginBean.getUsuarioHermes().getCodusuario();
				proyectos = proyectoEJB.listaProyectosPorEmpleado(codigoUsuario);
				estadosProyecto = estadoProyectoEJB.listaEstadosProyecto();
			} catch (Exception e) {
				logger.error("ocurrio un error ", e);
			}
			
		}
		
	}
	
	public void insertar(){
		try {
			horaProyecto.getProyecto().setIdproyecto(selectedProyecto);
			horaProyecto.getEstadoProyecto().setIdestadoproyecto(selectedEstadoProyecto);
			horaProyecto.setNombrePersona(loginBean.getUsuarioHermes().getNombre());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			String formattedDate = formatter.format(new java.sql.Date(fechaReporte.getTime()));			
			horaProyecto.setFecha(formattedDate);
			
			horaProyecto.setIdusuariocrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));
			if(horaProyectoEJB.insertarHoraProyecto(horaProyecto)> 0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se guardada éxitosamente "));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
						"No se guardo "));
			}
		} catch (Exception e) {
			logger.error("ocurrio un error ", e);
		} finally {
			horaProyecto = new HoraProyecto();
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

	public IProyectoEJBLocal getProyectoEJB() {
		return proyectoEJB;
	}

	public void setProyectoEJB(IProyectoEJBLocal proyectoEJB) {
		this.proyectoEJB = proyectoEJB;
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
	
	public IEstadoProyectoEJBLocal getEstadoProyectoEJB() {
		return estadoProyectoEJB;
	}

	public void setEstadoProyectoEJB(IEstadoProyectoEJBLocal estadoProyectoEJB) {
		this.estadoProyectoEJB = estadoProyectoEJB;
	}

	public HoraProyecto getHoraProyecto() {
		return horaProyecto;
	}

	public void setHoraProyecto(HoraProyecto horaProyecto) {
		this.horaProyecto = horaProyecto;
	}

	public Integer getSelectedProyecto() {
		return selectedProyecto;
	}

	public void setSelectedProyecto(Integer selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public IHoraProyectoEJBLocal getHoraProyectoEJB() {
		return horaProyectoEJB;
	}

	public void setHoraProyectoEJB(IHoraProyectoEJBLocal horaProyectoEJB) {
		this.horaProyectoEJB = horaProyectoEJB;
	}

	public Date getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(Date fechaReporte) {
		this.fechaReporte = fechaReporte;
	}

	
	
	
	
	
	
}
