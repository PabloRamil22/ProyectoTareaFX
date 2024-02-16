package com.example.proyectotareasfx.controllerviews;

import com.example.proyectotareasfx.controller.TaskController;
import com.example.proyectotareasfx.models.Rol;
import com.example.proyectotareasfx.models.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.util.List;

public class AdminController extends ControllerView {


    @FXML
    protected TableView<User> tblUser;
    @FXML
    protected TableColumn <User, Integer> idColum;
    @FXML
    protected TableColumn <User,String> UserNameColum;
    @FXML
    protected TableColumn <User, String> RolColum;
    @FXML
    protected TextField txtUserName;
    @FXML
    protected PasswordField txtPassword;
    @FXML
    protected PasswordField txtRepPassword;
    @FXML
    protected ComboBox<Rol> comboRol;
    @FXML
    protected Label lblMSG;
    @FXML
    protected Button btnAdd;
    @FXML
    protected Button btnUpdateUser;

    private ObservableList<User> observableList= FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        idColum.setCellValueFactory(cell->new SimpleObjectProperty<>(cell.getValue().getIduser()));
        UserNameColum.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getUsername()));
        RolColum.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getRol().getDescription()));
        comboRol.setConverter(new StringConverter<Rol>() {
            @Override
            public String toString(Rol rol) {
                return rol.getDescription();
            }

            @Override
            public Rol fromString(String s) {
                return null;
            }
        });

    tblUser.setOnMouseClicked(e ->{
        if (e.getClickCount()==1){
            User user=tblUser.getSelectionModel().getSelectedItem();
            txtUserName.setText(user.getUsername());
            comboRol.setValue(user.getRol());
            btnAdd.setVisible(false);
            btnUpdateUser.setVisible(true);

        }

            });
    }
    public AdminController(){
    }



    @Override
    public void cargaInicial() {
        List<User> userList=taskController.getAllUser();
        observableList.addAll(userList);
        tblUser.setItems(observableList);
        List<Rol> rolList=taskController.getAllRol();
        comboRol.getItems().addAll(rolList);
    }
@FXML
    public void btnAddUser(ActionEvent actionEvent) {
        if (txtPassword.getText().equals(txtRepPassword.getText())) {
            taskController.createUser(txtUserName.getText(), txtPassword.getText(), comboRol.getSelectionModel().getSelectedItem().getIdrol());
            List<User> userList=taskController.getAllUser();
            observableList.clear();
            observableList.addAll(userList);
            tblUser.refresh();
        }else{
            lblMSG.setText("Password must be equals");
        }
    }
@FXML
    public void btnUpdateUser(ActionEvent actionEvent) {
        if (txtPassword.getText().equals(txtRepPassword.getText())) {
            User user=tblUser.getSelectionModel().getSelectedItem();
            user.setPassword(txtPassword.getText());
            user.setRol(comboRol.getSelectionModel().getSelectedItem());
            taskController.updateUser(user);
        }else{
            lblMSG.setText("Password must be equals");
        }
        btnAdd.setVisible(true);
        btnUpdateUser.setVisible(false);
    }
@FXML
    public void btnDelete(ActionEvent actionEvent) {
     User user= tblUser.getSelectionModel().getSelectedItem();
     observableList.remove(user);
     tblUser.refresh();

    }
}
