package projectoop2;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projectoop2.classes.Main;
import projectoop2.classes.Organizers;

class sessionTest {

	private static SessionFactory sessionFactory;
    private Session session;
    
    
    @BeforeAll
    public static void setup() {
        sessionFactory = Main.getCurrentSessionfromConfig().getSessionFactory();
        System.out.println("SessionFactory created");
    }
    
    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory closed");
    }
    
    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session open");
    }
    
    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed");
    }
    
	@Test
	void testInsert() {
	    session.beginTransaction();   
	    Organizers product = new Organizers("kamen","sotirov","Kamen","Sotirov","testmail@abv.bg");
	    Integer id = (Integer) session.save(product);
	     
	    session.getTransaction().commit();
	     
	    Assertions.assertTrue(id > 0);
	}
	
	@Test
	public void testGet() {	     
	    Integer id = 1;
	     
	    Organizers org = session.find(Organizers.class, id);
	     
	    assertEquals("kamen99", org.getOrgUsername());      
	}


}
