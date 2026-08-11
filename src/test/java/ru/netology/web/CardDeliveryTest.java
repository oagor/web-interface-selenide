package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldSuccessfullyBookCardDelivery() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input")
                .setValue("Москва");

        String date = LocalDate.now()
                .plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id='date'] input")
                .doubleClick()
                .sendKeys(date);

        $("[data-test-id='name'] input")
                .setValue("Иванов Иван");

        $("[data-test-id='phone'] input")
                .setValue("+79999999999");

        $("[data-test-id='agreement']")
                .click();

        $$("button")
                .findBy(text("Забронировать"))
                .shouldBe(visible)
                .click();

        $("[data-test-id='notification']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно!"))
                .shouldHave(text("Встреча успешно забронирована на " + date));
    }
}