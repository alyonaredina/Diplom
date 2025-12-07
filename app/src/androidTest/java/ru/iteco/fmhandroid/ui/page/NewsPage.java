package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.element.NewsElement;

public class NewsPage {
    NewsElement myObjectNews = new NewsElement();


    public void checkDisplayingRefreshButton(){
        Allure.step("Проверка отображения кнопки REFRESH");
        myObjectNews.RefreshButton.check(matches(isDisplayed()));
    }


    public void clickGoToFilterButton(){
        Allure.step("Нажатие на кнопку перехода в фильтрацию новостей");
        myObjectNews.GoToFilterButton.check(matches(isDisplayed()));
        myObjectNews.GoToFilterButton.perform(click());
    }


    public void clickEditNewsButton(){
        Allure.step("Нажатие на кнопку перехода в создание новостей");
        myObjectNews.EditNewsButton.check(matches(isDisplayed()));
        myObjectNews.EditNewsButton.perform(click());
    }


    public void checkDisplayingSortNewsButton(){
        Allure.step("Отображение кнопки сортировки новостей");
        myObjectNews.SortNewsButton.check(matches(isDisplayed()));
    }
}
