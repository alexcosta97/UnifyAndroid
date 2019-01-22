package io.github.alexcosta97.unify;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.alexcosta97.unify.Presenters.MainMenuPresenter;
import io.github.alexcosta97.unify.Views.MainMenuView;

@RunWith(MockitoJUnitRunner.class)
public class MainMenuPresenterTest {
    MainMenuPresenter presenter;

    @Mock
    MainMenuView view;

    @Before
    public void setUp() throws Exception{
        presenter = new MainMenuPresenter(view);
    }

    @Test
    public void newOrderClicked() throws Exception{
        //Arrange
        Class expectedClass = NewOrder.class;

        //Act
        presenter.newOrderClicked();

        //Assert
        Mockito.verify(view).launchNextActivity(expectedClass);
    }

    @Test
    public void templateOrderClicked() throws Exception{
        //Arrange
        Class expectedClass = TemplateOrder.class;

        //Act
        presenter.templateOrderClicked();

        //Assert
        Mockito.verify(view).launchNextActivity(expectedClass);
    }

    @Test
    public void reportsClicked() throws Exception{
        //Arrange
        Class expectedClass = Reports.class;

        //Act
        presenter.reportsClicked();

        //Assert
        Mockito.verify(view).launchNextActivity(expectedClass);
    }
}
