package co.sistemcobro.horas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.bean.LineaNegocio;
import co.sistemcobro.horas.constante.EstadoEnum;


public class LineaNegocioDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(LineaNegocioDAO.class);

	public LineaNegocioDAO(DataSource ds) {
		super(ds);
	}
	
	private String SQL_LINEAS_NEGOCIO = "SELECT idlineanegocio, detalle, idusuariocrea, fechacrea, idusuariomod, fechamod, estado "
			+ " FROM horasproyecto.linea_negocio WHERE estado = ? ";
	
	public List<LineaNegocio> listaLineasNegocio() throws Exception{
		List<LineaNegocio> lineasNegocio = new ArrayList<>();
		LineaNegocio lineaNegocio = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_LINEAS_NEGOCIO, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				lineaNegocio = new LineaNegocio();
				
				lineaNegocio.setIdlineanegocio(rs.getInt(t++)); 
				lineaNegocio.setDetalle(rs.getString(t++)); 
				lineaNegocio.setIdusuariocrea(rs.getInt(t++)); 
				lineaNegocio.setFechacrea(rs.getTimestamp(t++)); 
				lineaNegocio.setIdusuariomod(rs.getInt(t++)); 
				lineaNegocio.setFechamod(rs.getTimestamp(t++)); 
				lineaNegocio.setEstado(rs.getInt(t++));
				
				lineasNegocio.add(lineaNegocio);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaLineasNegocio " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaLineasNegocio "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaLineasNegocio ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaLineasNegocio " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaLineasNegocio "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar de cuotas acuerdo ");
		} finally {
			closeConexion();
			logger.info("finalizo dao LineaNegocioDAO método listaLineasNegocio!");
		}
		return lineasNegocio;
	}
	
	public Integer insertarLineaNegocio(LineaNegocio lineaNegocio) throws Exception {
		Integer llave = 0;
		StringBuilder varname1 = new StringBuilder();

		varname1.append(" INSERT INTO horasproyecto.linea_negocio ");
		varname1.append(" ( ");
		varname1.append(" detalle, ");//1
		varname1.append(" idusuariocrea, ");//2
		varname1.append(" fechacrea,  ");//3
		varname1.append(" estado ");//4
		varname1.append(" ) ");
		varname1.append(" VALUES ( ");
		varname1.append(" ?, ");//1
		varname1.append(" ?, ");//2
		varname1.append(" GETDATE(), ");//3
		varname1.append(" ? ");//4
		varname1.append(" ) ");
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(varname1.toString(), Statement.RETURN_GENERATED_KEYS);
			
			int t = 1;
			
			ps.setString(t++, lineaNegocio.getDetalle());//1
			ps.setInt(t++, lineaNegocio.getIdusuariocrea());//2
			ps.setInt(t++, EstadoEnum.ACTIVO.getIndex());//3
		
			ps.executeUpdate();
			llave = Statement.RETURN_GENERATED_KEYS;

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar de insertarLineaNegocio  "
					+ " id del registro.... " + Statement.RETURN_GENERATED_KEYS + " tabla afectada....  insertarLineaNegocio "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de insertarLineaNegocio  ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de insertarLineaNegocio " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... insertarLineaNegocio" + "descripción de evento..."
					+ e);
			throw new Exception("Exception Error al tratar de insertarLineaNegocio ");
		} finally {
			closeConexion();
			logger.info("finalizo dao LineaNegocioDAO método insertarLineaNegocio!");
		}
		return llave;
	}

}
