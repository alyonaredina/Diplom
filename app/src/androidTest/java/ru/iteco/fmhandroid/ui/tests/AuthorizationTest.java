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

import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.DataАuthorization;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthorizationTest {
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    //Тестовые данные страницы
    public String titleAuthorization = "Authorization";
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String titleAllNews = "ALL NEWS";
    public String invalidPassword = "password";
    public String invalidLogin = "login";
    public String invalidPassword3 = "password3";
    public String invalidLogin3 = "login3";
    public String EmptyFieldLogin = "";
    public String EmptyFieldPassword = "";
    public String SpecialCharacterLogin = "login#";
    public String SpecialCharacterPassword = "password#";
    public String capitalLetterLogin = "Login1";
    public String capitalLetterPassword = "Password1";
    public String errorEnter = "Something went wrong. Try again later.";
    public String errorEnterEmptyField = "Login and password cannot be empty";

    @Before
    public void authorizationTestBefore() {
        /*waitUntilElement(R.id.login_text_input_layout); //таймаут

        ViewInteraction textView = onView(
                allOf(withText(titleAuthorization),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView.check(matches(withText(titleAuthorization)));*/

        try {
            waitUntilElement(R.id.login_text_input_layout); //таймаут

            ViewInteraction textView = onView(
                    allOf(withText(titleAuthorization),
                            withParent(withParent(withId(R.id.nav_host_fragment))),
                            isDisplayed()));
            textView.check(matches(withText(titleAuthorization)));
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
        }

        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }


    @Test
    @Step("Авторизация.Валидные логин и пароль")
    public void id1ValidUsernameAndPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

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

        ViewInteraction textView = onView(withId(R.id.all_news_text_view));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText(titleAllNews)));

        waitUntilElement(R.id.authorization_image_button);

        ViewInteraction appCompatImageButton = onView(Matchers.allOf(withId(R.id.authorization_image_button)));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());

        waitUntilElement(android.R.id.title);

        ViewInteraction materialTextView = onView(withId(android.R.id.title));
        materialTextView.perform(click());
    }

    @Test
    @Step("Авторизация.Валидный логин и не валидный пароль")
    public void id2ValidUsernameAndInvalidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(validLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(invalidPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Step("Авторизация.Не валидный логин и валидный пароль")
    public void id3InvalidUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(invalidLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(validPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Step("Авторизация.Не валидный логин и не валидный пароль")
    public void id4InvalidUsernameAndInvalidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(invalidLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(invalidPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Step("Авторизация.Без ввода логина и пароля")
    public void id5EmptyFieldUsernameAndEmptyFieldPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(EmptyFieldLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(EmptyFieldPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnterEmptyField, decorView);
    }

    @Test
    @Step("Авторизация.Ввод спец символа в поле логин")
    public void id6SpecialCharacterFieldUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(SpecialCharacterLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(validPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Step("Авторизация.Ввод спец символа в поле пароль")
    public void id7ValidUsernameAndSpecialCharacterFieldPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(validLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(SpecialCharacterPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Step("Авторизация.Ввод логина с заглавной буквы")
    public void id8CapitalLetterUsernameAndValidPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(capitalLetterLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(validPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Step("Авторизация.Ввод пароля с заглавной буквы")
    public void id9ValidUsernameAndCapitalLetterPassword(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(validLogin), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(capitalLetterPassword), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }

    @Test
    @Step("Авторизация.ОШИБКА:не валидные логин и пароль")
    public void id4InvalidUsernameAndInvalidPassword_ERROR(){
        waitUntilElement(R.id.login_text_input_layout); //ждем загрузгу страницы Аuthorization

        ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(invalidLogin3), closeSoftKeyboard());


        ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText(invalidPassword3), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        DataАuthorization.aPop_upError(errorEnter, decorView);
    }



    /*@After
    public void authorizationTestAfter(){
        waitUntilElement(R.id.authorization_image_button);

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
    }*/


    public static Matcher<View> childAtPosition(
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
