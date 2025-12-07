package ru.iteco.fmhandroid.ui.element;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthorizationElement{

    //Надпись Authorization
    public ViewInteraction AuthorizationTitle = onView(withText("Authorization"));

    //Кнопка входа в приложение
    public ViewInteraction ButtonEnter = onView(withId(R.id.enter_button));

    //Поле ввода логина
    public ViewInteraction LoginInputField = onView(allOf(supportsInputMethods()
            ,isDescendantOfA(withId(R.id.login_text_input_layout))));

    //Поле ввода пароля
    public ViewInteraction PasswordInputField = onView(allOf(supportsInputMethods()
            ,isDescendantOfA(withId(R.id.password_text_input_layout))));
}
