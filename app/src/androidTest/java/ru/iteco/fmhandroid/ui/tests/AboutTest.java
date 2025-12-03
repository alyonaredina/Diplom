package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AboutTest extends Data {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Before
    public void authorizationTestBefore() {
        try {
            waitUntilElement(R.id.login_text_input_layout);
            ViewInteraction textView = onView(Matchers.allOf(withText("Authorization"),isDisplayed()));
            AuthorizationPage.fillingInTheLoginField(validLogin);
            AuthorizationPage.fillingInThePasswordField(validPassword);
            AuthorizationPage.clickButtonEnter();
            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS
            MainPage.checkDisplayingTheAllNewsLabel();
            //открыть меню
            AppBarPage.clickMenuButton();
            //выбрать About
            AppBarPage.clickLineAbout();
            //проверка
            AboutPage.checkDisplayingTheVersionLabel();
        } catch (androidx.test.espresso.PerformException e) {
            AppBarPage.clickManButton();
            waitUntilElement(android.R.id.title);
            AppBarPage.clickExit();
            waitUntilElement(R.id.login_text_input_layout);
            AuthorizationPage.checkAuthorizationTitle();
            AuthorizationPage.fillingInTheLoginField(validLogin);
            AuthorizationPage.fillingInThePasswordField(validPassword);
            AuthorizationPage.clickButtonEnter();
            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS
            //открыть меню
            AppBarPage.clickMenuButton();
            //выбрать About
            AppBarPage.clickLineAbout();
            //проверка
            AboutPage.checkDisplayingTheVersionLabel();
        }
    }

    @Test
    @Step("About. Переход по ссылке Privacy Policy")
    public void id65followingTheLinkPrivacyPolicy(){
        //включаем прослушку
        Intents.init();
        //проверяем переход по ссылке
        AboutPage.clickLinkPrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy/"));
        //очищаем прослушку
        Intents.release();
    }

    @Test
    @Step("About. Переход по ссылке Terms Of Use")
    public void id65followingTheLinkTermsOfUse(){
        //включаем прослушку
        Intents.init();
        //проверяем переход по ссылке
        AboutPage.clickLinkTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        //очищаем прослушку
        Intents.release();
    }

    @Test
    @Step("About. Возврат на главную страницу")
    public void id66ReturnToTheMainPage(){
        //кликнуть по стрелочке на App bar
        AboutPage.clickGoToMainPageButton();
        //проверка
        MainPage.checkDisplayingTheAllNewsLabel();
    }
}
