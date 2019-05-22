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
import co.sistemcobro.horas.ejb.ILineaNegocioEJBLocal;
import co.sistemcobro.horas.session.LoginBean;

@ManagedBean(name = "lineaNegocioBean")
@ViewScoped
public class LineaNegocioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(LineaNegocioBean.class);
	
	private LoginBean loginBean;
	
	private LineaNegocio lineaNegocio;
	
	@EJB
	private ILineaNegocioEJBLocal lineaNegocioEJB;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			try {
				lineaNegocio = new LineaNegocio();
			} catch (Exception e) {
				logger.error("ocurrio un error ", e);
			}
			
		}
		
	}
	
	public void insertar(){
		try {
			lineaNegocio.setIdusuariocrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));
			if(lineaNegocioEJB.insertarLineaNegocio(lineaNegocio)> 0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se guardada éxitosamente "));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
						"No se guardo "));
			}
		} catch (Exception e) {
			logger.error("ocurrio un error ", e);
		} finally {
			lineaNegocio = new LineaNegocio();
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

	public LineaNegocio getLineaNegocio() {
		return lineaNegocio;
	}

	public void setLineaNegocio(LineaNegocio lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	public ILineaNegocioEJBLocal getLineaNegocioEJB() {
		return lineaNegocioEJB;
	}

	public void setLineaNegocioEJB(ILineaNegocioEJBLocal lineaNegocioEJB) {
		this.lineaNegocioEJB = lineaNegocioEJB;
	}

	
	
	
	
	
	
}
