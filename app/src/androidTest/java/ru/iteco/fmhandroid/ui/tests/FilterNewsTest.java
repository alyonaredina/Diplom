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
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.FilterNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class FilterNewsTest extends Data {
    NewsPage myObjectNews = new NewsPage();
    MainPage myObjectMaim = new MainPage();
    FilterNewsPage myObjectFilterNews = new FilterNewsPage();
    AuthorizationPage myObjectAuthorization = new AuthorizationPage();
    AppBarPage myObjectAppBar = new AppBarPage();
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
            myObjectNews.clickGoToFilterButton();
            myObjectFilterNews.checkDisplayingFilterNewsLabel();
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
            myObjectNews.clickGoToFilterButton();
            myObjectFilterNews.checkDisplayingFilterNewsLabel();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка с выбором дат")
    @Severity(SeverityLevel.CRITICAL)
    public void id16selectingCategoriesFromADateSelectionList(){
        //развернуть список
        myObjectFilterNews.clickDropDownListButton();
        //выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //выбрать начальную дату
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(0));
        //выбрать конечную дату
        myObjectFilterNews.SelectNewsDataFilterEnd(myObjectFilterNews.futureDateDays(7));
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        try {
            myObjectFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectFilterNews.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка с выбором дат")
    @Severity(SeverityLevel.CRITICAL)
    public void id16ERRORselectingCategoriesFromADateSelectionList(){
        //развернуть список
        myObjectFilterNews.clickDropDownListButton();
        //выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //выбрать начальную дату
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(7));
        //выбрать конечную дату
        myObjectFilterNews.SelectNewsDataFilterEnd(myObjectFilterNews.futureDateDays(0));
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        try {
            myObjectFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectFilterNews.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка с выбором дат")
    @Severity(SeverityLevel.CRITICAL)
    public void id17selectingCategoriesFromADateSelectionList(){
        //развернуть список
        myObjectFilterNews.clickDropDownListButton();
        //выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //выбрать начальную дату
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(7));
        //выбрать конечную дату
        myObjectFilterNews.SelectNewsDataFilterEnd(myObjectFilterNews.futureDateDays(14));
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        try {
            myObjectFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectFilterNews.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка без дат")
    @Severity(SeverityLevel.CRITICAL)
    public void id18selectingCategoriesFromSelectionList(){
        //развернуть список
        myObjectFilterNews.clickDropDownListButton();
        //выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        try {
            myObjectFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectFilterNews.noNews();
        }
    }

    @Test
    @Description("FilterNews. Ввод категории вручную")
    @Severity(SeverityLevel.CRITICAL)
    public void id19EnteringACategoryManually(){
        //развернуть список
        myObjectFilterNews.clickDropDownListButton();
        //ввод категории
        myObjectFilterNews.changeTextCategory(massaj);
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        try {
            myObjectFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectFilterNews.noNews();
        }
    }

    @Test
    @Description("FilterNews. Фильтрация без данных")
    @Severity(SeverityLevel.CRITICAL)
    public void id23FilteringWithoutData(){
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        try {
            myObjectFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectFilterNews.noNews();
        }
    }

    @Test
    @Description("FilterNews. Без выбора категорий из списка с выбором дат")
    @Severity(SeverityLevel.CRITICAL)
    public void id24onlyDate(){
        //выбрать начальную дату
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(0));
        //выбрать конечную дату
        myObjectFilterNews.SelectNewsDataFilterEnd(myObjectFilterNews.futureDateDays(7));
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        try {
            myObjectFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            myObjectFilterNews.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка и даты начала поиска")
    @Severity(SeverityLevel.CRITICAL)
    public void id26selectingCategoriesFromADateSelectionList(){
        //развернуть список
        myObjectFilterNews.clickDropDownListButton();
        //выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //выбрать начальную дату
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(0));
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        myObjectFilterNews.checkDisplayingErrorWrongPeriod();
    }

    @Test
    @Description("FilterNews. Завершение фильтрации")
    @Severity(SeverityLevel.CRITICAL)
    public void id28EndFilter(){
        //кликнуть Filter
        myObjectFilterNews.clickFilterButton();
        //проверка
        myObjectMaim.checkDisplayingTheNewsLabel();
    }
}
