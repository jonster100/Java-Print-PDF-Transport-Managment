package continuas_effective_managment;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class AppDriver {
	private int hours;
	private int noVehicles;
	private int[][] lookup;

	public AppDriver(int noVeh,int hrs) {
		hours = hrs;

	}

	public void addDataToLookup() {
		try {
			String fileName ="lookupdata.txt";
			Scanner infile = new Scanner(new FileReader(fileName)); // creating scanner to read the file
				infile.useDelimiter(":|\r?\n|\r"); // certain pattern
				noVehicles = infile.nextInt();
				lookup = new int[noVehicles][3];
				for (int i = 0; i < noVehicles; i++){ // reading all the roads in the file
					int noVeh = infile.nextInt();
					int minHour = infile.nextInt();
					int maxHour = infile.nextInt();
					lookup[i][0] = noVeh;
					lookup[i][1] = minHour;
					lookup[i][2] = maxHour;
			}
				System.out.println("Data Loaded");
				infile.close();
			}
			catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
	}

	private String findData(int noVeh,int hr) {
		String inOrOut = "";
		String toBeReturned = "";
		try {
				if(hr >= lookup[noVeh-1][1] && hr <= lookup[noVeh-1][2]){
					inOrOut = "within";
				}
				else {
					inOrOut = "outside";
				}

		}
		catch(ArrayIndexOutOfBoundsException e){
			toBeReturned = "Entered No Vehicles Out of Bounds Limit is 1-51";
		}
		if(inOrOut == "within" || inOrOut == "outside"){
			String localVeh = noVeh +"";
			if(noVeh == noVehicles){
				localVeh = noVeh + " or more";
			}
			toBeReturned ="The Transport Manager spends approximately " + hr + " hours a week "
			     + "carrying out transport management duties. With a fleet consisting "
			     + "of " + localVeh + " vehicles, this is "+ inOrOut +" the guidlines issued by the Senior "
			     + "Traffic Commissioner. The guidlines recommend that the "
			     + "person responsible for the transport should spend approximately "
			     + lookup[noVeh-1][1] + " to " + lookup[noVeh-1][2] + " hours per week.";//within;
		}

		return toBeReturned;
	}



	private String guiOutput(){
		return "";
	}

	private void printTestData(){
		for(int i = 0; i<noVehicles;i++){
				System.out.println(lookup[i][0]+":"+lookup[i][1]+":"+lookup[i][2]);
		}
	}

	public int getNoVehicles() {
		return noVehicles;
	}


	public String run(int noVeh,int noHr){
		//printTestData();
		return findData(noVeh,noHr);
	}

}
