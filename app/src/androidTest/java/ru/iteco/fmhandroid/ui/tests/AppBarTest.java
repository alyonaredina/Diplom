package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AppBarTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    //Тестовые данные страницы
    public String titleAuthorization = "Authorization";
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String titleAllNews = "ALL NEWS";
    public String news = "News";
    public String main = "Main";
    public String version = "Version:";
    public String about = "About";
    public String loveIsAll = "Love is all";


    @Before
    public void authorizationTestBefore() {
        try {
            waitUntilElement(R.id.login_text_input_layout); //таймаут

            ViewInteraction textView = onView(
                    allOf(withText(titleAuthorization),
                            withParent(withParent(withId(R.id.nav_host_fragment))),
                            isDisplayed()));
            textView.check(matches(withText(titleAuthorization)));

            ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
            textInputEditText.check(matches(isDisplayed()));
            textInputEditText.perform(replaceText(validLogin), closeSoftKeyboard());


            ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
            textInputEditText2.check(matches(isDisplayed()));
            textInputEditText2.perform(replaceText(validPassword), closeSoftKeyboard());

            ViewInteraction materialButton = onView(withId(R.id.enter_button));
            materialButton.check(matches(isDisplayed()));
            materialButton.perform(click());

            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS

            ViewInteraction textView2 = onView(withId(R.id.all_news_text_view));
            textView2.check(matches(isDisplayed()));
            textView2.check(matches(withText(titleAllNews)));

        } catch (androidx.test.espresso.PerformException e) {
            ViewInteraction appCompatImageButton = onView(Matchers.allOf(withId(R.id.authorization_image_button)));
            appCompatImageButton.check(matches(isDisplayed()));
            appCompatImageButton.perform(click());

            waitUntilElement(android.R.id.title);

            ViewInteraction materialTextView = onView(withId(android.R.id.title));
            //materialTextView.check(matches(isDisplayed()));
            materialTextView.perform(click());

            ViewInteraction textView = onView(
                    allOf(withText(titleAuthorization),
                            withParent(withParent(withId(R.id.nav_host_fragment))),
                            isDisplayed()));
            textView.check(matches(withText(titleAuthorization)));

            ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
            textInputEditText.check(matches(isDisplayed()));
            textInputEditText.perform(replaceText(validLogin), closeSoftKeyboard());


            ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
            textInputEditText2.check(matches(isDisplayed()));
            textInputEditText2.perform(replaceText(validPassword), closeSoftKeyboard());

            ViewInteraction materialButton = onView(withId(R.id.enter_button));
            materialButton.check(matches(isDisplayed()));
            materialButton.perform(click());

            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS

            ViewInteraction textView2 = onView(withId(R.id.all_news_text_view));
            textView2.check(matches(isDisplayed()));
            textView2.check(matches(withText(titleAllNews)));
        }
    }

    @Test
    @Step("AppBar.Перейти на страницу Main")
    public void id59GoToTheMainPage(){
        //кликнуть по ALL NEWS
        ViewInteraction materialTextView = onView(
                Matchers.allOf(withId(R.id.all_news_text_view), withText(titleAllNews),isDisplayed()));
        materialTextView.perform(click());

        //открыть меню
        ViewInteraction appCompatImageButton = onView(
                Matchers.allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),isDisplayed()));
        appCompatImageButton.perform(click());

        //выбрать Main
        ViewInteraction materialTextView2 = onView(
                Matchers.allOf(withId(android.R.id.title), withText(main),isDisplayed()));
        materialTextView2.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.all_news_text_view), withText(titleAllNews),
                        withParent(Matchers.allOf(withId(R.id.container_list_news_include_on_fragment_main),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText(titleAllNews)));
    }

    @Test
    @Step("AppBar.Перейти на страницу News")
    public void id60GoToTheNewsPage(){
        //открыть меню
        ViewInteraction appCompatImageButton = onView(
                Matchers.allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),isDisplayed()));
        appCompatImageButton.perform(click());

        //выбрать News
        ViewInteraction materialTextView = onView(Matchers.allOf(withId(android.R.id.title), withText(news),isDisplayed()));
        materialTextView.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withText("News"),
                        withParent(withParent(withId(R.id.container_list_news_include))),
                        isDisplayed()));
        textView.check(matches(withText(news)));
    }

    @Test
    @Step("AppBar.Перейти на страницу About")
    public void id61GoToTheAboutPage(){
        //открыть меню
        ViewInteraction appCompatImageButton = onView(
                Matchers.allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),isDisplayed()));
        appCompatImageButton.perform(click());

        //выбрать About
        ViewInteraction materialTextView = onView(Matchers.allOf(withId(android.R.id.title),
                withText(about),isDisplayed()));
        materialTextView.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.about_version_title_text_view), withText("Version:"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText(version)));
    }

    @Test
    @Step("AppBar.Переход по страницам")
    public void id72ERRORPageNavigation(){
        //открыть меню
        ViewInteraction appCompatImageButton = onView(
                Matchers.allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),isDisplayed()));
        appCompatImageButton.perform(click());

        //выбрать News
        ViewInteraction materialTextView = onView(Matchers.allOf(withId(android.R.id.title), withText(news),isDisplayed()));
        materialTextView.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withText("News"),
                        withParent(withParent(withId(R.id.container_list_news_include))),
                        isDisplayed()));
        textView.check(matches(withText(news)));

        //открыть меню
        ViewInteraction appCompatImageButton2 = onView(
                Matchers.allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),isDisplayed()));
        appCompatImageButton2.perform(click());

        //выбрать About
        ViewInteraction materialTextView2 = onView(Matchers.allOf(withId(android.R.id.title),
                withText(about),isDisplayed()));
        materialTextView2.perform(click());

        //проверка
        ViewInteraction textView2 = onView(
                Matchers.allOf(withId(R.id.about_version_title_text_view), withText("Version:"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView2.check(matches(withText(version)));


    }

    @Test
    @Step("AppBar.Перейти на страницу Love is all")
    public void id62GoToTheLoveIsAllPage(){
        //кликнуть по значку бабочки
        ViewInteraction appCompatImageButton = onView(Matchers.allOf(withId(R.id.our_mission_image_button),
                        withContentDescription("Our Mission"),isDisplayed()));
        appCompatImageButton.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.our_mission_title_text_view), withText(loveIsAll),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText(loveIsAll)));
    }

    @Test
    @Step("AppBar. Развернуть и свернуть цитату на странице Love is all")
    public void id63GoToTheLoveIsAllPage(){
        //кликнуть по значку бабочки
        ViewInteraction appCompatImageButton = onView(Matchers.allOf(withId(R.id.our_mission_image_button),
                withContentDescription("Our Mission"),isDisplayed()));
        appCompatImageButton.perform(click());

        //развернуть цитату
        ViewInteraction recyclerView3 = onView(
                Matchers.allOf(withId(R.id.our_mission_item_list_recycler_view)));
        recyclerView3.perform(actionOnItemAtPosition(3, click()));

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.our_mission_item_description_text_view), withText("“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \nБи Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\""),
                        withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \nБи Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\"")));

        //свернуть цитату
        ViewInteraction recyclerView4 = onView(
                Matchers.allOf(withId(R.id.our_mission_item_list_recycler_view)));
        recyclerView4.perform(actionOnItemAtPosition(3, click()));

        //проверка
        ViewInteraction textView2 = onView(
                Matchers.allOf(withId(R.id.our_mission_item_description_text_view), withText("“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \nБи Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\""),
                        withParent(withParent(withId(R.id.our_mission_item_material_card_view))),isDisplayed()));
        textView2.check(doesNotExist());



    }

    @Test
    @Step("AppBar. Выйти из учетной записи")
    public void id64Exit(){
        //кликнуть по значку человека
        ViewInteraction appCompatImageButton = onView(Matchers.allOf(withId(R.id.authorization_image_button),
                withContentDescription("Authorization"),isDisplayed()));
        appCompatImageButton.perform(click());

        //кликнуть по Log out
        ViewInteraction materialTextView = onView(
                Matchers.allOf(withId(android.R.id.title), withText("Log out"),isDisplayed()));
        materialTextView.perform(click());

        //проверка
        ViewInteraction textView = onView(Matchers.allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),isDisplayed()));
        textView.check(matches(withText(titleAuthorization)));
    }



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
}
