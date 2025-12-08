package com.github.joannakaminska;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.*;

public class BaseTestWithBookAndCopy extends BaseTestWithBook {

    protected static final LocalDate purchaseDate = LocalDate.of(2023, 3, 15);

    @BeforeAll
    static void createCopy() {
        // otwieramy listę egzemplarzy
        $x("//div[contains(text(),'Harry Potter')]/ancestor::li//button[contains(@class,'show-copies-btn')]")
                .click();

        $("#add-item-button").click();

        setCalendarDate(purchaseDate);

        $("button[name='submit-button']").click();

        // upewniamy się, że kopia jest na liście
        $x("//div[contains(@class,'items-list__item__purchase-date') and text()='" + purchaseDate + "']")
                .shouldBe(com.codeborne.selenide.Condition.visible);
    }

    public static void setCalendarDate(LocalDate date) {
        $("#id").click();

        SelenideElement calendar =
                $x("//div[contains(@class,'vdp-datepicker__calendar') and not(contains(@style,'display: none'))]");

        calendar.$("span.day__month_btn.up").click();

        calendar =
                $x("//div[contains(@class,'vdp-datepicker__calendar') and not(contains(@style,'display: none'))]");
        calendar.$("span.month__year_btn.up").click();

        calendar.$x(".//span[text()='" + date.getYear() + "']").click();

        String monthName = date.getMonth().name().substring(0,1)
                + date.getMonth().name().substring(1).toLowerCase();

        calendar.$x(".//span[text()='" + monthName + "']").click();

        calendar.$x(".//span[text()='" + date.getDayOfMonth() + "']").click();
    }
}
