package co.sistemcobro.horas.ejb.impl;

import java.util.List;
import javax.ejb.Stateless;
import co.sistemcobro.horas.bean.TipoProyecto;
import co.sistemcobro.horas.dao.TipoProyectoDAO;
import co.sistemcobro.horas.ejb.ITipoProyectoEJBLocal;

@Stateless(name = "TipoProyectoEJB")
public class TipoProyectoEJB extends BaseEJB implements ITipoProyectoEJBLocal {

	@Override
	public List<TipoProyecto> listaTiposProyecto() throws Exception {
		TipoProyectoDAO tipoProyectoDAO = new TipoProyectoDAO(dc_horario);
		return tipoProyectoDAO.listaTiposProyecto();
	}

	@Override
	public Integer insertarTipoProyecto(TipoProyecto tipoProyecto) throws Exception {
		TipoProyectoDAO tipoProyectoDAO = new TipoProyectoDAO(dg_horario);
		return tipoProyectoDAO.insertarTipoProyecto(tipoProyecto);
	}

}
