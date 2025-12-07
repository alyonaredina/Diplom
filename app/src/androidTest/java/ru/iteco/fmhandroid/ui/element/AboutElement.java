package ru.iteco.fmhandroid.ui.element;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class AboutElement {

    //Надпись Version
    public ViewInteraction VersionTitle = onView(Matchers.allOf(
            withId(R.id.about_version_title_text_view), withText("Version:"),withParent(
                    withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))));

    //ссылка Privacy Policy
    public ViewInteraction LinkPrivacyPolicy = onView(allOf(withId(R.id.about_privacy_policy_value_text_view),
            withText("https://vhospice.org/#/privacy-policy/"),
            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))));

    //ссылка Terms Of Use
    public ViewInteraction LinkTermsOfUse = onView(allOf(withId(R.id.about_terms_of_use_value_text_view),
            withText("https://vhospice.org/#/terms-of-use"),
            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))));

    //Кнопка перехода на главную страницу
    public ViewInteraction GoToMainPageButton = onView(Matchers.allOf(withId(R.id.about_back_image_button)));
}
