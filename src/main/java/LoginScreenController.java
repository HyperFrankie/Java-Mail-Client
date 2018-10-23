import Mail.EmailUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginScreenController {

    @FXML
    TextField loginMail;
    @FXML
    PasswordField loginPass;

    @FXML
    public void initialize() throws IOException {

    }

    @FXML
    public void login() {
        System.out.println("FXML Login invoked");
        EmailUtil.login(loginMail.getText(), loginPass.getText());
    }

}