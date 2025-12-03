package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.element.FilterNewsElement;

public class FilterNewsPage {

    //Дата новости
    @Step("Выбор даты начала поиска")
    public static void SelectNewsDataFilterStart(String data){
        FilterNewsElement.NewsDataFilterStart.perform(replaceText(data));
    }
    @Step("Выбор даты окончания поиска")
    public static void SelectNewsDataFilterEnd(String data){
        FilterNewsElement.NewsDataFilterEnd.perform(replaceText(data));
    }
    @Step("текущая дата")
    public static String futureDateDays(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusDays(input).format(DateTimeFormatter
                .ofPattern(pattern));
        return date;
    }


    @Step("Проверка отображения надписи Filter news")
    public static void checkDisplayingFilterNewsLabel(){
        FilterNewsElement.FilterNewsTitle.check(matches(isDisplayed()));
        FilterNewsElement.FilterNewsTitle.check(matches(withText("Filter news")));
    }

    @Step("Отображение ошибки не заполненного поля даты")
    public static void checkDisplayingErrorWrongPeriod(){
        FilterNewsElement.ErrorWrongPeriod.check(matches(isDisplayed()));
        FilterNewsElement.ErrorWrongPeriod.check(matches(withText("Wrong period")));
    }

    @Step("Кликнуть по кнопке выпадающего списка")
    public static void clickDropDownListButton(){
        FilterNewsElement.DropDownListButton.check(matches(isDisplayed()));
        FilterNewsElement.DropDownListButton.perform(click());
    }

    @Step("Кликнуть по нопке Filter")
    public static void clickFilterButton(){
        FilterNewsElement.FilterButton.check(matches(isDisplayed()));
        FilterNewsElement.FilterButton.perform(click());
    }

    @Step("Проверка фильтрации.Есть новость")
    public static void checkFilter() {
        waitUntilElement(R.id.container_list_news_include);
        waitUntilElement(R.id.news_item_category_text_view);
        FilterNewsElement.NewsForFilter.check(matches(isEnabled()));
    }
    @Step("Проверка фильтрации.Нет новостей")
    public static void noNews() {
        waitUntilElement(R.id.empty_news_list_text_view);
        FilterNewsElement.NoNewsForFilter.check(matches(isDisplayed()));
    }

    @Step("Изменить текст в поле Категория")
    public static void changeTextCategory(String category){
        FilterNewsElement.CategoryField.check(matches(isDisplayed()));
        FilterNewsElement.CategoryField.perform(replaceText(category), closeSoftKeyboard());
    }



    //Создание новости
    public static <T> Matcher<T> first(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isFirst = true;

            @Override
            public boolean matches(final Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return first matching item");
            }
        };
    }
}
