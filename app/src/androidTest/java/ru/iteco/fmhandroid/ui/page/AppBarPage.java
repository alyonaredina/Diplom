package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.element.AppBarElement;
import ru.iteco.fmhandroid.ui.element.AuthorizationElement;

public class AppBarPage {


    @Step("Нажатие на кнопку Меню")
    public static void clickMenuButton(){
        AppBarElement.MenuButton.check(matches(isDisplayed()));
        AppBarElement.MenuButton.perform(click());
    }

    @Step("Нажатие на кнопку Развернуть цитату")
    public static void clickExpandTheQuoteButton(){
        AppBarElement.ExpandTheQuoteButton.perform(actionOnItemAtPosition(3, click()));
    }

    @Step("Нажатие на кнопку Свернуть цитату")
    public static void clickRollUpTheQuoteButton(){
        AppBarElement.RollUpTheQuoteButton.perform(actionOnItemAtPosition(3, click()));
    }

    @Step("Нажатие на значек бабочки")
    public static void clickButterflyBadge(){
        AppBarElement.ButterflyBadge.check(matches(isDisplayed()));
        AppBarElement.ButterflyBadge.perform(click());
    }

    @Step("Нажатие на кнопку человечка на панеле App bar")
    public static void clickManButton(){
        AppBarElement.ManButton.perform(click());
    }

    @Step("Нажатие на кнопку выхода из приложения Log out")
    public static void clickExit(){
        AppBarElement.Exit.perform(click());
    }

    @Step("Выбрать строку Main в списке")
    public static void clickLineMain(){
        AppBarElement.LineMain.check(matches(isDisplayed()));
        AppBarElement.LineMain.perform(click());
    }

    @Step("Выбрать строку News в списке")
    public static void clickLineNews(){
        AppBarElement.LineNews.check(matches(isDisplayed()));
        AppBarElement.LineNews.perform(click());
    }

    @Step("Выбрать строку About в списке")
    public static void clickLineAbout(){
        AppBarElement.LineAbout.check(matches(isDisplayed()));
        AppBarElement.LineAbout.perform(click());
    }

    @Step("Проверка отображения надписи Love is all")
    public static void checkDisplayingTheLoveIsAllLabel(){
        AppBarElement.LoveIsAllTitle.check(matches(isDisplayed()));
        AppBarElement.LoveIsAllTitle.check(matches(withText("Love is all")));
    }

    @Step("Проверка отображения цитаты")
    public static void checkDisplayingTheQuote(){
        AppBarElement.Quote.check(matches(isDisplayed()));
        AppBarElement.Quote.check(matches(withText("“Творчески и осознанно подойти к " +
                "проектированию опыта умирания. Создать пространство физическое и психологическое, " +
                "чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с " +
                "дороги. Тогда старение и умирание могут стать процессом восхождения до самого " +
                "конца” \nБи Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\"")));
    }

    @Step("Проверка цитата не отображается")
    public static void checkNoDisplayingTheQuote(){
        AppBarElement.Quote.check(matches(not(isDisplayed())));
    }
}
