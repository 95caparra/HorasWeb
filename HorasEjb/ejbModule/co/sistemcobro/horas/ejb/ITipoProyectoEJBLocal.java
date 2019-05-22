package co.sistemcobro.horas.ejb;

import java.util.List;
import javax.ejb.Local;
import co.sistemcobro.horas.bean.TipoProyecto;

@Local
public interface ITipoProyectoEJBLocal {
	
	public List<TipoProyecto> listaTiposProyecto() throws Exception;
	
	public Integer insertarTipoProyecto(TipoProyecto tipoProyecto) throws Exception;

}
