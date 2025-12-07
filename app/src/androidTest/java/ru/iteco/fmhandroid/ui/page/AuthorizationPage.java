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

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.element.AuthorizationElement;


public class AuthorizationPage extends Data {
    AuthorizationElement myObjectAuthorization = new AuthorizationElement();

    public void checkAuthorizationTitle() {
        Allure.step("Отображение надписи Authorization");
        waitUntilElement("Authorization");
        //waitUntilElement(R.id.login_text_input_layout);
        myObjectAuthorization.AuthorizationTitle.check(matches(isDisplayed()));
    }

    public void aPop_upError(String text, View decorView) {
        Allure.step("Отображение ошибки входа");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void fillingInTheLoginField(String input){
        Allure.step("Заполнение поля ввода логина");
        myObjectAuthorization.LoginInputField.perform(click());
        myObjectAuthorization.LoginInputField.perform(replaceText(input)/*,closeSoftKeyboard()*/);
        myObjectAuthorization.LoginInputField.check(matches(isDisplayed()));
    }

    public void fillingInThePasswordField(String input){
        Allure.step("Заполнение поля ввода пароля");
        myObjectAuthorization.PasswordInputField.perform(replaceText(input)/*,closeSoftKeyboard()*/);
        myObjectAuthorization.PasswordInputField.check(matches(isDisplayed()));
    }

    public void clickButtonEnter(){
        Allure.step("Нажатие кнопки входа");
        myObjectAuthorization.ButtonEnter.check(matches(isDisplayed()));
        myObjectAuthorization.ButtonEnter.perform(click());
    }
}
