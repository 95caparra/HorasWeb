package co.sistemcobro.horas.ejb.impl;

import java.util.List;
import javax.ejb.Stateless;
import co.sistemcobro.horas.bean.Campana;
import co.sistemcobro.horas.dao.CampanaDAO;
import co.sistemcobro.horas.ejb.ICampanaEJBLocal;

@Stateless(name = "CampanaEJB")
public class CampanaEJB extends BaseEJB implements ICampanaEJBLocal {

	@Override
	public List<Campana> listaCampanas() throws Exception {
		CampanaDAO campanaDAO = new CampanaDAO(dc_horario);
		return campanaDAO.listaCampanas();
	}

	@Override
	public Integer insertarCampana(Campana campana) throws Exception {
		CampanaDAO campanaDAO = new CampanaDAO(dg_horario);
		return campanaDAO.insertarCampana(campana);
	}

}
