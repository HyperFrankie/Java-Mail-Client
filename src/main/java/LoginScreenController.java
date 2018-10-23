import Mail.EmailUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;

public class LoginScreenController {

    @FXML
    AnchorPane loginScreen;
    @FXML
    VBox loginInformation;
    @FXML
    TextField loginMail;
    @FXML
    PasswordField loginPass;

    @FXML
    public void initialize() throws IOException {
        incorrectLogin.setTextFill(Color.RED);
    }

    @FXML
    public void login() {
        loginInformation.getChildren().remove(incorrectLogin);
        loginScreen.setPrefHeight(loginScreen.getHeight() - 30);
        System.out.println("FXML Login invoked");
        Session session = EmailUtil.login(loginMail.getText(), loginPass.getText());
        if(session != null) {
            try {
                session.getStore("imap").connect();
                MainScreenController.session = session;
                System.out.println("has set mainScreenController.session to session that was returned from EmailUtil.login");
            } catch (MessagingException e) {
                e.printStackTrace();
                retryLogin();
            }
        } else {
            retryLogin();
        }
    }

    Label incorrectLogin = new Label("Incorrect Email or Password");

    public void retryLogin() {
        loginInformation.getChildren().add(2, incorrectLogin);
        loginScreen.setPrefHeight(loginScreen.getHeight() + 30);
    }

}