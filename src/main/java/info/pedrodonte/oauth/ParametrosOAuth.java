package info.pedrodonte.oauth;

/**
 * Clase con la constantes que utilizara el proceso de Autenticacion
 * 
 * @author Pedro Carrasco, pedrodonte@gmail.com
 *
 */
public class ParametrosOAuth {
	
	
	/*Constantes que dependen de la aplicación*/
	public static final String CLIENT_ID = "9c8535c8e89e253a89239336a37f5572"; 
    public static final String CLIENT_SECRET = "20c8ca9c6d37579f59a6ad5e9cde6b8b";
    public static final String OAUTH_SERVER_REDIRECT_URI =  "http://localhost:8080/cliente-oauth/recepcion-token";
    /*Ver en https://www.claveunica.cl/consola */
    
    
    /*Constantes del Servidor OAUTH*/
	public static final String OAUTH_SERVER_URL =  "https://www.claveunica.cl/oauth2/auth"; 
	public static final String OAUTH_SERVER_TOKEN_URL =  "https://www.claveunica.cl/oauth2/token"; 
    public static final String OAUTH_SERVICE_API =  "https://apis.modernizacion.cl/registrocivil/informacionpersonal/v1/info.php";
	public static final String SCOPE = "basico";
	public static final String STATE = "xyz";

}
