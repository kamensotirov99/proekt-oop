package projectoop2.classes;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class organizerViewController implements Initializable{
@FXML Label welcomeLabel;

private Organizers curr_org;



@Override
public void initialize(URL arg0, ResourceBundle arg1) {

}




public void setWelcomeLabel(String string) {
	
	welcomeLabel.setText("Welcome "+string);
}

public Organizers getCurr_org() {
	return curr_org;
}

public void setCurr_org(Organizers curr_org) {
	this.curr_org = curr_org;
}



}
