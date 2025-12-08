package com.github.joannakaminska.rentsList;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBookAndCopy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddRentWithIncompleteData extends BaseTestWithBookAndCopy {

    private final LocalDate rentDate = LocalDate.of(2024, 12, 8);

    @Test
    void shouldShowErrorWhenCustomerNameIsEmpty() {

        // Szukamy egzemplarza po dacie zakupu
        SelenideElement copyLi = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .shouldBe(visible);

        copyLi.$("button.show-rents-btn").shouldBe(visible).click();

        // Klikamy "Add rent"
        $("#add-rent-button").shouldBe(visible).click();

        // Ustawiamy datę wypożyczenia
        setCalendarDate(rentDate);

        // Submit bez wpisania customer-name
        $("button[name='submit-button']").click();

        // Sprawdzamy alert
        $x("//form[contains(@class,'rent-form')]//p[contains(@class,'alert__content')]")
                .shouldBe(visible)
                .shouldHave(text("\"customerName\" field shouldn't be empty"));
    }

    @AfterEach
    void cleanup() {

        // Zamykamy formularz
        $(".fog__icon-close").click();

        // Wracamy do listy kopii
        $x("//button[@id='return-button']").click();

        // Usuwamy egzemplarz
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .$("button.remove-btn")
                .click();

        // Wracamy do listy książek
        $x("//button[@id='return-button']").click();

        // Usuwamy książkę
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .$("button.remove-btn")
                .click();

        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .shouldNotBe(visible);
    }
}
