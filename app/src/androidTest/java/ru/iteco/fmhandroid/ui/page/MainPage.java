package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.element.MaimElement;

public class MainPage {
    MaimElement myObjectMaim = new MaimElement();


    public void clickAllNews(){
        Allure.step("Нажатие на кнопку ALL NEWS");
        myObjectMaim.ALLNEWSTitle.check(matches(isDisplayed()));
        myObjectMaim.ALLNEWSTitle.perform(click());
    }


    public void clickArrowExpandNewsList(){
        Allure.step("Нажатие на кнопку развернуть новости");
        waitUntilElement(R.id.all_news_text_view);
        myObjectMaim.ArrowExpandNewsList.check(matches(isDisplayed()));
        myObjectMaim.ArrowExpandNewsList.perform(click());
    }


    public void clickBreakNewsListArrow(){
        Allure.step("Нажатие на кнопку свернуть новости");
        myObjectMaim.BreakNewsListArrow.check(matches(isDisplayed()));
        myObjectMaim.BreakNewsListArrow.perform(click());
    }


    public void checkDisplayingTheAllNewsLabel(){
        Allure.step("Проверка отображения надписи ALL NEWS");
        myObjectMaim.ALLNEWSTitle.check(matches(isDisplayed()));
        myObjectMaim.ALLNEWSTitle.check(matches(withText("ALL NEWS")));
    }


    public void checkDisplayingTheNewsLabel(){
        Allure.step("Проверка отображения надписи News");
        myObjectMaim.NewsTitle.check(matches(isDisplayed()));
        myObjectMaim.NewsTitle.check(matches(withText("News")));
    }
}
