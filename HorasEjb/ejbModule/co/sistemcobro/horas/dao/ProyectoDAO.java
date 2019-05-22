package co.sistemcobro.horas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.bean.Proyecto;
import co.sistemcobro.horas.constante.EstadoEnum;

public class ProyectoDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(ProyectoDAO.class);

	public ProyectoDAO(DataSource ds) {
		super(ds);
	}

	private String SQL_PROYECTOS = "SELECT p.idproyecto, p.priorizacioncomite, p.nombresolicitante,"
			+ " p.idtipoproyecto, p.idcampana, p.idlineanegocio, p.nombreproyecto, p.detalleproyecto, "
			+ " p.iddesarrollador, p.idestadoproyecto, p.fechainicioproyecto, p.fechafinproyecto, "
			+ " p.idusuariocrea, p.fechacrea, p.idusuariomod, p.fechamod, p.estado,"
			+ " tp.idtipoproyecto, tp.detalle, tp.idusuariocrea, tp.fechacrea, tp.idusuariomod, "
			+ " tp.fechamod, tp.estado, ca.idcampana, ca.detalle, ca.idusuariocrea, ca.fechacrea,"
			+ " ca.idusuariomod, ca.fechamod, ca.estado, ln.idlineanegocio, ln.detalle, "
			+ " ln.idusuariocrea, ln.fechacrea, ln.idusuariomod, ln.fechamod, ln.estado, "
			+ " d.iddesarrollador, d.nombre, d.idusuariocrea, d.fechacrea, d.idusuariomod, d.fechamod, "
			+ " d.estado, ep.idestadoproyecto, ep.detalle, ep.idusuariocrea, ep.fechacrea, ep.idusuariomod,"
			+ " ep.fechamod, ep.estado " + " FROM horasproyecto.proyecto p  "
			+ " JOIN horasproyecto.tipo_proyecto tp ON tp.idtipoproyecto = p.idtipoproyecto"
			+ " JOIN horasproyecto.campana ca ON ca.idcampana = p.idcampana "
			+ " JOIN horasproyecto.linea_negocio ln ON ln.idlineanegocio = p.idlineanegocio "
			+ " JOIN horasproyecto.desarrollador d ON d.iddesarrollador = p.iddesarrollador "
			+ " JOIN horasproyecto.estado_proyecto ep ON ep.idestadoproyecto = p.idestadoproyecto "
			+ " WHERE p.estado = ? ";

	public List<Proyecto> listaProyectos() throws Exception {
		List<Proyecto> proyectos = new ArrayList<>();
		Proyecto proyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_PROYECTOS, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				proyecto = new Proyecto();

				proyecto.setIdproyecto(rs.getInt(t++));
				proyecto.setPriorizacioncomite(rs.getString(t++));
				proyecto.setNombresolicitante(rs.getString(t++));
				proyecto.getTipoProyecto().setIdTipoProyecto(rs.getInt(t++));
				proyecto.getCampana().setIdcampana(rs.getInt(t++));
				proyecto.getLineaNegocio().setIdlineanegocio(rs.getInt(t++));
				proyecto.setNombreproyecto(rs.getString(t++));
				proyecto.setDetalleproyecto(rs.getString(t++));
				proyecto.getDesarrollador().setIddesarrollador(rs.getInt(t++));
				proyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				proyecto.setFechainicioproyecto(rs.getDate(t++));
				proyecto.setFechafinproyecto(rs.getDate(t++));
				proyecto.setIdusuariocrea(rs.getInt(t++));
				proyecto.setFechacrea(rs.getTimestamp(t++));
				proyecto.setIdusuariomod(rs.getInt(t++));
				proyecto.setFechamod(rs.getTimestamp(t++));
				proyecto.setEstado(rs.getInt(t++));

				// tipo de proyecto
				proyecto.getTipoProyecto().setIdTipoProyecto(rs.getInt(t++));
				proyecto.getTipoProyecto().setDetalle(rs.getString(t++));
				proyecto.getTipoProyecto().setIdusuariocrea(rs.getInt(t++));
				proyecto.getTipoProyecto().setFechacrea(rs.getTimestamp(t++));
				proyecto.getTipoProyecto().setIdusuariomod(rs.getInt(t++));
				proyecto.getTipoProyecto().setFechamod(rs.getTimestamp(t++));
				proyecto.getTipoProyecto().setEstado(rs.getInt(t++));

				// campaña
				proyecto.getCampana().setIdcampana(rs.getInt(t++));
				proyecto.getCampana().setDetalle(rs.getString(t++));
				proyecto.getCampana().setIdusuariocrea(rs.getInt(t++));
				proyecto.getCampana().setFechacrea(rs.getTimestamp(t++));
				proyecto.getCampana().setIdusuariomod(rs.getInt(t++));
				proyecto.getCampana().setFechamod(rs.getTimestamp(t++));
				proyecto.getCampana().setEstado(rs.getInt(t++));

				// linea de negocio
				proyecto.getLineaNegocio().setIdlineanegocio(rs.getInt(t++));
				proyecto.getLineaNegocio().setDetalle(rs.getString(t++));
				proyecto.getLineaNegocio().setIdusuariocrea(rs.getInt(t++));
				proyecto.getLineaNegocio().setFechacrea(rs.getTimestamp(t++));
				proyecto.getLineaNegocio().setIdusuariomod(rs.getInt(t++));
				proyecto.getLineaNegocio().setFechamod(rs.getTimestamp(t++));
				proyecto.getLineaNegocio().setEstado(rs.getInt(t++));

				// desarrollador
				proyecto.getDesarrollador().setIddesarrollador(rs.getInt(t++));
				proyecto.getDesarrollador().setNombre(rs.getString(t++));
				proyecto.getDesarrollador().setIdusuariocrea(rs.getInt(t++));
				proyecto.getDesarrollador().setFechacrea(rs.getTimestamp(t++));
				proyecto.getDesarrollador().setIdusuariomod(rs.getInt(t++));
				proyecto.getDesarrollador().setFechamod(rs.getTimestamp(t++));
				proyecto.getDesarrollador().setEstado(rs.getInt(t++));

				// estado proyecto
				proyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				proyecto.getEstadoProyecto().setDetalle(rs.getString(t++));
				proyecto.getEstadoProyecto().setIdusuariocrea(rs.getInt(t++));
				proyecto.getEstadoProyecto().setFechacrea(rs.getTimestamp(t++));
				proyecto.getEstadoProyecto().setIdusuariomod(rs.getInt(t++));
				proyecto.getEstadoProyecto().setFechamod(rs.getTimestamp(t++));
				proyecto.getEstadoProyecto().setEstado(rs.getInt(t++));

				proyectos.add(proyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaTiposProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaProyectos "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaProyectos ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaTiposProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaProyectos "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar listaProyectos ");
		} finally {
			closeConexion();
			logger.info("finalizo dao ProyectoDAO método listaProyectos!");
		}
		return proyectos;
	}
	
	private String SQL_PROYECTOS_POR_EMPLEADO = "SELECT p.idproyecto, p.priorizacioncomite, p.nombresolicitante,"
			+ " p.idtipoproyecto, p.idcampana, p.idlineanegocio, p.nombreproyecto, p.detalleproyecto, "
			+ " p.iddesarrollador, p.idestadoproyecto, p.fechainicioproyecto, p.fechafinproyecto, "
			+ " p.idusuariocrea, p.fechacrea, p.idusuariomod, p.fechamod, p.estado,"
			+ " tp.idtipoproyecto, tp.detalle, tp.idusuariocrea, tp.fechacrea, tp.idusuariomod, "
			+ " tp.fechamod, tp.estado, ca.idcampana, ca.detalle, ca.idusuariocrea, ca.fechacrea,"
			+ " ca.idusuariomod, ca.fechamod, ca.estado, ln.idlineanegocio, ln.detalle, "
			+ " ln.idusuariocrea, ln.fechacrea, ln.idusuariomod, ln.fechamod, ln.estado, "
			+ " d.iddesarrollador, d.nombre, d.idusuariocrea, d.fechacrea, d.idusuariomod, d.fechamod, "
			+ " d.estado, d.codigousuario, ep.idestadoproyecto, ep.detalle, ep.idusuariocrea, ep.fechacrea, ep.idusuariomod,"
			+ " ep.fechamod, ep.estado " + " FROM horasproyecto.proyecto p  "
			+ " JOIN horasproyecto.tipo_proyecto tp ON tp.idtipoproyecto = p.idtipoproyecto"
			+ " JOIN horasproyecto.campana ca ON ca.idcampana = p.idcampana "
			+ " JOIN horasproyecto.linea_negocio ln ON ln.idlineanegocio = p.idlineanegocio "
			+ " JOIN horasproyecto.desarrollador d ON d.iddesarrollador = p.iddesarrollador "
			+ " JOIN horasproyecto.estado_proyecto ep ON ep.idestadoproyecto = p.idestadoproyecto "
			+ " WHERE p.estado = ? AND  d.codigousuario = ? ";

	public List<Proyecto> listaProyectosPorEmpleado(String codigoUsuario) throws Exception {
		List<Proyecto> proyectos = new ArrayList<>();
		Proyecto proyecto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_PROYECTOS_POR_EMPLEADO, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());
			ps.setString(2, codigoUsuario);

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				proyecto = new Proyecto();

				proyecto.setIdproyecto(rs.getInt(t++));
				proyecto.setPriorizacioncomite(rs.getString(t++));
				proyecto.setNombresolicitante(rs.getString(t++));
				proyecto.getTipoProyecto().setIdTipoProyecto(rs.getInt(t++));
				proyecto.getCampana().setIdcampana(rs.getInt(t++));
				proyecto.getLineaNegocio().setIdlineanegocio(rs.getInt(t++));
				proyecto.setNombreproyecto(rs.getString(t++));
				proyecto.setDetalleproyecto(rs.getString(t++));
				proyecto.getDesarrollador().setIddesarrollador(rs.getInt(t++));
				proyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				proyecto.setFechainicioproyecto(rs.getDate(t++));
				proyecto.setFechafinproyecto(rs.getDate(t++));
				proyecto.setIdusuariocrea(rs.getInt(t++));
				proyecto.setFechacrea(rs.getTimestamp(t++));
				proyecto.setIdusuariomod(rs.getInt(t++));
				proyecto.setFechamod(rs.getTimestamp(t++));
				proyecto.setEstado(rs.getInt(t++));

				// tipo de proyecto
				proyecto.getTipoProyecto().setIdTipoProyecto(rs.getInt(t++));
				proyecto.getTipoProyecto().setDetalle(rs.getString(t++));
				proyecto.getTipoProyecto().setIdusuariocrea(rs.getInt(t++));
				proyecto.getTipoProyecto().setFechacrea(rs.getTimestamp(t++));
				proyecto.getTipoProyecto().setIdusuariomod(rs.getInt(t++));
				proyecto.getTipoProyecto().setFechamod(rs.getTimestamp(t++));
				proyecto.getTipoProyecto().setEstado(rs.getInt(t++));

				// campaña
				proyecto.getCampana().setIdcampana(rs.getInt(t++));
				proyecto.getCampana().setDetalle(rs.getString(t++));
				proyecto.getCampana().setIdusuariocrea(rs.getInt(t++));
				proyecto.getCampana().setFechacrea(rs.getTimestamp(t++));
				proyecto.getCampana().setIdusuariomod(rs.getInt(t++));
				proyecto.getCampana().setFechamod(rs.getTimestamp(t++));
				proyecto.getCampana().setEstado(rs.getInt(t++));

				// linea de negocio
				proyecto.getLineaNegocio().setIdlineanegocio(rs.getInt(t++));
				proyecto.getLineaNegocio().setDetalle(rs.getString(t++));
				proyecto.getLineaNegocio().setIdusuariocrea(rs.getInt(t++));
				proyecto.getLineaNegocio().setFechacrea(rs.getTimestamp(t++));
				proyecto.getLineaNegocio().setIdusuariomod(rs.getInt(t++));
				proyecto.getLineaNegocio().setFechamod(rs.getTimestamp(t++));
				proyecto.getLineaNegocio().setEstado(rs.getInt(t++));

				// desarrollador
				proyecto.getDesarrollador().setIddesarrollador(rs.getInt(t++));
				proyecto.getDesarrollador().setNombre(rs.getString(t++));
				proyecto.getDesarrollador().setIdusuariocrea(rs.getInt(t++));
				proyecto.getDesarrollador().setFechacrea(rs.getTimestamp(t++));
				proyecto.getDesarrollador().setIdusuariomod(rs.getInt(t++));
				proyecto.getDesarrollador().setFechamod(rs.getTimestamp(t++));
				proyecto.getDesarrollador().setEstado(rs.getInt(t++));
				proyecto.getDesarrollador().setCodigoUsuario(rs.getString(t++));

				// estado proyecto
				proyecto.getEstadoProyecto().setIdestadoproyecto(rs.getInt(t++));
				proyecto.getEstadoProyecto().setDetalle(rs.getString(t++));
				proyecto.getEstadoProyecto().setIdusuariocrea(rs.getInt(t++));
				proyecto.getEstadoProyecto().setFechacrea(rs.getTimestamp(t++));
				proyecto.getEstadoProyecto().setIdusuariomod(rs.getInt(t++));
				proyecto.getEstadoProyecto().setFechamod(rs.getTimestamp(t++));
				proyecto.getEstadoProyecto().setEstado(rs.getInt(t++));

				proyectos.add(proyecto);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaTiposProyecto " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaProyectos "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaProyectos ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaTiposProyecto " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaProyectos "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar listaProyectos ");
		} finally {
			closeConexion();
			logger.info("finalizo dao ProyectoDAO método listaProyectos!");
		}
		return proyectos;
	}

	public Integer insertarProyecto(Proyecto proyecto) throws Exception {
		Integer llave = 0;
		StringBuilder varname1 = new StringBuilder();

		varname1.append(" INSERT INTO horasproyecto.proyecto ");
		varname1.append(" ( ");
		varname1.append(" priorizacioncomite, ");// 1
		varname1.append(" nombresolicitante, ");// 2
		varname1.append(" idtipoproyecto, ");// 3
		varname1.append(" idcampana, ");// 4
		varname1.append(" idlineanegocio, ");// 5
		varname1.append(" nombreproyecto, ");// 6
		varname1.append(" detalleproyecto, ");// 7
		varname1.append(" iddesarrollador, ");// 8
		varname1.append(" idestadoproyecto, ");// 9
		varname1.append(" fechainicioproyecto, ");// 10
		varname1.append(" fechafinproyecto, ");// 11
		varname1.append(" idusuariocrea, ");// 12
		varname1.append(" fechacrea, ");// 13
		varname1.append(" estado ");// 14
		varname1.append(" ) ");
		varname1.append(" VALUES ( ");
		varname1.append(" ?, ");// 1
		varname1.append(" ?, ");// 2
		varname1.append(" ?, ");// 3
		varname1.append(" ?, ");// 4
		varname1.append(" ?, ");// 5
		varname1.append(" ?, ");// 6
		varname1.append(" ?, ");// 7
		varname1.append(" ?, ");// 8
		varname1.append(" ?, ");// 9
		varname1.append(" ?, ");// 10
		varname1.append(" ?, ");// 11
		varname1.append(" ?, ");// 12
		varname1.append(" GETDATE(), ");// 13
		varname1.append(" ? ");// 14
		varname1.append(" ) ");

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(varname1.toString(), Statement.RETURN_GENERATED_KEYS);

			int t = 1;

			// priorización// 1
			if (proyecto.getPriorizacioncomite() != null) {
				ps.setString(t++, proyecto.getPriorizacioncomite());
			} else {
				ps.setNull(t++, java.sql.Types.VARCHAR);
			}

			// solicitante// 2
			if (proyecto.getNombresolicitante() != null) {
				ps.setString(t++, proyecto.getNombresolicitante());
			} else {
				ps.setNull(t++, java.sql.Types.VARCHAR);
			}

			// idtipoproyecto// 3
			if (proyecto.getTipoProyecto().getIdTipoProyecto() != null) {
				ps.setInt(t++, proyecto.getTipoProyecto().getIdTipoProyecto());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			// idcampana// 4
			if (proyecto.getCampana().getIdcampana() != null) {
				ps.setInt(t++, proyecto.getCampana().getIdcampana());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			// idlineanegocio// 5
			if (proyecto.getLineaNegocio().getIdlineanegocio() != null) {
				ps.setInt(t++, proyecto.getLineaNegocio().getIdlineanegocio());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			// nombre proyecto // 6
			if (proyecto.getNombreproyecto() != null) {
				ps.setString(t++, proyecto.getNombreproyecto());
			} else {
				ps.setNull(t++, java.sql.Types.VARCHAR);
			}

			// detalle proyecto // 7
			if (proyecto.getDetalleproyecto() != null) {
				ps.setString(t++, proyecto.getDetalleproyecto());
			} else {
				ps.setNull(t++, java.sql.Types.VARCHAR);
			}

			// iddesarrollador // 8
			if (proyecto.getDesarrollador().getIddesarrollador() != null) {
				ps.setInt(t++, proyecto.getDesarrollador().getIddesarrollador());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			// idestadoproyecto // 9
			if (proyecto.getEstadoProyecto().getIdestadoproyecto() != null) {
				ps.setInt(t++, proyecto.getEstadoProyecto().getIdestadoproyecto());
			} else {
				ps.setNull(t++, java.sql.Types.INTEGER);
			}

			// fecha inicio proyecto // 10
			if (proyecto.getFechainicioproyecto() != null) {
				ps.setDate(t++, proyecto.getFechainicioproyecto());
			} else {
				ps.setNull(t++, java.sql.Types.DATE);
			}

			// fecha fin proyecto // 11
			if (proyecto.getFechafinproyecto() != null) {
				ps.setDate(t++, proyecto.getFechafinproyecto());
			} else {
				ps.setNull(t++, java.sql.Types.DATE);
			}

			ps.setInt(t++, proyecto.getIdusuariocrea());// 12
			// 13 fecha
			ps.setInt(t++, EstadoEnum.ACTIVO.getIndex());// 14

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
			logger.info("finalizo dao ProyectoDAO método insertarEstadoProyecto!");
		}
		return llave;
	}

}
