package com.github.joannakaminska.rentsList;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBookAndCopy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AddRent extends BaseTestWithBookAndCopy {

    private final LocalDate rentDate = LocalDate.of(2024, 12, 8);

    @Test
    void shouldAddRentSuccessfully() {
        // Szukamy egzemplarza po dacie zakupu
        SelenideElement copyLi = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .shouldBe(visible);

        copyLi.$("button.show-rents-btn").shouldBe(visible).click();

        // Klikamy "Add rent"
        $x("//button[@id='add-rent-button']").shouldBe(visible).click();

        // Wpisujemy imię klienta
        $x("//input[@name='customer-name']").setValue("Jan Kowalski");

        // Ustawiamy datę wypożyczenia
        setCalendarDate(rentDate);

        // Submit
        $x("//button[@name='submit-button']").shouldBe(visible).click();

        // Weryfikacja: rent na liście
        SelenideElement rentLi = $x("//div[contains(@class,'rents-list__rent__rent-date') and contains(text(), '" + rentDate + "')]")
                .closest("li")
                .shouldBe(visible);

        // Sprawdzamy widoczność przycisków "Edit" i "Remove"
        rentLi.$("button.edit-btn").shouldBe(visible);
        rentLi.$("button.remove-btn").shouldBe(visible);
    }

    @AfterEach
    void cleanup() {
        // usuwamy dodany rent
        $x("//div[contains(@class,'rents-list__rent__rent-date') and contains(text(), '" + rentDate + "')]")
                .closest("li")
                .$(".remove-btn")
                .shouldBe(visible)
                .click();

        // wracamy do listy kopii
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // Usuwamy dodany egzemplarz
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

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
