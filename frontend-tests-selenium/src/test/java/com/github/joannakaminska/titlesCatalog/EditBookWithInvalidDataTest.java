package com.github.joannakaminska.titlesCatalog;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class EditBookWithInvalidDataTest extends BaseTestWithBook {

    @Test
    void shouldNotEditBookWithIncompleteData() {

        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li//button[contains(@class,'edit-btn')]")
                .shouldBe(visible)
                .click();

        SelenideElement field = $("[name='author']");
        field.shouldBe(visible)
                .sendKeys(Keys.chord(Keys.CONTROL, "a")); // trzeba tak, żeby input faktycznie stał się pusty, bo przy zwykłym czyszczeniu pola system zapamiętuje co było wpisane
        field.sendKeys(Keys.DELETE);

        $("button[name='submit-button']").click();

        $(".alert__content")
                .shouldHave(text("\"author\" field shouldn't be empty..."));

        $("a.icon-close").click();
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
