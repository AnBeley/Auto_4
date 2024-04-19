import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    @Test
    void shouldSendCorrectForm() {

        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + dateOfDelivery));
    }

    @Test
    void shouldNotSendInCorrectNameLatin() {

        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Ivanov Ivan");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void shouldNotSendInCorrectNameSymbol() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input").setValue("Иванов=Сидоров Иван");
        $("[data-test-id=phone] input").setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
        @Test
        void shouldNotSendInCorrectNameVoid() {
            open("http://localhost:9999");

            $("[data-test-id=city] input"). setValue("Москва");

            $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
            LocalDate date = LocalDate.now();
            LocalDate delivery = date.plusDays(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String dateOfDelivery = formatter.format(delivery);
            $("[data-test-id=date] input").setValue(dateOfDelivery);

            $("[data-test-id=name] input"). setValue("");
            $("[data-test-id=phone] input"). setValue("+79252330000");
            $("[data-test-id=agreement]").click();
            $$("button").find(Condition.exactText("Забронировать")).click();
            $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendInCorrectNameSpace() {
            open("http://localhost:9999");

            $("[data-test-id=city] input"). setValue("Москва");

            $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
            LocalDate date = LocalDate.now();
            LocalDate delivery = date.plusDays(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String dateOfDelivery = formatter.format(delivery);
            $("[data-test-id=date] input").setValue(dateOfDelivery);

            $("[data-test-id=name] input"). setValue(" ");
            $("[data-test-id=phone] input"). setValue("+79252330000");
            $("[data-test-id=agreement]").click();
            $$("button").find(Condition.exactText("Забронировать")).click();
            $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendInCorrectPhoneWithoutPlus() {
        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input"). setValue("79252330000");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendInCorrectPhoneWithSymbols() {
        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input"). setValue("+7(925) 233 00 00");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendInCorrectPhoneVoid() {
        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input"). setValue("");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendWithoutAgreement() {
        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

   @Test
   void shouldNotSendTodayDate() {
   open("http://localhost:9999");

    $("[data-test-id=city] input"). setValue("Москва");

    $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateOfDelivery = formatter.format(date);
    $("[data-test-id=date] input").setValue(dateOfDelivery);

    $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
    $("[data-test-id=phone] input"). setValue("+79252330000");
    $("[data-test-id=agreement]").click();
    $$("button").find(Condition.exactText("Забронировать")).click();
    $(".input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
}

    @Test
     void shouldNotSendEarlyDate() {
        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москва");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNotSendWrongCity() {
        open("http://localhost:9999");

        $("[data-test-id=city] input"). setValue("Москв");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        LocalDate delivery = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfDelivery = formatter.format(delivery);
        $("[data-test-id=date] input").setValue(dateOfDelivery);

        $("[data-test-id=name] input"). setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }


}
