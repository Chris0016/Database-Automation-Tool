import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLoader {
	private String filename;
	private List<String> arrListHolder;
	
	public FileLoader() {
		arrListHolder = new ArrayList<String>();
	}

	public String[] load(String filename) {
		arrListHolder.clear();//Method is meant to be reused, therefore existing data from previous transaction should be flushed. 
		
		String  temp  = "";
		
		try {
		Scanner fileIn = new Scanner(new File(filename));
		
		while(fileIn.hasNext()) {
			temp = fileIn.next();
			arrListHolder.add(temp);
		}
		
		fileIn.close();
		
		return toList(arrListHolder);
		
		
		}catch(Exception e) {
			System.out.println("Erro while loading file :");
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}

	
	private String[] toList(List<String> arrList) {
		String[] tempList = new String[arrList.size()];
		tempList = arrList.toArray(tempList);
		return tempList;
	}

}