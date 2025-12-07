package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.element.AppBarElement;

public class AppBarPage {
    AppBarElement myObjectAppBar = new AppBarElement();

    public void clickMenuButton(){
        Allure.step("Нажатие на кнопку Меню");
        myObjectAppBar.MenuButton.check(matches(isDisplayed()));
        myObjectAppBar.MenuButton.perform(click());
    }

    public void clickExpandTheQuoteButton(){
        Allure.step("Нажатие на кнопку Развернуть цитату");
        myObjectAppBar.ExpandTheQuoteButton.perform(actionOnItemAtPosition(3, click()));
    }

    public void clickRollUpTheQuoteButton(){
        Allure.step("Нажатие на кнопку Свернуть цитату");
        myObjectAppBar.RollUpTheQuoteButton.perform(actionOnItemAtPosition(3, click()));
    }

    public void clickButterflyBadge() {
        Allure.step("Нажатие на значек бабочки");
        myObjectAppBar.ButterflyBadge.check(matches(isDisplayed()));
        myObjectAppBar.ButterflyBadge.perform(click());
    }

    public void clickManButton(){
        Allure.step("Нажатие на кнопку человечка на панеле App bar");
        myObjectAppBar.ManButton.perform(click());
    }

    public void clickExit(){
        Allure.step("Нажатие на кнопку выхода из приложения Log out");
        myObjectAppBar.Exit.perform(click());
    }

    public void clickLineMain(){
        Allure.step("Выбрать строку Main в списке");
        myObjectAppBar.LineMain.check(matches(isDisplayed()));
        myObjectAppBar.LineMain.perform(click());
    }

    public void clickLineNews(){
        Allure.step("Выбрать строку News в списке");
        myObjectAppBar.LineNews.check(matches(isDisplayed()));
        myObjectAppBar.LineNews.perform(click());
    }

    public void clickLineAbout(){
        Allure.step("Выбрать строку About в списке");
        myObjectAppBar.LineAbout.check(matches(isDisplayed()));
        myObjectAppBar.LineAbout.perform(click());
    }

    public void checkDisplayingTheLoveIsAllLabel(){
        Allure.step("Проверка отображения надписи Love is all");
        myObjectAppBar.LoveIsAllTitle.check(matches(isDisplayed()));
        myObjectAppBar.LoveIsAllTitle.check(matches(withText("Love is all")));
    }

    public void checkDisplayingTheQuote(){
        Allure.step("Проверка отображения цитаты");
        myObjectAppBar.Quote.check(matches(isDisplayed()));
        myObjectAppBar.Quote.check(matches(withText("“Творчески и осознанно подойти к " +
                "проектированию опыта умирания. Создать пространство физическое и психологическое, " +
                "чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с " +
                "дороги. Тогда старение и умирание могут стать процессом восхождения до самого " +
                "конца” \nБи Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\"")));
    }

    public void checkNoDisplayingTheQuote(){
        Allure.step("Проверка цитата не отображается");
        myObjectAppBar.Quote.check(matches(not(isDisplayed())));
    }
}
