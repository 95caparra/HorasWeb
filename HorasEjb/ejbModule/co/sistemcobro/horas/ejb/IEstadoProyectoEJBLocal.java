package co.sistemcobro.horas.ejb;

import java.util.List;
import javax.ejb.Local;
import co.sistemcobro.horas.bean.EstadoProyecto;

@Local
public interface IEstadoProyectoEJBLocal {
	
	public List<EstadoProyecto> listaEstadosProyecto() throws Exception;
	
	public Integer insertarEstadoProyecto(EstadoProyecto estadoProyecto) throws Exception;
	
}
