package com.github.joannakaminska.titlesCatalog;

import com.codeborne.selenide.Configuration;
import com.github.joannakaminska.AuthenticatedBaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class AddBook extends AuthenticatedBaseTest {

    @Test
    void shouldAddBookSuccessfullyTest() {

        Configuration.timeout = 30000;
        $("#add-title-button").click();

        $("[name='title']").shouldBe(visible).setValue("Harry Potter");
        $("[name='author']").shouldBe(visible).setValue("J.K. Rowling");
        $("[name='year']").shouldBe(visible).setValue("1997");

        $("button[name='submit-button']").click();

        $(".titles-list.list").shouldBe(visible);

        $(".titles-list__item").shouldHave(text("Harry Potter")).shouldBe(visible);

        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li//div[contains(@class, 'titles-list__item__actions')]//button[contains(@class, 'show-copies-btn')]")
                .shouldBe(visible);
        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li//div[contains(@class, 'titles-list__item__actions')]//button[contains(@class, 'edit-btn')]")
                .shouldBe(visible);
        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li//div[contains(@class, 'titles-list__item__actions')]//button[contains(@class, 'remove-btn')]")
                .shouldBe(visible);

    }

    @AfterEach
    public void cleanup() {
        String testTitle = "Harry Potter";
        var book = $x("//div[contains(text(),'" + testTitle + "')]/ancestor::li");
        if (book.exists()) {
            book.$("button.remove-btn").click();
            sleep(500); // żeby UI zdążyło się odświeżyć
        }
    }

}