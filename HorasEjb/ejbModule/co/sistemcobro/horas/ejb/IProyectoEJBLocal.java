package co.sistemcobro.horas.ejb;

import java.util.List;
import javax.ejb.Local;
import co.sistemcobro.horas.bean.Proyecto;

@Local
public interface IProyectoEJBLocal {
	
	public List<Proyecto> listaProyectos() throws Exception;
	
	public Integer insertarProyecto(Proyecto proyecto) throws Exception;
	
	public List<Proyecto> listaProyectosPorEmpleado(String codigoUsuario) throws Exception; 
	
}
