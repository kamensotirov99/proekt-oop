package projectoop2.classes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javafx.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main extends Application{

	public static void main(String[] args) {
		/*EvType tip=new EvType();
		tip.setEvtypeDesc("Party");
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(tip);
		session.getTransaction().commit();
		session.close();*/

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.initStyle(StageStyle.UNIFIED);
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	

	public static Session getCurrentSessionfromConfig() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		  return session;
	}
	
	
	/*public static void addEvType(String desc) {
		
		try {
			SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(tip);
		session.getTransaction().commit();
		}
		catch(Exception ex) {
			if(session.getTransaction()!=null) {
				session.rollback();
			}
			
		}
		finally {
			session.close();
		}
		
	}*/
}
