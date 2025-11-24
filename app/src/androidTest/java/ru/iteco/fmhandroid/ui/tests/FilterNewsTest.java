package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.DataFilterNews.futureDate0Days;
import static ru.iteco.fmhandroid.ui.DataFilterNews.futureDate14Days;
import static ru.iteco.fmhandroid.ui.DataFilterNews.futureDate7Days;
import static ru.iteco.fmhandroid.ui.WaitId.waitUntilElement;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
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
import ru.iteco.fmhandroid.ui.DataFilterNews;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class FilterNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    //Тестовые данные страницы
    public String titleAuthorization = "Authorization";
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String titleAllNews = "ALL NEWS";
    public String filter = "Filter news";
    public String holiday = "Праздник";
    public String massaj = "массаж";
    public String errorFilter = "Wrong period";
    public String news = "News";

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

            ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.filter_news_material_button),isDisplayed()));
            materialButton2.perform(click());

            ViewInteraction textView2 = onView(Matchers.allOf(withId(R.id.filter_news_title_text_view), withText(filter),isDisplayed()));
            textView2.check(matches(withText(filter)));

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

            ViewInteraction materialButton2 = onView(Matchers.allOf(withId(R.id.filter_news_material_button),isDisplayed()));
            materialButton2.perform(click());

            ViewInteraction textView2 = onView(Matchers.allOf(withId(R.id.filter_news_title_text_view), withText(filter),isDisplayed()));
            textView2.check(matches(withText(filter)));
        }
    }

    @Test
    @Step("FilterNews. Выбор категорий из списка с выбором дат")
    public void id16selectingCategoriesFromADateSelectionList(){
        //развернуть список
        ViewInteraction checkableImageButton = onView(
                Matchers.allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu"),isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //выбрать начальную дату
        DataFilterNews.NewsDataFilterStart(futureDate0Days(0));

        //выбрать конечную дату
        DataFilterNews.NewsDataFilterEnd(futureDate7Days(0));

        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        try {
            DataFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            DataFilterNews.noNews();
        }
    }

    @Test
    @Step("FilterNews. Выбор категорий из списка с выбором дат")
    public void id16ERRORselectingCategoriesFromADateSelectionList(){
        //развернуть список
        ViewInteraction checkableImageButton = onView(
                Matchers.allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu"),isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //выбрать начальную дату
        DataFilterNews.NewsDataFilterStart(futureDate7Days(0));

        //выбрать конечную дату
        DataFilterNews.NewsDataFilterEnd(futureDate0Days(0));

        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        try {
            DataFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            DataFilterNews.noNews();
        }
    }

    @Test
    @Step("FilterNews. Выбор категорий из списка с выбором дат")
    public void id17selectingCategoriesFromADateSelectionList(){
        //развернуть список
        ViewInteraction checkableImageButton = onView(
                Matchers.allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu"),isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //выбрать начальную дату
        DataFilterNews.NewsDataFilterStart(futureDate7Days(0));

        //выбрать конечную дату
        DataFilterNews.NewsDataFilterEnd(futureDate14Days(0));

        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        try {
            DataFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            DataFilterNews.noNews();
        }
    }

    @Test
    @Step("FilterNews. Выбор категорий из списка")
    public void id18selectingCategoriesFromSelectionList(){
        //развернуть список
        ViewInteraction checkableImageButton = onView(
                Matchers.allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu"),isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        try {
            DataFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            DataFilterNews.noNews();
        }
    }

    @Test
    @Step("FilterNews. Ввод категории вручную")
    public void id19EnteringACategoryManually(){
        //развернуть список
        ViewInteraction checkableImageButton = onView(
                Matchers.allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu"),isDisplayed()));
        checkableImageButton.perform(click());

        //ввод категории

        ViewInteraction materialAutoCompleteTextView2 = onView(Matchers.allOf(withId(R.id.news_item_category_text_auto_complete_text_view),isDisplayed()));
        materialAutoCompleteTextView2.perform(replaceText(massaj), closeSoftKeyboard());

        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        try {
            DataFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            DataFilterNews.noNews();
        }
    }

    @Test
    @Step("FilterNews. Фильтрация без данных")
    public void id23FilteringWithoutData(){
        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        try {
            DataFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            DataFilterNews.noNews();
        }
    }

    @Test
    @Step("FilterNews. Без выбора категорий из списка с выбором дат")
    public void id24onlyDate(){
        //выбрать начальную дату
        DataFilterNews.NewsDataFilterStart(futureDate0Days(0));

        //выбрать конечную дату
        DataFilterNews.NewsDataFilterEnd(futureDate7Days(0));

        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        try {
            DataFilterNews.checkFilter();
        } catch (androidx.test.espresso.PerformException e) {
            DataFilterNews.noNews();
        }
    }

    @Test
    @Step("FilterNews. Выбор категорий из списка и даты начала поиска")
    public void id26selectingCategoriesFromADateSelectionList(){
        //развернуть список
        ViewInteraction checkableImageButton = onView(
                Matchers.allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withContentDescription("Show dropdown menu"),isDisplayed()));
        checkableImageButton.perform(click());

        //выбрать категорию из списка
        DataControlPanel.SelectCategory(holiday);

        //выбрать начальную дату
        DataFilterNews.NewsDataFilterStart(futureDate0Days(0));

        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        ViewInteraction textView = onView(
                Matchers.allOf(withId(android.R.id.message), withText(errorFilter),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView.check(matches(withText(errorFilter)));
    }

    @Test
    @Step("FilterNews. Завершение фильтрации")
    public void id28EndFilter(){
        //кликнуть Filter
        ViewInteraction materialButton4 = onView(Matchers.allOf(withId(R.id.filter_button), withText("Filter"),isDisplayed()));
        materialButton4.perform(click());

        //проверка
        ViewInteraction textView = onView(Matchers.allOf(withText(news),
                        withParent(withParent(withId(R.id.container_list_news_include))),
                        isDisplayed()));
        textView.check(matches(withText(news)));
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
