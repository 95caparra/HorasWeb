package co.sistemcobro.horas.ejb.impl;

import java.util.List;
import javax.ejb.Stateless;
import co.sistemcobro.horas.bean.LineaNegocio;
import co.sistemcobro.horas.dao.LineaNegocioDAO;
import co.sistemcobro.horas.ejb.ILineaNegocioEJBLocal;

@Stateless(name = "LineaNegocioEJB")
public class LineaNegocioEJB extends BaseEJB implements ILineaNegocioEJBLocal {

	@Override
	public List<LineaNegocio> listaLineasNegocio() throws Exception {
		LineaNegocioDAO lineaNegocioDAO = new LineaNegocioDAO(dc_horario);
		return lineaNegocioDAO.listaLineasNegocio();
	}

	@Override
	public Integer insertarLineaNegocio(LineaNegocio lineaNegocio) throws Exception {
		LineaNegocioDAO lineaNegocioDAO = new LineaNegocioDAO(dg_horario);
		return lineaNegocioDAO.insertarLineaNegocio(lineaNegocio);
	}

}
