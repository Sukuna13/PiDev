package fitnessny.configr;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.After;  
import org.junit.Before;  
import org.junit.Test;
import org.mockito.Mock;




class DatabasTesting {
	
	final static Logger logger = Logger.getLogger(DatabasTesting.class);

	
	

	@Mock
	MyConnection connection;
	
	@Mock
	Connection cnx;
	
	
	@Before
	public void getting_Connexion_Instance() {
		System.out.println("Asserting that connexion instance isn't null");
		cnx=MyConnection.getInstance().getCnx();
	}
	
	
	@Test
	public void discovering_is_it_null_or_not() {
		assertNotNull(cnx);
	}
	
	
	
	@After
	public void loggin_the_result() {
		logger.info("connection done !");
	}
	
}
