package im.het.android.unittest.mockito;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import im.het.android.unittest.NetworkCallback;
import im.het.android.unittest.UserManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Test
    public void login() {
        UserManager mockUserManager = mock(UserManager.class) ;

        LoginPresenter presenter = new LoginPresenter() ;
        presenter.setUserManager(mockUserManager);
        presenter.login("hello" , "world");

        // Mockito 使用 - 验证方法调用
        verify(mockUserManager).performLogin("hello" , "world");
        verify(mockUserManager).performLogin(anyString() , anyString());
    }

    @Test
    public void testMock() {
        PasswordValidator validator = mock(PasswordValidator.class) ;

        // Mockito 使用 - 指定方法返回特定值
        when(validator.verifyPassword("world")).thenReturn(true) ;
        assertEquals(false , validator.verifyPassword("hello"));

        when(validator.verifyPassword(anyString())).thenReturn(true) ;
        assertEquals(true , validator.verifyPassword("hello"));
    }


    @Test
    public void loginWithCallback() {
        UserManager mockUserManager = Mockito.mock(UserManager.class);

        // Mockito 使用 - 指定一个方法执行特定的动作
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                // 这里可以获得传给 performLogin 的参数
                Object[] arguments = invocation.getArguments();

                NetworkCallback callback = (NetworkCallback) arguments[2];
                callback.onFailure(500, "Server error");
                return 500; // 对于如果mock的是非void方法来说，这个将作为目标方法的返回值
            }
        }).when(mockUserManager).performLogin(anyString(), anyString(), any(NetworkCallback.class));

        mockUserManager.performLogin("hello", "world", mock(NetworkCallback.class));
    }

    @Test
    public void testMockSpy()
    {
        // Mockito 使用 - 使用 spy
        // 跟创建mock类似，只不过调用的是 spy 方法，而不是 mock 方法
        PasswordValidator spyValidator = spy(PasswordValidator.class);

        // 在默认情况下，spy 对象会调用这个类的 real implementation，并返回相应的返回值
        boolean result = spyValidator.verifyPassword("hello"); // true
        assertTrue(result);
        result = spyValidator.verifyPassword("hello2"); // false
        assertFalse(result);

        // 也可以指定 spy 对象的方法的行为
        when(spyValidator.verifyPassword(anyString())).thenReturn(true);

        result = spyValidator.verifyPassword("hello2");
        assertTrue(result);
        verify(spyValidator, times(2)).verifyPassword("hello2");
    }

}