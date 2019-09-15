package im.het.android.unittest;

public class UserManager {
    public void performLogin(String username, String password) {
        //perform login and post LoginResult event
    }

    public void performLogin(String username, String password, NetworkCallback networkCallback) {
        //perform login and notify networkCallback
    }
}
