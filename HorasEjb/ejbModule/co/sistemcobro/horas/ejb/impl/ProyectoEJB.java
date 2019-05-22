package co.sistemcobro.horas.ejb.impl;

import java.util.List;
import javax.ejb.Stateless;
import co.sistemcobro.horas.bean.Proyecto;
import co.sistemcobro.horas.dao.ProyectoDAO;
import co.sistemcobro.horas.ejb.IProyectoEJBLocal;

@Stateless(name = "ProyectoEJB")
public class ProyectoEJB extends BaseEJB implements IProyectoEJBLocal {

	@Override
	public List<Proyecto> listaProyectos() throws Exception {
		ProyectoDAO proyectoDAO = new ProyectoDAO(dc_horario);
		return proyectoDAO.listaProyectos();
	}

	@Override
	public Integer insertarProyecto(Proyecto proyecto) throws Exception {
		ProyectoDAO proyectoDAO = new ProyectoDAO(dg_horario);
		return proyectoDAO.insertarProyecto(proyecto);
	}

	@Override
	public List<Proyecto> listaProyectosPorEmpleado(String codigoUsuario) throws Exception {
		ProyectoDAO proyectoDAO = new ProyectoDAO(dc_horario);
		return proyectoDAO.listaProyectosPorEmpleado(codigoUsuario);
	}

}
