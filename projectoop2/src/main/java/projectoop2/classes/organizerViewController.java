package projectoop2.classes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class organizerViewController implements Initializable{


@FXML Label welcomeLabel;

public Organizers curr_org;
@FXML TableView<Events> events;
@FXML TableColumn<Events,String>evType;
@FXML TableColumn<Events,String>evLocation;
@FXML TableColumn<Events,LocalDate>evDate;
@FXML TableColumn<Events,String>evName;
@FXML TableColumn<Events,String>evStatus;
@FXML TableColumn<Events,Integer>evSeatCount;
@FXML TableColumn<Events,Integer>evCurrSeats;
@FXML TableColumn<Events,Double>evTicketPrice;
@FXML TableColumn<Events,Integer>evMaxTickets;
@FXML TableColumn<Events,Integer>evId;

@FXML ChoiceBox<String> evTypeChoiceBox;
@FXML ChoiceBox<String> evStatusChoiceBox;
@FXML JFXTextField evLocationField;
@FXML JFXTextField evTicketPriceField;
@FXML JFXTextField evNameField;
@FXML JFXTextField evSeatCountField;
@FXML JFXTextField evMaxTicketsField;
@FXML DatePicker evDatePicker;
@FXML ListView<String>distributorListView;
List<Distributors>disList;
@FXML JFXButton CreateEventButton;


@FXML Label UserNameLabel;

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
evType.setCellValueFactory(new PropertyValueFactory<>("evType"));
evLocation.setCellValueFactory(new PropertyValueFactory<>("evLocation"));
evDate.setCellValueFactory(new PropertyValueFactory<>("evDate"));
evName.setCellValueFactory(new PropertyValueFactory<>("evName"));
evStatus.setCellValueFactory(new PropertyValueFactory<>("evStatus"));
evSeatCount.setCellValueFactory(new PropertyValueFactory<>("evSeatcount"));
evCurrSeats.setCellValueFactory(new PropertyValueFactory<>("evCurrSeats"));
evTicketPrice.setCellValueFactory(new PropertyValueFactory<>("evTicketprice"));
evMaxTickets.setCellValueFactory(new PropertyValueFactory<>("evMaxRestriction"));
evId.setCellValueFactory(new PropertyValueFactory<>("evId"));

events.setEditable(true);

evLocation.setCellFactory(TextFieldTableCell.forTableColumn());
evType.setCellFactory(TextFieldTableCell.forTableColumn());
evName.setCellFactory(TextFieldTableCell.forTableColumn());
evStatus.setCellFactory(TextFieldTableCell.forTableColumn());
evSeatCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
evDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
evCurrSeats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
evMaxTickets.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
evTicketPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

evTypeChoiceBox.getItems().add("Sports");
evTypeChoiceBox.getItems().add("Music");
evTypeChoiceBox.getItems().add("Theatre");
evTypeChoiceBox.getItems().add("Seminar");
evTypeChoiceBox.getItems().add("Conference");
evTypeChoiceBox.getItems().add("Other");
evStatusChoiceBox.getItems().add("Held");
evStatusChoiceBox.getItems().add("Not yet held");


disList=findAllDistributors();
List<String>disNameList=new ArrayList<String>();
for(int i=0;i<disList.size();i++) {
	disNameList.add(disList.get(i).getDisUsername());
}
distributorListView.getItems().addAll(disNameList);
distributorListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);




}

public void createEvents() {
	if(evTypeChoiceBox.getSelectionModel().isEmpty()||evStatusChoiceBox.getSelectionModel().isEmpty()||evLocationField.getText().isBlank()||evTicketPriceField.getText().isBlank()||evNameField.getText().isBlank()||evSeatCountField.getText().isBlank()||evMaxTicketsField.getText().isBlank()) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Create Event");
		alert.setContentText("Null Field Found");
		alert.showAndWait();
	}else if(Integer.parseInt(evMaxTicketsField.getText())>=Integer.parseInt(evSeatCountField.getText())){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Create Event");
		alert.setContentText("Max Tickets should be less than the seat count");
		alert.showAndWait();
	}
	else
	{
	Session session1=Main.getCurrentSessionfromConfig();
	Events ev=new Events();
	ev.setEvType(evTypeChoiceBox.getValue());
	ev.setEvLocation(evLocationField.getText());
	ev.setEvDate(evDatePicker.getValue());
	ev.setEvName(evNameField.getText());
	ev.setEvStatus(evStatusChoiceBox.getValue());
	ev.setEvSeatcount(Integer.parseInt(evSeatCountField.getText()));
	ev.setEvCurrSeats(ev.getEvSeatcount());
	ev.setEvTicketprice(Double.parseDouble(evTicketPriceField.getText()));
	ev.setEvMaxRestriction(Integer.parseInt(evMaxTicketsField.getText()));
	
	
	curr_org=session1.find(Organizers.class, curr_org.getOrgId());
	
	
	curr_org.getOrgEvents().add(ev);
	ev.getOrganizerses().add(curr_org);
	try {
		session1.getSession();
		session1.beginTransaction();
		session1.saveOrUpdate(curr_org);
		session1.getTransaction().commit();
		session1.close();
		events.getItems().add(ev);
		
	}
	catch(Exception e) {
		
	}
	
	
	ObservableList<String>selectedDis=distributorListView.getSelectionModel().getSelectedItems();
	List<Distributors>disToBeSaved=new ArrayList<Distributors>();
	for(String d:selectedDis) {
		for(int i=0;i<disList.size();i++) {
			if(d==disList.get(i).getDisUsername()) {
				disList.get(i).getDisEvents().add(ev);
				ev.getDistributorses().add(disList.get(i));
				disToBeSaved.add(disList.get(i));
			}
		}
	}
	Session session2=Main.getCurrentSessionfromConfig();
	session2.getSessionFactory().openSession();
	session2.getSession();
	session2.beginTransaction();
	for(Distributors d:disToBeSaved) {
		d=session2.find(Distributors.class, d.getDisId());
		ev=session2.find(Events.class, ev.getEvId());
		d.getDisEvents().add(ev);
		ev.getDistributorses().add(d);
		session2.saveOrUpdate(d);
	}session2.getTransaction().commit();
	session2.close();
	
	evTypeChoiceBox.setValue(null);
	evStatusChoiceBox.setValue(null);
	evLocationField.setText(null);
	evTicketPriceField.setText(null);
	evNameField.setText(null);
	evSeatCountField.setText(null);
	evMaxTicketsField.setText(null);
	evDatePicker.setValue(null);
	
	}
}



public void EditEventLocation(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvLocation(editCell.getNewValue().toString());
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}

public void EditEventType(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvType(editCell.getNewValue().toString());
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}

public void EditEventDate(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvDate(LocalDate.parse(editCell.getNewValue().toString()));
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}

public void EditEventName(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvName(editCell.getNewValue().toString());
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}

public void EditEventStatus(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvStatus(editCell.getNewValue().toString());
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}


public void EditEventTicketPrice(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvTicketprice(Double.parseDouble(editCell.getNewValue().toString()));
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}


public void EditEventSeatCount(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvSeatcount(Integer.parseInt(editCell.getNewValue().toString()));
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}

public void EditEventCurrentSeats(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvCurrSeats(Integer.parseInt(editCell.getNewValue().toString()));
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}

public void EditEventMaxTickets(CellEditEvent editCell) {
	Session session=Main.getCurrentSessionfromConfig();
	Events event=events.getSelectionModel().getSelectedItem();
	event=session.find(Events.class,event.getEvId());
	event.setEvMaxRestriction(Integer.parseInt(editCell.getNewValue().toString()));
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	session.saveOrUpdate(event);
	session.getTransaction().commit();
	session.close();
	events.setItems(getEvents());
}

public void AssignEvent() {

	if(distributorListView.getSelectionModel().isEmpty()||events.getSelectionModel().isEmpty()) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Assign Event");
		alert.setContentText("Empty selection found");
		alert.showAndWait();
	}
	else {
			Events event=events.getSelectionModel().getSelectedItem();
	ObservableList<String>selectedDis=distributorListView.getSelectionModel().getSelectedItems();
	List<Distributors>disToBeSaved=new ArrayList<Distributors>();
	
	Session session=Main.getCurrentSessionfromConfig();
	session.getSessionFactory().openSession();
	session.getSession();
	session.beginTransaction();
	event=session.find(Events.class, event.getEvId());
		
	for(String d:selectedDis) {
		for(int i=0;i<disList.size();i++) {
			if(d==disList.get(i).getDisUsername()) {
				if(disList.get(i).getDisEvents().contains(event)) {
				}else {disList.get(i).getDisEvents().add(event);
				event.getDistributorses().add(disList.get(i));
				disToBeSaved.add(disList.get(i));}
			}
		}
	}
	
	
	for(Distributors d:disToBeSaved) {
		d=session.find(Distributors.class, d.getDisId());
		event=session.find(Events.class, event.getEvId());
		d.getDisEvents().add(event);
		event.getDistributorses().add(d);
		session.saveOrUpdate(d);

		session.saveOrUpdate(d);
	}session.getTransaction().commit();
	session.close();
	
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Assign Event");
	alert.setContentText("Assigned selected event to selected distributors");
	alert.showAndWait();
	}}


public void MyProfile(ActionEvent event) {
	FXMLLoader orgViewLoader=new FXMLLoader(getClass().getResource("orgProfileView.fxml"));
	try {
		
		Parent root=(Parent)orgViewLoader.load();
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
		orgProfileController controller=orgViewLoader.getController();
		controller.setCurrent_org(curr_org);
		controller.UserNameLabel.setText(curr_org.getOrgUsername());
		controller.PasswordLabel.setText(curr_org.getOrgPassword());
		controller.FirstNameLabel.setText(curr_org.getOrgFname());
		controller.LastNameLabel.setText(curr_org.getOrgLname());
		controller.EventCountLabel.setText(Integer.toString(curr_org.getOrgEvents().size()));
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void MyEventsList() {
	curr_org=Main.getCurrentSessionfromConfig().find(Organizers.class, curr_org.getOrgId());
	events.setItems(getEvents());
	if(events.getItems().isEmpty()) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("My Events");
		alert.setHeaderText("Empty Events List");
		alert.setContentText("Create an Event!");

		alert.showAndWait();
	}



}

public ObservableList<Events> getEvents(){
	ObservableList<Events>eventsList=FXCollections.observableArrayList();
	Session session1=Main.getCurrentSessionfromConfig();
	curr_org=session1.find(Organizers.class, curr_org.getOrgId());
	for (Events ev : curr_org.getOrgEvents()) {
        eventsList.add(ev);
     }
	return eventsList;	
}

public void unsoldTickets() {
	for(Events event : curr_org.getOrgEvents()){
		  if((event.getEvCurrSeats()==event.getEvSeatcount())&&event.getEvStatus().equals("Not yet held")) {
			  Notifications notification=Notifications.create().title("Unsold event").
					  text(event.getEvName()+" has 0 sold tickets").hideAfter(Duration.seconds(5)).position(Pos.TOP_LEFT);
			  notification.darkStyle();
			  notification.showInformation();
		  }
		}
}



public List<Distributors> findAllDistributors() {
	CriteriaBuilder cb = Main.getCurrentSessionfromConfig().getCriteriaBuilder();
    CriteriaQuery<Distributors> cq = cb.createQuery(Distributors.class);
    Root<Distributors> rootEntry = cq.from(Distributors.class);
    CriteriaQuery<Distributors> all = cq.select(rootEntry);
 
    TypedQuery<Distributors> allQuery = Main.getCurrentSessionfromConfig().createQuery(all);
    return allQuery.getResultList();    
}

public void setWelcomeLabel(String string) {
	
	welcomeLabel.setText("Welcome, "+string);
}

public Organizers getCurr_org() {
	return curr_org;
}

public void setCurr_org(Organizers curr_org) {
	this.curr_org = curr_org;
}


public void seatCountCheck(KeyEvent event) {
	if ((event.getCharacter().matches("[a-z]"))||(event.getCharacter().matches("[A-Z]"))||(event.getCharacter().matches("-"))||(event.getCharacter().matches("/")))
    {
        event.consume();
        evSeatCountField.backward();
        evSeatCountField.deleteNextChar();

    }
}//proverka dali e inputa e chislo

public void ticketPriceCheck(KeyEvent event) {
	if ((event.getCharacter().matches("[a-z]"))||(event.getCharacter().matches("[A-Z]"))||(event.getCharacter().matches("-"))||(event.getCharacter().matches("/")))
    {
        event.consume();
        evTicketPriceField.backward();
        evTicketPriceField.deleteNextChar();

    }
}


public void maxTicketsCheck(KeyEvent event) {
	if ((event.getCharacter().matches("[a-z]"))||(event.getCharacter().matches("[A-Z]"))||(event.getCharacter().matches("-"))||(event.getCharacter().matches("/")))
    {
        event.consume();
        evMaxTicketsField.backward();
        evMaxTicketsField.deleteNextChar();

    }
}


public void logOutButtonPressed(ActionEvent event) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Log out Confirmation");
	alert.setContentText("Are sure you wish to exit?");

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){
		FXMLLoader loginViewLoader=new FXMLLoader(getClass().getResource("sample.fxml"));
		try {
			
			Parent root=(Parent)loginViewLoader.load();
			Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} else {

	}
}


}
