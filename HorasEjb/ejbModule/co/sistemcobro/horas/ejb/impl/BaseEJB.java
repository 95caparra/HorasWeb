package co.sistemcobro.horas.ejb.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;


public class BaseEJB {

	@Resource(mappedName = "dc_horario")
	protected DataSource dc_horario;

	
	@Resource(mappedName = "dg_horario")
	protected DataSource dg_horario;

	
	
	

}
