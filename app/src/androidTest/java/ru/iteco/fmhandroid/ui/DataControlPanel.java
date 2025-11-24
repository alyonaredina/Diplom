package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ru.iteco.fmhandroid.R;


public class DataControlPanel {


    //Выбор категории из списка
    public static void SelectCategory(String category) {
        onView(withText(category))
                .inRoot(RootMatchers
                        .isPlatformPopup())
                .perform(click());
    }


    //Дата новости
    public static void NewsData(String data){
        onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text))).perform(replaceText(data));
    }
    public static String futureDate(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }
    public static void NewsDataFilterContPanel(String data){
        onView(allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text))).perform(replaceText(data));
    }
    public static String futureDateFilterContPanel(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusMonths(20).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }

    //проверка даты
    public static void CheckDateNews(String date) {
        waitUntilElement(R.id.news_item_publication_date_text_view);
        onView(first(allOf(withId(R.id.news_item_publication_date_text_view))))
                .check(matches(withText(date)));
    }





    //Время новости
    public static void NewsTime(String time) {
        onView(allOf(withId(R.id.news_item_publish_time_text_input_edit_text))).perform(replaceText(time));
    }
    public static String futureTimeMinute(int input) {
        String pattern = "HH:mm";
        String time = LocalTime.now().plusMinutes(input).format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }
    public static void CheckTimeNews(String time) {
        waitUntilElement(R.id.news_item_publish_time_text_input_edit_text);
        onView(first(allOf(withId(R.id.news_item_publish_time_text_input_edit_text))))
                .check(matches(withText(time)));
    }




    //описание новости
    public static void NewsDiscription(String text) {
        waitUntilElement(R.id.news_item_description_text_input_edit_text);
        onView(allOf(withId(R.id.news_item_description_text_input_edit_text))).perform(replaceText(text));
    }
    public static String addTime(int hours, int minutes) {
        return hours + ":" + minutes;
    }
    private static Matcher<View> allOf(Matcher<View> viewMatcher) {
        return viewMatcher;
    }



    //проверка созданной новости
    public static void TitleNews(String text) {
        onView(allOf(withId(R.id.news_item_title_text_view))).check(matches(withText(text)));
    }
    public static void NewsCheck(String text) {
        waitUntilElement(R.id.news_item_title_text_view);
        TitleNews(text);
    }


    //Создание новости
    protected static <T> Matcher<T> first(final Matcher<T> matcher) {
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



