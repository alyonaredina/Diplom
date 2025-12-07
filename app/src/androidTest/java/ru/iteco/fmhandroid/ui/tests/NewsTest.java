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
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.FilterNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class NewsTest extends Data {
    NewsPage myObjectNews = new NewsPage();
    MainPage myObjectMaim = new MainPage();
    AuthorizationPage myObjectAuthorization = new AuthorizationPage();
    AppBarPage myObjectAppBar = new AppBarPage();
    FilterNewsPage myObjectFilterNews = new FilterNewsPage();
    ControlPanelPage myObjectControlPanel = new ControlPanelPage();

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
            myObjectMaim.clickAllNews();
            myObjectNews.checkDisplayingRefreshButton();

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
            myObjectMaim.clickAllNews();
            myObjectNews.checkDisplayingRefreshButton();
        }
    }

    @Test
    @Description("News.Перейти в раздел фильтрации новостей")
    @Severity(SeverityLevel.CRITICAL)
    public void id14GoToTheNewsFilteringSection(){
        myObjectNews.clickGoToFilterButton();
        myObjectFilterNews.checkDisplayingFilterNewsLabel();
    }

    @Test
    @Step("News.Перейти в раздел редактирования новостей")
    @Severity(SeverityLevel.CRITICAL)
    public void id15GoToTheNewsEditingSection(){
        myObjectNews.clickEditNewsButton();
        myObjectControlPanel.checkDisplayingControlPanelLabel();
    }
}



