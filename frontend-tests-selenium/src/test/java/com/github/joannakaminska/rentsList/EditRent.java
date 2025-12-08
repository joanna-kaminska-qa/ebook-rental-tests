package com.github.joannakaminska.rentsList;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBookAndCopyAndRent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class EditRent extends BaseTestWithBookAndCopyAndRent {

    private final String updatedCustomer = "Jan Nowak";

    @Test
    void shouldEditRentSuccessfully() {

        // Szukamy naszego renta poprzez starą nazwę klienta
        SelenideElement rentLi = $x("//div[contains(@class,'rents-list__rent__customer-name') and text()='Jan Kowalski']")
                .closest("li")
                .shouldBe(visible);

        // Klikamy Edit
        rentLi.$("button.edit-btn").shouldBe(visible).click();

        // Czyścimy pole i wpisujemy nowe nazwisko
        SelenideElement customerInput = $x("//input[@name='customer-name']");
        customerInput.clear();
        customerInput.setValue(updatedCustomer);

        // Potwierdzamy zmiany
        $x("//button[@name='submit-button']").shouldBe(visible).click();

        // Weryfikacja: nowa wartość widoczna na liście
        $x("//div[contains(@class,'rents-list__rent__customer-name') and text()='" + updatedCustomer + "']")
                .shouldBe(visible);
    }

    @AfterEach
    void cleanup() {

        // Usuwamy zaktualizowany rent
        $x("//div[contains(@class,'rents-list__rent__customer-name') and text()='" + updatedCustomer + "']")
                .closest("li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        // wracamy do listy kopii
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // usuwamy egzemplarz
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        // wracamy do listy tytułów
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // usuwamy książkę
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .$("button.remove-btn")
                .shouldBe(visible)
                .click();

        // potwierdzenie usunięcia
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li")
                .shouldNotBe(visible);
    }
}
