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
import co.sistemcobro.horas.ejb.ITipoProyectoEJBLocal;
import co.sistemcobro.horas.session.LoginBean;

@ManagedBean(name = "tipoProyectoBean")
@ViewScoped
public class TipoProyectoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(TipoProyectoBean.class);
	
	private LoginBean loginBean;
	
	private TipoProyecto tipoProyecto;
	
	@EJB
	private ITipoProyectoEJBLocal tipoProyectoEJB;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			try {
				tipoProyecto = new TipoProyecto();
			} catch (Exception e) {
				logger.error("ocurrio un error ", e);
			}
			
		}
		
	}
	
	public void insertar(){
		try {
			tipoProyecto.setIdusuariocrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));
			if(tipoProyectoEJB.insertarTipoProyecto(tipoProyecto)> 0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se guardada éxitosamente "));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
						"No se guardo "));
			}
		} catch (Exception e) {
			logger.error("ocurrio un error ", e);
		} finally {
			tipoProyecto = new TipoProyecto();
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

	public TipoProyecto getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(TipoProyecto tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	public ITipoProyectoEJBLocal getTipoProyectoEJB() {
		return tipoProyectoEJB;
	}

	public void setTipoProyectoEJB(ITipoProyectoEJBLocal tipoProyectoEJB) {
		this.tipoProyectoEJB = tipoProyectoEJB;
	}
	
	
	
	
	
	
	
	
}
