package projectoop2.classes;

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

public class disProfileController implements Initializable{
	@FXML Label UserNameLabel;
	@FXML Label PasswordLabel;
	@FXML Label FirstNameLabel;
	@FXML Label LastNameLabel;
	@FXML Label EventCountLabel;
	@FXML Label RatingLabel;
	private Distributors current_dis;


	public Distributors getCurrent_dis() {
		return current_dis;
	}


	public void setCurrent_dis(Distributors current_dis) {
		this.current_dis = current_dis;
	}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			
		}
		
	public void BackButtonPressed(ActionEvent event) {
		try {
		FXMLLoader disViewLoader=new FXMLLoader(getClass().getResource("distributorView.fxml"));
		
		Parent root=(Parent)disViewLoader.load();
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
		

		disViewController controller=disViewLoader.getController();
		controller.setCurr_dis(current_dis);
		controller.setWelcomeLabel(controller.getCurr_dis().getDisFname());
		controller.MyEventsList();
		controller.MyTransactionsList();
	} catch(Exception e) {System.out.println("Error");}}
}
