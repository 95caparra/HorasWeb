package co.sistemcobro.horas.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.ejb.IEstadoProyectoEJBLocal;
import co.sistemcobro.horas.session.LoginBean;

@ManagedBean(name = "estadoProyectoBean")
@ViewScoped
public class EstadoProyectoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(EstadoProyectoBean.class);
	
	private LoginBean loginBean;
	
	private EstadoProyecto estadoProyecto;
	
	@EJB
	private IEstadoProyectoEJBLocal estadoProyectoEJB;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			try {
				estadoProyecto = new EstadoProyecto();
			} catch (Exception e) {
				logger.error("ocurrio un error ", e);
			}
			
		}
		
	}
	
	public void insertar(){
		try {
			estadoProyecto.setIdusuariocrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));
			if(estadoProyectoEJB.insertarEstadoProyecto(estadoProyecto)> 0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se guardada éxitosamente "));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
						"No se guardo "));
			}
		} catch (Exception e) {
			logger.error("ocurrio un error ", e);
		} finally {
			estadoProyecto = new EstadoProyecto();
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

	public EstadoProyecto getEstadoProyecto() {
		return estadoProyecto;
	}

	public void setEstadoProyecto(EstadoProyecto estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
	}

	public IEstadoProyectoEJBLocal getEstadoProyectoEJB() {
		return estadoProyectoEJB;
	}

	public void setEstadoProyectoEJB(IEstadoProyectoEJBLocal estadoProyectoEJB) {
		this.estadoProyectoEJB = estadoProyectoEJB;
	}
	
	
	
	
	
	
	
}
