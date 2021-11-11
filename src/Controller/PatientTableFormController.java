package Controller;


import DB.DbConnection;
import Model.Patient;
import View.tm.PatientTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PatientTableFormController {
    public AnchorPane atientAncorPane;
    public TableColumn colAge;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colContactNo;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableView <PatientTm>tblPation;
    public TableColumn colEmpId;
    public TableColumn btn;
    public TextField txtSearch;

    PatientController patientController = new PatientController();

    public void btnBackToFactoryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) atientAncorPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("../View/UpdatePatientForm.fxml"));
        Parent load = fxmlLoader.load();
        UpdatePatientFormController controller = fxmlLoader.getController();
        controller.setController(this);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void loadAllPatient(){
        ObservableList<PatientTm> obList = FXCollections.observableArrayList();
        for (Patient temp :patientController.getAllPatient()) {
;            Button btn = new Button("    Delete    ");
            btn.setStyle("-fx-background-color: red");
            //btn.setStyle("-fx-alignment: center");
            obList.add(
                    new PatientTm(temp.getnIC(),temp.getName(),temp.getContactNo(),temp.getGender(),temp.getAddress(),temp.getDate(),temp.getTime(),temp.getAge(),temp.getEmpId(),btn)
            );
            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this Patient Details?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == yes) {
                    try {
                        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Patients` WHERE nIC='"+temp.getPatientId()+"'").executeUpdate()>0) {
                                obList.clear();loadAllPatient();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                } else {

                }
            });
            tblPation.setItems(obList);
        }
    }
    //ObservableList<PatientTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        colNIC.setCellValueFactory(new PropertyValueFactory<>("ptnNIC"));
        colNIC.setStyle("-fx-alignment: center");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setStyle("-fx-alignment: center");
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colContactNo.setStyle("-fx-alignment: center");
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colGender.setStyle("-fx-alignment: center");
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setStyle("-fx-alignment: center");
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.setStyle("-fx-alignment: center");
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTime.setStyle("-fx-alignment: center");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAge.setStyle("-fx-alignment: center");
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpId.setStyle("-fx-alignment: center");
        btn.setCellValueFactory(new PropertyValueFactory<>("btn"));
        btn.setStyle("-fx-alignment: center");

            loadAllPatient();
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        search(txtSearch.getText());

    }
    private void search(String value) {
        List<Patient> patients = null;
        try {
            patients = PatientController.searchPatient(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<PatientTm> tableData = FXCollections.observableArrayList();
        for (Patient details :patients){
            tableData.add(new PatientTm(
                    details.getnIC(),
                    details.getName(),
                    details.getContactNo(),
                    details.getGender(),
                    details.getAddress(),
                    details.getDate(),
                    details.getTime(),
                    details.getAge(),
                    details.getEmpId()

            ));
        }
        tblPation.getItems().setAll(tableData);
    }

}
