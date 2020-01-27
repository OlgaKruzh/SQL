package myTests;
import lombok.val;
import org.junit.jupiter.api.Test;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TablesCleaner {


    public static void cleanTable() throws SQLException {
        val deleteCardTransaction = "DELETE FROM card_transactions;";
        val deleteAuthCodes = "DELETE FROM auth_codes;";
        val deleteFromCards = "DELETE FROM cards;";
        val deleteFromUsers = "DELETE FROM users;";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass");
                val deleteCardTransactionStmt = conn.prepareStatement(deleteCardTransaction);
                val deleteAuthCodesStmt = conn.prepareStatement(deleteAuthCodes);
                val deleteCardsStmt = conn.prepareStatement(deleteFromCards);
                val deleteUsersStmt = conn.prepareStatement(deleteFromUsers);
        ) {
              deleteCardTransactionStmt.executeUpdate();
              deleteCardsStmt.executeUpdate();
              deleteAuthCodesStmt.executeUpdate();
              deleteUsersStmt.executeUpdate();

        }
    }

}
