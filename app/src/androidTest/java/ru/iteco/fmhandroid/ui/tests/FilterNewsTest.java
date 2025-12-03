package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.page.FilterNewsPage.futureDateDays;

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
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.FilterNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class FilterNewsTest extends Data {

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
            MainPage.clickAllNews();
            NewsPage.clickGoToFilterButton();
            FilterNewsPage.checkDisplayingFilterNewsLabel();
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
            MainPage.clickAllNews();
            NewsPage.clickGoToFilterButton();
            FilterNewsPage.checkDisplayingFilterNewsLabel();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка с выбором дат")
    public void id16selectingCategoriesFromADateSelectionList(){
        //развернуть список
        FilterNewsPage.clickDropDownListButton();
        //выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //выбрать начальную дату
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(0));
        //выбрать конечную дату
        FilterNewsPage.SelectNewsDataFilterEnd(futureDateDays(7));
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        try {
            FilterNewsPage.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            FilterNewsPage.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка с выбором дат")
    public void id16ERRORselectingCategoriesFromADateSelectionList(){
        //развернуть список
        FilterNewsPage.clickDropDownListButton();
        //выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //выбрать начальную дату
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(7));
        //выбрать конечную дату
        FilterNewsPage.SelectNewsDataFilterEnd(futureDateDays(0));
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        try {
            FilterNewsPage.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            FilterNewsPage.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка с выбором дат")
    public void id17selectingCategoriesFromADateSelectionList(){
        //развернуть список
        FilterNewsPage.clickDropDownListButton();
        //выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //выбрать начальную дату
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(7));
        //выбрать конечную дату
        FilterNewsPage.SelectNewsDataFilterEnd(futureDateDays(14));
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        try {
            FilterNewsPage.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            FilterNewsPage.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка без дат")
    public void id18selectingCategoriesFromSelectionList(){
        //развернуть список
        FilterNewsPage.clickDropDownListButton();
        //выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        try {
            FilterNewsPage.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            FilterNewsPage.noNews();
        }
    }

    @Test
    @Description("FilterNews. Ввод категории вручную")
    public void id19EnteringACategoryManually(){
        //развернуть список
        FilterNewsPage.clickDropDownListButton();
        //ввод категории
        FilterNewsPage.changeTextCategory(massaj);
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        try {
            FilterNewsPage.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            FilterNewsPage.noNews();
        }
    }

    @Test
    @Description("FilterNews. Фильтрация без данных")
    public void id23FilteringWithoutData(){
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        try {
            FilterNewsPage.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            FilterNewsPage.noNews();
        }
    }

    @Test
    @Description("FilterNews. Без выбора категорий из списка с выбором дат")
    public void id24onlyDate(){
        //выбрать начальную дату
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(0));
        //выбрать конечную дату
        FilterNewsPage.SelectNewsDataFilterEnd(futureDateDays(7));
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        try {
            FilterNewsPage.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            FilterNewsPage.noNews();
        }
    }

    @Test
    @Description("FilterNews. Выбор категорий из списка и даты начала поиска")
    public void id26selectingCategoriesFromADateSelectionList(){
        //развернуть список
        FilterNewsPage.clickDropDownListButton();
        //выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //выбрать начальную дату
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(0));
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        FilterNewsPage.checkDisplayingErrorWrongPeriod();
    }

    @Test
    @Description("FilterNews. Завершение фильтрации")
    public void id28EndFilter(){
        //кликнуть Filter
        FilterNewsPage.clickFilterButton();
        //проверка
        MainPage.checkDisplayingTheNewsLabel();
    }
}
