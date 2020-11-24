package projectoop2.classes;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class disViewController implements Initializable {
	@FXML Label welcomeLabel;

	private Distributors curr_dis;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}

	public void setWelcomeLabel(String string) {
		
		welcomeLabel.setText("Welcome "+string);
	}

	public Distributors getCurr_dis() {
		return curr_dis;
	}

	public void setCurr_dis(Distributors curr_dis) {
		this.curr_dis= curr_dis;
	}
	
	
}
