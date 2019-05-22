package co.sistemcobro.horas.ejb.impl;

import java.util.List;
import javax.ejb.Stateless;
import co.sistemcobro.horas.bean.EstadoProyecto;
import co.sistemcobro.horas.dao.EstadoProyectoDAO;
import co.sistemcobro.horas.ejb.IEstadoProyectoEJBLocal;

@Stateless(name = "EstadoProyectoEJB")
public class EstadoProyectoEJB extends BaseEJB implements IEstadoProyectoEJBLocal {

	@Override
	public List<EstadoProyecto> listaEstadosProyecto() throws Exception {
		EstadoProyectoDAO estadoProyectoDAO = new EstadoProyectoDAO(dc_horario);
		return estadoProyectoDAO.listaEstadosProyecto();
	}

	@Override
	public Integer insertarEstadoProyecto(EstadoProyecto estadoProyecto) throws Exception {
		EstadoProyectoDAO estadoProyectoDAO = new EstadoProyectoDAO(dg_horario);
		return estadoProyectoDAO.insertarEstadoProyecto(estadoProyecto);
	}

}
