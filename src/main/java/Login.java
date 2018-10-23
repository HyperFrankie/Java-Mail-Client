import javax.mail.Session;

public interface Login {

    void loginSucces(Session session);

    void loginFailure();

}
