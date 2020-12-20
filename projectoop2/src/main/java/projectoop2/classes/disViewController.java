package projectoop2.classes;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.hibernate.Session;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;




public class disViewController implements Initializable {
	@FXML Label welcomeLabel;
	@FXML TableView<Events> events;
	@FXML TableColumn<Events,String>evType;
	@FXML TableColumn<Events,String>evLocation;
	@FXML TableColumn<Events,Date>evDate;
	@FXML TableColumn<Events,String>evName;
	@FXML TableColumn<Events,String>evStatus;
	@FXML TableColumn<Events,Integer>evSeatCount;
	@FXML TableColumn<Events,Integer>evCurrSeats;
	@FXML TableColumn<Events,Double>evTicketPrice;
	@FXML TableColumn<Events,Integer>evMaxTickets;
	@FXML TableColumn<Events,Integer>evId;
	@FXML JFXTextField firstNameField;
	@FXML JFXTextField lastNameField;
	@FXML JFXTextField ticketCountField;
	private Distributors curr_dis;
	@FXML TableView<Transactions>transactions;
	@FXML TableColumn<Transactions,String>tr_fName;
	@FXML TableColumn<Transactions,String>tr_lName;
	@FXML TableColumn<Transactions,String>tr_evName;
	@FXML TableColumn<Transactions,Integer>tr_ticketCount;

	@FXML JFXButton createTransactionButton;
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
		tr_fName.setCellValueFactory(new PropertyValueFactory<>("trBuyerFname"));
		tr_lName.setCellValueFactory(new PropertyValueFactory<>("trBuyerLname"));
		tr_evName.setCellValueFactory(new PropertyValueFactory<>("events"));
		tr_ticketCount.setCellValueFactory(new PropertyValueFactory<>("ticketTrCount"));
	}
	
	
	public void MyProfile(ActionEvent event) {
		FXMLLoader disViewLoader=new FXMLLoader(getClass().getResource("disProfileView.fxml"));
		try {
			
			Parent root=(Parent)disViewLoader.load();
			Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
			disProfileController controller=disViewLoader.getController();
			controller.setCurrent_dis(curr_dis);
			controller.UserNameLabel.setText(curr_dis.getDisUsername());
			controller.PasswordLabel.setText(curr_dis.getDisPassword());
			controller.FirstNameLabel.setText(curr_dis.getDisFname());
			controller.LastNameLabel.setText(curr_dis.getDisLname());
			controller.EventCountLabel.setText(Integer.toString(curr_dis.getDisEvents().size()));
			controller.RatingLabel.setText(Double.toString(curr_dis.getDisRating()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void numCheck(KeyEvent event) {
		if ((event.getCharacter().matches("[a-z]"))||(event.getCharacter().matches("[A-Z]"))||(event.getCharacter().matches("-"))||(event.getCharacter().matches("/")))
	    {
	        event.consume();
	        ticketCountField.backward();
	        ticketCountField.deleteNextChar();

	    }
	}
	
	public void firstNameCheck(KeyEvent event) {
		if ((event.getCharacter().matches("[a-z]"))||(event.getCharacter().matches("[A-Z]")))
	    {
	    }else {
	    event.consume();
        firstNameField.backward();
        firstNameField.deleteNextChar();}
	}
	
	public void lastNameCheck(KeyEvent event) {
		if ((event.getCharacter().matches("[a-z]"))||(event.getCharacter().matches("[A-Z]")))
	    {
	    }else {
	    event.consume();
        lastNameField.backward();
        lastNameField.deleteNextChar();}
	}
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}//funkciq za zakruglqne na double chislo
	
	public void createTransaction() {
		if(lastNameField.getText().isBlank()||firstNameField.getText().isBlank()||ticketCountField.getText().isBlank()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Create Transaction");
			alert.setContentText("Empty field found");
			alert.showAndWait();
		}else {
		
		if(events.getSelectionModel().isEmpty()||curr_dis.getDisEvents().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Create Transaction");
			alert.setContentText("No event selected");
			alert.showAndWait();
		}else {
			Session session1=Main.getCurrentSessionfromConfig();
		Events event=events.getSelectionModel().getSelectedItem();
		event=session1.find(Events.class, event.getEvId());
		curr_dis=session1.find(Distributors.class,curr_dis.getDisId());
		Transactions tr=new Transactions();
		tr.setEvents(event);
		tr.setTrBuyerFname(firstNameField.getText());
		tr.setTrBuyerLname(lastNameField.getText());
		tr.setTicketTrCount(Integer.parseInt(ticketCountField.getText()));
		tr.setDistributors(curr_dis);
		 if(Integer.parseInt(ticketCountField.getText())>event.getEvCurrSeats()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Create Transactions");
			alert.setContentText("Insufficient seat count");
			alert.showAndWait();
		}
		else if(Integer.parseInt(ticketCountField.getText())>event.getEvMaxRestriction()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Create Transactions");
			alert.setContentText("Ticket count is bigger than the maximum per transaction");
			alert.showAndWait();
		}else {
			event.setEvCurrSeats(event.getEvCurrSeats()-Integer.parseInt(ticketCountField.getText()));
			
			double totalTicketsSold = 0;
			List<Transactions> trList = new ArrayList<>(curr_dis.getTransactionses());
			for(int i=0;i<trList.size();i++) {
				totalTicketsSold=totalTicketsSold+trList.get(i).getTicketTrCount();
			}
			curr_dis.setDisRating(round((totalTicketsSold/curr_dis.getDisEvents().size()),2));
			try {
				session1.getSession();
				session1.beginTransaction();
				session1.saveOrUpdate(curr_dis);
				session1.save(tr);
				session1.getTransaction().commit();
				session1.close();
			}
			catch(Exception e) {
				System.out.println("Transaction error");
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Create Transaction");
			alert.setContentText("Transaction successful");
			alert.showAndWait();

		}}}
	}
	
	public void MyEventsList() {
		curr_dis=Main.getCurrentSessionfromConfig().find(Distributors.class, curr_dis.getDisId());
		events.getItems().addAll(curr_dis.getDisEvents());
	}
	
	public void unsoldTickets() {
		for(Events event : curr_dis.getDisEvents()){
			  if((event.getEvCurrSeats()==event.getEvSeatcount())&&event.getEvStatus().equals("Not yet held")) {
				  Notifications notification=Notifications.create().title("Unsold event").
						  text(event.getEvName()+" has 0 sold tickets").hideAfter(Duration.seconds(5)).position(Pos.TOP_LEFT);
				  notification.darkStyle();
				  notification.showInformation();
			  }
			}
	}

	
	public void MyTransactionsList() {
		curr_dis=Main.getCurrentSessionfromConfig().find(Distributors.class,curr_dis.getDisId());
		transactions.getItems().addAll(curr_dis.getTransactionses());
		
	}
	public void setWelcomeLabel(String string) {
		
		welcomeLabel.setText("Welcome, "+string);
	}

	public Distributors getCurr_dis() {
		return curr_dis;
	}

	public void setCurr_dis(Distributors curr_dis) {
		this.curr_dis= curr_dis;
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
