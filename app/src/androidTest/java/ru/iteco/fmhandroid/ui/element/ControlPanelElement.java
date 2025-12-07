package ru.iteco.fmhandroid.ui.element;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.page.FilterNewsPage.first;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class ControlPanelElement {

    //Дата новости
    public ViewInteraction NewsDateElement = onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));
    /*public static void NewsDataFilterContPanel(String data){
        onView(allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text))).perform(replaceText(data));
    }
    public static String futureDateFilterContPanel(int input) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusMonths(20).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }*/
    //Дата для проверки
    public ViewInteraction DateNews = onView(first(allOf(withId(R.id.news_item_publication_date_text_view))));


    //Время новости
    public ViewInteraction NewsTimeElement = onView(allOf(withId(R.id.news_item_publish_time_text_input_edit_text)));
    //Время для проверки
    public ViewInteraction TimeNews = onView(first(allOf(withId(R.id.news_item_publish_time_text_input_edit_text))));


    //описание новости
    public ViewInteraction NewsDiscriptionElement = onView(allOf(withId(R.id.news_item_description_text_input_edit_text)));

    //Для проверки созданной новости
    public ViewInteraction TitleNewsElement = onView(allOf(withId(R.id.news_item_title_text_view)));


    //Надпись Control panel
    public ViewInteraction ControlPanelTitle = onView(Matchers.allOf(withText("Control panel"),
            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))));

    //Текст описания новости
    public ViewInteraction TextOfNewsDescription = onView(
            Matchers.allOf(withId(R.id.news_item_description_text_view), withText("Новый день"),
                    withParent(withParent(withId(R.id.news_item_material_card_view)))));

    //Текст категории
    public ViewInteraction TextOfCategory = onView(
            Matchers.allOf(withId(R.id.news_item_title_text_view), withText("Праздник"),
                    withParent(withParent(withId(R.id.news_item_material_card_view)))));

    //Текст категории
    public ViewInteraction TextOfCategoryMersi = onView(
            Matchers.allOf(withId(R.id.news_item_title_text_view), withText("Благодарность"),
                    withParent(withParent(withId(R.id.news_item_material_card_view)))));

    //Сообщение что нет новостей
    public ViewInteraction NoNews = onView(
            Matchers.allOf(withId(R.id.control_panel_empty_news_list_text_view), withText("There is nothing here yet…"),
                    withParent(withParent(withId(R.id.news_control_panel_swipe_to_refresh)))));

    //Текст заглавия
    public ViewInteraction TextOfTitles = onView(allOf(withId(R.id.news_item_title_text_input_edit_text)));

    //Текст cообщения об удалении новости
    public ViewInteraction TextOfDeleteNews = onView(allOf(withId(android.R.id.message),
            withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future."),
            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class)))));

    //Поле Категория
    public ViewInteraction CategoryField = onView(allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                    withContentDescription("Show dropdown menu")));

    //Кнопка Добавить новость
    public ViewInteraction AddNewsButton = onView(Matchers.allOf(withId(R.id.add_news_image_view)));

    //Кнопка Save
    public ViewInteraction SaveButton = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));

    //Кнопка сортировки
    public ViewInteraction SortButton = onView(Matchers.allOf(withId(R.id.sort_news_material_button)));

    //Кнопка развернуть новость
    public ViewInteraction UnfurlNewsButton = onView(withId(R.id.news_list_recycler_view));

    //Кнопка свернуть новость
    public ViewInteraction RollUpNewsButton = onView(withId(R.id.news_list_recycler_view));

    //Кнопка удалить новость
    public ViewInteraction DeleteNews = onView(first(allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button"),
            childAtPosition(childAtPosition(withId(R.id.news_item_material_card_view),
                    0),14))));

    //Кнопка cогласие удалить новость
    public ViewInteraction OkDeleteNews = onView(allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")),
                    0),3)));

    //Кнопка отмена удаления
    public ViewInteraction CancelButton = onView(
            Matchers.allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")),
                            0),2)));

    //Кнопка редактирования
    public ViewInteraction EditingButton = onView(first(allOf(
            withId(R.id.edit_news_item_image_view),
            withContentDescription("News editing button"))));
            /*onView(
            allOf(withId(R.id.edit_news_item_image_view), withContentDescription("News editing button"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.news_item_material_card_view),
                                    0),
                            15)));*/

    //Кнопка раздела Filter
    public ViewInteraction FilterButton = onView(Matchers.allOf(withId(R.id.filter_news_material_button)));

    //Кнопка Filter
    public ViewInteraction FilterButtonStart = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter")));

    //Кнопка Cancel Filter
    public ViewInteraction CancelFilterButton = onView(Matchers.allOf(withId(R.id.cancel_button),
            withText("Cancel")));

    //Бегунок статуса ACTIVE
    public ViewInteraction SliderOfStatus = onView(Matchers.allOf(withId(R.id.switcher), withText("Active")));


    //Статус
    public ViewInteraction Status = onView(
            Matchers.allOf(withId(R.id.news_item_published_text_view), withText("NOT ACTIVE"),
                    withParent(withParent(withId(R.id.news_item_material_card_view)))));

    //Галочка статуса Activ
    public ViewInteraction CheckMarkStatusActiv = onView(Matchers.allOf(withId(R.id.filter_news_active_material_check_box),
            withText("Active")));

    //Галочка статуса NotActiv
    public ViewInteraction CheckMarkStatusNotActiv = onView(Matchers.allOf(withId(R.id.filter_news_inactive_material_check_box),
            withText("Not active")));













    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    /*//Создание новости
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
    }*/


}






