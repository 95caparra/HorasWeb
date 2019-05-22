package co.sistemcobro.horas.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "navegacionBean")
@SessionScoped
public class NavegacionBean implements Serializable {
	private static final long serialVersionUID = 4545919119678482516L;

	private String ruta;
	private Integer pagina;
	private Integer tipificacion;
	private boolean render;
	
	public static final String redireccionInicio = "pages/inicio/inicio.xhmtl?faces-redirect=true";
	public static final String redireccionLogin = "login.xhmtl?faces-redirect=true";
	public static final String redireccionUrl = "../../pages/obligacion/obligacion.xhtml";
	
	
	@PostConstruct
	public void init() {		
		pagina = 2;
		render = false;
		try {
			//FacesContext context = FacesContext.getCurrentInstance();
			//Application application = context.getApplication();
			
		}catch (Exception e) {
			
		}
	}
	
	

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Integer getTipificacion() {
		return tipificacion;
	}

	public void setTipificacion(Integer tipificacion) {
		this.tipificacion = tipificacion;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getRedireccioninicio() {
		return redireccionInicio;
	}


	public static String getRedireccionlogin() {
		return redireccionLogin;
	}

	public static String getRedireccionurl() {
		return redireccionUrl;
	}
	
	
	
	

}
