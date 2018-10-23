import GUI.GUIUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.Session;
import java.io.IOException;

public class MainScreenController {

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(MainScreenStart.stage);
        stage.setTitle("Login");
        stage.show();
    }

}
