package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;

import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.element.ControlPanelElement;
public class ControlPanelPage {

    @Step("Проверка отображения надписи Control panel")
    public static void checkDisplayingControlPanelLabel(){
        ControlPanelElement.ControlPanelTitle.check(matches(isDisplayed()));
        ControlPanelElement.ControlPanelTitle.check(matches(withText("Control panel")));
    }

    @Step("Проверка отображения описания новости")
    public static void checkDisplayingTextOfNewsDescription(){
        ControlPanelElement.TextOfNewsDescription.check(matches(isDisplayed()));
        ControlPanelElement.TextOfNewsDescription.check(matches(withText("Новый день")));
    }

    @Step("Проверка что описание новости не отображается")
    public static void checkNoDisplayingTextOfNewsDescription(){
        ControlPanelElement.TextOfNewsDescription.check(matches(not(isDisplayed())));
    }

    @Step("Проверка отображения категории новости")
    public static void checkDisplayingTextOfCategory(String text){
        ControlPanelElement.TextOfCategory.check(matches(isDisplayed()));
        ControlPanelElement.TextOfCategory.check(matches(withText(text)));
    }

    @Step("Проверка отображения cooбщения что новостей нет")
    public static void checkDisplayingNoNews(String text){
        ControlPanelElement.NoNews.check(matches(isDisplayed()));
        ControlPanelElement.NoNews.check(matches(withText(text)));
    }

    @Step("Проверка отображения категории новости")
    public static void checkDisplayingTextOfCategoryMersi(String text){
        ControlPanelElement.TextOfCategoryMersi.check(matches(isDisplayed()));
        ControlPanelElement.TextOfCategoryMersi.check(matches(withText(text)));
    }

    @Step("Проверка текста заглавия")
    public static void CheckTextOfTitles(String text){
        ControlPanelElement.TextOfTitles.check(matches(isDisplayed()));
        ControlPanelElement.TextOfTitles.check(matches(withText(text)));
    }

    @Step("Проверка статуса")
    public static void CheckStatus(){
        ControlPanelElement.Status.check(matches(isDisplayed()));
        ControlPanelElement.Status.check(matches(withText("NOT ACTIVE")));
    }


    @Step("Проверка отображения текста об удалении новости")
    public static void checkDisplayingTextOfDeleteNews(){
        ControlPanelElement.TextOfDeleteNews.check(matches(isDisplayed()));
    }

    @Step("Выбор категории из списка")
    public static void SelectCategory(String category) {
        onView(withText(category)).inRoot(RootMatchers.isPlatformPopup())
            .perform(click());
    }

    @Step("Задать дату новости")
    public static void NewsData(String data) {
        ControlPanelElement.NewsDateElement.perform(replaceText(data));
    }
    @Step("Сгенерировать дату новости")
    public static String futureDate(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }
    @Step("Проверить дату новости")
    public static void CheckDateNews(String date) {
        waitUntilElement(R.id.news_item_publication_date_text_view);
        ControlPanelElement.DateNews.check(matches(withText(date)));
    }


    @Step("Задать время новости")
    public static void NewsTime(String time) {
        ControlPanelElement.NewsTimeElement.perform(replaceText(time));
    }
    @Step("Сгенерировать время новости")
    public static String futureTimeMinute(int input) {
        String pattern = "HH:mm";
        String time = LocalTime.now().plusMinutes(input).format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }
    @Step("Проверить время новости")
    public static void CheckTimeNews(String time) {
        waitUntilElement(R.id.news_item_publish_time_text_input_edit_text);
        ControlPanelElement.TimeNews.check(matches(withText(time)));
    }

    @Step("Задать описание новости")
    public static void NewsDiscription(String text) {
        waitUntilElement(R.id.news_item_description_text_input_edit_text);
        ControlPanelElement.NewsDiscriptionElement.perform(replaceText(text));
    }
    @Step("Сгенерировать описание новости")
    public static String addTime(int hours, int minutes) {
        return hours + ":" + minutes;
    }
    private static Matcher<View> allOf(Matcher<View> viewMatcher) {
        return viewMatcher;
    }


    @Step("проверка созданной новости")
    public static void TitleNews(String text) {
        ControlPanelElement.TitleNewsElement.check(matches(withText(text)));
    }

    public static void NewsCheck(String text) {
        waitUntilElement(R.id.news_item_title_text_view);
        TitleNews(text);
    }

    @Step("Изменить текст заглавия")
    public static void ChangeTextOfTitles(String text){
        ControlPanelElement.TextOfTitles.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Кликнуть по кнопке Добавить новость")
    public static void ClickAddNews(){
        ControlPanelElement.AddNewsButton.check(matches(isDisplayed()));
        ControlPanelElement.AddNewsButton.perform(click());
    }

    @Step("Кликнуть по полю Категория")
    public static void ClickCategoryField(){
        //ControlPanelElement.CategoryField.check(matches(isDisplayed()));
        ControlPanelElement.CategoryField.perform(click());
    }

    @Step("Кликнуть по кнопке Save")
    public static void ClickSaveButton(){
        ControlPanelElement.SaveButton.perform(scrollTo(), click());
    }

    @Step("Кликнуть по кнопке Сортировать")
    public static void ClickSortButton(){
        ControlPanelElement.SortButton.check(matches(isDisplayed()));
        ControlPanelElement.SortButton.perform(click());
    }

    @Step("Кликнуть по кнопке Развернуть новость")
    public static void ClickUnfurlNewsButton(){
        ControlPanelElement.UnfurlNewsButton.perform(actionOnItemAtPosition(0, click()));
    }

    @Step("Кликнуть по кнопке Свернуть новость")
    public static void ClickRollUpNewsButton(){
        ControlPanelElement.RollUpNewsButton.perform(actionOnItemAtPosition(0, click()));
    }

    @Step("Кликнуть по кнопке удалить новость")
    public static void ClickDeleteNews(){
        ControlPanelElement.DeleteNews.perform(click());
    }

    @Step("Кликнуть по кнопке cогласия удалить новость")
    public static void ClickOkDeleteNews(){
        ControlPanelElement.OkDeleteNews.perform(click());
    }

    @Step("Кликнуть по кнопке отмены удаления новости")
    public static void ClickCancelButton(){
        ControlPanelElement.CancelButton.perform(click());
    }

    @Step("Кликнуть по кнопке редактирования новости")
    public static void ClickEditingButton(){
        //ControlPanelElement.EditingButton.check(matches(isDisplayed()));
        ControlPanelElement.EditingButton.perform(click());
    }

    @Step("Кликнуть по бегунку статуса")
    public static void ClickSliderOfStatus(){
        ControlPanelElement.SliderOfStatus.perform(scrollTo(), click());
    }


    @Step("Кликнуть по кнопке Filter")
    public static void ClickFilterButton(){
        ControlPanelElement.FilterButton.check(matches(isDisplayed()));
        ControlPanelElement.FilterButton.perform(click());
    }

    @Step("Кликнуть по кнопке начать Filter")
    public static void ClickFilterButtonStart(){
        ControlPanelElement.FilterButtonStart.check(matches(isDisplayed()));
        ControlPanelElement.FilterButtonStart.perform(click());
    }

    @Step("Кликнуть по кнопке Cancel Filter")
    public static void ClickCancelFilterButton(){
        ControlPanelElement.CancelFilterButton.check(matches(isDisplayed()));
        ControlPanelElement.CancelFilterButton.perform(click());
    }

    @Step("убрать галочку у активного статуса")
    public static void ClickCheckMarkStatusActiv(){
        ControlPanelElement.CheckMarkStatusActiv.check(matches(isDisplayed()));
        ControlPanelElement.CheckMarkStatusActiv.perform(click());
    }

    @Step("убрать галочку у не активного статуса")
    public static void ClickCheckMarkStatusNotActiv(){
        ControlPanelElement.CheckMarkStatusNotActiv.check(matches(isDisplayed()));
        ControlPanelElement.CheckMarkStatusNotActiv.perform(click());
    }



    @Step("Cоздать новость")
    public static void CreateNews(String text1, String text2){
        //Создать новость
        ControlPanelPage.ClickAddNews();
        //кликнуть по полю Category
        ControlPanelPage.ClickCategoryField();
        //Выбрать категорию из списка
        ControlPanelPage.SelectCategory(text1);
        //Выбрать дату
        ControlPanelPage.NewsData(futureDate(1));
        //Выбрать время
        ControlPanelPage.NewsTime(futureTimeMinute(1));
        //Ввести описание новости
        ControlPanelPage.NewsDiscription(text2);
        //Создать
        ControlPanelPage.ClickSaveButton();
        //Сортировка
        waitUntilElement(R.id.news_item_title_text_view);
        ControlPanelPage.ClickSortButton();
    }


}
