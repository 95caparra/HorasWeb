package co.sistemcobro.horas.ejb.impl;

import java.util.List;
import javax.ejb.Stateless;
import co.sistemcobro.horas.bean.Desarrollador;
import co.sistemcobro.horas.dao.DesarrolladorDAO;
import co.sistemcobro.horas.ejb.IDesarrolladorEJBLocal;

@Stateless(name = "DesarrolladorEJB")
public class DesarrolladorEJB extends BaseEJB implements IDesarrolladorEJBLocal  {

	@Override
	public List<Desarrollador> listaDesarrolladores() throws Exception {
		DesarrolladorDAO desarrolladorDAO = new DesarrolladorDAO(dc_horario);
		return desarrolladorDAO.listaDesarrolladores();
	}

	@Override
	public Integer insertarDesarrollador(Desarrollador desarrollador) throws Exception {
		DesarrolladorDAO desarrolladorDAO = new DesarrolladorDAO(dg_horario);
		return desarrolladorDAO.insertarDesarrollador(desarrollador);
	}

}
