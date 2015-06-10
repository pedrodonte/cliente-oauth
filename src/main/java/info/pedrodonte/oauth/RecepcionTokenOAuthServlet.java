package info.pedrodonte.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

@WebServlet(value = "/recepcion-token")
public class RecepcionTokenOAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 2015_06_11L;
	Logger logger = Logger.getLogger(getClass().getSimpleName());

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>Hello World!</h1>");

			/*-Recepcion Codigo Autorizacion-*/
			String codigoAutorizacion = request.getParameter("code");
			logger.info("CODIGO AUTORIZACION" + codigoAutorizacion);

			/*-Obtener el TOKEN de Seguridad-*/
			OAuthAccessTokenResponse oauthResponse = obtenerTokenOAuth(codigoAutorizacion);

			/*-Obtener los datos de la entidad que autoriza-*/
			String jsonDatosRequest = obtenerEntidadAutorizada(oauthResponse);

			logger.info("RESULTADO\n" + jsonDatosRequest.toString());

			/*-Obtener la instancia del Session Bean Controlador-*/
			ClaveUnicaControlador controladorJSF = (ClaveUnicaControlador) request
					.getSession().getAttribute("controlador");

			/*-Mapear los datos de la Entidad a un Objeto-*/
			EntidadClaveUnicaVO entidadVO = JsonClaveUnicaMapper
					.toVO(jsonDatosRequest.toString());

			/*-Setear el Value Object al Session Bean-*/
			controladorJSF.setEntidadVO(entidadVO);

			/*-Redireccionar al sitio privado-*/
			response.sendRedirect("sitio-privado.xhtml");

		} catch (OAuthProblemException e) {
			logger.fine("OAuth ERROR: " + e.getError());
			logger.fine("OAuth DESCRIPCION: " + e.getDescription());
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que tiene la funcion de obtener los datos JSON de la persona que
	 * autoriza a la aplicación a utilizar el mecanismo de autenticación
	 * 
	 * @param oauthResponse
	 * @return String
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private String obtenerEntidadAutorizada(
			OAuthAccessTokenResponse oauthResponse) throws IOException,
			ClientProtocolException {

		HttpClient clienteHTTP = HttpClientBuilder.create().build();
		HttpGet metodoGET = new HttpGet(ParametrosOAuth.OAUTH_SERVICE_API
				+ "?access_token=" + oauthResponse.getAccessToken());

		metodoGET.setHeader("User-Agent", "USER_AGENT");

		HttpResponse httpResponse = clienteHTTP.execute(metodoGET);

		logger.info("HTTP CODE RESPONSE : "
				+ httpResponse.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		StringBuilder datosRequest = new StringBuilder();
		String linea = "";
		while ((linea = rd.readLine()) != null) {
			datosRequest.append(linea);
		}

		return datosRequest.toString();
	}

	/**
	 * Metodo que procesa el codigo de autorización para obtener el Token de
	 * Seguridad otorgado por el servidor OAUTH
	 * 
	 * @param codigoAutorizacion
	 * @return OAuthAccessTokenResponse
	 * @throws OAuthSystemException
	 * @throws OAuthProblemException
	 */
	private OAuthAccessTokenResponse obtenerTokenOAuth(String codigoAutorizacion)
			throws OAuthSystemException, OAuthProblemException {
		logger.info("GENERANDO TOKEN....");

		OAuthClientRequest requestOAuth = OAuthClientRequest
				.tokenLocation(ParametrosOAuth.OAUTH_SERVER_TOKEN_URL)
				.setGrantType(GrantType.AUTHORIZATION_CODE)
				.setClientId(ParametrosOAuth.CLIENT_ID)
				.setClientSecret(ParametrosOAuth.CLIENT_SECRET)
				.setRedirectURI(ParametrosOAuth.OAUTH_SERVER_REDIRECT_URI)
				.setCode(codigoAutorizacion).buildBodyMessage();

		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

		OAuthAccessTokenResponse oauthResponse = oAuthClient
				.accessToken(requestOAuth);

		logger.info("TOKEN DE ACCESO: " + oauthResponse.getAccessToken());
		logger.info("EXPIRA EN: " + oauthResponse.getExpiresIn());
		return oauthResponse;
	}

}
