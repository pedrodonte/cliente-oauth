package info.pedrodonte.oauth;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.message.types.ResponseType;

/**
 * Clase tipo Bean de Session que tiene el ROL en el patron MVC de Controlador
 * 
 * @author Pedro Carrasco, pedrodonte@gmail.com
 *
 */
@ManagedBean(name = "controlador")
@SessionScoped
public class ClaveUnicaControlador implements Serializable{
	private static final long serialVersionUID = 2015_06_11L;
	
	private EntidadClaveUnicaVO entidadVO = new EntidadClaveUnicaVO();

	@PostConstruct
	public void init() {
		entidadVO.setApellidoMaterno("NO-DATA");
		entidadVO.setApellidoPaterno("NO-DATA");
		entidadVO.setNombres("NO-DATA");
		entidadVO.setRun("NO-DATA");
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void doValidarClaveUnica(ActionEvent actionEvent) {

		try {
			// Proceso inicial para generar el codigo de autorizacion
			OAuthClientRequest request = OAuthClientRequest
					.authorizationLocation(ParametrosOAuth.OAUTH_SERVER_URL)
					.setClientId(ParametrosOAuth.CLIENT_ID)
					.setRedirectURI(ParametrosOAuth.OAUTH_SERVER_REDIRECT_URI)
					.setResponseType(ResponseType.CODE.toString())
					.setScope(ParametrosOAuth.SCOPE)
					.setState(ParametrosOAuth.STATE).buildQueryMessage();

			// Redireccion al OAUTH_SERVER_REDIRECT_URI más los parametros
			// necesarios para generar el codigo de autorizacion
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			externalContext.redirect(request.getLocationUri());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EntidadClaveUnicaVO getEntidadVO() {
		return entidadVO;
	}

	public void setEntidadVO(EntidadClaveUnicaVO entidadVO) {
		this.entidadVO = entidadVO;
	}

}
