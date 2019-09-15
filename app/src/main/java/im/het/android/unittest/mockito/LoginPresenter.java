package im.het.android.unittest.mockito;


import im.het.android.unittest.NetworkCallback;
import im.het.android.unittest.UserManager;

public class LoginPresenter {
    private UserManager mUserManager = new UserManager();
    private PasswordValidator mPasswordValidator = new PasswordValidator();

    public void login(String username, String password) {
        if (username == null || username.length() == 0) {
            return;
        }

        // 假设我们对密码强度有一定要求，使用一个专门的 validator 来验证密码的有效性
        if (mPasswordValidator.verifyPassword(password)) {
            return;
        }

        mUserManager.performLogin(username, password);
    }

    public void loginWithCallback(String username, String password) {
        if (username == null || username.length() == 0) {
            return;
        }

        if (mPasswordValidator.verifyPassword(password)) {
            return;
        }

        mUserManager.performLogin(username, password, new NetworkCallback() {
            @Override
            public void onSuccess(Object data) {
                //update view with data
            }

            @Override
            public void onFailure(int code, String msg) {
                //show error msg
            }
        });
    }

    public void setUserManager(UserManager userManager) {
        this.mUserManager = userManager;
    }

    public void setPasswordValidator(PasswordValidator passwordValidator) {
        this.mPasswordValidator = passwordValidator;
    }
}
