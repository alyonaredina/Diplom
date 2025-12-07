package ru.iteco.fmhandroid.ui.element;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.page.FilterNewsPage.first;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class FilterNewsElement {

    //Поле даты начала поиска
    public ViewInteraction NewsDataFilterStart= onView(allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));

    //Поле даты окончания поиска
    public ViewInteraction NewsDataFilterEnd = onView(allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));

    //Поле ввода категории
    public ViewInteraction CategoryField = onView(Matchers.allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));

    //Заголовок Filter news
    public ViewInteraction FilterNewsTitle = onView(withId(R.id.filter_news_title_text_view));

    //Кнопка выпадающего списка
    public ViewInteraction DropDownListButton = onView(Matchers.allOf(withId(R.id.text_input_end_icon),
                    withContentDescription("Show dropdown menu")));

    //Кнопка Filter
    public ViewInteraction FilterButton = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter")));

    //Новости для проверки фильтрации
    public ViewInteraction NewsForFilter = onView(first(withId(R.id.news_item_category_text_view)));
    //Нет новостей для проверки фильтрации
    public ViewInteraction NoNewsForFilter = onView(allOf(withId(R.id.empty_news_list_text_view),
            withText("There is nothing here yet…")));

    //Ошибка при не заполненном поле даты
    public ViewInteraction ErrorWrongPeriod = onView(
            Matchers.allOf(withId(android.R.id.message), withText("Wrong period"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class)))));

}
