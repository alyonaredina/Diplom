package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;


import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
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
            MainPage.checkDisplayingTheAllNewsLabel();
        }
    }

    @Test
    @Description("AppBar.Перейти на страницу Main")
    public void id59GoToTheMainPage(){
        //кликнуть по ALL NEWS
        MainPage.clickAllNews();
        //открыть меню
        AppBarPage.clickMenuButton();
        //выбрать Main
        AppBarPage.clickLineMain();
        //проверка
        MainPage.checkDisplayingTheAllNewsLabel();
    }

    @Test
    @Description("AppBar.Перейти на страницу News")
    public void id60GoToTheNewsPage(){
        //открыть меню
        AppBarPage.clickMenuButton();
        //выбрать News
        AppBarPage.clickLineNews();
        //проверка
        MainPage.checkDisplayingTheNewsLabel();
    }

    @Test
    @Description("AppBar.Перейти на страницу About")
    public void id61GoToTheAboutPage(){
        //открыть меню
        AppBarPage.clickMenuButton();
        //выбрать About
        AppBarPage.clickLineAbout();
        //проверка
        AboutPage.checkDisplayingTheVersionLabel();
    }

    @Test
    @Description("AppBar.Переход по страницам")
    public void id72ERRORPageNavigation(){
        //открыть меню
        AppBarPage.clickMenuButton();
        //выбрать News
        AppBarPage.clickLineNews();
        //проверка
        MainPage.checkDisplayingTheNewsLabel();
        //открыть меню
        AppBarPage.clickMenuButton();
        //выбрать About
        AppBarPage.clickLineAbout();
        //проверка
        AboutPage.checkDisplayingTheVersionLabel();
    }

    @Test
    @Description("AppBar.Перейти на страницу Love is all")
    public void id62GoToTheLoveIsAllPage(){
        //кликнуть по значку бабочки
        AppBarPage.clickButterflyBadge();
        //проверка
        AppBarPage.checkDisplayingTheLoveIsAllLabel();
    }

    @Test
    @Description("AppBar. Развернуть и свернуть цитату на странице Love is all")
    public void id63GoToTheLoveIsAllPage(){
        //кликнуть по значку бабочки
        AppBarPage.clickButterflyBadge();
        //развернуть цитату
        AppBarPage.clickExpandTheQuoteButton();
        //свернуть цитату
        AppBarPage.clickRollUpTheQuoteButton();
        //проверка
        AppBarPage.checkNoDisplayingTheQuote();
    }

    @Test
    @Description("AppBar. Выйти из учетной записи")
    public void id64Exit(){
        //кликнуть по значку человека
        AppBarPage.clickManButton();
        //кликнуть по Log out
        AppBarPage.clickExit();
        //проверка
        AuthorizationPage.checkAuthorizationTitle();
    }
}
