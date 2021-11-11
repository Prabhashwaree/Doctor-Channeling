package Controller;


import DB.DbConnection;
import Model.Appointment;
import View.tm.AppointmentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppointmentFormController {
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colDisease;
    public TableColumn colStatus;
    public TableColumn colAppointmentId;
    public AnchorPane AppointmentPane;
    public TableColumn colDelete;
    public TextField txtSearch;

    public TableView tblAppointment;
    public TableColumn colPatientId;
    public TableColumn colDoctorId;

    AppointmentController appointmentController = new  AppointmentController();


    public void btnBackToFactoryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AppointmentPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("../View/UpdatePatientInformationForm.fxml"));
        Parent load = fxmlLoader.load();
        UpdatePatientInformationFormController controller = fxmlLoader.getController();
        controller.setController(this);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        search(txtSearch.getText());
    }

    public void loadAllAppointment() throws IOException {
        ObservableList<AppointmentTm> obList = FXCollections.observableArrayList();
        for (Appointment temp : appointmentController.getAllAppointment()) {
            Button btns;
            if (AppointmentController.getStatus(temp.getAppointmentId()).equals("Active")) {
                btns = new Button("    Active    ");
                btns.setStyle("-fx-background-color: aqua");
            }else {
                btns = new Button("    Canceled    ");
                btns.setStyle("-fx-background-color: chocolate");
            }

            btns.setCursor(Cursor.HAND);
            btns.setOnMouseClicked(event -> {
                if (btns.getText().equals("    Active    ")) {
                    btns.setStyle("-fx-background-color: chocolate");
                    btns.setText("    Canceled    ");
                    AppointmentController.changeStatusById("Canceled",temp.getAppointmentId());
                }else {
                    btns.setStyle("-fx-background-color: aqua");
                    btns.setText("    Active    ");
                    AppointmentController.changeStatusById("Active",temp.getAppointmentId());
                }

            });


            Button btn = new Button("   Delete   ");
            btn.setStyle("-fx-background-color: red");
            btn.setCursor(Cursor.HAND);
            //btn.setStyle("-fx-alignment: center");
            obList.add(
                    new AppointmentTm(temp.getAppointmentId(),temp.getDisease(),temp.getDate(),temp.getTime(),temp.getnIC(),temp.getDrId(),btns,btn)
            );

            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this Appointment Detail?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == yes) {
                    try {
                        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Appointment WHERE appointId='"+temp.getAppointmentId()+"'").executeUpdate()>0){
                            System.out.println("type");obList.clear();
                            try {
                                loadAllAppointment();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                } else {

                }
            });
            tblAppointment.setItems(obList);
        }
    }
    public void initialize(){
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colAppointmentId.setStyle("-fx-alignment: center");
        colDisease.setCellValueFactory(new PropertyValueFactory<>("disease"));
        colDisease.setStyle("-fx-alignment: center");
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.setStyle("-fx-alignment: center");
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTime.setStyle("-fx-alignment: center");
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("ptnNIC"));
        colPatientId.setStyle("-fx-alignment: center");
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("drId"));
        colDoctorId.setStyle("-fx-alignment: center");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("btns"));
        colStatus.setStyle("-fx-alignment: center");
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colDelete.setStyle("-fx-alignment: center");


        try {
            loadAllAppointment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void search(String value) {
        List<Appointment> appointmentList = null;
        try {
            appointmentList = AppointmentController.searchAppointment(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<AppointmentTm> tableData = FXCollections.observableArrayList();
        for (Appointment details :appointmentList){
            tableData.add(new AppointmentTm(
                    details.getAppointmentId(),
                    details.getDisease(),
                    details.getDate(),
                    details.getTime(),
                    details.getnIC(),
                    details.getDrId()

            ));
        }
        tblAppointment.getItems().setAll(tableData);
    }

}
