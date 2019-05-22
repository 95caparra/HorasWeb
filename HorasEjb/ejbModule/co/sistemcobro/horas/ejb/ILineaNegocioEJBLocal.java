package co.sistemcobro.horas.ejb;

import java.util.List;
import javax.ejb.Local;
import co.sistemcobro.horas.bean.LineaNegocio;

@Local
public interface ILineaNegocioEJBLocal {
	
	public List<LineaNegocio> listaLineasNegocio() throws Exception;
	
	public Integer insertarLineaNegocio(LineaNegocio lineaNegocio) throws Exception;
	
}
