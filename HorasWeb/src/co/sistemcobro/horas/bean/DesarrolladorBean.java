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
import co.sistemcobro.horas.ejb.IDesarrolladorEJBLocal;
import co.sistemcobro.horas.session.LoginBean;

@ManagedBean(name = "desarrolladorBean")
@ViewScoped
public class DesarrolladorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(DesarrolladorBean.class);
	
	private LoginBean loginBean;
	
	private Desarrollador desarrollador;
	
	@EJB
	private IDesarrolladorEJBLocal desarrolladorEJB;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			try {
				desarrollador = new Desarrollador();
			} catch (Exception e) {
				logger.error("ocurrio un error ", e);
			}
			
		}
		
	}
	
	public void insertar(){
		try {
			desarrollador.setIdusuariocrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));
			if(desarrolladorEJB.insertarDesarrollador(desarrollador)> 0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se guardada éxitosamente "));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
						"No se guardo "));
			}
		} catch (Exception e) {
			logger.error("ocurrio un error ", e);
		} finally {
			desarrollador = new Desarrollador();
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

	public Desarrollador getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(Desarrollador desarrollador) {
		this.desarrollador = desarrollador;
	}

	public IDesarrolladorEJBLocal getDesarrolladorEJB() {
		return desarrolladorEJB;
	}

	public void setDesarrolladorEJB(IDesarrolladorEJBLocal desarrolladorEJB) {
		this.desarrolladorEJB = desarrolladorEJB;
	}
	
	
	
	
	
	
}
