package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;
import static ru.iteco.fmhandroid.ui.tests.AuthorizationTest.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.DataControlPanel;
import ru.iteco.fmhandroid.ui.DataАuthorization;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class ControlPanelTest extends DataControlPanel {
    private View decorView;
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    //Тестовые данные страницы
    public String titleAuthorization = "Authorization";
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String titleAllNews = "ALL NEWS";
    public String control = "Control panel";
    public String textNews = "нет";
    public String holiday = "Праздник";
    public String nameOfTheHoliday = "Новый день";
    public String zp = "Зарплата";
    public String money = "Зп";
    public String mersi = "Благодарность";
    public String home = "Дом";
    public String thereNoNews = "There is nothing here yet…";
    public String errorCreateNews = "Fill empty fields";

    public void CreateNews(String text1, String text2){
        //DataControlPanel.CreateTheNews();
        //Создать новость
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.add_news_image_view)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());

        //кликнуть по полю Category
        ViewInteraction checkableImageButton = onView(
                allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu")));
        checkableImageButton.perform(click());;

        //Выбрать категорию из списка
        DataControlPanel.SelectCategory(text1);

        //Выбрать дату
        DataControlPanel.NewsData(futureDate(1));

        //Выбрать время
        DataControlPanel.NewsTime(futureTimeMinute(1));

        //Ввести описание новости
        DataControlPanel.NewsDiscription(text2);

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //Сортировка
        waitUntilElement(R.id.news_item_title_text_view);

        ViewInteraction materialButton6 = onView(
                Matchers.allOf(withId(R.id.sort_news_material_button), isDisplayed()));
        materialButton6.perform(click());
    }




    @Before
    public void authorizationTestBefore() {
        try {
            waitUntilElement(R.id.login_text_input_layout); //таймаут

            ViewInteraction textView = onView(
                    allOf(withText(titleAuthorization),
                            withParent(withParent(withId(R.id.nav_host_fragment))),
                            isDisplayed()));
            textView.check(matches(withText(titleAuthorization)));

            ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
            textInputEditText.check(matches(isDisplayed()));
            textInputEditText.perform(replaceText(validLogin), closeSoftKeyboard());


            ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
            textInputEditText2.check(matches(isDisplayed()));
            textInputEditText2.perform(replaceText(validPassword), closeSoftKeyboard());

            ViewInteraction materialButton = onView(withId(R.id.enter_button));
            materialButton.check(matches(isDisplayed()));
            materialButton.perform(click());

            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS

            ViewInteraction materialTextView = onView(withId(R.id.all_news_text_view));
            materialTextView.check(matches(isDisplayed()));
            materialTextView.perform(click());

            ViewInteraction materialButton2 = onView(withId(R.id.edit_news_material_button));
            materialButton2.check(matches(isDisplayed()));
            materialButton2.perform(click());

            ViewInteraction textView2 = onView(
                    Matchers.allOf(withText(control),
                            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                            isDisplayed()));
            textView2.check(matches(withText(control)));

        } catch (androidx.test.espresso.PerformException e) {
            ViewInteraction appCompatImageButton = onView(Matchers.allOf(withId(R.id.authorization_image_button)));
            appCompatImageButton.check(matches(isDisplayed()));
            appCompatImageButton.perform(click());

            waitUntilElement(android.R.id.title);

            ViewInteraction materialTextView = onView(withId(android.R.id.title));
            //materialTextView.check(matches(isDisplayed()));
            materialTextView.perform(click());

            ViewInteraction textView = onView(
                    allOf(withText(titleAuthorization),
                            withParent(withParent(withId(R.id.nav_host_fragment))),
                            isDisplayed()));
            textView.check(matches(withText(titleAuthorization)));

            ViewInteraction textInputEditText = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.login_text_input_layout))));
            textInputEditText.check(matches(isDisplayed()));
            textInputEditText.perform(replaceText(validLogin), closeSoftKeyboard());


            ViewInteraction textInputEditText2 = onView(allOf(supportsInputMethods(),isDescendantOfA(withId(R.id.password_text_input_layout))));
            textInputEditText2.check(matches(isDisplayed()));
            textInputEditText2.perform(replaceText(validPassword), closeSoftKeyboard());

            ViewInteraction materialButton = onView(withId(R.id.enter_button));
            materialButton.check(matches(isDisplayed()));
            materialButton.perform(click());

            waitUntilElement(R.id.all_news_text_view); //ждем загрузгу страницы ALL NEWS

            ViewInteraction materialTextView2 = onView(withId(R.id.all_news_text_view));
            materialTextView2.check(matches(isDisplayed()));
            materialTextView2.perform(click());

            ViewInteraction materialButton2 = onView(withId(R.id.edit_news_material_button));
            materialButton2.check(matches(isDisplayed()));
            materialButton2.perform(click());

            ViewInteraction textView2 = onView(
                    Matchers.allOf(withText(control),
                            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                            isDisplayed()));
            textView2.check(matches(withText(control)));
        }
    }



    @Test
    @Step("ControlPanel.Развернуть новость")
    public void id29ExpandTheNews(){
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_description_text_view), withText(textNews),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(textNews)));
    }

    @Test
    @Step("ControlPanel.Свернуть новость")
    public void id29collapseTheNews(){
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(Matchers.allOf(withId(R.id.news_list_recycler_view)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_description_text_view), withText(textNews),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(doesNotExist());
    }

    @Test
    @Step("ControlPanel.Создать новость")
    public void id53createTheNews(){
        //Создать новость
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.add_news_image_view)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());

        //кликнуть по полю Category
        ViewInteraction checkableImageButton = onView(
                allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu")));
        checkableImageButton.perform(click());;

        //Выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //Выбрать дату
        DataControlPanel.NewsData(futureDate(1));

        //Выбрать время
        DataControlPanel.NewsTime(futureTimeMinute(1));

        //Ввести описание новости
        DataControlPanel.NewsDiscription(nameOfTheHoliday);

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //Проверка
        waitUntilElement(R.id.news_item_title_text_view);

        ViewInteraction materialButton6 = onView(
                Matchers.allOf(withId(R.id.sort_news_material_button), isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_title_text_view), withText(holiday),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(holiday)));

    }

    @Test
    @Step("ControlPanel.Удалить новость")
    public void id30deleteTheNews(){
        CreateNews(holiday ,nameOfTheHoliday);

        //кликнуть на значек удаления
        waitUntilElement(R.id.delete_news_item_image_view);
        onView(first(allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button"),
                        childAtPosition(childAtPosition(withId(R.id.news_item_material_card_view),
                                        0),14)))).perform(click());
        //кликнуть ок
        onView(allOf(withId(android.R.id.message), withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future."),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))))).check(matches(isDisplayed()));

        onView(allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")),
                                        0),3))).perform(click());
    }

    @Test
    @Step("ControlPanel. Отмена удаления")
    public void id31cancellationDelete(){
        //кликнуть на значек удаления
        waitUntilElement(R.id.delete_news_item_image_view);
        onView(first(allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button"),
                childAtPosition(childAtPosition(withId(R.id.news_item_material_card_view),
                        0),14)))).perform(click());
        //кликнуть Cancel
        onView(allOf(withId(android.R.id.message), withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future."),
                withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))))).check(matches(isDisplayed()));

        ViewInteraction materialButton2 = onView(
                Matchers.allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")),
                                        0),2)));
        materialButton2.perform(scrollTo(), click());

        //Проверка
        waitUntilElement(R.id.news_item_title_text_view);

        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_title_text_view), withText(zp),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(zp)));
    }

    @Test
    @Step("ControlPanel. Изменить категорию новости")
    public void id32changeTheNewsCategory(){
        //кликнуть на иконку редактирования
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //кликнуть по полю Category
        ViewInteraction checkableImageButton = onView(
                allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu")));
        checkableImageButton.perform(click());;

        //Выбрать категорию из списка
        DataControlPanel.SelectCategory(mersi);

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_title_text_view), withText("Благодарность"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("Благодарность")));

        //кликнуть на иконку редактирования
        //waitUntilElement(R.id.edit_news_material_button);
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //кликнуть по полю Category
        ViewInteraction checkableImageButton2 = onView(
                allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu")));
        checkableImageButton2.perform(click());;

        //Выбрать категорию из списка
        DataControlPanel.SelectCategory(zp);

        //Создать
        ViewInteraction materialButton6 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton6.perform(scrollTo(), click());
    }

    @Test
    @Step("ControlPanel. Изменить заглавие новости")
    public void id33changeTheNewsCategory(){
        //кликнуть на иконку редактирования
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //изменить текст
        onView(allOf(withId(R.id.news_item_title_text_input_edit_text))).perform(click());
        onView(allOf(withId(R.id.news_item_title_text_input_edit_text))).perform(replaceText(money), closeSoftKeyboard());

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_title_text_view), withText(money),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(money)));

        //кликнуть на иконку редактирования
        //waitUntilElement(R.id.edit_news_material_button);
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //изменить текст
        onView(allOf(withId(R.id.news_item_title_text_input_edit_text))).perform(click());
        onView(allOf(withId(R.id.news_item_title_text_input_edit_text))).perform(replaceText(zp), closeSoftKeyboard());

        //Создать
        ViewInteraction materialButton6 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton6.perform(scrollTo(), click());
    }

    @Test
    @Step("ControlPanel. Изменить дату публикации")
    public void id34changeThePublicationDate(){
        //Cоздать новость
        CreateNews(holiday,nameOfTheHoliday);

        //кликнуть на иконку редактирования
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //Выбрать дату
        DataControlPanel.NewsData(futureDate(1));

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //проверка
        DataControlPanel.CheckDateNews(futureDate(1));;
    }

    @Test
    @Step("ControlPanel. Изменить время публикации")
    public void id36changeThePublicationTime(){
        //Cоздать новость
        CreateNews(holiday,nameOfTheHoliday);

        //кликнуть на иконку редактирования
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //Выбрать время
        DataControlPanel.NewsTime(futureTimeMinute(1));

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //кликнуть на иконку редактирования
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //проверка
        DataControlPanel.CheckTimeNews(futureTimeMinute(1));
    }

    @Test
    @Step("ControlPanel. Изменить статус новости")
    public void id39changeTheNewsStatus(){
        //кликнуть на иконку редактирования
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //переместить бегунок
        ViewInteraction switchMaterial = onView(Matchers.allOf(withId(R.id.switcher), withText("Active")));
        switchMaterial.perform(scrollTo(), click());

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_published_text_view), withText("NOT ACTIVE"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("NOT ACTIVE")));

        //кликнуть на иконку редактирования
        onView(first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button")))).perform(click());

        //переместить бегунок
        ViewInteraction switchMaterial2 = onView(Matchers.allOf(withId(R.id.switcher), withText("Not active")));
        switchMaterial2.perform(scrollTo(), click());

        //Создать
        ViewInteraction materialButton6 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton6.perform(scrollTo(), click());
    }

    @Test
    @Step("ControlPanel. Фильтровать новости по категории")
    public void id41filteringByCategory(){
        //oткрыть фильтр
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.filter_news_material_button), isDisplayed()));
        materialButton2.perform(click());

        //открыть список категорий
        ViewInteraction checkableImageButton = onView(Matchers.allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию
        DataControlPanel.SelectCategory(zp);

        //кликнуть фильтровать
        ViewInteraction materialButton3 = onView(
                Matchers.allOf(withId(R.id.filter_button), withText("Filter"), isDisplayed()));
        materialButton3.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_title_text_view), withText(zp),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(zp)));
    }

    @Test
    @Step("ControlPanel. Фильтровать новости по категории и дате")
    public void id42filteringByCategoryAndDate(){
        //oткрыть фильтр
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.filter_news_material_button), isDisplayed()));
        materialButton2.perform(click());

        //открыть список категорий
        ViewInteraction checkableImageButton = onView(Matchers.allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию
        DataControlPanel.SelectCategory(zp);

        //выбрать дату начала поиска
        ViewInteraction textInputEditText = onView(Matchers.allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text),isDisplayed()));
        textInputEditText.perform(click());

        //кликнуть ок
        ViewInteraction materialButton3 = onView(Matchers.allOf(withId(android.R.id.button1), withText("OK")));
        materialButton3.perform(scrollTo(), click());

        //выбрать дату окончания поиска
        ViewInteraction textInputEditText2 = onView(Matchers.allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text),isDisplayed()));
        textInputEditText2.perform(click());

        //кликнуть ок
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(android.R.id.button1), withText("OK")));
        materialButton4.perform(scrollTo(), click());

        //кликнуть фильтровать
        ViewInteraction materialButton5 = onView(
                Matchers.allOf(withId(R.id.filter_button), withText("Filter"), isDisplayed()));
        materialButton5.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.control_panel_empty_news_list_text_view), withText(thereNoNews),
                        withParent(withParent(withId(R.id.news_control_panel_swipe_to_refresh))),
                        isDisplayed()));
        textView.check(matches(withText(thereNoNews)));
    }

    @Test
    @Step("ControlPanel. Фильтровать новости по категории, дате и статусу")
    public void id43filteringByCategoryAndDateAndStatus(){
        //oткрыть фильтр
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.filter_news_material_button), isDisplayed()));
        materialButton2.perform(click());

        //открыть список категорий
        ViewInteraction checkableImageButton = onView(Matchers.allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию
        DataControlPanel.SelectCategory(zp);

        //выбрать дату начала поиска
        ViewInteraction textInputEditText = onView(Matchers.allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text),isDisplayed()));
        textInputEditText.perform(click());

        //кликнуть ок
        ViewInteraction materialButton3 = onView(Matchers.allOf(withId(android.R.id.button1), withText("OK")));
        materialButton3.perform(scrollTo(), click());

        //выбрать дату окончания поиска
        ViewInteraction textInputEditText2 = onView(Matchers.allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text),isDisplayed()));
        textInputEditText2.perform(click());

        //кликнуть ок
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(android.R.id.button1), withText("OK")));
        materialButton4.perform(scrollTo(), click());

        //убрать галочку у активного статуса
        ViewInteraction materialCheckBox = onView(Matchers.allOf(withId(R.id.filter_news_active_material_check_box), withText("Active"),isDisplayed()));
        materialCheckBox.perform(click());

        //кликнуть фильтровать
        ViewInteraction materialButton5 = onView(
                Matchers.allOf(withId(R.id.filter_button), withText("Filter"), isDisplayed()));
        materialButton5.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.control_panel_empty_news_list_text_view), withText(thereNoNews),
                        withParent(withParent(withId(R.id.news_control_panel_swipe_to_refresh))),
                        isDisplayed()));
        textView.check(matches(withText(thereNoNews)));
    }

    @Test
    @Step("ControlPanel. Фильтровать новости по категории, дате и статусу")
    public void id44filteringByCategoryAndDateAndStatus(){
        //oткрыть фильтр
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.filter_news_material_button), isDisplayed()));
        materialButton2.perform(click());

        //открыть список категорий
        ViewInteraction checkableImageButton = onView(Matchers.allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию
        DataControlPanel.SelectCategory(zp);

        //выбрать дату начала поиска
        ViewInteraction textInputEditText = onView(Matchers.allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text),isDisplayed()));
        textInputEditText.perform(click());

        //кликнуть ок
        ViewInteraction materialButton3 = onView(Matchers.allOf(withId(android.R.id.button1), withText("OK")));
        materialButton3.perform(scrollTo(), click());

        //выбрать дату окончания поиска
        ViewInteraction textInputEditText2 = onView(Matchers.allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text),isDisplayed()));

        DataControlPanel.NewsDataFilterContPanel(futureDateFilterContPanel(1));

        //убрать галочку у не активного статуса
        ViewInteraction materialCheckBox2 = onView(Matchers.allOf(withId(R.id.filter_news_inactive_material_check_box), withText("Not active"),isDisplayed()));
        materialCheckBox2.perform(click());

        //кликнуть фильтровать
        ViewInteraction materialButton5 = onView(
                Matchers.allOf(withId(R.id.filter_button), withText("Filter"), isDisplayed()));
        materialButton5.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(R.id.news_item_title_text_view), withText(zp),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(zp)));
    }

    @Test
    @Step("ControlPanel. Выйти из фильтра")
    public void id48exitTheFilter() {
        //oткрыть фильтр
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.filter_news_material_button),isDisplayed()));
        materialButton2.perform(click());

        //кликнуть cancel
        ViewInteraction materialButton3 = onView(Matchers.allOf(withId(R.id.cancel_button), withText("Cancel"),isDisplayed()));
        materialButton3.perform(click());

        //проверка
        ViewInteraction textView = onView(Matchers.allOf(withText(control),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText(control)));
    }

    @Test
    @Step("ControlPanel. Создать новость по категории из списка")
    public void id49createNewsByCategoryFromTheList(){
        //Создать новость
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.add_news_image_view)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());

        //кликнуть по полю Category
        ViewInteraction checkableImageButton = onView(
                allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu")));
        checkableImageButton.perform(click());;

        //Выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //оповещение об ошибке
        DataАuthorization.aPop_upError(errorCreateNews, decorView);
    }

    @Test
    @Step("ControlPanel. Создать новость по категории из списка и дате")
    public void id50createNewsByCategoryFromTheListAndDate(){
        //Создать новость
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.add_news_image_view)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());

        //кликнуть по полю Category
        ViewInteraction checkableImageButton = onView(
                allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu")));
        checkableImageButton.perform(click());;

        //Выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //Выбрать дату
        DataControlPanel.NewsData(futureDate(1));

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //оповещение об ошибке
        DataАuthorization.aPop_upError(errorCreateNews, decorView);
    }

    @Test
    @Step("ControlPanel. Создать новость по категории из списка, дате и времени")
    public void id51createNewsByCategoryFromTheListAndDateAndTime(){
        //Создать новость
        ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.add_news_image_view)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());

        //кликнуть по полю Category
        ViewInteraction checkableImageButton = onView(
                allOf(withId(/*com.google.android.material.*/R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu")));
        checkableImageButton.perform(click());;

        //Выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //Выбрать дату
        DataControlPanel.NewsData(futureDate(1));

        //Выбрать время
        DataControlPanel.NewsTime(futureTimeMinute(1));

        //Создать
        ViewInteraction materialButton5 = onView(Matchers.allOf(withId(R.id.save_button), withText("Save")));
        materialButton5.perform(scrollTo(), click());

        //оповещение об ошибке
        DataАuthorization.aPop_upError(errorCreateNews, decorView);
    }

}
