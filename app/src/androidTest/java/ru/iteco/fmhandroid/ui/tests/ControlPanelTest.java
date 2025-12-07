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
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.FilterNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class ControlPanelTest extends Data {
    NewsPage myObjectNews = new NewsPage();
    MainPage myObjectMaim = new MainPage();
    ControlPanelPage myObjectControlPanel = new ControlPanelPage();
    AuthorizationPage myObjectAuthorization = new AuthorizationPage();
    AppBarPage myObjectAppBar = new AppBarPage();
    FilterNewsPage myObjectFilterNews = new FilterNewsPage();

    private View decorView;

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
            myObjectNews.clickEditNewsButton();
            myObjectControlPanel.checkDisplayingControlPanelLabel();


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
            myObjectNews.clickEditNewsButton();
            myObjectControlPanel.checkDisplayingControlPanelLabel();
        }
    }



    @Test
    @Description("ControlPanel.Развернуть новость")
    @Severity(SeverityLevel.NORMAL)
    public void id29ExpandTheNews(){
        myObjectControlPanel.CreateNews(holiday, nameOfTheHoliday);
        myObjectControlPanel.ClickUnfurlNewsButton();
        //проверка
        myObjectControlPanel.checkDisplayingTextOfNewsDescription();
    }

    @Test
    @Description("ControlPanel.Свернуть новость")
    @Severity(SeverityLevel.NORMAL)
    public void id29collapseTheNews(){
        myObjectControlPanel.CreateNews(holiday, nameOfTheHoliday);
        myObjectControlPanel.ClickUnfurlNewsButton();
        myObjectControlPanel.ClickRollUpNewsButton();
        //проверка
        myObjectControlPanel.checkNoDisplayingTextOfNewsDescription();
    }

    @Test
    @Description("ControlPanel.Создать новость")
    @Severity(SeverityLevel.CRITICAL)
    public void id53createTheNews(){
        myObjectControlPanel.CreateNews(holiday, nameOfTheHoliday);
        //проверка
        myObjectControlPanel.checkDisplayingTextOfCategory(holiday);

    }

    @Test
    @Description("ControlPanel.Удалить новость")
    @Severity(SeverityLevel.CRITICAL)
    public void id30deleteTheNews(){
        myObjectControlPanel.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на значек удаления
        myObjectControlPanel.ClickDeleteNews();
        //проверка сообщения об удалении
        myObjectControlPanel.checkDisplayingTextOfDeleteNews();
        //кликнуть ок
        myObjectControlPanel.ClickOkDeleteNews();


    }

    @Test
    @Description("ControlPanel. Отмена удаления")
    @Severity(SeverityLevel.CRITICAL)
    public void id31cancellationDelete(){
        myObjectControlPanel.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на значек удаления
        waitUntilElement(R.id.delete_news_item_image_view);
        myObjectControlPanel.ClickDeleteNews();
        //кликнуть Cancel
        myObjectControlPanel.checkDisplayingTextOfDeleteNews();
        myObjectControlPanel.ClickCancelButton();
        //Проверка
        waitUntilElement(R.id.news_item_material_card_view);
        myObjectControlPanel.checkDisplayingTextOfCategory(holiday);
    }

    @Test
    @Description("ControlPanel. Изменить категорию новости")
    @Severity(SeverityLevel.CRITICAL)
    public void id32changeTheNewsCategory(){
        myObjectControlPanel.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на иконку редактирования
        myObjectControlPanel.ClickEditingButton();
        //кликнуть по полю Category
        myObjectControlPanel.ClickCategoryField();
        //Выбрать категорию из списка
        myObjectControlPanel.SelectCategory(mersi);
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //проверка
        myObjectControlPanel.checkDisplayingTextOfCategoryMersi(mersi);

    }

    @Test
    @Description("ControlPanel. Изменить заглавие новости")
    @Severity(SeverityLevel.CRITICAL)
    public void id33changeTheNewsCategory(){
        myObjectControlPanel.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на иконку редактирования
        myObjectControlPanel.ClickEditingButton();
        //изменить текст
        myObjectControlPanel.ChangeTextOfTitles(mersi);
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //проверка
        myObjectControlPanel.checkDisplayingTextOfCategoryMersi(mersi);
    }

    @Test
    @Description("ControlPanel. Изменить дату публикации")
    @Severity(SeverityLevel.CRITICAL)
    public void id34changeThePublicationDate(){
        //Cоздать новость
        myObjectControlPanel.CreateNews(holiday,nameOfTheHoliday);
        //кликнуть на иконку редактирования
        myObjectControlPanel.ClickEditingButton();
        //Выбрать дату
        myObjectControlPanel.NewsData(myObjectControlPanel.futureDate(1));
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //проверка

    }

    @Test
    @Description("ControlPanel. Изменить статус новости")
    @Severity(SeverityLevel.CRITICAL)
    public void id39changeTheNewsStatus(){
        myObjectControlPanel.CreateNews(holiday,nameOfTheHoliday);
        //кликнуть на иконку редактирования
        myObjectControlPanel.ClickEditingButton();
        //переместить бегунок
        myObjectControlPanel.ClickSliderOfStatus();
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //проверка
        myObjectControlPanel.CheckStatus();
    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории")
    @Severity(SeverityLevel.CRITICAL)
    public void id41filteringByCategory(){
        myObjectControlPanel.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        myObjectControlPanel.ClickFilterButton();
        //открыть список категорий
        myObjectControlPanel.ClickCategoryField();
        //выбрать категорию
        myObjectControlPanel.SelectCategory(holiday);
        //кликнуть фильтровать
        myObjectControlPanel.ClickFilterButtonStart();
        //проверка
        myObjectControlPanel.checkDisplayingTextOfCategory(holiday);
    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории и дате")
    @Severity(SeverityLevel.CRITICAL)
    public void id42filteringByCategoryAndDate(){
        myObjectControlPanel.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        myObjectControlPanel.ClickFilterButton();
        //открыть список категорий
        myObjectControlPanel.ClickCategoryField();
        //выбрать категорию
        myObjectControlPanel.SelectCategory(holiday);
        //выбрать дату начала поиска
        myObjectFilterNews.SelectNewsDataFilterEnd(myObjectFilterNews.futureDateDays(0));
        //выбрать дату окончания поиска
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(7));
        //кликнуть фильтровать
        myObjectControlPanel.ClickFilterButtonStart();
        //проверка
        myObjectControlPanel.checkDisplayingNoNews(thereNoNews);

    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории, дате и статусу")
    @Severity(SeverityLevel.CRITICAL)
    public void id43filteringByCategoryAndDateAndStatus(){
        myObjectControlPanel.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        myObjectControlPanel.ClickFilterButton();
        //открыть список категорий
        myObjectControlPanel.ClickCategoryField();
        //выбрать категорию
        myObjectControlPanel.SelectCategory(holiday);
        //выбрать дату начала поиска
        myObjectFilterNews.SelectNewsDataFilterEnd(myObjectFilterNews.futureDateDays(0));
        //выбрать дату окончания поиска
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(7));
        //убрать галочку у активного статуса
        myObjectControlPanel.ClickCheckMarkStatusActiv();
        //кликнуть фильтровать
        myObjectControlPanel.ClickFilterButtonStart();
        //проверка
        myObjectControlPanel.checkDisplayingNoNews(thereNoNews);
    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории, дате и статусу")
    @Severity(SeverityLevel.CRITICAL)
    public void id44filteringByCategoryAndDateAndStatus(){
        myObjectControlPanel.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        myObjectControlPanel.ClickFilterButton();
        //открыть список категорий
        myObjectControlPanel.ClickCategoryField();
        //выбрать категорию
        myObjectControlPanel.SelectCategory(holiday);
        //выбрать дату начала поиска
        myObjectFilterNews.SelectNewsDataFilterEnd(myObjectFilterNews.futureDateDays(0));
        //выбрать дату окончания поиска
        myObjectFilterNews.SelectNewsDataFilterStart(myObjectFilterNews.futureDateDays(7));
        //убрать галочку у не активного статуса
        myObjectControlPanel.ClickCheckMarkStatusNotActiv();
        //кликнуть фильтровать
        myObjectControlPanel.ClickFilterButtonStart();
        //проверка
        myObjectControlPanel.checkDisplayingNoNews(thereNoNews);
    }

    @Test
    @Description("ControlPanel. Выйти из фильтра")
    @Severity(SeverityLevel.CRITICAL)
    public void id48exitTheFilter() {
        //oткрыть фильтр
        myObjectControlPanel.ClickFilterButton();
        //кликнуть cancel
        myObjectControlPanel.ClickCancelFilterButton();
        //проверка
        myObjectControlPanel.checkDisplayingControlPanelLabel();
    }

    @Test
    @Description("ControlPanel. Создать новость по категории из списка")
    @Severity(SeverityLevel.CRITICAL)
    public void id49createNewsByCategoryFromTheList(){
        //Создать новость
        myObjectControlPanel.ClickAddNews();
        //кликнуть по полю Category
        myObjectControlPanel.ClickCategoryField();
        //Выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //оповещение об ошибке
        myObjectAuthorization.aPop_upError(errorCreateNews, decorView);
    }

    @Test
    @Description("ControlPanel. Создать новость по категории из списка и дате")
    @Severity(SeverityLevel.CRITICAL)
    public void id50createNewsByCategoryFromTheListAndDate(){
        //Создать новость
        myObjectControlPanel.ClickAddNews();
        //кликнуть по полю Category
        myObjectControlPanel.ClickCategoryField();
        //Выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //Выбрать дату
        myObjectControlPanel.NewsData(myObjectControlPanel.futureDate(1));
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //оповещение об ошибке
        myObjectAuthorization.aPop_upError(errorCreateNews, decorView);
    }

    @Test
    @Description("ControlPanel. Создать новость по категории из списка, дате и времени")
    @Severity(SeverityLevel.CRITICAL)
    public void id51createNewsByCategoryFromTheListAndDateAndTime(){
        //Создать новость
        myObjectControlPanel.ClickAddNews();
        //кликнуть по полю Category
        myObjectControlPanel.ClickCategoryField();
        //Выбрать категорию из списка
        myObjectControlPanel.SelectCategory(holiday);
        //Выбрать дату
        myObjectControlPanel.NewsData(myObjectControlPanel.futureDate(1));
        //Выбрать время
        myObjectControlPanel.NewsTime(myObjectControlPanel.futureTimeMinute(1));
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //оповещение об ошибке
        myObjectAuthorization.aPop_upError(errorCreateNews, decorView);
    }

}
