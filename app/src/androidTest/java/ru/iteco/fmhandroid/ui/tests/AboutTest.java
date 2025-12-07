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
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
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
    MainPage myObjectMaim = new MainPage();
    AuthorizationPage myObjectAuthorization = new AuthorizationPage();
    AboutPage myObjectAbout = new AboutPage();
    AppBarPage myObjectAppBar = new AppBarPage();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Before
    public void authorizationTestBefore() {
        try {
            waitUntilElement(R.id.login_text_input_layout);
            ViewInteraction textView = onView(Matchers.allOf(withText("Authorization"),isDisplayed()));
            myObjectAuthorization.fillingInTheLoginField(validLogin);
            myObjectAuthorization.fillingInThePasswordField(validPassword);
            myObjectAuthorization.clickButtonEnter();
            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS
            myObjectMaim.checkDisplayingTheAllNewsLabel();
            //открыть меню
            myObjectAppBar.clickMenuButton();
            //выбрать About
            myObjectAppBar.clickLineAbout();
            //проверка
            myObjectAbout.checkDisplayingTheVersionLabel();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectAppBar.clickManButton();
            waitUntilElement(android.R.id.title);
            myObjectAppBar.clickExit();
            waitUntilElement(R.id.login_text_input_layout);
            myObjectAuthorization.checkAuthorizationTitle();
            myObjectAuthorization.fillingInTheLoginField(validLogin);
            myObjectAuthorization.fillingInThePasswordField(validPassword);
            myObjectAuthorization.clickButtonEnter();
            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS
            //открыть меню
            myObjectAppBar.clickMenuButton();
            //выбрать About
            myObjectAppBar.clickLineAbout();
            //проверка
            myObjectAbout.checkDisplayingTheVersionLabel();
        }
    }

    @Test
    @Description("About. Переход по ссылке Privacy Policy")
    @Severity(value = SeverityLevel.MINOR)
    public void id65followingTheLinkPrivacyPolicy(){
        //включаем прослушку
        Intents.init();
        //проверяем переход по ссылке
        myObjectAbout.clickLinkPrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy/"));
        //очищаем прослушку
        Intents.release();
    }

    @Test
    @Description("About. Переход по ссылке Terms Of Use")
    @Severity(value = SeverityLevel.MINOR)
    public void id65followingTheLinkTermsOfUse(){
        //включаем прослушку
        Intents.init();
        //проверяем переход по ссылке
        myObjectAbout.clickLinkTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        //очищаем прослушку
        Intents.release();
    }

    @Test
    @Description("About. Возврат на главную страницу")
    @Severity(SeverityLevel.CRITICAL)
    public void id66ReturnToTheMainPage(){
        //кликнуть по стрелочке на App bar
        myObjectAbout.clickGoToMainPageButton();
        //проверка
        myObjectMaim.checkDisplayingTheAllNewsLabel();
    }
}
