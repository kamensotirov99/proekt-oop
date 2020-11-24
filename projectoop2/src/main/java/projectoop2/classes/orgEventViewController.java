package projectoop2.classes;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class orgEventViewController implements Initializable {
@FXML private TableView<Events> eventView;
@FXML private TableColumn<Events,String> eventNameColumn;
@FXML private TableColumn<Events,Date> eventDateColumn;
@FXML private TableColumn<Events,String> eventLocationColumn;
@FXML private TableColumn<Events,EvType> eventTypeColumn;
@FXML private TableColumn<Events,Boolean> eventStatusColumn;
@FXML private TableColumn<Events,Integer> eventSeatCountColumn;
@FXML private TableColumn<Events,Integer> eventCurrSeatCountColumn;
@FXML private TableColumn<Events,Float> eventTicketPriceColumn;
@FXML private TableColumn<Events,Integer> eventRestrictionColumn;
@FXML private TableColumn<Events,Integer> eventIDColumn;

private Organizers curr_org;

public Organizers getCurr_org() {
	return curr_org;
}


public void setCurr_org(Organizers curr_org) {
	this.curr_org = curr_org;
}


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	
}
	

public ObservableList<Events> getEvents(){
	
	ObservableList<Events> events=FXCollections.observableArrayList();
	for(Events ev:curr_org.getOrgEvents()) {
		events.add(ev);
	}
	return events;
}
	
	
}
