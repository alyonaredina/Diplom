package ru.iteco.fmhandroid.ui.element;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class AppBarElement {

    //Кнопка Меню
    public ViewInteraction MenuButton = onView(Matchers.allOf(withId(R.id.main_menu_image_button),
                    withContentDescription("Main menu")));

    //Кнопка выхода из приложения
    public ViewInteraction Exit = onView(withId(android.R.id.title));

    //Кнопка Развернуть цитату
    public ViewInteraction ExpandTheQuoteButton = onView(
            Matchers.allOf(withId(R.id.our_mission_item_list_recycler_view)));

    //Кнопка Свернуть цитату
    public ViewInteraction RollUpTheQuoteButton = onView(
            Matchers.allOf(withId(R.id.our_mission_item_list_recycler_view)));

    //Строка Main в выпадающем списке
    public ViewInteraction LineMain = onView(
            Matchers.allOf(withId(android.R.id.title), withText("Main")));

    //Строка News в выпадающем списке
    public ViewInteraction LineNews = onView(Matchers.allOf(withId(android.R.id.title),
            withText("News")));

    //Строка About в выпадающем списке
    public ViewInteraction LineAbout = onView(Matchers.allOf(withId(android.R.id.title),
            withText("About")));

    //Надпись Love is all
    public ViewInteraction LoveIsAllTitle = onView(Matchers.allOf(
            withId(R.id.our_mission_title_text_view), withText("Love is all"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))));

    //Значек бабочки
    public ViewInteraction ButterflyBadge = onView(Matchers.allOf(
            withId(R.id.our_mission_image_button),withContentDescription("Our Mission")));

    //Значек человечка на панеле App bar
    public ViewInteraction ManButton = onView(Matchers.allOf(withId(R.id.authorization_image_button)));

    //Цитата
    public ViewInteraction Quote = onView(
            Matchers.allOf(withId(R.id.our_mission_item_description_text_view), withText("“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \nБи Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\""),
                    withParent(withParent(withId(R.id.our_mission_item_material_card_view)))));

}
