package co.sistemcobro.horas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.bean.HoraProyecto;
import co.sistemcobro.horas.constante.EstadoEnum;

public class HoraProyectoDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(HoraProyectoDAO.class);

	public HoraProyectoDAO(DataSource ds) {
		super(ds);
	}

	private String SQL_HORA_PROYECTO = "SELECT hp.idhoraproyecto, hp.idproyecto, hp.nombrepersona,"
			+ " hp.actividad,CONVERT(varchar, hp.fecha, 120) as fecha, hp.horas, hp.idestadoproyecto, hp.idusuariocrea, "
			+ " hp.fechacrea, hp.idusuariomod, hp.fechamod, hp.estado, p.idproyecto, p.priorizacioncomite,"
			+ " p.nombresolicitante, p.idtipoproyecto, p.idcampana, p.idlineanegocio, p.nombreproyecto,"
			+ " p.detalleproyecto, p.iddesarrollador, p.idestadoproyecto, p.fechainicioproyecto, "
			+ " p.fechafinproyecto, p.idusuariocrea, p.fechacrea, p.idusuariomod, p.fechamod, p.estado, "
			+ " ep.idestadoproyecto, ep.detalle, ep.idusuariocrea, ep.fechacrea, ep.idusuariomod, ep.fechamod,"
			+ " ep.estado " + " FROM horasproyecto.hora_proyecto hp "
			+ " JOIN horasproyecto.proyecto p ON p.idproyecto = hp.idproyecto "
			+ " JOIN horasproyecto.estado_proyecto ep ON ep.idestadoproyecto = hp.idestadoproyecto "
			+ " WHERE hp.estado = ? ";

	public List<HoraProyecto> listaHorasProyecto() throws Exception {
		List<HoraProyecto> horasProyecto = new ArrayList<>();
		HoraProyecto horaProyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_HORA_PROYECTO, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				horaProyecto = new HoraProyecto();

				horaProyecto.setIdhoraproyecto(rs.getInt(t++));
				horaProyecto.getProyecto().setIdproyecto(rs.getInt(t++));
				horaProyecto.setNombrePersona(rs.getString(t++));
				horaProyecto.setActividad(rs.getString(t++));
				horaProyecto.setFecha(rs.getString(t++));
				horaProyecto.setHoras(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				horaProyecto.setIdusuariocrea(rs.getInt(t++));
				horaProyecto.setFechacrea(rs.getTimestamp(t++));
				horaProyecto.setIdusuariomod(rs.getInt(t++));
				horaProyecto.setFechamod(rs.getTimestamp(t++));
				horaProyecto.setEstado(rs.getInt(t++));

				// proyecto
				horaProyecto.getProyecto().setIdproyecto(rs.getInt(t++));
				horaProyecto.getProyecto().setPriorizacioncomite(rs.getString(t++));
				horaProyecto.getProyecto().setNombresolicitante(rs.getString(t++));
				horaProyecto.getProyecto().getTipoProyecto().setIdTipoProyecto(rs.getInt(t++));
				horaProyecto.getProyecto().getCampana().setIdcampana(rs.getInt(t++));
				horaProyecto.getProyecto().getLineaNegocio().setIdlineanegocio(rs.getInt(t++));
				horaProyecto.getProyecto().setNombreproyecto(rs.getString(t++));
				horaProyecto.getProyecto().setDetalleproyecto(rs.getString(t++));
				horaProyecto.getProyecto().getDesarrollador().setIddesarrollador(rs.getInt(t++));
				horaProyecto.getProyecto().getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				horaProyecto.getProyecto().setFechainicioproyecto(rs.getDate(t++));
				horaProyecto.getProyecto().setFechafinproyecto(rs.getDate(t++));
				horaProyecto.getProyecto().setIdusuariocrea(rs.getInt(t++));
				horaProyecto.getProyecto().setFechacrea(rs.getTimestamp(t++));
				horaProyecto.getProyecto().setIdusuariomod(rs.getInt(t++));
				horaProyecto.getProyecto().setFechamod(rs.getTimestamp(t++));
				horaProyecto.getProyecto().setEstado(rs.getInt(t++));

				// estado proyecto
				horaProyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setDetalle(rs.getString(t++));
				horaProyecto.getEstadoProyecto().setIdusuariocrea(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setFechacrea(rs.getTimestamp(t++));
				horaProyecto.getEstadoProyecto().setIdusuariomod(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setFechamod(rs.getTimestamp(t++));
				horaProyecto.getEstadoProyecto().setEstado(rs.getInt(t++));

				horasProyecto.add(horaProyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaHorasProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaProyectos "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaHorasProyecto ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaHorasProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaHorasProyecto "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar listaHorasProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao HoraProyectoDAO método listaHorasProyecto!");
		}
		return horasProyecto;
	}
	
	private String SQL_HORA_PROYECTO_POR_EMPLEADO = "SELECT hp.idhoraproyecto, hp.idproyecto, hp.nombrepersona,"
			+ " hp.actividad,CONVERT(varchar, hp.fecha, 120) as fecha, hp.horas, hp.idestadoproyecto, hp.idusuariocrea, "
			+ " hp.fechacrea, hp.idusuariomod, hp.fechamod, hp.estado, p.idproyecto, p.priorizacioncomite,"
			+ " p.nombresolicitante, p.idtipoproyecto, p.idcampana, p.idlineanegocio, p.nombreproyecto,"
			+ " p.detalleproyecto, p.iddesarrollador, p.idestadoproyecto, p.fechainicioproyecto, "
			+ " p.fechafinproyecto, p.idusuariocrea, p.fechacrea, p.idusuariomod, p.fechamod, p.estado, "
			+ " ep.idestadoproyecto, ep.detalle, ep.idusuariocrea, ep.fechacrea, ep.idusuariomod, ep.fechamod,"
			+ " ep.estado, d.iddesarrollador, d.nombre, d.idusuariocrea, d.fechacrea, d.idusuariomod, d.fechamod, d.estado, d.codigousuario " 
			+ " FROM horasproyecto.hora_proyecto hp "
			+ " JOIN horasproyecto.proyecto p ON p.idproyecto = hp.idproyecto "
			+ " JOIN horasproyecto.estado_proyecto ep ON ep.idestadoproyecto = hp.idestadoproyecto"
			+ " JOIN horasproyecto.desarrollador d ON d.iddesarrollador = p.iddesarrollador "
			+ " WHERE hp.estado = ? AND d.codigousuario = ? ";

	public List<HoraProyecto> listaHorasProyectoPorEmpleado(String codigoUsuario) throws Exception {
		List<HoraProyecto> horasProyecto = new ArrayList<>();
		HoraProyecto horaProyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_HORA_PROYECTO_POR_EMPLEADO, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());
			ps.setString(2, codigoUsuario);

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				horaProyecto = new HoraProyecto();

				horaProyecto.setIdhoraproyecto(rs.getInt(t++));
				horaProyecto.getProyecto().setIdproyecto(rs.getInt(t++));
				horaProyecto.setNombrePersona(rs.getString(t++));
				horaProyecto.setActividad(rs.getString(t++));
				horaProyecto.setFecha(rs.getString(t++));
				horaProyecto.setHoras(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				horaProyecto.setIdusuariocrea(rs.getInt(t++));
				horaProyecto.setFechacrea(rs.getTimestamp(t++));
				horaProyecto.setIdusuariomod(rs.getInt(t++));
				horaProyecto.setFechamod(rs.getTimestamp(t++));
				horaProyecto.setEstado(rs.getInt(t++));

				// proyecto
				horaProyecto.getProyecto().setIdproyecto(rs.getInt(t++));
				horaProyecto.getProyecto().setPriorizacioncomite(rs.getString(t++));
				horaProyecto.getProyecto().setNombresolicitante(rs.getString(t++));
				horaProyecto.getProyecto().getTipoProyecto().setIdTipoProyecto(rs.getInt(t++));
				horaProyecto.getProyecto().getCampana().setIdcampana(rs.getInt(t++));
				horaProyecto.getProyecto().getLineaNegocio().setIdlineanegocio(rs.getInt(t++));
				horaProyecto.getProyecto().setNombreproyecto(rs.getString(t++));
				horaProyecto.getProyecto().setDetalleproyecto(rs.getString(t++));
				horaProyecto.getProyecto().getDesarrollador().setIddesarrollador(rs.getInt(t++));
				horaProyecto.getProyecto().getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				horaProyecto.getProyecto().setFechainicioproyecto(rs.getDate(t++));
				horaProyecto.getProyecto().setFechafinproyecto(rs.getDate(t++));
				horaProyecto.getProyecto().setIdusuariocrea(rs.getInt(t++));
				horaProyecto.getProyecto().setFechacrea(rs.getTimestamp(t++));
				horaProyecto.getProyecto().setIdusuariomod(rs.getInt(t++));
				horaProyecto.getProyecto().setFechamod(rs.getTimestamp(t++));
				horaProyecto.getProyecto().setEstado(rs.getInt(t++));

				// estado proyecto
				horaProyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setDetalle(rs.getString(t++));
				horaProyecto.getEstadoProyecto().setIdusuariocrea(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setFechacrea(rs.getTimestamp(t++));
				horaProyecto.getEstadoProyecto().setIdusuariomod(rs.getInt(t++));
				horaProyecto.getEstadoProyecto().setFechamod(rs.getTimestamp(t++));
				horaProyecto.getEstadoProyecto().setEstado(rs.getInt(t++));
				
				// desarrollador
				horaProyecto.getDesarrollador().setIddesarrollador(rs.getInt(t++));
				horaProyecto.getDesarrollador().setNombre(rs.getString(t++));
				horaProyecto.getDesarrollador().setIdusuariocrea(rs.getInt(t++));
				horaProyecto.getDesarrollador().setFechacrea(rs.getTimestamp(t++));
				horaProyecto.getDesarrollador().setIdusuariomod(rs.getInt(t++));
				horaProyecto.getDesarrollador().setFechamod(rs.getTimestamp(t++));
				horaProyecto.getDesarrollador().setEstado(rs.getInt(t++));
				horaProyecto.getDesarrollador().setCodigoUsuario(rs.getString(t++));

				horasProyecto.add(horaProyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaHorasProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaProyectos "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaHorasProyecto ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaHorasProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaHorasProyecto "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar listaHorasProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao HoraProyectoDAO método listaHorasProyecto!");
		}
		return horasProyecto;
	}
	
	private String SQL_HORAS_PROYECTO = "SELECT DISTINCT d.nombre, "
			+ " p.nombreproyecto, SUM(hp.horas) as horas_trabajadas "
			+ " FROM horasproyecto.proyecto p "
			+ " JOIN horasproyecto.hora_proyecto hp ON hp.idproyecto = p.idproyecto "
			+ " JOIN horasproyecto.desarrollador d ON d.iddesarrollador = p.iddesarrollador "
			+ " WHERE hp.estado =  ? AND d.estado = ? AND p.estado = ? "
			+ " GROUP BY d.nombre, p.nombreproyecto ";

	public List<HoraProyecto> horasPorProyecto() throws Exception {
		List<HoraProyecto> horasProyecto = new ArrayList<>();
		HoraProyecto horaProyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_HORAS_PROYECTO, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());
			ps.setInt(2, EstadoEnum.ACTIVO.getIndex());
			ps.setInt(3, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				horaProyecto = new HoraProyecto();

				horaProyecto.setNombreEmpleado(rs.getString(t++));
				horaProyecto.setNombreProyecto(rs.getString(t++));
				horaProyecto.setHorasTrabajadas(rs.getInt(t++));

				horasProyecto.add(horaProyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar horasPorProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....horasPorProyecto "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de horasPorProyecto ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de horasPorProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... horasPorProyecto "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar horasPorProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao HoraProyectoDAO método horasPorProyecto!");
		}
		return horasProyecto;
	}
	
	private String SQL_HORAS_EMPLEADO = "SELECT DISTINCT d.nombre, "
			+ " SUM(hp.horas) as horas_trabajadas "
			+ " FROM horasproyecto.proyecto p "
			+ " JOIN horasproyecto.hora_proyecto hp ON hp.idproyecto = p.idproyecto "
			+ " JOIN horasproyecto.desarrollador d ON d.iddesarrollador = p.iddesarrollador "
			+ " WHERE hp.estado =  ? AND d.estado = ? AND p.estado = ? "
			+ " GROUP BY d.nombre ";

	public List<HoraProyecto> horasPorEmpleado() throws Exception {
		List<HoraProyecto> horasProyecto = new ArrayList<>();
		HoraProyecto horaProyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_HORAS_EMPLEADO, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());
			ps.setInt(2, EstadoEnum.ACTIVO.getIndex());
			ps.setInt(3, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				horaProyecto = new HoraProyecto();

				horaProyecto.setNombreEmpleado(rs.getString(t++));
				horaProyecto.setHorasTrabajadas(rs.getInt(t++));

				horasProyecto.add(horaProyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar horasPorProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....horasPorProyecto "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de horasPorProyecto ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de horasPorProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... horasPorProyecto "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar horasPorProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao HoraProyectoDAO método horasPorProyecto!");
		}
		return horasProyecto;
	}

	public Integer insertarHoraProyecto(HoraProyecto horaProyecto) throws Exception {
		Integer llave = 0;
		StringBuilder varname1 = new StringBuilder();

		varname1.append(" INSERT INTO horasproyecto.hora_proyecto ");
		varname1.append(" ( ");
		varname1.append(" idproyecto, ");// 1
		varname1.append(" nombrepersona,");// 2
		varname1.append(" actividad, ");// 3
		varname1.append(" fecha, ");// 4
		varname1.append(" horas, ");// 5
		varname1.append(" idestadoproyecto, ");// 6
		varname1.append(" idusuariocrea, ");// 7
		varname1.append(" fechacrea, ");// 8
		varname1.append(" estado ");// 9
		varname1.append(" ) ");
		varname1.append(" VALUES ( ");
		varname1.append(" ?, ");// 1
		varname1.append(" ?, ");// 2
		varname1.append(" ?, ");// 3
		varname1.append(" ?, ");// 4
		varname1.append(" ?, ");// 5
		varname1.append(" ?, ");// 6
		varname1.append(" ?, ");// 7
		varname1.append(" GETDATE(), ");// 8
		varname1.append(" ? ");// 9
		varname1.append(" ) ");

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(varname1.toString(), Statement.RETURN_GENERATED_KEYS);

			int t = 1;

			// idproyecto // 1
			if (horaProyecto.getProyecto().getIdproyecto() != null) {
				ps.setInt(t++, horaProyecto.getProyecto().getIdproyecto());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			// nombre persona// 2
			if (horaProyecto.getNombrePersona() != null) {
				ps.setString(t++, horaProyecto.getNombrePersona());
			} else {
				ps.setNull(t++, java.sql.Types.VARCHAR);
			}

			// actividad // 3
			if (horaProyecto.getActividad() != null) {
				ps.setString(t++, horaProyecto.getActividad());
			} else {
				ps.setNull(t++, java.sql.Types.VARCHAR);
			}

			// fecha // 4
			if (horaProyecto.getFecha() != null) {				
				ps.setString(t++, horaProyecto.getFecha());
			} else {
				ps.setNull(t++, java.sql.Types.DATE);
			}

			// horas // 5
			if (horaProyecto.getHoras() != null) {
				ps.setInt(t++, horaProyecto.getHoras());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			// id estado proyecto // 6
			if (horaProyecto.getEstadoProyecto().getIdestadoproyecto() != null) {
				ps.setInt(t++, horaProyecto.getEstadoProyecto().getIdestadoproyecto());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			ps.setInt(t++, horaProyecto.getIdusuariocrea());// 7
			// 8 fecha
			ps.setInt(t++, EstadoEnum.ACTIVO.getIndex());// 9

			ps.executeUpdate();
			llave = Statement.RETURN_GENERATED_KEYS;

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar de insertarEstadoProyecto  " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....  insertarEstadoProyecto "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de insertarEstadoProyecto  ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de insertarEstadoProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... insertarEstadoProyecto"
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar de insertarEstadoProyecto ");
		} finally {
			closeConexion();
			logger.info("finalizo dao EstadoProyectoDAO método insertarEstadoProyecto!");
		}
		return llave;
	}

}
