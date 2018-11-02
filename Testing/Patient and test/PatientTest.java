package projectphase2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PatientTest {
	
	private Patient andrew;
	private String[] s;
	private Patient p;
	private String v;


	@Before
	public void setUp() throws Exception {
		andrew = new Patient("975345", "Andrew Ng", "1980-05-12");
		
	}
	
	@After
	public void tearDown() throws Exception {
		andrew = null;
	}
	
	@Test
	public void Gettest() {
		/*Patient p = new Patient("975345", "Andrew Ng", "1980-05-12");
		assertTrue((p.getInfo()).equals(new String[]{"975345", "Andrew Ng", "1980-05-12"}));*/
		assertEquals("975345", andrew.getInfo()[0]);
		assertEquals("Andrew Ng", andrew.getInfo()[1]);
		assertEquals("1980-05-12", andrew.getInfo()[2]);
	}

	@Test
	public void testGetRe() {
		Patient p = new Patient("452133", "Joyce", "1994-08-18");
		s = p.getVisitRecord("2014-04-10");
		assertEquals("2014-04-10", s[0]);
		assertEquals("20", s[1]);
		assertEquals("39.0", s[2]);
		assertEquals("140", s[3]);
		assertEquals("120", s[4]);
		assertEquals("92", s[5]);
	}
    
    @Test
    public void testUrgence() {
    	p = new Patient("324231", "Andrew", "1983-09-11");
    	s = p.getVisitRecord("2015-10-20");
    	v = p.urgentLevel(s);
    	assertEquals("Less Urgent", v);
    }

	
}
