package cl.fonasa.cliente_oauth;

import static org.junit.Assert.*;
import info.pedrodonte.oauth.EntidadClaveUnicaVO;
import info.pedrodonte.oauth.JsonClaveUnicaMapper;

import java.io.IOException;

import org.junit.Test;

public class JSonMapperTest {
	
	static String ejemplo = "{\"run\":\"1-9\",\"nombres\":\"ANDRES\",\"apellidoPaterno\":\"BELLO\",\"apellidoMaterno\":\"MAR\u00cdN\"}";

	@Test
	public void test() {
		
		try {
			EntidadClaveUnicaVO vo = JsonClaveUnicaMapper.toVO(ejemplo);
			
			assertTrue( "1-9".equals(vo.getRun()) );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
