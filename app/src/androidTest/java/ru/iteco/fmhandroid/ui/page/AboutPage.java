package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.element.AboutElement;

public class AboutPage {
    AboutElement myObjectAbout = new AboutElement();

    public void checkDisplayingTheVersionLabel(){
        Allure.step("Проверка отображения надписи Version");
        myObjectAbout.VersionTitle.check(matches(isDisplayed()));
        myObjectAbout.VersionTitle.check(matches(withText("Version:")));
    }

    public void clickLinkPrivacyPolicy(){
        Allure.step("Кликаем по ссылке Privacy Policy");
        myObjectAbout.LinkPrivacyPolicy.perform(click());
    }

    public void clickLinkTermsOfUse(){
        Allure.step("Кликаем по ссылке Terms Of Use");
        myObjectAbout.LinkTermsOfUse.check(matches(isDisplayed()));
        myObjectAbout.LinkTermsOfUse.perform(click());
    }

    public void clickGoToMainPageButton(){
        Allure.step("Кликнуть по кнопке перехода на главную страницу");
        myObjectAbout.GoToMainPageButton.check(matches(isDisplayed()));
        myObjectAbout.GoToMainPageButton.perform(click());
    }
}
