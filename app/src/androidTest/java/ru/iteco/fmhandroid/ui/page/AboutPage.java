package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.element.AboutElement;

public class AboutPage {

    @Step("Проверка отображения надписи Version")
    public static void checkDisplayingTheVersionLabel(){
        AboutElement.VersionTitle.check(matches(isDisplayed()));
        AboutElement.VersionTitle.check(matches(withText("Version:")));
    }

    @Step("Кликаем по ссылке Privacy Policy")
    public static void clickLinkPrivacyPolicy(){
        AboutElement.LinkPrivacyPolicy.perform(click());
    }

    @Step("Кликаем по ссылке Terms Of Use")
    public static void clickLinkTermsOfUse(){
        AboutElement.LinkTermsOfUse.check(matches(isDisplayed()));
        AboutElement.LinkTermsOfUse.perform(click());
    }

    @Step("Кликнуть по кнопке перехода на главную страницу")
    public static void clickGoToMainPageButton(){
        AboutElement.GoToMainPageButton.check(matches(isDisplayed()));
        AboutElement.GoToMainPageButton.perform(click());
    }
}
