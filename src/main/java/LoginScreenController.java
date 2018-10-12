import Mail.EmailUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

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