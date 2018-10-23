import GUI.GUIUtil;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import javax.mail.Session;
import java.io.IOException;

public class MainScreenController implements Login {

    public static Session session;

    @FXML
    BorderPane borderPane;
    @FXML
    ImageView inboxIcon;

    @FXML
    public void initialize() {
        MainScreenStart.mainScreenController = this;

        Image image = GUIUtil.getImageFromResourcePath("/GUI/envelope.png");
        System.out.println(inboxIcon);
        inboxIcon.setImage(image);
//        inboxIcon.setFitWidth(50);
    }

    public void postInit() throws IOException {
        LoginScreenController.addLoginRequest(this);
    }

    @Override
    public void loginSucces(Session session) {
        System.out.println("Login request method got reply from login utils");
    }

    @Override
    public void loginFailure() {

    }
}
