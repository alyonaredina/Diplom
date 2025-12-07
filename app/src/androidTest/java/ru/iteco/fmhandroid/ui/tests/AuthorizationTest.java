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
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthorizationTest extends Data {
    AuthorizationPage myObjectAuthorization = new AuthorizationPage();
    AppBarPage myObjectAppBar = new AppBarPage();
    MainPage myObjectMaim = new MainPage();

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
            myObjectAppBar.clickManButton();
            waitUntilElement(android.R.id.title);
            myObjectAppBar.clickExit();
            waitUntilElement(R.id.login_text_input_layout);
            myObjectAuthorization.checkAuthorizationTitle();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }


    @Test
    @Description("Авторизация.Валидные логин и пароль")
    @Severity(SeverityLevel.BLOCKER)
    public void id1ValidUsernameAndPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(validLogin);
        myObjectAuthorization.fillingInThePasswordField(validPassword);
        myObjectAuthorization.clickButtonEnter();
        waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS
        myObjectMaim.checkDisplayingTheAllNewsLabel();
        waitUntilElement(R.id.authorization_image_button);
        myObjectAppBar.clickManButton();
        waitUntilElement(android.R.id.title);
        myObjectAppBar.clickExit();
    }

    @Test
    @Description("Авторизация.Валидный логин и не валидный пароль")
    @Severity(SeverityLevel.BLOCKER)
    public void id2ValidUsernameAndInvalidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(validLogin);
        myObjectAuthorization.fillingInThePasswordField(invalidPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Не валидный логин и валидный пароль")
    @Severity(SeverityLevel.BLOCKER)
    public void id3InvalidUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(invalidLogin);
        myObjectAuthorization.fillingInThePasswordField(validPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Не валидный логин и не валидный пароль")
    @Severity(SeverityLevel.BLOCKER)
    public void id4InvalidUsernameAndInvalidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(invalidLogin);
        myObjectAuthorization.fillingInThePasswordField(invalidPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Без ввода логина и пароля")
    @Severity(SeverityLevel.BLOCKER)
    public void id5EmptyFieldUsernameAndEmptyFieldPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(emptyFieldLogin);
        myObjectAuthorization.fillingInThePasswordField(emptyFieldPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnterEmptyField, decorView);
    }

    @Test
    @Description("Авторизация.Ввод спец символа в поле логин")
    @Severity(SeverityLevel.BLOCKER)
    public void id6SpecialCharacterFieldUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(specialCharacterLogin);
        myObjectAuthorization.fillingInThePasswordField(validPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Ввод спец символа в поле пароль")
    @Severity(SeverityLevel.BLOCKER)
    public void id7ValidUsernameAndSpecialCharacterFieldPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(validLogin);
        myObjectAuthorization.fillingInThePasswordField(specialCharacterPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Ввод логина с заглавной буквы")
    @Severity(SeverityLevel.BLOCKER)
    public void id8CapitalLetterUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(capitalLetterLogin);
        myObjectAuthorization.fillingInThePasswordField(validPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.Ввод пароля с заглавной буквы")
    @Severity(SeverityLevel.BLOCKER)
    public void id9ValidUsernameAndCapitalLetterPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(validLogin);
        myObjectAuthorization.fillingInThePasswordField(capitalLetterPassword);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Description("Авторизация.ОШИБКА:не валидные логин и пароль")
    @Severity(SeverityLevel.BLOCKER)
    public void id4InvalidUsernameAndInvalidPassword_ERROR(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization
        myObjectAuthorization.fillingInTheLoginField(invalidLogin3);
        myObjectAuthorization.fillingInThePasswordField(invalidPassword3);
        myObjectAuthorization.clickButtonEnter();
        myObjectAuthorization.aPop_upError(errorEnter, decorView);
    }
}
