package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.page.ControlPanelPage.futureDate;
import static ru.iteco.fmhandroid.ui.page.ControlPanelPage.futureTimeMinute;
import static ru.iteco.fmhandroid.ui.page.FilterNewsPage.futureDateDays;

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
    private View decorView;

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
            NewsPage.clickEditNewsButton();
            ControlPanelPage.checkDisplayingControlPanelLabel();


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
            NewsPage.clickEditNewsButton();
            ControlPanelPage.checkDisplayingControlPanelLabel();
        }
    }



    @Test
    @Description("ControlPanel.Развернуть новость")
    public void id29ExpandTheNews(){
        ControlPanelPage.CreateNews(holiday, nameOfTheHoliday);
        ControlPanelPage.ClickUnfurlNewsButton();
        //проверка
        ControlPanelPage.checkDisplayingTextOfNewsDescription();
    }

    @Test
    @Description("ControlPanel.Свернуть новость")
    public void id29collapseTheNews(){
        ControlPanelPage.CreateNews(holiday, nameOfTheHoliday);
        ControlPanelPage.ClickUnfurlNewsButton();
        ControlPanelPage.ClickRollUpNewsButton();
        //проверка
        ControlPanelPage.checkNoDisplayingTextOfNewsDescription();
    }

    @Test
    @Description("ControlPanel.Создать новость")
    public void id53createTheNews(){
        ControlPanelPage.CreateNews(holiday, nameOfTheHoliday);
        //проверка
        ControlPanelPage.checkDisplayingTextOfCategory(holiday);

    }

    @Test
    @Description("ControlPanel.Удалить новость")
    public void id30deleteTheNews(){
        ControlPanelPage.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на значек удаления
        ControlPanelPage.ClickDeleteNews();
        //проверка сообщения об удалении
        ControlPanelPage.checkDisplayingTextOfDeleteNews();
        //кликнуть ок
        ControlPanelPage.ClickOkDeleteNews();


    }

    @Test
    @Description("ControlPanel. Отмена удаления")
    public void id31cancellationDelete(){
        ControlPanelPage.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на значек удаления
        waitUntilElement(R.id.delete_news_item_image_view);
        ControlPanelPage.ClickDeleteNews();
        //кликнуть Cancel
        ControlPanelPage.checkDisplayingTextOfDeleteNews();
        ControlPanelPage.ClickCancelButton();
        //Проверка
        waitUntilElement(R.id.news_item_material_card_view);
        ControlPanelPage.checkDisplayingTextOfCategory(holiday);
    }

    @Test
    @Description("ControlPanel. Изменить категорию новости")
    public void id32changeTheNewsCategory(){
        ControlPanelPage.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на иконку редактирования
        ControlPanelPage.ClickEditingButton();
        //кликнуть по полю Category
        ControlPanelPage.ClickCategoryField();
        //Выбрать категорию из списка
        ControlPanelPage.SelectCategory(mersi);
        //Создать
        ControlPanelPage.ClickSaveButton();
        //проверка
        ControlPanelPage.checkDisplayingTextOfCategoryMersi(mersi);

    }

    @Test
    @Description("ControlPanel. Изменить заглавие новости")
    public void id33changeTheNewsCategory(){
        ControlPanelPage.CreateNews(holiday, nameOfTheHoliday);
        //кликнуть на иконку редактирования
        ControlPanelPage.ClickEditingButton();
        //изменить текст
        ControlPanelPage.ChangeTextOfTitles(mersi);
        //Создать
        ControlPanelPage.ClickSaveButton();
        //проверка
        ControlPanelPage.checkDisplayingTextOfCategoryMersi(mersi);
    }

    @Test
    @Description("ControlPanel. Изменить дату публикации")
    public void id34changeThePublicationDate(){
        //Cоздать новость
        ControlPanelPage.CreateNews(holiday,nameOfTheHoliday);
        //кликнуть на иконку редактирования
        ControlPanelPage.ClickEditingButton();
        //Выбрать дату
        ControlPanelPage.NewsData(futureDate(1));
        //Создать
        ControlPanelPage.ClickSaveButton();
        //проверка

    }

    @Test
    @Description("ControlPanel. Изменить статус новости")
    public void id39changeTheNewsStatus(){
        ControlPanelPage.CreateNews(holiday,nameOfTheHoliday);
        //кликнуть на иконку редактирования
        ControlPanelPage.ClickEditingButton();
        //переместить бегунок
        ControlPanelPage.ClickSliderOfStatus();
        //Создать
        ControlPanelPage.ClickSaveButton();
        //проверка
        ControlPanelPage.CheckStatus();
    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории")
    public void id41filteringByCategory(){
        ControlPanelPage.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        ControlPanelPage.ClickFilterButton();
        //открыть список категорий
        ControlPanelPage.ClickCategoryField();
        //выбрать категорию
        ControlPanelPage.SelectCategory(holiday);
        //кликнуть фильтровать
        ControlPanelPage.ClickFilterButtonStart();
        //проверка
        ControlPanelPage.checkDisplayingTextOfCategory(holiday);
    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории и дате")
    public void id42filteringByCategoryAndDate(){
        ControlPanelPage.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        ControlPanelPage.ClickFilterButton();
        //открыть список категорий
        ControlPanelPage.ClickCategoryField();
        //выбрать категорию
        ControlPanelPage.SelectCategory(holiday);
        //выбрать дату начала поиска
        FilterNewsPage.SelectNewsDataFilterEnd(futureDateDays(0));
        //выбрать дату окончания поиска
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(7));
        //кликнуть фильтровать
        ControlPanelPage.ClickFilterButtonStart();
        //проверка
        ControlPanelPage.checkDisplayingNoNews(thereNoNews);

    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории, дате и статусу")
    public void id43filteringByCategoryAndDateAndStatus(){
        ControlPanelPage.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        ControlPanelPage.ClickFilterButton();
        //открыть список категорий
        ControlPanelPage.ClickCategoryField();
        //выбрать категорию
        ControlPanelPage.SelectCategory(holiday);
        //выбрать дату начала поиска
        FilterNewsPage.SelectNewsDataFilterEnd(futureDateDays(0));
        //выбрать дату окончания поиска
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(7));
        //убрать галочку у активного статуса
        ControlPanelPage.ClickCheckMarkStatusActiv();
        //кликнуть фильтровать
        ControlPanelPage.ClickFilterButtonStart();
        //проверка
        ControlPanelPage.checkDisplayingNoNews(thereNoNews);
    }

    @Test
    @Description("ControlPanel. Фильтровать новости по категории, дате и статусу")
    public void id44filteringByCategoryAndDateAndStatus(){
        ControlPanelPage.CreateNews(holiday,nameOfTheHoliday);
        //oткрыть фильтр
        ControlPanelPage.ClickFilterButton();
        //открыть список категорий
        ControlPanelPage.ClickCategoryField();
        //выбрать категорию
        ControlPanelPage.SelectCategory(holiday);
        //выбрать дату начала поиска
        FilterNewsPage.SelectNewsDataFilterEnd(futureDateDays(0));
        //выбрать дату окончания поиска
        FilterNewsPage.SelectNewsDataFilterStart(futureDateDays(7));
        //убрать галочку у не активного статуса
        ControlPanelPage.ClickCheckMarkStatusNotActiv();
        //кликнуть фильтровать
        ControlPanelPage.ClickFilterButtonStart();
        //проверка
        ControlPanelPage.checkDisplayingNoNews(thereNoNews);
    }

    @Test
    @Description("ControlPanel. Выйти из фильтра")
    public void id48exitTheFilter() {
        //oткрыть фильтр
        ControlPanelPage.ClickFilterButton();
        //кликнуть cancel
        ControlPanelPage.ClickCancelFilterButton();
        //проверка
        ControlPanelPage.checkDisplayingControlPanelLabel();
    }

    @Test
    @Description("ControlPanel. Создать новость по категории из списка")
    public void id49createNewsByCategoryFromTheList(){
        //Создать новость
        ControlPanelPage.ClickAddNews();
        //кликнуть по полю Category
        ControlPanelPage.ClickCategoryField();
        //Выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //Создать
        ControlPanelPage.ClickSaveButton();
        //оповещение об ошибке
        AuthorizationPage.aPop_upError(errorCreateNews, decorView);
    }

    @Test
    @Description("ControlPanel. Создать новость по категории из списка и дате")
    public void id50createNewsByCategoryFromTheListAndDate(){
        //Создать новость
        ControlPanelPage.ClickAddNews();
        //кликнуть по полю Category
        ControlPanelPage.ClickCategoryField();
        //Выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //Выбрать дату
        ControlPanelPage.NewsData(futureDate(1));
        //Создать
        ControlPanelPage.ClickSaveButton();
        //оповещение об ошибке
        AuthorizationPage.aPop_upError(errorCreateNews, decorView);
    }

    @Test
    @Description("ControlPanel. Создать новость по категории из списка, дате и времени")
    public void id51createNewsByCategoryFromTheListAndDateAndTime(){
        //Создать новость
        ControlPanelPage.ClickAddNews();
        //кликнуть по полю Category
        ControlPanelPage.ClickCategoryField();
        //Выбрать категорию из списка
        ControlPanelPage.SelectCategory(holiday);
        //Выбрать дату
        ControlPanelPage.NewsData(futureDate(1));
        //Выбрать время
        ControlPanelPage.NewsTime(futureTimeMinute(1));
        //Создать
        ControlPanelPage.ClickSaveButton();
        //оповещение об ошибке
        AuthorizationPage.aPop_upError(errorCreateNews, decorView);
    }

}
