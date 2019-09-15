package im.het.android.unittest.mockito;

public class PasswordValidator {
    public PasswordValidator() {
    }

    public boolean verifyPassword(String password) {
        // 假设这个方法需要联网，耗时可能很长
        return "hello".equals(password);
    }
}
