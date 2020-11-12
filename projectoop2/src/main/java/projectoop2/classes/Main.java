package projectoop2.classes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javafx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main extends Application{

	public static void main(String[] args) {
		/*EvType tip=new EvType();
		tip.setEvtypeDesc("Conference");
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
		primaryStage.setTitle("Test");
		Button buton=new Button();
		buton.setText("Test butonche");
		StackPane layout=new StackPane();
		layout.getChildren().add(buton);
		Scene scene=new Scene(layout,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
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
