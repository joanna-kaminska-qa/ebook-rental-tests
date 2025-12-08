package com.github.joannakaminska.titlesCatalog;

import com.github.joannakaminska.BaseTestWithBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class EditBook extends BaseTestWithBook {

    @Test
    void shouldEditBookSuccessfullyTest() {

        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li//button[contains(@class,'edit-btn')]")
                .shouldBe(visible)
                .click();
        $("[name='author']").shouldBe(visible).setValue("new author for test");

        $("button[name='submit-button']").click();

        $x("//div[contains(text(),'Harry Potter')]//ancestor::li//div[contains(@class,'titles-list__item__author')]")
                .shouldHave(text("new author for test"));
    }

    @AfterEach
    void cleanup() {
        String testTitle = "Harry Potter";
        var book = $x("//div[contains(text(),'" + testTitle + "')]/ancestor::li");
        book.$("button.remove-btn").click();
        sleep(500); // żeby UI się zdążyło odświeżyć
    }
}
