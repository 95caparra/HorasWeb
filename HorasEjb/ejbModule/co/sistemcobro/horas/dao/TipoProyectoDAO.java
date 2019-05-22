package co.sistemcobro.horas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.bean.TipoProyecto;
import co.sistemcobro.horas.constante.EstadoEnum;


public class TipoProyectoDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(TipoProyectoDAO.class);

	public TipoProyectoDAO(DataSource ds) {
		super(ds);
	}
	
	private String SQL_LINEAS_TIPO_PROYECTO  = "SELECT idtipoproyecto, detalle, idusuariocrea, fechacrea, idusuariomod, fechamod, estado "
			+ " FROM horasproyecto.tipo_proyecto WHERE estado = ? ";
	
	public List<TipoProyecto> listaTiposProyecto() throws Exception{
		List<TipoProyecto> tiposProyecto = new ArrayList<>();
		TipoProyecto tipoProyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_LINEAS_TIPO_PROYECTO, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				tipoProyecto = new TipoProyecto();
				
				tipoProyecto.setIdTipoProyecto(rs.getInt(t++)); 
				tipoProyecto.setDetalle(rs.getString(t++)); 
				tipoProyecto.setIdusuariocrea(rs.getInt(t++)); 
				tipoProyecto.setFechacrea(rs.getTimestamp(t++)); 
				tipoProyecto.setIdusuariomod(rs.getInt(t++)); 
				tipoProyecto.setFechamod(rs.getTimestamp(t++)); 
				tipoProyecto.setEstado(rs.getInt(t++));
				
				tiposProyecto.add(tipoProyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaTiposProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaTiposProyecto "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaTiposProyecto ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaTiposProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaTiposProyecto "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar listaTiposProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao TipoProyectoDAO método listaTiposProyecto!");
		}
		return tiposProyecto;
	}
	
	public Integer insertarTipoProyecto(TipoProyecto tipoProyecto) throws Exception {
		Integer llave = 0;
		StringBuilder varname1 = new StringBuilder();

		varname1.append(" INSERT INTO horasproyecto.tipo_proyecto ");
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
			
			ps.setString(t++, tipoProyecto.getDetalle());//1
			ps.setInt(t++, tipoProyecto.getIdusuariocrea());//2
			ps.setInt(t++, EstadoEnum.ACTIVO.getIndex());//3
		
			ps.executeUpdate();
			llave = Statement.RETURN_GENERATED_KEYS;

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar de insertarTipoProyecto  "
					+ " id del registro.... " + Statement.RETURN_GENERATED_KEYS + " tabla afectada....  insertarTipoProyecto "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de insertarTipoProyecto  ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de insertarTipoProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... insertarTipoProyecto" + "descripción de evento..."
					+ e);
			throw new Exception("Exception Error al tratar de insertarTipoProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao TipoProyectoDAO método insertarTipoProyecto!");
		}
		return llave;
	}

}
