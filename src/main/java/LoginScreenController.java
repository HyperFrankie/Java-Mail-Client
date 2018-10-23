import Mail.EmailUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<loginRequest> loginQue = new ArrayList<>();

    public static void addLoginRequest(Login source) throws IOException {
        loginQue.add(new loginRequest(source));
        if(loginQue.size() == 1) {
            createNewLoginScreen();
        }
    }

    private static Stage currentLoginScreen;

    private static void createNewLoginScreen() throws IOException {
        Parent root = FXMLLoader.load(MainScreenController.class.getClassLoader().getResource("loginScreen.fxml"));
        Scene scene = new Scene(root);
        currentLoginScreen = new Stage();
        currentLoginScreen.setScene(scene);
        currentLoginScreen.initModality(Modality.WINDOW_MODAL);
        currentLoginScreen.initOwner(MainScreenStart.stage);
        currentLoginScreen.setTitle("Login");
        currentLoginScreen.show();
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
                System.out.println("loginSucces");
                loginQue.get(0).source.loginSucces(session);
                loginQue.remove(0);
                currentLoginScreen.close();
            } catch (MessagingException e) {
                e.printStackTrace();
                retryLogin();
            }
        } else {
            retryLogin();
        }
    }

    private Label incorrectLogin = new Label("Incorrect Email or Password");

    public void retryLogin() {
        loginInformation.getChildren().add(2, incorrectLogin);
        loginScreen.setPrefHeight(loginScreen.getHeight() + 30);
    }

}