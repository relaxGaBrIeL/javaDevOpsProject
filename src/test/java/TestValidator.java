import com.devops.lbnum_project.Models.Validator;

public class TestValidator {

    public static void main(String[] args) {
        if (Validator.validateEmail("email@gmailcom")) {
            System.out.println("mot de passe correct");
        } else {
            System.out.println("mot de passe incorrect");

        }
    }
}
