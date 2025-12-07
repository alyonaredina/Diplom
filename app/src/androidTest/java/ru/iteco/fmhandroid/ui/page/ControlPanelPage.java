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

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.element.ControlPanelElement;
public class ControlPanelPage {
    ControlPanelElement myObjectControlPanel = new ControlPanelElement();


    public void checkDisplayingControlPanelLabel(){
        Allure.step("Проверка отображения надписи Control panel");
        myObjectControlPanel.ControlPanelTitle.check(matches(isDisplayed()));
        myObjectControlPanel.ControlPanelTitle.check(matches(withText("Control panel")));
    }

    public void checkDisplayingTextOfNewsDescription(){
        Allure.step("Проверка отображения описания новости");
        myObjectControlPanel.TextOfNewsDescription.check(matches(isDisplayed()));
        myObjectControlPanel.TextOfNewsDescription.check(matches(withText("Новый день")));
    }

    public void checkNoDisplayingTextOfNewsDescription(){
        Allure.step("Проверка что описание новости не отображается");
        myObjectControlPanel.TextOfNewsDescription.check(matches(not(isDisplayed())));
    }

    public void checkDisplayingTextOfCategory(String text){
        Allure.step("Проверка отображения категории новости");
        myObjectControlPanel.TextOfCategory.check(matches(isDisplayed()));
        myObjectControlPanel.TextOfCategory.check(matches(withText(text)));
    }

    public void checkDisplayingNoNews(String text){
       Allure.step("Проверка отображения cooбщения что новостей нет");
        myObjectControlPanel.NoNews.check(matches(isDisplayed()));
        myObjectControlPanel.NoNews.check(matches(withText(text)));
    }

    public void checkDisplayingTextOfCategoryMersi(String text){
        Allure.step("Проверка отображения категории новости");
        myObjectControlPanel.TextOfCategoryMersi.check(matches(isDisplayed()));
        myObjectControlPanel.TextOfCategoryMersi.check(matches(withText(text)));
    }

    public void CheckTextOfTitles(String text){
        Allure.step("Проверка текста заглавия");
        myObjectControlPanel.TextOfTitles.check(matches(isDisplayed()));
        myObjectControlPanel.TextOfTitles.check(matches(withText(text)));
    }

    public void CheckStatus(){
        Allure.step("Проверка статуса");
        myObjectControlPanel.Status.check(matches(isDisplayed()));
        myObjectControlPanel.Status.check(matches(withText("NOT ACTIVE")));
    }


    public void checkDisplayingTextOfDeleteNews(){
       Allure.step("Проверка отображения текста об удалении новости");
        myObjectControlPanel.TextOfDeleteNews.check(matches(isDisplayed()));
    }

    public void SelectCategory(String category) {
        Allure.step("Выбор категории из списка");
        onView(withText(category)).inRoot(RootMatchers.isPlatformPopup())
            .perform(click());
    }

    public void NewsData(String data) {
        Allure.step("Задать дату новости");
        myObjectControlPanel.NewsDateElement.perform(replaceText(data));
    }

    public String futureDate(int input) {
        Allure.step("Сгенерировать дату новости");
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }

    public void CheckDateNews(String date) {
        Allure.step("Проверить дату новости");
        waitUntilElement(R.id.news_item_publication_date_text_view);
        myObjectControlPanel.DateNews.check(matches(withText(date)));
    }


    public void NewsTime(String time) {
        Allure.step("Задать время новости");
        myObjectControlPanel.NewsTimeElement.perform(replaceText(time));
    }

    public String futureTimeMinute(int input) {
        Allure.step("Сгенерировать время новости");
        String pattern = "HH:mm";
        String time = LocalTime.now().plusMinutes(input).format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }

    public void CheckTimeNews(String time) {
        Allure.step("Проверить время новости");
        waitUntilElement(R.id.news_item_publish_time_text_input_edit_text);
        myObjectControlPanel.TimeNews.check(matches(withText(time)));
    }

    public void NewsDiscription(String text) {
        Allure.step("Задать описание новости");
        waitUntilElement(R.id.news_item_description_text_input_edit_text);
        myObjectControlPanel.NewsDiscriptionElement.perform(replaceText(text));
    }

    public String addTime(int hours, int minutes) {
        Allure.step("Сгенерировать описание новости");

        return hours + ":" + minutes;
    }
    private static Matcher<View> allOf(Matcher<View> viewMatcher) {

        return viewMatcher;
    }

    public void TitleNews(String text) {
        Allure.step("проверка созданной новости");
        myObjectControlPanel.TitleNewsElement.check(matches(withText(text)));
    }

    public void NewsCheck(String text) {
        waitUntilElement(R.id.news_item_title_text_view);
        TitleNews(text);
    }

    public void ChangeTextOfTitles(String text){
        Allure.step("Изменить текст заглавия");
        myObjectControlPanel.TextOfTitles.perform(replaceText(text), closeSoftKeyboard());
    }

    public void ClickAddNews(){
        Allure.step("Кликнуть по кнопке Добавить новость");
        myObjectControlPanel.AddNewsButton.check(matches(isDisplayed()));
        myObjectControlPanel.AddNewsButton.perform(click());
    }

    public void ClickCategoryField(){
        Allure.step("Кликнуть по полю Категория");
        //ControlPanelElement.CategoryField.check(matches(isDisplayed()));
        myObjectControlPanel.CategoryField.perform(click());
    }

    public void ClickSaveButton(){
        Allure.step("Кликнуть по кнопке Save");
        myObjectControlPanel.SaveButton.perform(scrollTo(), click());
    }

    public void ClickSortButton(){
        Allure.step("Кликнуть по кнопке Сортировать");
        myObjectControlPanel.SortButton.check(matches(isDisplayed()));
        myObjectControlPanel.SortButton.perform(click());
    }

    public void ClickUnfurlNewsButton(){
        Allure.step("Кликнуть по кнопке Развернуть новость");
        myObjectControlPanel.UnfurlNewsButton.perform(actionOnItemAtPosition(0, click()));
    }

    public void ClickRollUpNewsButton(){
        Allure.step("Кликнуть по кнопке Свернуть новость");
        myObjectControlPanel.RollUpNewsButton.perform(actionOnItemAtPosition(0, click()));
    }

    public void ClickDeleteNews(){
        Allure.step("Кликнуть по кнопке удалить новость");
        myObjectControlPanel.DeleteNews.perform(click());
    }

    public void ClickOkDeleteNews(){
        Allure.step("Кликнуть по кнопке cогласия удалить новость");
        myObjectControlPanel.OkDeleteNews.perform(click());
    }

    public void ClickCancelButton(){
        Allure.step("Кликнуть по кнопке отмены удаления новости");
        myObjectControlPanel.CancelButton.perform(click());
    }

    public void ClickEditingButton(){
        Allure.step("Кликнуть по кнопке редактирования новости");
        //ControlPanelElement.EditingButton.check(matches(isDisplayed()));
        myObjectControlPanel.EditingButton.perform(click());
    }

    public void ClickSliderOfStatus(){
        Allure.step("Кликнуть по бегунку статуса");
        myObjectControlPanel.SliderOfStatus.perform(scrollTo(), click());
    }

    public void ClickFilterButton(){
        Allure.step("Кликнуть по кнопке Filter");
        myObjectControlPanel.FilterButton.check(matches(isDisplayed()));
        myObjectControlPanel.FilterButton.perform(click());
    }


    public void ClickFilterButtonStart(){
       Allure.step("Кликнуть по кнопке начать Filter");
        myObjectControlPanel.FilterButtonStart.check(matches(isDisplayed()));
        myObjectControlPanel.FilterButtonStart.perform(click());
    }


    public void ClickCancelFilterButton(){
        Allure.step("Кликнуть по кнопке Cancel Filter");
        myObjectControlPanel.CancelFilterButton.check(matches(isDisplayed()));
        myObjectControlPanel.CancelFilterButton.perform(click());
    }


    public void ClickCheckMarkStatusActiv(){
        Allure.step("убрать галочку у активного статуса");
        myObjectControlPanel.CheckMarkStatusActiv.check(matches(isDisplayed()));
        myObjectControlPanel.CheckMarkStatusActiv.perform(click());
    }


    public void ClickCheckMarkStatusNotActiv(){
        Allure.step("убрать галочку у не активного статуса");
        myObjectControlPanel.CheckMarkStatusNotActiv.check(matches(isDisplayed()));
        myObjectControlPanel.CheckMarkStatusNotActiv.perform(click());
    }




    public void CreateNews(String text1, String text2){
        Allure.step("Cоздать новость");
        ControlPanelPage myObjectControlPanel = new ControlPanelPage();
        //Создать новость
        myObjectControlPanel.ClickAddNews();
        //кликнуть по полю Category
        myObjectControlPanel.ClickCategoryField();
        //Выбрать категорию из списка
        myObjectControlPanel.SelectCategory(text1);
        //Выбрать дату
        myObjectControlPanel.NewsData(futureDate(1));
        //Выбрать время
        myObjectControlPanel.NewsTime(futureTimeMinute(1));
        //Ввести описание новости
        myObjectControlPanel.NewsDiscription(text2);
        //Создать
        myObjectControlPanel.ClickSaveButton();
        //Сортировка
        waitUntilElement(R.id.news_item_title_text_view);
        myObjectControlPanel.ClickSortButton();
    }


}
