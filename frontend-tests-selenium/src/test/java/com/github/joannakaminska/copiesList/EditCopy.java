package com.github.joannakaminska.copiesList;

import com.github.joannakaminska.BaseTestWithBookAndCopy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EditCopy extends BaseTestWithBookAndCopy {

    private LocalDate lastDateUsed = purchaseDate;

    @Test
    void shouldEditDateOfCopySuccessfully() {
        // Szukamy dodanego egzemplarza po początkowej dacie
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .$(".edit-btn")
                .click();

        // Nowa data
        LocalDate newDate = LocalDate.of(2023, 4, 20);
        setCalendarDate(newDate.plusDays(1));

        $("button[name='submit-button']").shouldBe(visible).click();

        // Sprawdzamy nową datę
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + newDate + "']")
                .shouldBe(visible);

        lastDateUsed = newDate;
    }

    @AfterEach
    void cleanup() {
        // usuwamy egzemplarz po ostatniej dacie
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + lastDateUsed + "']")
                .closest("li")
                .$(".remove-btn")
                .shouldBe(visible)
                .click();

        $("#return-button").shouldBe(visible).click();

        // usuwamy książkę "Harry Potter"
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .shouldNotBe(visible);
    }
}
