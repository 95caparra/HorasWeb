package co.sistemcobro.horas.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import co.sistemcobro.horas.bean.FechaCalendarioDTO;

public class CalendarioWS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CalendarioWS.class);
	private static final String URL = "http://172.16.1.60:8180/CalendarioWS/Rest/";
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private Client client;

	public CalendarioWS() {
		client = Client.create();
		client.setConnectTimeout(30000);
	}

	public String crearFestivo(String idpais, String fechafestivo, String observacion, Integer idUsuarioCrea)
			throws Exception {

		String input = "{\"idpais\": \"" + idpais + "\",\"fechafestivo\": \"" + fechafestivo + "\",\"observacion\": \""
				+ observacion + "\"}";

		ClientResponse response = client.resource(URL).path("AsignacionFecha/InsertarFecha")
				.header("Content-Type", "application/json").post(ClientResponse.class, input);

		String respuesta = response.getEntity(String.class);
		logger.info("Code: [" + response.getStatus() + "] -> Respuesta servicio de creación de festivo: " + respuesta);

		if (response.getStatus() != 200) {
			throw new Exception("Error al consumir servicio CrearFestivo");
		}

		return respuesta;
	}

	public String crearFestivoMasivo(List<FechaCalendarioDTO> listaFestivos) throws Exception {

		String input = "[";

		if (listaFestivos.size() > 1) {
			for (FechaCalendarioDTO listaFestivo : listaFestivos) {
				input += "{\"idpais\": \"" + listaFestivo.getIdpais() + "\",\"fechafestivo\": \""
						+ listaFestivo.getFechaFestivo() + "\",\"observacion\": \"" + listaFestivo.getObservacion()
						+ "\",\"idusuariocrea\": \"" + listaFestivo.getIdUsuarioCrea() + "\"}";
				if (listaFestivo != listaFestivos.get(listaFestivos.size() - 1)) {
					input += ",";
				}
			}
		} else {
			for (FechaCalendarioDTO lf : listaFestivos) {
				input += "{\"idpais\": \"" + lf.getIdpais() + "\",\"fechafestivo\": \"" + lf.getFechaFestivo()
						+ "\",\"observacion\": \"" + lf.getObservacion() + "\",\"idusuariocrea\": \""
						+ lf.getIdUsuarioCrea() + "\"}";
			}
		}

		input += "]";
		
		ClientResponse response = client.resource(URL).path("AsignacionFecha/InsertarFechaMasivo")
				.header("Content-Type", "application/json").post(ClientResponse.class, input);

		String respuesta = response.getEntity(String.class);
		logger.info("Code: [" + response.getStatus() + "] -> Respuesta servicio de creación de festivo: " + respuesta);

		if (response.getStatus() != 200) {
			throw new Exception("Error al consumir servicio crearFestivoMasivo");
		}

		return respuesta;
	}

	public List<FechaCalendarioDTO> consultaFestivos(String idpais) throws Exception {

		ClientResponse response = client.resource(URL).queryParam("idpais", idpais)
				.path("CalendarioAsignado/ListaFechaCalendario").accept("application/json").get(ClientResponse.class);

		String respuesta = response.getEntity(String.class);
		logger.info("Code: [" + response.getStatus() + "] -> Respuesta servicio de consulta de festivos: " + respuesta);

		List<FechaCalendarioDTO> festividades = new ArrayList<>();

		if (response.getStatus() != 200) {
			throw new Exception("Error al consumir servicio ConsultarFestivo");
		}

		festividades = JSON_MAPPER.readValue(respuesta,
				JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, FechaCalendarioDTO.class));

		return festividades;
	}

}
