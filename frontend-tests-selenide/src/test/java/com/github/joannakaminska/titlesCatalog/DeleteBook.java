package com.github.joannakaminska.titlesCatalog;

import com.github.joannakaminska.BaseTestWithBook;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class DeleteBook extends BaseTestWithBook {

    @Test
    void shouldDeleteBookCorrectly() {

        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li//div[contains(@class, 'titles-list__item__actions')]//button[contains(@class, 'remove-btn')]")
                .shouldBe(visible)
                .click();

        $x("//div[contains(text(), 'Harry Potter')]").shouldNot(exist);
    }
}
