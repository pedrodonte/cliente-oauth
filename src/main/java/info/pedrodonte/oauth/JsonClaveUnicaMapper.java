package info.pedrodonte.oauth;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase Utilitaria que encapsula el proceso de Mapeo entre JSON y una Clase POJO
 * 
 * @author Pedro Carrasco, pedrodonte@gmail.com
 *
 */
public class JsonClaveUnicaMapper {
	
	static ObjectMapper jsonMapper = new ObjectMapper();
	
	
	/**
	 * Metodo que recibe un String JSON y la convierte en una clase POJO
	 * @param entidadString
	 * @return EntidadClaveUnicaVO
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static EntidadClaveUnicaVO toVO(String entidadString) throws JsonParseException, JsonMappingException, IOException{
		EntidadClaveUnicaVO vo = jsonMapper.readValue(entidadString.getBytes(), EntidadClaveUnicaVO.class);
		return vo;
	}
	
}
