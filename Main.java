import com.mysql.cj.jdbc.Blob;

public class Main {
    public static void main(String[] args) {
        final int MAX_PRICE = 2000;
        final int MIN_NUM = 100_000_0000;
        final int ROWS = 100;

        String basePath = ".\\resources\\";

        FileLoader bLoader = new FileLoader();
        String[] departments = bLoader.load(basePath + "departments.txt");
        String[] emails = bLoader.load(basePath + "emails.txt");
        String[] firstNames = bLoader.load(basePath + "first-names.txt");
        String[] lastNames = firstNames;
        String[] phoneNumbers = generateRandomNumberList(MIN_NUM, MIN_NUM * 2, ROWS);
        String[] ages = generateRandomNumberList(18, 100, ROWS);
        String[] randNumbers = generateRandomNumberList(0, 200, ROWS);
        String[] years = generateRandomNumberList(1, 4, ROWS);
        String[] courses = bLoader.load(basePath + "courses.txt");
        String[] semesters = { "Fall", "Spring" };
        String[] foundationYears = generateOrderedList(1929, 2018);
        String[] yes_no = { "yes", "no" };
        String[] funds = generateRandomNumberList(1_000_000, 10_000_000, 100); // Hehehe Unlimited budget?
        String[] addresses = bLoader.load(basePath + "addresses.txt");

        // Order is important
        String[][] studentTableValues = {
                firstNames,
                lastNames,
                emails,
                phoneNumbers,
                ages,
                randNumbers, // Credits
                years
        };

        String[] courseIds = generateOrderedList(1, courses.length);
        String[] deptIds = generateOrderedList(1, departments.length); // DepartmentIds
        String[][] facultyTableValues = {
                firstNames,
                lastNames,
                phoneNumbers,
                deptIds,
                courseIds,
                courseIds,
                courseIds,
                courseIds,

        };

        // Although this is wron to do bc coureses have a corresponding dept and should
        // therefore not be chosen at random.
        // for this case it doesn't matter.
        String[][] coursesTableValues = {
                courses,
                deptIds,
                semesters,
                yes_no
        };

        String[][] dptTableValues = {
                departments,
                addresses,
                foundationYears,
                funds
        };

        int rows = 10;
        int currentPKValue = 1; // Must be manually updated
        String table = "faculty";

        DataLoader dPop = new DataLoader(facultyTableValues, table, currentPKValue);
        // dPop.testFunction();
        dPop.populateDatabase(rows);
        currentPKValue = dPop.getCurrentPKvalue();

    }

    static String[] generateRandomNumberList(int min, int max, int rows) {
        String[] holder = new String[rows];
        for (int i = 0; i < rows; i++) {
            holder[i] = Integer.toString((int) (Math.random() * (max - min + 1)) + min);
        }

        return holder;
    }

    static String[] generateOrderedList(int start, int end) {

        int size = end - start;

        String[] holder = new String[size];
        for (int i = 0; i < size; i++) {
            holder[i] = Integer.toString(start + i);
            // System.out.println("Holder[i]: " + holder[i]);
        }
        System.out.println();
        return holder;
    }
}