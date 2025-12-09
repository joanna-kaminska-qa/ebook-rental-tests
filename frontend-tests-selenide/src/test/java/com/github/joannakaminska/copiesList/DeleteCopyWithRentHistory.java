package com.github.joannakaminska.copiesList;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBookAndCopyAndRent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DeleteCopyWithRentHistory extends BaseTestWithBookAndCopyAndRent {

    @Test
    void shouldNotRemoveCopyWithRentHistory() {
        // Wracamy do listy kopii (jeśli jesteśmy w widoku wypożyczeń)
        $x("//button[@id='return-button']").click();

        // Szukamy egzemplarza po dacie zakupu
        SelenideElement copyLi = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .shouldBe(visible);

        // Próba usunięcia kopii
        copyLi.$(".remove-btn").click();

        // Sprawdzenie komunikatu
        $(".alert__content")
                .shouldBe(visible)
                .shouldHave(text("You can't remove copy with the rents history!"));
    }

    @AfterEach
    void cleanup() {
        // Szukamy egzemplarza po dacie zakupu
        SelenideElement copyLi = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .shouldBe(visible);

        // Klikamy "Show rents"
        copyLi.$("button.show-rents-btn").shouldBe(visible).click();

        // Szukamy rentu po nazwisku klienta i usuwamy
        SelenideElement rentLi = $x("//div[contains(@class,'rents-list__rent__customer-name') and text()='Jan Kowalski']")
                .closest("li")
                .shouldBe(visible);
        rentLi.$(".remove-btn").click();

        // Wracamy do listy kopii
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // Usuwamy egzemplarz
        copyLi.$(".remove-btn").shouldBe(visible).click();

        // Wracamy do listy książek
        $x("//button[@id='return-button']").shouldBe(visible).click();

        // Usuwamy książkę
        SelenideElement bookLi = $x("//div[contains(text(),'Harry Potter')]/ancestor::li");
        bookLi.$("button.remove-btn").shouldBe(visible).click();
        bookLi.shouldNotBe(visible);
    }
}
