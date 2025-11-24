package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
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

public class MainTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    //Тестовые данные страницы
    public String titleAuthorization = "Authorization";
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String titleAllNews = "ALL NEWS";

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
    @Step("Main.Развернуть и свернуть новость")
    public void id10CollapseAndExpandTheLatestNews(){
        waitUntilElement(R.id.all_news_text_view);
        ViewInteraction materialButton = onView(withId(R.id.expand_material_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(withId(R.id.expand_material_button));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView = onView(withId(R.id.all_news_text_view));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText(titleAllNews)));
    }

    @Test
    @Step("Main.Перейти на страницу News")
    public void id11GoToTheNewsPage(){
        waitUntilElement(R.id.all_news_text_view);
        ViewInteraction materialTextView = onView(withId(R.id.all_news_text_view));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction compoundButton = onView(withId(R.id.sort_news_material_button));
        compoundButton.check(matches(isDisplayed()));
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
