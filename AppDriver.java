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
/**
 * @param hrs hrs becomes the intitial global int hours
 */
	public AppDriver(int hrs) {
		hours = hrs;
	}
/**
* Find a  text file and reads the data into the global lookup 2d Array
*/
	public void addDataToLookup() {
		try {
			String fileName ="lookupdata.txt";
			Scanner infile = new Scanner(new FileReader(fileName)); // creating scanner to read the file
				infile.useDelimiter(":|\r?\n|\r"); // certain pattern
				noVehicles = infile.nextInt();
				lookup = new int[noVehicles][3];
				for (int i = 0; i < noVehicles; i++){ // reading all the roads in the file
					int noVeh = infile.nextInt(); // the number of verhicles also acting as an id
					int minHour = infile.nextInt(); // the minimum hour of service for the no of trucks 
					int maxHour = infile.nextInt(); // the maximum hour of service for the no of trucks 
					lookup[i][0] = noVeh;
					lookup[i][1] = minHour;
					lookup[i][2] = maxHour;
			}
				System.out.println("Data Loaded"); // testing if the data was loaded 
				infile.close();
			}
			catch(FileNotFoundException e) {
				System.out.println(e.getMessage()); //  if the files was not found print message
			}
	}

/**
* @param noVeh The parameter is used to find the data in the lookup array acting as an id
* @param hr The parameter is used to compare the minimum and maximum service required comparing with the dat form the lookup array
* @return String returns an appropriate response from the entered parameters
*/
	private String findData(int noVeh,int hr) {
		String inOrOut = ""; // whether the no of hrs parameter is within the standard service time
		String toBeReturned = ""; // to be returned through the method
		try {
				if(hr >= lookup[noVeh-1][1] && hr <= lookup[noVeh-1][2]){ // checking the maximum and minumum time of service agaist the lookup array
					inOrOut = "within";
				}
				else {
					inOrOut = "outside";
				}

		}
		catch(ArrayIndexOutOfBoundsException e){ // if the entered noVeh parameter is too little or too much by the size of the lookp array an error message will be returned though the method
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

/**
* @return Sring a string is reutned thought the method
*/
	private String guiOutput(){
		return "";
	}
	
/**
* This method is used to test if the data was loaded correctly into the lookup array
*/
	private void printTestData(){ 
		for(int i = 0; i<noVehicles;i++){
				System.out.println(lookup[i][0]+":"+lookup[i][1]+":"+lookup[i][2]);
		}
	}

/**
* @return int an int is returned through the method
*/
	public int getNoVehicles() {
		return noVehicles;
	}

/**
* @param noVeh is passed through to the method
* @param noHr is passed through to the method
* @return String is returned though the method
*/
	public String run(int noVeh,int noHr){
		//printTestData();
		return findData(noVeh,noHr);
	}

}
