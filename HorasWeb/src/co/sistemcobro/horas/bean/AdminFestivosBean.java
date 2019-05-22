package co.sistemcobro.horas.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.sistemcobro.horas.constante.Constante;
import co.sistemcobro.horas.session.LoginBean;
import co.sistemcobro.horas.ws.CalendarioWS;
import co.sistemcobro.horas.bean.FechaCalendarioDTO;

@ManagedBean(name = "adminFestivosBean")
@ViewScoped
public class AdminFestivosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LoginBean loginBean;

	private Logger logger = Logger.getLogger(AdminFestivosBean.class);

	private StreamedContent file2;
	private UploadedFile file;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			loginBean = (application.evaluateExpressionGet(context, "#{loginBean}", LoginBean.class));

			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void plantillaXLS() {
		try {
			HSSFWorkbook wbObj = new HSSFWorkbook();
			HSSFSheet sheet = wbObj.createSheet("festivos");

			HSSFRow rowhead = sheet.createRow(0);

			rowhead.createCell(0).setCellValue("id País (1= Colombia, 2=Perú, 3=Panamá, 4=chile, 5=guatemala )");
			rowhead.createCell(1).setCellValue("fecha de festividad formato yyyy-MM-dd");
			rowhead.createCell(2).setCellValue("observación descripción de la festividad");

			HSSFRow row = sheet.createRow(1);

			row.createCell(0).setCellValue("2");
			row.createCell(1).setCellValue("2019-05-01");
			row.createCell(2).setCellValue("día del trabajo");

			String extensionFile = "xls";
			String nombre = "festivos" + "." + extensionFile;

			// FileOutputStream out = new FileOutputStream(new File(nombre));
			// workbook.write(out);
			// out.close();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			wbObj.write(outputStream);

			InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			file2 = new DefaultStreamedContent(inputStream, "application/xls", nombre);

		} catch (Exception e) {
			logger.error("se presento un error al bajar la plantilla ", e);
		}
	}

	public void upload() {
		if (file != null) {
			POIFSFileSystem poifsFileSystem = null;
			HSSFWorkbook hssfWorkbook = null;
			boolean bandera = false;
			try {

				List<FechaCalendarioDTO> listaFestivos = new ArrayList<>();

				InputStream inputStream = file.getInputstream();

				poifsFileSystem = new POIFSFileSystem(inputStream);

				hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

				HSSFRow row;

				for (int i = 1; i < hssfSheet.getLastRowNum()+1; i++) {
					row = hssfSheet.getRow(i);

					FechaCalendarioDTO fecha = new FechaCalendarioDTO();

					fecha.setIdpais(Integer.parseInt(row.getCell(0).toString()));
					fecha.setFechaFestivo(row.getCell(1).toString());
					fecha.setObservacion(row.getCell(2).toString());
					fecha.setIdUsuarioCrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));

					listaFestivos.add(fecha);
					bandera = true;
				}

				if (bandera == false) {
					for (int i = 1; i < hssfSheet.getLastRowNum() + 1; i++) {
						row = hssfSheet.getRow(i);

						FechaCalendarioDTO fecha = new FechaCalendarioDTO();

						fecha.setIdpais(Integer.parseInt(row.getCell(0).toString()));
						fecha.setFechaFestivo(row.getCell(1).toString());
						fecha.setObservacion(row.getCell(2).toString());
						fecha.setIdUsuarioCrea(Integer.parseInt(loginBean.getUsuarioHermes().getCodusuario()));

						listaFestivos.add(fecha);
					}
				}

				CalendarioWS calendarioWS = new CalendarioWS();

				List<FechaCalendarioDTO> listaFestivosWS = calendarioWS.consultaFestivos(Constante.PAIS);
				for (FechaCalendarioDTO listaFestivo1 : listaFestivosWS) {
					if (!listaFestivos.isEmpty()) {
						for (FechaCalendarioDTO listaFestivo2 : listaFestivos) {
							if (listaFestivo2.getFechaFestivo().toString()
									.equals(listaFestivo1.getFechaFestivo().toString())) {
								logger.info("encontro la fecha  " + listaFestivo2.getFechaFestivo()
										+ " que ya existe en el ws ");
								listaFestivos.remove(listaFestivo2);
								break;
							}
						}
					}else{
						break;
					}
				}

				if (!listaFestivos.isEmpty()) {
					calendarioWS.crearFestivoMasivo(listaFestivos);
					FacesMessage message = new FacesMessage("Se subio el archivo con fechas ",
							file.getFileName() + " ");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}

			} catch (Exception e) {
				logger.error("se presento un error al subir la plantilla ", e);
			}

		}
	}

	

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public StreamedContent getFile2() {
		return file2;
	}

	public void setFile2(StreamedContent file2) {
		this.file2 = file2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
