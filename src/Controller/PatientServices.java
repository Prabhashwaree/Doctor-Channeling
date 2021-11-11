package Controller;

import Model.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientServices {
    public boolean savePatient(Patient p) throws SQLException, ClassNotFoundException;
    public boolean updatePatient(Patient p) throws SQLException, ClassNotFoundException;
    public boolean deletePatient(String nIc) throws SQLException, ClassNotFoundException;
    public Patient getPatient(String nIc) throws SQLException, ClassNotFoundException;
    public ArrayList<Patient> getAllPatient() throws SQLException, ClassNotFoundException;
    public List<String> getPatientIds() throws SQLException, ClassNotFoundException;
}
