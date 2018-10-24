import GUI.GUIUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import javax.mail.*;
import java.io.IOException;

public class MainScreenController implements Login {

    public static Session session;

    @FXML
    BorderPane borderPane;
    @FXML
    ListView emailList;
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
        System.out.println("Login request got success reply from login utils");
        try {
            Store imapStore = session.getStore("imap");
            imapStore.connect();
            Folder inboxFolder = imapStore.getFolder("INBOX");
			inboxFolder.open(Folder.READ_ONLY);
			Message[] messages = inboxFolder.getMessages();
            ObservableList<String> emails = FXCollections.observableArrayList();
			for(int i = messages.length - 1; i >= messages.length - 20; i--) {
			    emails.add(messages[i].getSubject());
            }
            emailList.setItems(emails);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginFailure() {

    }
}
