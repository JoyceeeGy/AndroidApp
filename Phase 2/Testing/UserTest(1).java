package projectphase2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User a;
	private User c;
	private Patient b;

	
	
	@Before
	public void setUp() throws Exception{
		a = new User();
		c = new User();
	}
	
	@After
	public void tearDown() throws Exception {
		a = null;
	}


	@Test
	public void TestgetPatient(){
		Patient jie = a.getPatient("324231");
		
		Patient Vijay = a.getPatient("452133");
		
		Patient nadia = a.getPatient("324521");
		
		assertEquals("324231", jie.getInfo()[0]);
		assertEquals("Jie Wu", jie.getInfo()[1]);
		assertEquals("1992-10-12", jie.getInfo()[2]);
		
		assertEquals("452133", Vijay.getInfo()[0]);
		assertEquals("Vijay Vazirani", Vijay.getInfo()[1]);
		assertEquals("1994-03-12", Vijay.getInfo()[2]);
		
		assertEquals("324521", nadia.getInfo()[0]);
		assertEquals("Nadia Magnenat Thalmann", nadia.getInfo()[1]);
		assertEquals("1962-10-12", nadia.getInfo()[2]);


		
	}
	@Test
	public void TestgetPatientData(){
		String[][] c = a.getPatientsData();
		assertEquals("1992-10-12", c[1][2]);
		assertEquals("975345", c[3][0]);
		assertEquals("Zhou Chaochen", c[12][1]);

			
		}
		
	}


