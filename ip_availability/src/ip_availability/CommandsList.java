package ip_availability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CommandsList {
	static int i = 0;
	final static HashMap<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();
	
	public static void main(String[] args) {
		final Scanner in = new Scanner(System.in);
		
		while(i != 1) {
			System.out.print("Enter command: ");
			
			final String command = in.next();			
			final String result = execute(command);

			System.out.println(result);	
		}
		in.close();
	}

	private static String execute(String command) throws IllegalArgumentException {
		final String[] split = command.split(":");	  
		ArrayList<String> arraylist = new ArrayList<String>();
		switch (split[1]) {
		case "login":
			if (hashmap.get(split[0]) == null) {
		    	arraylist = new ArrayList<String>();
			    arraylist.add("1");
			    hashmap.put(split[0], arraylist);
		    } else {
				arraylist = hashmap.get(split[0]);

		    	arraylist.add("1");
			    hashmap.put(split[0], arraylist);
		    }
			return "ok";
		case "logout":
			arraylist = hashmap.get(split[0]);
			if (hashmap.get(split[0]) == null) {
				return "error:notlogged";
			} else if (arraylist.get(arraylist.size()-1) == "1") {
			    arraylist.add("0");
			    hashmap.put(split[0], arraylist);
			    return "ok";
			}
			return "error:notlogged";
		case "info":
			Object value = hashmap.get(split[2]);
			ArrayList<String> isOnline = hashmap.get(split[0]);
			if ((value != null) && (isOnline != null) && (isOnline.get(isOnline.size()-1) == "1")) {
				arraylist = hashmap.get(split[2]);
			} else return "error:notlogged";
		case "shutdown":
			i = 1;
			break;
		default:
			return "error:unknowncommand";
		}
		return "ok";
	}
}