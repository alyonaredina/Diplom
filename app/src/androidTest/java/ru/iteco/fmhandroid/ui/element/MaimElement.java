package ru.iteco.fmhandroid.ui.element;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class MaimElement {

    //Надпись All News
    public ViewInteraction ALLNEWSTitle = onView(withId(R.id.all_news_text_view));

    //Надпись News
    public ViewInteraction NewsTitle = onView(Matchers.allOf(withText("News"),
            withParent(withParent(withId(R.id.container_list_news_include)))));

    //Cтрелка развернуть список новостей
    public ViewInteraction ArrowExpandNewsList = onView(withId(R.id.expand_material_button));

    //Cтрелка cвернуть список новостей
    public ViewInteraction BreakNewsListArrow = onView(withId(R.id.expand_material_button));
}
