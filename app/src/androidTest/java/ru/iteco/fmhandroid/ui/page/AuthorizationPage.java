package ru.iteco.fmhandroid.ui.page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.element.AuthorizationElement;


public class AuthorizationPage extends Data {
    @Step ("Отображение надписи Authorization")
    public static void checkAuthorizationTitle() {
        waitUntilElement("Authorization");
        //waitUntilElement(R.id.login_text_input_layout);
        AuthorizationElement.AuthorizationTitle.check(matches(isDisplayed()));
    }

    @Step("Отображение ошибки входа")
    public static void aPop_upError(String text, View decorView) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Step("Заполнение поля ввода логина")
    public static void fillingInTheLoginField(String input){
        AuthorizationElement.LoginInputField.perform(click());
        AuthorizationElement.LoginInputField.perform(replaceText(input)/*,closeSoftKeyboard()*/);
        AuthorizationElement.LoginInputField.check(matches(isDisplayed()));
    }

    @Step("Заполнение поля ввода пароля")
    public static void fillingInThePasswordField(String input){
        AuthorizationElement.PasswordInputField.perform(replaceText(input)/*,closeSoftKeyboard()*/);
        AuthorizationElement.PasswordInputField.check(matches(isDisplayed()));
    }

    @Step("Нажатие кнопки входа")
    public static void clickButtonEnter(){
        AuthorizationElement.ButtonEnter.check(matches(isDisplayed()));
        AuthorizationElement.ButtonEnter.perform(click());
    }
}
