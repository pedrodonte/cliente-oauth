package info.pedrodonte.oauth;

/**
 * Clase POJO que contiene los datos de la entidad que autoriza a nuestra a
 * aplicación.
 * 
 * @author Pedro Carrasco, pedrodonte@gmail.com
 *
 */
public class EntidadClaveUnicaVO {

	private String run;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;

	public String getRun() {
		return run;
	}

	public void setRun(String run) {
		this.run = run;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Override
	public String toString() {
		return "EntidadClaveUnicaVO [run=" + run + ", nombres=" + nombres
				+ ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
				+ apellidoMaterno + "]";
	}

}
