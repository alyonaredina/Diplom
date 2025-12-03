package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthorizationTest extends Data {
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void authorizationTestBefore() {
        try {
            waitUntilElement(R.id.login_text_input_layout);
            ViewInteraction textView = onView(Matchers.allOf(withText("Authorization"),isDisplayed()));
        } catch (androidx.test.espresso.PerformException e) {
            AppBarPage.clickManButton();
            waitUntilElement(android.R.id.title);
            AppBarPage.clickExit();
            waitUntilElement(R.id.login_text_input_layout);
            AuthorizationPage.checkAuthorizationTitle();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }


    @Test
    @Description("Авторизация.Валидные логин и пароль")
    public void id1ValidUsernameAndPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(validLogin);
        AuthorizationPage.fillingInThePasswordField(validPassword);
        AuthorizationPage.clickButtonEnter();
        waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS
        MainPage.checkDisplayingTheAllNewsLabel();
        waitUntilElement(R.id.authorization_image_button);
        AppBarPage.clickManButton();
        waitUntilElement(android.R.id.title);
        AppBarPage.clickExit();
    }

    @Test
    @Description("Авторизация.Валидный логин и не валидный пароль")
    public void id2ValidUsernameAndInvalidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(validLogin);
        AuthorizationPage.fillingInThePasswordField(invalidPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Не валидный логин и валидный пароль")
    public void id3InvalidUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(invalidLogin);
        AuthorizationPage.fillingInThePasswordField(validPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Не валидный логин и не валидный пароль")
    public void id4InvalidUsernameAndInvalidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(invalidLogin);
        AuthorizationPage.fillingInThePasswordField(invalidPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Без ввода логина и пароля")
    public void id5EmptyFieldUsernameAndEmptyFieldPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(emptyFieldLogin);
        AuthorizationPage.fillingInThePasswordField(emptyFieldPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnterEmptyField, decorView);
    }

    @Test
    @Description("Авторизация.Ввод спец символа в поле логин")
    public void id6SpecialCharacterFieldUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(specialCharacterLogin);
        AuthorizationPage.fillingInThePasswordField(validPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Ввод спец символа в поле пароль")
    public void id7ValidUsernameAndSpecialCharacterFieldPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(validLogin);
        AuthorizationPage.fillingInThePasswordField(specialCharacterPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Ввод логина с заглавной буквы")
    public void id8CapitalLetterUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(capitalLetterLogin);
        AuthorizationPage.fillingInThePasswordField(validPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Ввод пароля с заглавной буквы")
    public void id9ValidUsernameAndCapitalLetterPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(validLogin);
        AuthorizationPage.fillingInThePasswordField(capitalLetterPassword);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.ОШИБКА:не валидные логин и пароль")
    public void id4InvalidUsernameAndInvalidPassword_ERROR(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        AuthorizationPage.fillingInTheLoginField(invalidLogin3);
        AuthorizationPage.fillingInThePasswordField(invalidPassword3);
        AuthorizationPage.clickButtonEnter();
        AuthorizationPage.aPop_upError(errorEnter, decorView);
    }
}
