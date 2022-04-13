import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataLoader {
	//final private String loremText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
	
	private String[][] dataValues;
	private int[] dataValuesLens;
	
	private int productCount, len;
	private String table;
	

	private String sqlQuestionMarks = "?,";
	private Connection connection;
	private DBHandler handler;
	
	public DataLoader(String[][] dataValues, String table) {
		this.dataValues = dataValues;
		this.table = table;

		len = dataValues.length; 
		this.dataValuesLens = new int[len];
		
		//Please test me before running.
		sqlQuestionMarks.repeat(len); // create the ?,?,?,? in the insert statement portion. 
		sqlQuestionMarks =  sqlQuestionMarks.substring(0, len-2); //remove extra comma. 
		//Please test me before running.
		
		fillValuesLength();

		handler = new DBHandler();
	}
	
	private int getRandomNumber(int min, int max) {
		return (int)(Math.random()* (max- min + 1)) + min; 
	}
	
	//Please explain me.
	private void fillValuesLength(){
		
		for(int i = 0; i < len; i++){
			dataValuesLens[i] = dataValues[i].length;
		}
	}

	public void populateDatabase(int rows) {
		try {
			connection = handler.getConnection();
			
			System.out.println("Connected With the database successfully.");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into " + table +" values("+ sqlQuestionMarks +")");
			
			
			for(int i = 0; i < rows; i++) {
				
				for(int j = 0 ; j < len; j++){
					preparedStatement.setString((j+1), dataValues[j][getRandomNumber(0, dataValuesLens[j] )]);
				}
			
				// productCount += 1;
				
				preparedStatement.executeUpdate();
			}
			
		}catch(Exception e) {
			System.out.print("Error in connection to database: ");
			e.printStackTrace();
		}
	}
	
	
	//Create setter methods
	 
}
