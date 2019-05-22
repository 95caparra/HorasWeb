package co.sistemcobro.horas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.PieChartModel;

import co.sistemcobro.horas.session.LoginBean;
import co.sistemcobro.horas.bean.UsuarioAplicacion;
import co.sistemcobro.horas.ejb.IHoraProyectoEJBLocal;

@ManagedBean(name = "inicioBean")
@ViewScoped
public class InicioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginBean loginBean;

	private UsuarioAplicacion usuarioAplicacion;

	private PieChartModel model;
	private PieChartModel model2;

	@EJB
	private IHoraProyectoEJBLocal horaProyectoEJB;

	private List<HoraProyecto> horasProyecto;
	private List<HoraProyecto> horasEmpleado;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			try {
				horasProyecto = new ArrayList<>();
				horasEmpleado = new ArrayList<>();

				horasProyecto = horaProyectoEJB.horasPorProyecto();
				horasEmpleado = horaProyectoEJB.horasPorEmpleado();
				
				createPieModel();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createPieModel() {
		model = new PieChartModel();
		model2 = new PieChartModel();
		
		for (HoraProyecto hp : horasProyecto) {
			model.set(hp.getNombreProyecto(), hp.getHorasTrabajadas());
		}

		// followings are some optional customizations:
		// set legend position to 'e' (east), other values are 'w', 's' and 'n'
		model.setLegendPosition("e");
		// enable tooltips
		model.setShowDatatip(true);
		// show labels inside pie chart
		model.setShowDataLabels(true);
		// show label text as 'value' (numeric) , others are 'label', 'percent'
		// (default). Only one can be used.
		model.setDataFormat("value");
		// format: %d for 'value', %s for 'label', %d%% for 'percent'
		model.setDataLabelFormatString("%s");


		for (HoraProyecto hp : horasEmpleado) {
			model2.set(hp.getNombreEmpleado(), hp.getHorasTrabajadas());			
		}

		// followings are some optional customizations:
		// set legend position to 'e' (east), other values are 'w', 's' and 'n'
		model2.setLegendPosition("e");
		// enable tooltips
		model2.setShowDatatip(true);
		// show labels inside pie chart
		model2.setShowDataLabels(true);
		// show label text as 'value' (numeric) , others are 'label', 'percent'
		// (default). Only one can be used.
		model2.setDataFormat("value");
		// format: %d for 'value', %s for 'label', %d%% for 'percent'
		model2.setDataLabelFormatString("%s");
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public UsuarioAplicacion getUsuarioAplicacion() {
		return usuarioAplicacion;
	}

	public void setUsuarioAplicacion(UsuarioAplicacion usuarioAplicacion) {
		this.usuarioAplicacion = usuarioAplicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PieChartModel getModel() {
		return model;
	}

	public void setModel(PieChartModel model) {
		this.model = model;
	}

	public IHoraProyectoEJBLocal getHoraProyectoEJB() {
		return horaProyectoEJB;
	}

	public void setHoraProyectoEJB(IHoraProyectoEJBLocal horaProyectoEJB) {
		this.horaProyectoEJB = horaProyectoEJB;
	}

	public List<HoraProyecto> getHorasProyecto() {
		return horasProyecto;
	}

	public void setHorasProyecto(List<HoraProyecto> horasProyecto) {
		this.horasProyecto = horasProyecto;
	}

	public PieChartModel getModel2() {
		return model2;
	}

	public void setModel2(PieChartModel model2) {
		this.model2 = model2;
	}

	public List<HoraProyecto> getHorasEmpleado() {
		return horasEmpleado;
	}

	public void setHorasEmpleado(List<HoraProyecto> horasEmpleado) {
		this.horasEmpleado = horasEmpleado;
	}
	
	

}
