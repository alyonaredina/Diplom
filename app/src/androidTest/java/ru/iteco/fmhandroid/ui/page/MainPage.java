package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.element.MaimElement;

public class MainPage {

    @Step("Нажатие на кнопку ALL NEWS")
    public static void clickAllNews(){
        MaimElement.ALLNEWSTitle.check(matches(isDisplayed()));
        MaimElement.ALLNEWSTitle.perform(click());
    }

    @Step("Нажатие на кнопку развернуть новости")
    public static void clickArrowExpandNewsList(){
        waitUntilElement(R.id.all_news_text_view);
        MaimElement.ArrowExpandNewsList.check(matches(isDisplayed()));
        MaimElement.ArrowExpandNewsList.perform(click());
    }

    @Step("Нажатие на кнопку свернуть новости")
    public static void clickBreakNewsListArrow(){
        MaimElement.BreakNewsListArrow.check(matches(isDisplayed()));
        MaimElement.BreakNewsListArrow.perform(click());
    }

    @Step("Проверка отображения надписи ALL NEWS")
    public static void checkDisplayingTheAllNewsLabel(){
        MaimElement.ALLNEWSTitle.check(matches(isDisplayed()));
        MaimElement.ALLNEWSTitle.check(matches(withText("ALL NEWS")));
    }

    @Step("Проверка отображения надписи News")
    public static void checkDisplayingTheNewsLabel(){
        MaimElement.NewsTitle.check(matches(isDisplayed()));
        MaimElement.NewsTitle.check(matches(withText("News")));
    }
}
