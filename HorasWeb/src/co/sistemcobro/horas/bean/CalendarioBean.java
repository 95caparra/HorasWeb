package co.sistemcobro.horas.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import co.sistemcobro.horas.ejb.IHoraProyectoEJBLocal;
import co.sistemcobro.horas.session.LoginBean;

@ManagedBean(name = "calendarioBean")
@ViewScoped
public class CalendarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;

	private ScheduleModel lazyEventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();

	private List<HoraProyecto> horasProyecto;
	
	private HorasProyectoBean horasProyectoBean;
	
	private LoginBean loginBean;

	@EJB
	private IHoraProyectoEJBLocal horaProyectoEJB;

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));
			horasProyectoBean = (application.evaluateExpressionGet(context, "#{horasProyectoBean}", HorasProyectoBean.class));
			try {
				String codigoUsuario = loginBean.getUsuarioHermes().getCodusuario();
				horasProyecto = horaProyectoEJB.listaHorasProyectoPorEmpleado(codigoUsuario);
				SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
				for (HoraProyecto hp : horasProyecto) {

					Date date1 = parseador.parse(hp.getFecha());
					Date date2 = parseador.parse(hp.getFecha());

					eventModel.addEvent(
							new DefaultScheduleEvent(hp.getHoras().toString().concat(" horas - ").concat(hp.getActividad()), date1, date2, true));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent() {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		Date fecha = new Date();		
		fecha = this.sumarDiaAfecha(event.getEndDate());		
		horasProyectoBean.setFechaReporte(fecha);
	}
	
	public Date sumarDiaAfecha(Date fecha) {
		Calendar c1 = Calendar.getInstance();
		try {
			c1.setTime(fecha);
			c1.add(Calendar.DAY_OF_MONTH, 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c1.getTime();
	}
	

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<HoraProyecto> getHorasProyecto() {
		return horasProyecto;
	}

	public void setHorasProyecto(List<HoraProyecto> horasProyecto) {
		this.horasProyecto = horasProyecto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

	public IHoraProyectoEJBLocal getHoraProyectoEJB() {
		return horaProyectoEJB;
	}

	public void setHoraProyectoEJB(IHoraProyectoEJBLocal horaProyectoEJB) {
		this.horaProyectoEJB = horaProyectoEJB;
	}

	public HorasProyectoBean getHorasProyectoBean() {
		return horasProyectoBean;
	}

	public void setHorasProyectoBean(HorasProyectoBean horasProyectoBean) {
		this.horasProyectoBean = horasProyectoBean;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	
	

}
