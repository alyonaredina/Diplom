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
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class MainTest extends Data {

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
    @Description("Main.Развернуть и свернуть список новостей")
    public void id10CollapseAndExpandTheLatestNews(){
        waitUntilElement(R.id.all_news_text_view);
        MainPage.clickArrowExpandNewsList();
        MainPage.clickBreakNewsListArrow();
        MainPage.checkDisplayingTheAllNewsLabel();
    }

    @Test
    @Description("Main.Перейти на страницу News")
    public void id11GoToTheNewsPage(){
        MainPage.clickAllNews();
        NewsPage.checkDisplayingSortNewsButton();
    }
}
