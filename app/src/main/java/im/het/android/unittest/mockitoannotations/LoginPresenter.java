package im.het.android.unittest.mockitoannotations;

import im.het.android.unittest.UserManager;
import im.het.android.unittest.mockito.PasswordValidator;

public class LoginPresenter {
    private final UserManager mUserManager;
    private final PasswordValidator mPasswordValidator;

    public LoginPresenter() {
        this(null, null);
    }

    public LoginPresenter(UserManager userManager, PasswordValidator passwordValidator) {
        this.mUserManager = userManager;
        this.mPasswordValidator = passwordValidator;
    }

    public void login(String username, String password) {
        if (username == null || username.length() == 0) {
            return;
        }

        if (!mPasswordValidator.verifyPassword(password)) {
            return;
        }

        mUserManager.performLogin(username, password);
    }
}
