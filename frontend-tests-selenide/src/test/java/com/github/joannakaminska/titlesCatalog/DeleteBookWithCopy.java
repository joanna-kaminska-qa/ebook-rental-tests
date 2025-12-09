package com.github.joannakaminska.titlesCatalog;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBookAndCopy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$;

public class DeleteBookWithCopy extends BaseTestWithBookAndCopy {

    private final LocalDate purchaseDate = LocalDate.of(2023, 3, 15);

    @Test
    void shouldNotRemoveBookWithCopies() {
        // Wracamy do listy książek
        $("#return-button").shouldBe(visible).click();

        // Próbujemy usunąć książkę
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        // Sprawdzamy alert
        $(".alert__content")
                .shouldBe(visible)
                .shouldHave(text("You can't remove titles with copies!"));

        // Książka nadal widoczna
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .$("button.remove-btn")
                .shouldBe(visible);
    }

    @AfterEach
    void cleanup() {
        // otwieramy listę kopii
        $x("//div[contains(text(),'Harry Potter')]//ancestor::li//div[contains(@class, 'titles-list__item__actions')]//button[contains(@class, 'show-copies-btn')]")
                .shouldBe(visible)
                .click();

        // usuwamy egzemplarz
        SelenideElement copy = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']");
        copy.closest("li").$(".remove-btn").shouldBe(visible).click();

        // wracamy do listy książek
        $("#return-button").shouldBe(visible).click();

        // usuwamy książkę
        SelenideElement book = $x("//div[contains(text(),'Harry Potter')]/ancestor::li");
        book.$("button.remove-btn").shouldBe(visible).click();
        book.shouldNotBe(visible);
    }
}
