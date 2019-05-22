package co.sistemcobro.horas.ejb;

import java.util.List;
import javax.ejb.Local;
import co.sistemcobro.horas.bean.Campana;

@Local
public interface ICampanaEJBLocal {
	
	public List<Campana> listaCampanas() throws Exception;
	
	public Integer insertarCampana(Campana campana) throws Exception;
	
}
