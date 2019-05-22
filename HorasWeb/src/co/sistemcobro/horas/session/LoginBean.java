package co.sistemcobro.horas.session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import co.sistemcobro.horas.bean.NavegacionBean;
import co.sistemcobro.horas.bean.UsuarioHermes;
import co.sistemcobro.horas.util.Hash;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	private boolean flag = false;
	private String usuario;
	private String password;
	private UsuarioHermes usuarioHermes;
	private String ipCliente;
	private String idCall;
	

	public String getIdCall() {
		return idCall;
	}

	public void setIdCall(String idCall) {
		this.idCall = idCall;
	}

	@ManagedProperty(value = "#{navegacionBean}")
	private NavegacionBean navegacionBean;

	@PostConstruct
	public void init() {
		usuario = new String();
		password = new String();
		usuarioHermes = new UsuarioHermes();
	}

	public String doLogin() {
		
		return navegacionBean.redireccionInicio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsuarioHermes getUsuarioHermes() {
		return usuarioHermes;
	}

	public void setUsuarioHermes(UsuarioHermes usuarioHermes) {
		this.usuarioHermes = usuarioHermes;
	}

	public NavegacionBean getNavegacionBean() {
		return navegacionBean;
	}

	public void setNavegacionBean(NavegacionBean navegacionBean) {
		this.navegacionBean = navegacionBean;
	}

	public String getIpCliente() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}

	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
