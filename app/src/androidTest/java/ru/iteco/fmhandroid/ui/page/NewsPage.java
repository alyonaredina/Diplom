package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.element.NewsElement;

public class NewsPage {

    @Step("Проверка отображения кнопки REFRESH")
    public static void checkDisplayingRefreshButton(){
        NewsElement.RefreshButton.check(matches(isDisplayed()));
    }

    @Step("Нажатие на кнопку перехода в фильтрацию новостей")
    public static void clickGoToFilterButton(){
        NewsElement.GoToFilterButton.check(matches(isDisplayed()));
        NewsElement.GoToFilterButton.perform(click());
    }

    @Step("Нажатие на кнопку перехода в создание новостей")
    public static void clickEditNewsButton(){
        NewsElement.EditNewsButton.check(matches(isDisplayed()));
        NewsElement.EditNewsButton.perform(click());
    }

    @Step("Отображение кнопки сортировки новостей")
    public static void checkDisplayingSortNewsButton(){
        NewsElement.SortNewsButton.check(matches(isDisplayed()));
    }
}
