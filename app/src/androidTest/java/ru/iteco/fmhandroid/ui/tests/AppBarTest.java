package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

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
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AppBarTest extends Data {
    MainPage myObjectMaim = new MainPage();
    AuthorizationPage myObjectAuthorization = new AuthorizationPage();
    AppBarPage myObjectAppBar = new AppBarPage();
    AboutPage myObjectAbout = new AboutPage();

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
            myObjectMaim.checkDisplayingTheAllNewsLabel();
        }
    }

    @Test
    @Description("AppBar.Перейти на страницу Main")
    @Severity(SeverityLevel.CRITICAL)
    public void id59GoToTheMainPage(){
        //кликнуть по ALL NEWS
        myObjectMaim.clickAllNews();
        //открыть меню
        myObjectAppBar.clickMenuButton();
        //выбрать Main
        myObjectAppBar.clickLineMain();
        //проверка
        myObjectMaim.checkDisplayingTheAllNewsLabel();
    }

    @Test
    @Description("AppBar.Перейти на страницу News")
    @Severity(SeverityLevel.CRITICAL)
    public void id60GoToTheNewsPage(){
        //открыть меню
        myObjectAppBar.clickMenuButton();
        //выбрать News
        myObjectAppBar.clickLineNews();
        //проверка
        myObjectMaim.checkDisplayingTheNewsLabel();
    }

    @Test
    @Description("AppBar.Перейти на страницу About")
    @Severity(SeverityLevel.CRITICAL)
    public void id61GoToTheAboutPage(){
        //открыть меню
        myObjectAppBar.clickMenuButton();
        //выбрать About
        myObjectAppBar.clickLineAbout();
        //проверка
        myObjectAbout.checkDisplayingTheVersionLabel();
    }

    @Test
    @Description("AppBar.Переход по страницам")
    @Severity(SeverityLevel.CRITICAL)
    public void id72ERRORPageNavigation(){
        //открыть меню
        myObjectAppBar.clickMenuButton();
        //выбрать News
        myObjectAppBar.clickLineNews();
        //проверка
        myObjectMaim.checkDisplayingTheNewsLabel();
        //открыть меню
        myObjectAppBar.clickMenuButton();
        //выбрать About
        myObjectAppBar.clickLineAbout();
        //проверка
        myObjectAbout.checkDisplayingTheVersionLabel();
    }

    @Test
    @Description("AppBar.Перейти на страницу Love is all")
    @Severity(SeverityLevel.CRITICAL)
    public void id62GoToTheLoveIsAllPage(){
        //кликнуть по значку бабочки
        myObjectAppBar.clickButterflyBadge();
        //проверка
        myObjectAppBar.checkDisplayingTheLoveIsAllLabel();
    }

    @Test
    @Description("AppBar. Развернуть и свернуть цитату на странице Love is all")
    @Severity(SeverityLevel.NORMAL)
    public void id63GoToTheLoveIsAllPage(){
        //кликнуть по значку бабочки
        myObjectAppBar.clickButterflyBadge();
        //развернуть цитату
        myObjectAppBar.clickExpandTheQuoteButton();
        //свернуть цитату
        myObjectAppBar.clickRollUpTheQuoteButton();
        //проверка
        myObjectAppBar.checkNoDisplayingTheQuote();
    }

    @Test
    @Description("AppBar. Выйти из учетной записи")
    @Severity(SeverityLevel.BLOCKER)
    public void id64Exit(){
        //кликнуть по значку человека
        myObjectAppBar.clickManButton();
        //кликнуть по Log out
        myObjectAppBar.clickExit();
        //проверка
        myObjectAuthorization.checkAuthorizationTitle();
    }
}
