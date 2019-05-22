package co.sistemcobro.horas.ejb.impl;

import java.util.List;
import javax.ejb.Stateless;
import co.sistemcobro.horas.bean.HoraProyecto;
import co.sistemcobro.horas.dao.HoraProyectoDAO;
import co.sistemcobro.horas.ejb.IHoraProyectoEJBLocal;

@Stateless(name = "HoraProyectoEJB")
public class HoraProyectoEJB extends BaseEJB implements IHoraProyectoEJBLocal {

	@Override
	public List<HoraProyecto> listaHorasProyecto() throws Exception {
		HoraProyectoDAO horaProyectoDAO = new HoraProyectoDAO(dc_horario);
		return horaProyectoDAO.listaHorasProyecto();
	}

	@Override
	public Integer insertarHoraProyecto(HoraProyecto horaProyecto) throws Exception {
		HoraProyectoDAO horaProyectoDAO = new HoraProyectoDAO(dg_horario);
		return horaProyectoDAO.insertarHoraProyecto(horaProyecto);
	}

	@Override
	public List<HoraProyecto> listaHorasProyectoPorEmpleado(String codigoUsuario) throws Exception {
		HoraProyectoDAO horaProyectoDAO = new HoraProyectoDAO(dc_horario);
		return horaProyectoDAO.listaHorasProyectoPorEmpleado(codigoUsuario);
	}

	@Override
	public List<HoraProyecto> horasPorProyecto() throws Exception {
		HoraProyectoDAO horaProyectoDAO = new HoraProyectoDAO(dc_horario);
		return horaProyectoDAO.horasPorProyecto();
	}

	@Override
	public List<HoraProyecto> horasPorEmpleado() throws Exception {
		HoraProyectoDAO horaProyectoDAO = new HoraProyectoDAO(dc_horario);
		return horaProyectoDAO.horasPorEmpleado();
	}

}
