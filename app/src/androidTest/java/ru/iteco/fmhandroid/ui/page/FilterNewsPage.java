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

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.element.FilterNewsElement;

public class FilterNewsPage {
    FilterNewsElement myObjectFilterNews = new FilterNewsElement();

    //Дата новости
    public void SelectNewsDataFilterStart(String data){
        Allure.step("Выбор даты начала поиска");
        myObjectFilterNews.NewsDataFilterStart.perform(replaceText(data));
    }

    public void SelectNewsDataFilterEnd(String data){
        Allure.step("Выбор даты окончания поиска");
        myObjectFilterNews.NewsDataFilterEnd.perform(replaceText(data));
    }

    public String futureDateDays(int input) {
        Allure.step("текущая дата");
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusDays(input).format(DateTimeFormatter
                .ofPattern(pattern));
        return date;
    }



    public void checkDisplayingFilterNewsLabel(){
        Allure.step("Проверка отображения надписи Filter news");
        myObjectFilterNews.FilterNewsTitle.check(matches(isDisplayed()));
        myObjectFilterNews.FilterNewsTitle.check(matches(withText("Filter news")));
    }


    public void checkDisplayingErrorWrongPeriod(){
        Allure.step("Отображение ошибки не заполненного поля даты");
        myObjectFilterNews.ErrorWrongPeriod.check(matches(isDisplayed()));
        myObjectFilterNews.ErrorWrongPeriod.check(matches(withText("Wrong period")));
    }


    public void clickDropDownListButton(){
        Allure.step("Кликнуть по кнопке выпадающего списка");
        myObjectFilterNews.DropDownListButton.check(matches(isDisplayed()));
        myObjectFilterNews.DropDownListButton.perform(click());
    }


    public void clickFilterButton(){
        Allure.step("Кликнуть по нопке Filter");
        myObjectFilterNews.FilterButton.check(matches(isDisplayed()));
        myObjectFilterNews.FilterButton.perform(click());
    }


    public void checkFilter() {
        Allure.step("Проверка фильтрации.Есть новость");
        waitUntilElement(R.id.container_list_news_include);
        waitUntilElement(R.id.news_item_category_text_view);
        myObjectFilterNews.NewsForFilter.check(matches(isEnabled()));
    }

    public void noNews() {
        Allure.step("Проверка фильтрации.Нет новостей");
        waitUntilElement(R.id.empty_news_list_text_view);
        myObjectFilterNews.NoNewsForFilter.check(matches(isDisplayed()));
    }


    public void changeTextCategory(String category){
        Allure.step("Изменить текст в поле Категория");
        myObjectFilterNews.CategoryField.check(matches(isDisplayed()));
        myObjectFilterNews.CategoryField.perform(replaceText(category), closeSoftKeyboard());
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
