package interfaces;

import java.sql.Connection;

public interface IDatabaseConnection {
	Connection getConnection() throws Exception;
}
