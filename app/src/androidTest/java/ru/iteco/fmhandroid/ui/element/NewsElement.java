package ru.iteco.fmhandroid.ui.element;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class NewsElement {
    //Кнопка REFRESH
    public ViewInteraction RefreshButton = onView(withId(R.id.news_retry_material_button));

    //Кнопка перехода в фильтрацию новостей
    public ViewInteraction GoToFilterButton = onView(withId(R.id.filter_news_material_button));

    //Кнопка перехода в создание новостей
    public ViewInteraction EditNewsButton = onView(withId(R.id.edit_news_material_button));

    //Кнопка сортировки новостей
    public ViewInteraction SortNewsButton = onView(withId(R.id.sort_news_material_button));


}
