package co.sistemcobro.horas.ejb;

import java.util.List;
import javax.ejb.Local;
import co.sistemcobro.horas.bean.Desarrollador;

@Local
public interface IDesarrolladorEJBLocal {
	
	public List<Desarrollador> listaDesarrolladores() throws Exception;
	
	public Integer insertarDesarrollador(Desarrollador desarrollador) throws Exception;

}
