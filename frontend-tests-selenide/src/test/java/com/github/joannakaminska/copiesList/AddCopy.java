package com.github.joannakaminska.copiesList;

import com.codeborne.selenide.SelenideElement;
import com.github.joannakaminska.BaseTestWithBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.github.joannakaminska.BaseTestWithBookAndCopy.setCalendarDate;

public class AddCopy extends BaseTestWithBook {

    // ustawiamy datę egzemplarza
    private final LocalDate purchaseDate = LocalDate.of(2023, 3, 15);

    @Test
    void shouldAddCopySuccessfully() {

        // Sprawdzamy, że książka "Harry Potter" jest na liście
        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li")
                .shouldBe(visible);

        // Otwieramy listę egzemplarzy książki
        $x("//div[contains(text(), 'Harry Potter')]//ancestor::li//button[contains(@class, 'show-copies-btn')]")
                .shouldBe(visible)
                .click();

        // Klikamy "Add item"
        $x("//button[@id='add-item-button']").shouldBe(visible).click();

        // Ustawiamy datę przy użyciu metody z BaseTestWithBook
        setCalendarDate(purchaseDate);

        // Submit formularza egzemplarza
        $x("//button[@name='submit-button']").shouldBe(visible).click();

        // Sprawdzamy, że egzemplarz pojawił się na liście
        SelenideElement copyLi = $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .closest("li")
                .shouldBe(visible);

        // Weryfikujemy, że pod spodem są przyciski "Edit", "Remove" i "Show history"
        copyLi.$("button.edit-btn").shouldBe(visible);
        copyLi.$("button.remove-btn").shouldBe(visible);
        copyLi.$("button.show-rents-btn").shouldBe(visible);
    }

    @AfterEach
    void cleanup() {
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
