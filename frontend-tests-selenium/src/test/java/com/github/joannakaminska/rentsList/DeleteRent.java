package com.github.joannakaminska.rentsList;

import com.github.joannakaminska.BaseTestWithBookAndCopyAndRent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class DeleteRent extends BaseTestWithBookAndCopyAndRent {

    @Test
    void shouldDeleteRentSuccessfully() {

        // Usuń wypożyczenie (rent)
        $x("//div[contains(@class,'rents-list__rent__customer-name') and text()='Jan Kowalski']")
                .closest("li")
                .$(".remove-btn")
                .shouldBe(visible)
                .click();

        // Sprawdzenie że zniknął
        $x("//div[contains(@class,'rents-list__rent__customer-name') and text()='Jan Kowalski']")
                .shouldNot(exist);
    }

    @AfterEach
    void cleanup() {
        // Jesteśmy nadal w widoku rents — wracamy do listy kopii
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // Usuwamy kopię
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        // Wracamy do listy tytułów
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // Usuwamy książkę
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        // Weryfikacja, że usunięto
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .shouldNotBe(visible);
    }
}
