package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.intent.Intents;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
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

public class AboutTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    //Тестовые данные страницы
    public String titleAuthorization = "Authorization";
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String titleAllNews = "ALL NEWS";
    public String version = "Version:";
    public String about = "About";

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

            //открыть меню
            ViewInteraction appCompatImageButton = onView(
                    Matchers.allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),isDisplayed()));
            appCompatImageButton.perform(click());

            //выбрать About
            ViewInteraction materialTextView = onView(Matchers.allOf(withId(android.R.id.title),
                    withText(about),isDisplayed()));
            materialTextView.perform(click());

            ViewInteraction textView2 = onView(
                    Matchers.allOf(withId(R.id.about_version_title_text_view), withText("Version:"),
                            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                            isDisplayed()));
            textView2.check(matches(withText(version)));
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

            //открыть меню
            ViewInteraction appCompatImageButton2 = onView(
                    Matchers.allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),isDisplayed()));
            appCompatImageButton2.perform(click());

            //выбрать About
            ViewInteraction materialTextView2 = onView(Matchers.allOf(withId(android.R.id.title),
                    withText(about),isDisplayed()));
            materialTextView2.perform(click());

            ViewInteraction textView2 = onView(
                    Matchers.allOf(withId(R.id.about_version_title_text_view), withText("Version:"),
                            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                            isDisplayed()));
            textView2.check(matches(withText(version)));
        }
    }

    @Test
    @Step("About. Переход по ссылке Privacy Policy")
    public void id65followingTheLinkPrivacyPolicy(){
        //включаем прослушку
        Intents.init();

        //проверяем переход по ссылке
        onView(allOf(withId(R.id.about_privacy_policy_value_text_view), withText("https://vhospice.org/#/privacy-policy/"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))))).perform(click());
        intended(hasData("https://vhospice.org/#/privacy-policy/"));

        //очищаем прослушку
        Intents.release();
    }

    @Test
    @Step("About. Переход по ссылке Terms Of Use")
    public void id65followingTheLinkTermsOfUse(){
        //включаем прослушку
        Intents.init();

        //проверяем переход по ссылке
        onView(allOf(withId(R.id.about_terms_of_use_value_text_view), withText("https://vhospice.org/#/terms-of-use"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed())).perform(click());
        intended(hasData("https://vhospice.org/#/terms-of-use"));

        //очищаем прослушку
        Intents.release();
    }

    @Test
    @Step("About. Возврат на главную страницу")
    public void id66ReturnToTheMainPage(){
        //кликнуть по стрелочке на App bar
        ViewInteraction appCompatImageButton2 = onView(
                Matchers.allOf(withId(R.id.about_back_image_button),isDisplayed()));
        appCompatImageButton2.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.all_news_text_view), withText("ALL NEWS"),
                        withParent(Matchers.allOf(withId(R.id.container_list_news_include_on_fragment_main),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText(titleAllNews)));
    }
}
