package co.sistemcobro.horas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.bean.Campana;
import co.sistemcobro.horas.constante.EstadoEnum;

public class CampanaDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(CampanaDAO.class);

	public CampanaDAO(DataSource ds) {
		super(ds);
	}

	private String SQL_CAMPANAS = "SELECT idcampana, detalle, idusuariocrea, fechacrea, idusuariomod,"
			+ " fechamod, estado " + " FROM  horasproyecto.campana WHERE estado = ? ";

	public List<Campana> listaCampanas() throws Exception {
		List<Campana> campanas = new ArrayList<>();
		Campana campana = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_CAMPANAS, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				campana = new Campana();

				campana.setIdcampana(rs.getInt(t++));
				campana.setDetalle(rs.getString(t++));
				campana.setIdusuariocrea(rs.getInt(t++));
				campana.setFechacrea(rs.getTimestamp(t++));
				campana.setIdusuariomod(rs.getInt(t++));
				campana.setFechamod(rs.getTimestamp(t++));
				campana.setEstado(rs.getInt(t++));

				campanas.add(campana);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaCampanas " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaCampanas " + "descripción de evento..."
					+ e);
			throw new SQLException("SQLException Error SQL al tratar de listaCampanas ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaCampanas " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaCampanas "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar de cuotas acuerdo ");
		} finally {
			closeConexion();
			logger.info("finalizo dao CampanaDAO método listaCampanas!");
		}
		return campanas;
	}

	public Integer insertarCampana(Campana campana) throws Exception {
		Integer llave = 0;
		StringBuilder varname1 = new StringBuilder();

		varname1.append(" INSERT INTO horasproyecto.campana ");
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
			
			ps.setString(t++, campana.getDetalle());//1
			ps.setInt(t++, campana.getIdusuariocrea());//2
			ps.setInt(t++, EstadoEnum.ACTIVO.getIndex());//3
		
			ps.executeUpdate();
			llave = Statement.RETURN_GENERATED_KEYS;

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar de insertarCampana  "
					+ " id del registro.... " + Statement.RETURN_GENERATED_KEYS + " tabla afectada....  insertarCampana "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de insertarCampana  ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de insertarCampana " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... insertarCampana" + "descripción de evento..."
					+ e);
			throw new Exception("Exception Error al tratar de insertarCampana ");
		} finally {
			closeConexion();
			logger.info("finalizo dao CampanaDAO método insertarCampana!");
		}
		return llave;

	}

}
