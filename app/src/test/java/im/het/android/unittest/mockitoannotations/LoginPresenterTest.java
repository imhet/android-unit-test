package im.het.android.unittest.mockitoannotations;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import im.het.android.unittest.UserManager;
import im.het.android.unittest.mockito.PasswordValidator;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    UserManager mockUserManager ;

    @Mock
    PasswordValidator mockValidator ;

    @InjectMocks
    LoginPresenter loginPresenter ;

    // @Spy PasswordValidator spyValidator ;

    @Before
    public void setUp() {
        // 上面的 @Rule 与下面语句等价
        // MockitoAnnotations.initMocks(this);

        // 上面的 @InjectMocks 是构造函数注入，与下面语句等价
        // LoginPresenter loginPresenter = new LoginPresenter(mockUserManager , mockValidator) ;

        // @InjectMocks 有坑，少用
        // 见 https://tedvinke.wordpress.com/2014/02/13/mockito-why-you-should-not-use-injectmocks-annotation-to-autowire-fields/
    }

    @Test
    public void login() {
        when(mockValidator.verifyPassword(anyString())).thenReturn(true) ;
        loginPresenter.login("hello" , "world");
        verify(mockUserManager).performLogin("hello","world");
    }
}