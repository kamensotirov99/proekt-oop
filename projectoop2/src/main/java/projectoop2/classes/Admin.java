package projectoop2.classes;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Admin implements Initializable {
@FXML TableView<Organizers> orgs;
@FXML TableView<Distributors> dises;
@FXML TextField username;
@FXML TextField password;
@FXML TextField firstName;
@FXML TextField lastName;
@FXML TextField email;
@FXML private JFXButton createOrg;
@FXML Label orgError;
@FXML Label disError;
@FXML TableColumn<Organizers,String> orgUsernameColumn;
@FXML TableColumn<Organizers,String> orgPasswordColumn;
@FXML TableColumn<Organizers,String> orgFirstNameColumn;
@FXML TableColumn<Organizers,String> orgLastNameColumn;
@FXML TableColumn<Organizers,String> orgEmailColumn;
@FXML TableColumn<Distributors,String> disUsernameColumn;
@FXML TableColumn<Distributors,String> disPasswordColumn;
@FXML TableColumn<Distributors,String> disFirstNameColumn;
@FXML TableColumn<Distributors,String> disLastNameColumn;
@FXML TableColumn<Distributors,String> disEmailColumn;


public void createOrganizer() {
	if(username.getText().isBlank()||password.getText().isBlank()||firstName.getText().isBlank()||lastName.getText().isBlank()||email.getText().isBlank()) {
		orgError.setVisible(true);
	}else 
	
	{
	Organizers org=new Organizers();
	org.setOrgUsername(username.getText().toString());
	org.setOrgPassword(password.getText().toString());
	org.setOrgFname(firstName.getText().toString());
	org.setOrgLname(lastName.getText().toString());
	org.setOrgEmail(email.getText().toString());
	
	List<Distributors>dis=findAllDistributors();

	Session session1=Main.getCurrentSessionfromConfig();
	boolean flag=false;
	for(int i=0;i<dis.size();i++) {
		if(org.getOrgUsername().equals(dis.get(i).getDisUsername())||org.getOrgEmail().equals(dis.get(i).getDisEmail())) {
			flag=true;
			System.out.println("Error");
		}
	}
	if(flag==false) {
	try {
		
	session1.getSession();
	session1.beginTransaction();
	session1.save(org);
	session1.getTransaction().commit();
	session1.close();
	orgError.setVisible(false);
	orgs.getItems().add(org);
	
	}
	catch(ConstraintViolationException e) {
		session1.getTransaction().rollback();
		System.out.println("error");
		orgs.getItems().remove(org);
	}}else if(flag==true){disError.setVisible(true);}
}}

public void createDistributor() {
	if(username.getText().isBlank()||password.getText().isBlank()||firstName.getText().isBlank()||lastName.getText().isBlank()||email.getText().isBlank()) {
		orgError.setVisible(true);
	}else 
	
	{
	Distributors dis=new Distributors();
	dis.setDisUsername(username.getText().toString());
	dis.setDisPassword(password.getText().toString());
	dis.setDisFname(firstName.getText().toString());
	dis.setDisLname(lastName.getText().toString());
	dis.setDisEmail(email.getText().toString());
	dis.setDisRating(0.0);
	

	Session session1=Main.getCurrentSessionfromConfig();
	List<Organizers>org=findAllOrganizers();
	boolean flag=false;
	for(int i=0;i<org.size();i++) {
		if(dis.getDisUsername().equals(org.get(i).getOrgUsername())||dis.getDisEmail().equals(org.get(i).getOrgEmail())) {
			flag=true;
			System.out.println("Error");
		}
	}
	if(flag==false) {
	try {
		
	session1.getSession();
	session1.beginTransaction();
	session1.save(dis);
	session1.getTransaction().commit();
	session1.close();
	orgError.setVisible(false);
	dises.getItems().add(dis);
	
	}
	catch(ConstraintViolationException e) {
		session1.getTransaction().rollback();
		System.out.println("error");
		dises.getItems().remove(dis);
	}
}else if(flag==true){orgError.setVisible(true);}
	}
	
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
orgUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("orgUsername"));
orgPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("orgPassword"));
orgFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("orgFname"));
orgLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("orgLname"));
orgEmailColumn.setCellValueFactory(new PropertyValueFactory<>("orgEmail"));
orgs.setItems(getOrgs());


disUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("disUsername"));
disPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("disPassword"));
disFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("disFname"));
disLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("disLname"));
disEmailColumn.setCellValueFactory(new PropertyValueFactory<>("disEmail"));
dises.setItems(getDises());
	
}


public ObservableList<Organizers> getOrgs(){
	ObservableList<Organizers>organizers=FXCollections.observableArrayList();
	List<Organizers>org=findAllOrganizers();
	for(int i=0;i<org.size();i++) {
		organizers.add(org.get(i));
	}
	return organizers;
	
	
}

public ObservableList<Distributors> getDises(){
	ObservableList<Distributors>distributors=FXCollections.observableArrayList();
	List<Distributors>dis=findAllDistributors();
	for(int i=0;i<dis.size();i++) {
		distributors.add(dis.get(i));
	}
	return distributors;
	
	
}

public List<Organizers> findAllOrganizers() {
	CriteriaBuilder cb = Main.getCurrentSessionfromConfig().getCriteriaBuilder();
    CriteriaQuery<Organizers> cq = cb.createQuery(Organizers.class);
    Root<Organizers> rootEntry = cq.from(Organizers.class);
    CriteriaQuery<Organizers> all = cq.select(rootEntry);
 
    TypedQuery<Organizers> allQuery = Main.getCurrentSessionfromConfig().createQuery(all);
    return allQuery.getResultList();    
}


public List<Distributors> findAllDistributors() {
	CriteriaBuilder cb = Main.getCurrentSessionfromConfig().getCriteriaBuilder();
    CriteriaQuery<Distributors> cq = cb.createQuery(Distributors.class);
    Root<Distributors> rootEntry = cq.from(Distributors.class);
    CriteriaQuery<Distributors> all = cq.select(rootEntry);
 
    TypedQuery<Distributors> allQuery = Main.getCurrentSessionfromConfig().createQuery(all);
    return allQuery.getResultList();    
}



}
