package io.github.alexcosta97.unify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.alexcosta97.unify.Presenters.SignInActivityPresenter;
import io.github.alexcosta97.unify.Views.SignInActivityView;

@RunWith(MockitoJUnitRunner.class)
public class SignInPresenterTest {
    SignInActivityPresenter presenter;

    @Mock
    SignInActivityView view;

    @Before
    public void setUp() throws Exception{
        presenter = new SignInActivityPresenter(view);
    }

    @Test
    public void correctLogin() throws Exception{
        //Arrange
        String email = "user@testco.com";
        String password = "Password";
        Class expectedClass = MainMenu.class;

        //Act
        presenter.login(email, password);

        //Assert
        Mockito.verify(view).launchNextActivity(expectedClass);
    }

    @Test
    public void incorrectLogin() throws Exception{
        //Arrange
        String email = "user@testco.com";
        String password = "password";
        String expectedErrorMessage = "Invalid Email or Password";

        //Act
        presenter.login(email, password);

        //Assert
        Mockito.verify(view).displayErrorDialog("Login Error", expectedErrorMessage);
    }
}
