package Controller;


import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserServices {
    public boolean newUser(User u) throws SQLException, ClassNotFoundException;
    public boolean refreshUser(User u) throws SQLException, ClassNotFoundException;
    public boolean RemoveUser(String userId) throws SQLException, ClassNotFoundException;
    public User getUserId(String userId) throws SQLException, ClassNotFoundException;
    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException;
    public List<String> getUser() throws SQLException, ClassNotFoundException;
}
