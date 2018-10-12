import Mail.EmailUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenStart extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Stage stage;
    public static MainScreenController mainScreenController;

    @Override
    public void start(Stage s) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainScreen.fxml"));
        Scene scene = new Scene(root);
        stage = s;
        stage.setScene(scene);
        stage.setTitle("TEST");
        stage.show();
        mainScreenController.postInit();
        EmailUtil.init();
    }
}
