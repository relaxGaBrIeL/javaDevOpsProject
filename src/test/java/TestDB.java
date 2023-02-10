import com.devops.lbnum_project.ConnectDB;
import com.devops.lbnum_project.Controllers.LoginResponse;
import com.devops.lbnum_project.Controllers.SignupResponse;
import com.devops.lbnum_project.Controllers.User;
import com.devops.lbnum_project.Models.Model;
import com.devops.lbnum_project.Models.Validator;

import java.util.Map;

public class TestDB {
    public static void main(String[] args) {
//        // Create a com.devops.project.ConnectDB object
        //ConnectDB db = new ConnectDB();
        Model db = new Model();


//        // Execute a SELECT query and process the results
//        db.selectData();
//        // Execute an INSERT query
//        db.executeUpdate("INSERT INTO Users (Name, Email, Password) VALUES ('John', 'john@example.com', 'password123')");
//        // Close the connection to the database
//        db.closeConnection();

        /*
         * Check User existence
         */
//        if (db.checkEmail("john@example.com")) {
//            System.out.println("exite");
//        } else {
//            System.out.println("no exit");
//        }

//        /*
//            Register
//         */
//        SignupResponse signupResponse = db.signup("john@gmail.com", "John", "password123","joo");
//       db.closeConnection();
//        if (signupResponse.isSuccess()) {
//            User user = signupResponse.getUser();
//            String msg = signupResponse.getMessage();
//            System.out.println(signupResponse.getMessage());
//            // user.getFname() will have the firstname, user.getLname() will have the lastname, user.getEmail() will have the email
//        } else {
//            //String message = signupResponse.getMessage();
//            // message will have the reason why the signup failed
//            System.out.println(signupResponse.getMessage());
//        }

        /*
            Login
         */

        LoginResponse loginResponse = db.login("john@gmail.com","John");
        db.closeConnection();
        if (loginResponse.isConnected()) {
            User user = loginResponse.getUser();
            System.out.println(user.getFname() +" "+ user.getLname());
            // user.getFname() will have the firstname, user.getLname() will have the lastname, user.getEmail() will have the email
        }else {
            System.out.println("non");
        }


    }


}