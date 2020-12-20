package projectoop2.classes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import com.jfoenix.controls.*;

public class LoginController implements Initializable {

@FXML private JFXTextField username_Field;

@FXML private JFXPasswordField password_Field;

@FXML private JFXButton login_button;

@FXML private Label incorrect_data;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void userLoginButtonPressed(ActionEvent event) {
    	if(username_Field.getText().equals("admin")&&password_Field.getText().equals("pass")) {
    		FXMLLoader adminViewLoader=new FXMLLoader(getClass().getResource("adminView.fxml"));
    		Parent root;
			try {
				root = (Parent)adminViewLoader.load();
				Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	}
    	else {
    	Main.getCurrentSessionfromConfig().beginTransaction();
    	Criteria OrganizersloginCriteria=Main.getCurrentSessionfromConfig().createCriteria(Organizers.class);
    	OrganizersloginCriteria.add(Restrictions.eq("orgUsername", username_Field.getText()));
    	OrganizersloginCriteria.add(Restrictions.eq("orgPassword",password_Field.getText()));
    	List<Organizers> organizers=(List<Organizers>)OrganizersloginCriteria.list();
    	if(organizers.isEmpty()) {
    		Criteria DistributorsloginCriteria=Main.getCurrentSessionfromConfig().createCriteria(Distributors.class);
    		DistributorsloginCriteria.add(Restrictions.eq("disUsername", username_Field.getText()));
    		DistributorsloginCriteria.add(Restrictions.eq("disPassword",password_Field.getText()));
        	List<Distributors> distributors=(List<Distributors>)DistributorsloginCriteria.list();
        	
        	
        	if(distributors.isEmpty()) {
        		incorrect_data.setVisible(true);
        	}else {
        		FXMLLoader disViewLoader=new FXMLLoader(getClass().getResource("distributorView.fxml"));
        		try {
        			
					Parent root=(Parent)disViewLoader.load();
					Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(new Scene(root));
					Distributors dis=distributors.get(0);
					disViewController controller=disViewLoader.getController();
					controller.setCurr_dis(dis);
					controller.setWelcomeLabel(controller.getCurr_dis().getDisFname());
					controller.MyEventsList();
					controller.MyTransactionsList();
					controller.unsoldTickets();
					
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
        	}
    	}else {
    		
    		try {
    		FXMLLoader orgViewLoader=new FXMLLoader(getClass().getResource("organizerView.fxml"));
    		
				Parent root=(Parent)orgViewLoader.load();
				Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
				
				//Organizers org=organizers.get(0);
				organizerViewController controller=orgViewLoader.getController();
				controller.setCurr_org(organizers.get(0));
				controller.setWelcomeLabel(controller.getCurr_org().getOrgFname());
				controller.MyEventsList();
				controller.unsoldTickets();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
    	
    }}
    
    
    

}




