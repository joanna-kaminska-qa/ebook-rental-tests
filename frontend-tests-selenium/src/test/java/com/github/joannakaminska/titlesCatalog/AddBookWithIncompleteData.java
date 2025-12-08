package com.github.joannakaminska.titlesCatalog;

import com.github.joannakaminska.AuthenticatedBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class AddBookWithIncompleteData extends AuthenticatedBaseTest {

    @BeforeEach
    void prepareForm() {

        // Jeśli formularz jest otwarty → zamknij go
        if ($("a.icon-close").exists()) {
            $("a.icon-close").click();
        }

        // Otwórz czysty formularz
        $("#add-title-button").shouldBe(visible).click();
    }

    @Test
    void shouldShowErrorWhenAddingBookWithoutAuthor() {
        $("[name='title']").setValue("Władca Pierścieni");
        $("[name='year']").setValue("1954");

        $("button[name='submit-button']").click();

        $(".title-form .alert__content")
                .shouldBe(visible)
                .shouldHave(text("\"author\" field shouldn't be empty..."));

    }

    @Test
    void shouldShowErrorWhenAddingBookWithoutTitle() {
        $("[name='author']").setValue("J.R.R. Tolkien");
        $("[name='year']").setValue("1954");

        $("button[name='submit-button']").click();

        $(".title-form .alert__content")
                .shouldBe(visible)
                .shouldHave(text("\"title\" field shouldn't be empty..."));

    }

    @Test
    void shouldShowErrorWhenAddingBookWithoutYear() {
        $("[name='title']").setValue("Władca Pierścieni");
        $("[name='author']").setValue("J.R.R. Tolkien");

        $("button[name='submit-button']").click();

        $(".title-form .alert__content")
                .shouldBe(visible)
                .shouldHave(text("\"year\" field shouldn't be empty..."));

    }
}
