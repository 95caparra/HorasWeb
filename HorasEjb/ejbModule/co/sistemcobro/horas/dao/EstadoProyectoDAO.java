package co.sistemcobro.horas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.bean.EstadoProyecto;
import co.sistemcobro.horas.constante.EstadoEnum;


public class EstadoProyectoDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(EstadoProyectoDAO.class);

	public EstadoProyectoDAO(DataSource ds) {
		super(ds);
	}
	
	private String SQL_ESTADOS_PROYECTO = "SELECT idestadoproyecto, detalle, idusuariocrea, fechacrea, idusuariomod, fechamod, estado"
			+ " FROM horasproyecto.estado_proyecto WHERE estado = ? ";
	
	public List<EstadoProyecto> listaEstadosProyecto() throws Exception{
		List<EstadoProyecto> estadosProyecto = new ArrayList<>();
		EstadoProyecto estadoProyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_ESTADOS_PROYECTO, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				estadoProyecto = new EstadoProyecto();
				
				estadoProyecto.setIdestadoproyecto(rs.getInt(t++)); 
				estadoProyecto.setDetalle(rs.getString(t++)); 
				estadoProyecto.setIdusuariocrea(rs.getInt(t++)); 
				estadoProyecto.setFechacrea(rs.getTimestamp(t++)); 
				estadoProyecto.setIdusuariomod(rs.getInt(t++)); 
				estadoProyecto.setFechamod(rs.getTimestamp(t++)); 
				estadoProyecto.setEstado(rs.getInt(t++));
				
				estadosProyecto.add(estadoProyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaEstadosProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaEstadosProyecto "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaEstadosProyecto ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaEstadosProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaEstadosProyecto "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar de cuotas acuerdo ");
		} finally {
			closeConexion();
			logger.info("finalizo dao EstadoProyectoDAO método listaEstadosProyecto!");
		}
		return estadosProyecto;
	}
	
	public Integer insertarEstadoProyecto(EstadoProyecto estadoProyecto) throws Exception {
		Integer llave = 0;
		StringBuilder varname1 = new StringBuilder();
		
		varname1.append(" INSERT INTO horasproyecto.estado_proyecto ");
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
			
			ps.setString(t++, estadoProyecto.getDetalle());//1
			ps.setInt(t++, estadoProyecto.getIdusuariocrea());//2
			ps.setInt(t++, EstadoEnum.ACTIVO.getIndex());//3
		
			ps.executeUpdate();
			llave = Statement.RETURN_GENERATED_KEYS;

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar de insertarEstadoProyecto  "
					+ " id del registro.... " + Statement.RETURN_GENERATED_KEYS + " tabla afectada....  insertarEstadoProyecto "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de insertarEstadoProyecto  ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de insertarEstadoProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... insertarEstadoProyecto" + "descripción de evento..."
					+ e);
			throw new Exception("Exception Error al tratar de insertarEstadoProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao EstadoProyectoDAO método insertarEstadoProyecto!");
		}
		return llave;
	}
	
}
