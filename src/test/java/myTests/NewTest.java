package myTests;
import com.codeborne.selenide.Condition;
import lombok.Data;
import lombok.Value;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@Value
@Data
public class NewTest {

    @Test
    void shouldLogin() throws SQLException {
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue("vasya");
        $("[data-test-id=password] input").setValue("qwerty123");
        $(By.tagName("button")).click();
        $(withText("Интернет")).waitUntil(Condition.visible, 15000);//убедились что зашли в личный кабинет


       // val getSmsCodes = "SELECT code FROM app.auth_codes FOR UPDATE;";
        val getSmsCodes = "SELECT FROM app.auth_codes ORDER BY code desc;";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass");
                val getSmsCodeStmt = conn.createStatement()

        ) {
            try (val rs = getSmsCodeStmt.executeQuery(getSmsCodes)) {
                if (rs.next()) {

                }
                val code = rs.getInt(1);
                    $("[data-test-id=code] input").setValue(String.valueOf(code));
                    $(By.tagName("button")).click();
                    $("[data-test-id=dashboard]").waitUntil(Condition.visible, 15000);
            }
             }
        }
@AfterEach
    void cleanUp() throws SQLException{
        TablesCleaner.cleanTable();
}
    }







