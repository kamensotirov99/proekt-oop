package projectoop2.classes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class orgProfileController implements Initializable{
@FXML Label UserNameLabel;
@FXML Label PasswordLabel;
@FXML Label FirstNameLabel;
@FXML Label LastNameLabel;
@FXML Label EventCountLabel;
private Organizers current_org;


public Organizers getCurrent_org() {
	return current_org;
}


public void setCurrent_org(Organizers current_org) {
	this.current_org = current_org;
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
public void BackButtonPressed(ActionEvent event) {
	try {
	FXMLLoader orgViewLoader=new FXMLLoader(getClass().getResource("organizerView.fxml"));
	
	Parent root=(Parent)orgViewLoader.load();
	Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	stage.setScene(new Scene(root));
	stage.show();
	

	organizerViewController controller=orgViewLoader.getController();
	controller.setCurr_org(current_org);
	controller.setWelcomeLabel(controller.getCurr_org().getOrgFname());
	controller.MyEventsList();
	
} catch(Exception e) {System.out.println("Error");}}
}


