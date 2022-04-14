import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataLoader {
	// final private String loremText = "Lorem ipsum dolor sit amet, consectetur
	// adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
	// aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
	// nisi ut aliquip ex ea commodo consequat.";

	private String[][] dataValues;
	private int[] dataValuesLens;

	private int currentPKValue, len;

	private String table;

	private String sqlQuestionMarks = "?,";
	private Connection connection;
	private DBHandler handler;

	public DataLoader(String[][] dataValues, String table, int currentPKValue) {
		this.dataValues = dataValues;
		this.table = table;

		len = dataValues.length;
		this.dataValuesLens = new int[len];

		// Please test me before running.
		// create the ?,?,?,? in the insert statement portion.
		// The pk values are not included in the dataValues to account for those by
		// adding 1.
		sqlQuestionMarks = sqlQuestionMarks.repeat(len + 1);
		sqlQuestionMarks = sqlQuestionMarks.substring(0, (len + 1) * 2 - 1); // remove extra comma.
		System.out.println("len: " + len);
		System.out.println("sqlQuestionMarks: " + sqlQuestionMarks);
		// Please test me before running.

		fillValuesLength();

		this.currentPKValue = currentPKValue;

		handler = new DBHandler();
	}

	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	// Please explain me.
	private void fillValuesLength() {

		for (int i = 0; i < len; i++) {
			dataValuesLens[i] = dataValues[i].length;
		}
	}

	public void populateDatabase(int rows) {
		try {
			connection = handler.getConnection();

			System.out.println("Connected With the database successfully.");
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into " + table + " values(" + sqlQuestionMarks + ")");

			int count = 2;
			for (int i = 0; i < rows; i++) {

				preparedStatement.setString(1, Integer.toString(currentPKValue)); // The Primary key must be hardcoded.

				for (int j = 0; j < len; j++) {
					preparedStatement.setString((count), dataValues[j][getRandomNumber(0, dataValuesLens[j] - 1)]);
					count++;
				}

				count = 2;
				currentPKValue += 1;

				preparedStatement.executeUpdate();
			}

		} catch (Exception e) {
			// Refine me. -> instanceof
			System.out.print("Error in connection to database: ");
			e.printStackTrace();
		}
	}

	/*
	 * public void sequentialLoad(int rows){
	 * try {
	 * connection = handler.getConnection();
	 * 
	 * System.out.println("Connected With the database successfully.");
	 * PreparedStatement preparedStatement = connection
	 * .prepareStatement("insert into " + table + " values(" + sqlQuestionMarks +
	 * ")");
	 * 
	 * int count = 2;
	 * for (int i = 0; i < rows; i++) {
	 * 
	 * preparedStatement.setString(1, Integer.toString(currentPKValue)); // The
	 * Primary key must be hardcoded.
	 * 
	 * for (int j = 0; j < len; j++) {
	 * preparedStatement.setString((count), dataValues[j][getRandomNumber(0,
	 * dataValuesLens[j])]);
	 * count++;
	 * }
	 * 
	 * count = 2;
	 * currentPKValue += 1;
	 * 
	 * preparedStatement.executeUpdate();
	 * }
	 * 
	 * } catch (Exception e) {
	 * // Refine me. -> instanceof
	 * System.out.print("Error in connection to database: ");
	 * e.printStackTrace();
	 * }
	 * }
	 */

	public int getCurrentPKvalue() {
		return currentPKValue;
	}

	public void testFunction() {
		try {
			connection = handler.getConnection();
			System.out.println("Connection Succesful.");
		} catch (Exception e) {

			System.err.println("Error Connecting to DB :");
			e.printStackTrace();
			System.exit(1);

		}
	}

	// Create setter methods

}
