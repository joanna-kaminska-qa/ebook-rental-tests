package com.github.joannakaminska;

import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.*;

public class BaseTestWithBook extends AuthenticatedBaseTest {

    @BeforeAll
    static void createBook() {
        $("#add-title-button").click();

        $("[name='title']").setValue("Harry Potter");
        $("[name='author']").setValue("J.K. Rowling");
        $("[name='year']").setValue("1997");

        $("button[name='submit-button']").click();
    }
}
