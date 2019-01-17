package io.github.alexcosta97.unify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.alexcosta97.unify.Presenters.MainActivityPresenter;
import io.github.alexcosta97.unify.Views.MainActivityView;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {
    MainActivityPresenter presenter;

    @Mock
    MainActivityView view;

    @Before
    public void setUp() throws Exception{
        presenter = new MainActivityPresenter(view);
    }

    @Test
    public void signInButtonPressed() throws Exception{
        //Arrange
        Class expectedClass = SignInActivity.class;

        //Act
        presenter.signInButtonPressed();

        //Assert
        Mockito.verify(view).launchNextActivity(expectedClass);
    }

    @Test
    public void signUpButtonPressed() throws Exception{
        //Arrange
        Class expectedClass = AddCompanyActivity.class;

        //Act
        presenter.signUpButtonPressed();

        //Assert
        Mockito.verify(view).launchNextActivity(expectedClass);
    }
}
