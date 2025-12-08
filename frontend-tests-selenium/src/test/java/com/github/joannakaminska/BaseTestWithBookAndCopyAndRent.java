package com.github.joannakaminska;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BaseTestWithBookAndCopyAndRent extends BaseTestWithBookAndCopy {

    protected static final LocalDate rentDate = LocalDate.of(2024, 12, 8);

    @BeforeAll
    static void createRent() {
        // Szukamy egzemplarza po dacie
        SelenideElement copyLi = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .shouldBe(visible);

        // Klikamy "Show history / rents"
        copyLi.$("button.show-rents-btn").shouldBe(visible).click();

        // Klikamy "Add rent"
        $x("//button[@id='add-rent-button']").shouldBe(visible).click();

        // Wpisujemy przykładowe imię klienta
        $x("//input[@name='customer-name']").setValue("Jan Kowalski");

        // Ustawiamy datę wypożyczenia
        setCalendarDate(rentDate);

        // Submit formularza wypożyczenia
        $x("//button[@name='submit-button']").shouldBe(visible).click();

        // Sprawdzamy, że rent pojawił się na liście
        $x("//div[contains(@class,'rents-list__rent__customer-name') and text()='Jan Kowalski']")
                .shouldBe(visible);
    }
}
