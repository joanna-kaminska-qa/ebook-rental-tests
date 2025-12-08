package com.github.joannakaminska.copiesList;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBookAndCopy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DeleteCopy extends BaseTestWithBookAndCopy {

    @Test
    void shouldDeleteCopySuccessfully() {
        // Szukamy dodanego egzemplarza po dacie z BaseTestWithBookAndCopy
        SelenideElement copyLi = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='"
                + purchaseDate + "']")
                .closest("li")
                .shouldBe(visible);

        // Klikamy przycisk Remove w tej kopii
        copyLi.$("button.remove-btn")
                .shouldBe(visible)
                .click();

        // Sprawdzamy, że kopia już nie istnieje
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .shouldNotBe(visible);
    }

    @AfterEach
    void cleanup() {
        // Wracamy do listy tytułów
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // Usuwamy książkę "Harry Potter"
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .shouldNotBe(visible);
    }
}
