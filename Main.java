public class Main {
	public static void main(String[] args) {
			final int MAX_PRICE = 2000;
            final int MIN_NUM = 100_000_0000;
            final int ROWS = 100;
            String table = "students";

            String basePath = ".\\resources\\";

			FileLoader bLoader = new FileLoader();
			String[] departments = bLoader.load(basePath + "departments.txt");
			String[] emails  = bLoader.load(basePath + "emails.txt");
			String[] firstNames = bLoader.load(basePath + "first-names.txt");
            String[] lastNames = firstNames;			
			String[] phoneNumbers = generateRandomNumberList(MIN_NUM, MIN_NUM*2, ROWS);
            String[] ages = generateRandomNumberList(18, 100, ROWS);
            String[] randNumbers = generateRandomNumberList(0, 200, ROWS);
            String[] years = generateRandomNumberList(1, 4, ROWS);

            //Order is important
            String[][] studentTableValues = {
                firstNames,
                lastNames,
                emails,
                phoneNumbers,
                ages,
                randNumbers, //Credits
                years
            };

			int rows = 10;
			int currentPKValue = 1;
			
			DataLoader dPop = new DataLoader(studentTableValues, table, currentPKValue);
			//dPop.testFunction();
            dPop.populateDatabase(10);
            currentPKValue = dPop.getCurrentPKvalue();
			
		}

    static String[] generateRandomNumberList(int min, int max, int rows){
        String[] holder = new String[rows];
        for(int i = 0; i < rows; i++){
            holder[i] = Integer.toString( (int)(Math.random()* (max - min + 1)) + min );
        }

        return holder;
    }
}