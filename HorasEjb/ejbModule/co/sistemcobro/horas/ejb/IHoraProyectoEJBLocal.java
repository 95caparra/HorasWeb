package co.sistemcobro.horas.ejb;

import java.util.List;
import javax.ejb.Local;
import co.sistemcobro.horas.bean.HoraProyecto;

@Local
public interface IHoraProyectoEJBLocal {
	
	public List<HoraProyecto> listaHorasProyecto() throws Exception;
	
	public Integer insertarHoraProyecto(HoraProyecto horaProyecto) throws Exception;
	
	public List<HoraProyecto> listaHorasProyectoPorEmpleado(String codigoUsuario) throws Exception;
	
	public List<HoraProyecto> horasPorProyecto() throws Exception;
	
	public List<HoraProyecto> horasPorEmpleado() throws Exception;

}
