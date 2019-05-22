package co.sistemcobro.horas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.sistemcobro.horas.bean.Desarrollador;
import co.sistemcobro.horas.constante.EstadoEnum;


public class DesarrolladorDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(DesarrolladorDAO.class);

	public DesarrolladorDAO(DataSource ds) {
		super(ds);
	}
	
	private String SQL_DESARROLLADORES = "SELECT iddesarrollador, nombre, idusuariocrea, fechacrea, idusuariomod, "
			+ " fechamod, estado "
			+ " FROM horasproyecto.desarrollador WHERE estado = ? ";
	
	public List<Desarrollador> listaDesarrolladores() throws Exception{
		List<Desarrollador> desarrolladores = new ArrayList<>();
		Desarrollador desarrollador = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_DESARROLLADORES, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, EstadoEnum.ACTIVO.getIndex());

			rs = ps.executeQuery();
			while (rs.next()) {
				int t = 1;
				desarrollador = new Desarrollador();
				
				desarrollador.setIddesarrollador(rs.getInt(t++)); 
				desarrollador.setNombre(rs.getString(t++)); 
				desarrollador.setIdusuariocrea(rs.getInt(t++)); 
				desarrollador.setFechacrea(rs.getTimestamp(t++)); 
				desarrollador.setIdusuariomod(rs.getInt(t++)); 
				desarrollador.setFechamod(rs.getTimestamp(t++)); 
				desarrollador.setEstado(rs.getInt(t++));
				
				desarrolladores.add(desarrollador);
			}

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar listaDesarrolladores " + " id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada....listaDesarrolladores "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de listaCampanas ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de listaDesarrolladores " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... listaDesarrolladores "
					+ "descripción de evento..." + e);
			throw new Exception("Exception Error al tratar de cuotas acuerdo ");
		} finally {
			closeConexion();
			logger.info("finalizo dao DesarrolladorDAO método listaDesarrolladores!");
		}
		return desarrolladores;
	}
	
	public Integer insertarDesarrollador(Desarrollador desarrollador) throws Exception {
		Integer llave = 0;
		StringBuilder varname1 = new StringBuilder();

		varname1.append(" INSERT INTO horasproyecto.desarrollador ");
		varname1.append(" ( ");
		varname1.append(" nombre, ");//1
		varname1.append(" idusuariocrea, ");//2
		varname1.append(" fechacrea,  ");//3
		varname1.append(" estado, ");//4
		varname1.append(" codigousuario ");//5
		varname1.append(" ) ");
		varname1.append(" VALUES ( ");
		varname1.append(" ?, ");//1
		varname1.append(" ?, ");//2
		varname1.append(" GETDATE(), ");//3
		varname1.append(" ?, ");//4
		varname1.append(" ? ");//5
		varname1.append(" ) ");
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(varname1.toString(), Statement.RETURN_GENERATED_KEYS);
			
			int t = 1;
			
			ps.setString(t++, desarrollador.getNombre());//1
			ps.setInt(t++, desarrollador.getIdusuariocrea());//2
			ps.setInt(t++, EstadoEnum.ACTIVO.getIndex());//3
			ps.setString(t++, desarrollador.getCodigoUsuario());//5
		
			ps.executeUpdate();
			llave = Statement.RETURN_GENERATED_KEYS;

		} catch (SQLException e) {
			logger.error("SQLException Error SQL al tratar de insertarDesarrollador  "
					+ " id del registro.... " + Statement.RETURN_GENERATED_KEYS + " tabla afectada....  insertarDesarrollador "
					+ "descripción de evento..." + e);
			throw new SQLException("SQLException Error SQL al tratar de insertarDesarrollador  ");
		} catch (Exception e) {
			logger.error("Exception Error al tratar de insertarDesarrollador " + "id del registro.... "
					+ Statement.RETURN_GENERATED_KEYS + " tabla afectada.... insertarDesarrollador" + "descripción de evento..."
					+ e);
			throw new Exception("Exception Error al tratar de insertarDesarrollador ");
		} finally {
			closeConexion();
			logger.info("finalizo dao DesarrolladorDAO método insertarDesarrollador!");
		}
		return llave;		
	}

}
