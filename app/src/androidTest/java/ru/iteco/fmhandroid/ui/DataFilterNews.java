package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ru.iteco.fmhandroid.R;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.DataControlPanel.first;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;


import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class DataFilterNews {

    //Дата новости
    public static void NewsDataFilterStart(String data){
        onView(allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text))).perform(replaceText(data));
    }
    public static void NewsDataFilterEnd(String data){
        onView(allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text))).perform(replaceText(data));
    }
    public static String futureDate0Days(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusDays(0).format(DateTimeFormatter
                .ofPattern(pattern));
        return date;
    }
    public static String futureDate7Days(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusDays(7).format(DateTimeFormatter
                .ofPattern(pattern));
        return date;
    }
    public static String futureDate14Days(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusDays(14).format(DateTimeFormatter
                .ofPattern(pattern));
        return date;
    }


    //Проверка фильтрации
    public static void checkFilter() {
        waitUntilElement(R.id.container_list_news_include);
        waitUntilElement(R.id.news_item_category_text_view);
        onView(first(withId(R.id.news_item_category_text_view))).check(matches(isEnabled()));
    }

    public static void noNews() {
        waitUntilElement(R.id.empty_news_list_text_view);
        onView(allOf(withId(R.id.empty_news_list_text_view),
                        withText("There is nothing here yet…"))).check(matches(isDisplayed()));
    }
}
