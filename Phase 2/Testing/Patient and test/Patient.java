package projectphase2;

import java.io.*;
import java.util.ArrayList;


/**
 * A Patient
 *
 */

public class Patient {
	
	  /** This Patient's health card number */
	private String healthCardNum;
	  /** This Patient's name */
	private String name;
	  /** This Patient's date of birth */
	private String dateOfBirth;
	
	/**
	 * Creates a Patient named name with birthday dateOfBirth and a health
	 * card number healthCardNum.
	 * @param healthCardNum the health card number of the new Patient
	 * @param name the name of the new Patient
	 * @param dateOfBirth the birthday of the new Patient	
	 */
	public Patient(String healthCardNum, String name, String dateOfBirth){
		this.healthCardNum = healthCardNum;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * Returns a String array consisted of the personal information of 
	 * this Patient, including health card number, name, and birthday.
	 * @return a String array of this Patient's personal information.
	 */
	public String[] getInfo(){
		String[] info = {healthCardNum, name, dateOfBirth};
		return info;
	}
	
	/**
	 * Add visitRecord to this Patient's visit records in the visit_records.
	 * The new visitRecord is added above all other records.
	 * @param visitRecord the new visit record of this Patient
	 */
	public void addVisitRecord(String[] visitRecord){
		// Prepare the line to be inserted
		String visitRecordStr = "";
		for (String s: visitRecord){visitRecordStr += s;}
		visitRecordStr += "/n";
		
		try {
			File fr = new File(AppConstant.V_RECORDS);
			RandomAccessFile raf = new RandomAccessFile(fr, "rw");

			// Search for the startpoint of this Patient's visit records
			String line = raf.readLine();
			while (!line.equals(this.healthCardNum)){line = raf.readLine();}
			long marker = raf.getFilePointer(); //this Patient's healthCardNum

			// Copy the rest of the file
			ArrayList<String> linesToOverwrt = new ArrayList<String>();
			line = raf.readLine();
			while (line != null) {
				linesToOverwrt.add(line);
				line = raf.readLine();
			}

			// Start Overwriting the file from marker
			int totalLines = linesToOverwrt.size();
			int ind = 0;
			String toWrite;
			raf.seek(marker);
			raf.writeChars(visitRecordStr);
			line = raf.readLine();
			while (ind < totalLines) {
				toWrite = linesToOverwrt.get(ind) + "\n";
				raf.writeChars(toWrite);
				line = raf.readLine();
				ind++;
			}raf.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a String array containing the visit record on visitDate of 
	 * this Patient. Visit record is prepared in an order of visit date, age, 
	 * temperature, systolc blood rate, diastolic blood rate, and heartrate. 
	 * @param visitDate the date when this Patient visit the hospital
	 * @return the visit record of this Patient on specified visitDate
	 */
	public String[] getVisitRecord(String visitDate){
		try {
			FileReader fr = new FileReader(AppConstant.V_RECORDS);
			BufferedReader br = new BufferedReader(fr);
			
			// Find the visit records of this Patient
			String line  = br.readLine();
			while (!(line.equals(healthCardNum))){
				line = br.readLine();
			} 
			
			// Find the record of this Patient on visitDate
			String[] record = line.split(",");
			while (!(visitDate.equals(record[0]))){
				line = br.readLine();
				record = line.split(",");
				// If the visit record on visitDate doesn't exist.
				if (line.length() == 6){
					System.out.println("Visit Record Not Found");
					return null; 
				}
			} br.close();
			
			return record;
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns an array of String arrays containing all the visit records of
	 * this Patient. Each visit record (the subarray) is an array of strings 
	 * in an order of visit date, age, temperature, systolc blood rate, 
	 * diastolic blood rate, and heartrate.
	 * @return all the visit records of this Patient.
	 */
	public String[][] getVisitRecords(){
		try {
			FileReader fr = new FileReader(AppConstant.V_RECORDS);
			BufferedReader br = new BufferedReader(fr);
			
			// Find the visit records of this Patient
			String line  = br.readLine();
			while (!(line.equals(healthCardNum))){
					line = br.readLine();
			} 
			// when healthnum is not in records, get new String[][].
			if (line == null){
				return new String[][]{};
			// Save records into visitRecordsList
			}else {
				line = br.readLine();
				ArrayList<String[]> visitRecordsList = new ArrayList<String[]>();
				String[] record;
				while (line != null && line.length() != 6){
					//!TODO...
					record = line.split(",");
					visitRecordsList.add(record);
					line = br.readLine();
				} br.close();
				String[][] visitRecords = new String[visitRecordsList.size()][6];
				for (int i=0; i<visitRecordsList.size(); i++){
					visitRecords[i] = visitRecordsList.get(i);
				}return visitRecords;
			}
		}catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns a String containing the urgency level of a Patient at on a 
	 * visitRecord. The urgency level is generated based on the Hospital Policy.
	 * @param visitRecord a visit record of this Patient
	 * @return a urgency level of this Patient on a specified visit record.
	 */
	public String urgentLevel(String[] visitRecord){

		double tem = Double.parseDouble(visitRecord[2]);
		int age = Integer.parseInt(visitRecord[1]);
		int systolic = Integer.parseInt(visitRecord[3]);
		int diastolic = Integer.parseInt(visitRecord[4]);
		int heartrate = Integer.parseInt(visitRecord[5]);
		int point = 0;
		String level = null;
		
		// Calculate urgent point
		if (tem >= 39.0) {
			point = point + 1;
		}if (age < 2) {
			point = point + 1;
			System.out.println(point);
		}if (systolic >= 140 || diastolic >= 90) {
			point = point + 1;
		}if (heartrate >= 100 || heartrate <= 50) {
			point = point + 1;
		}
		
		// Distinguish urgent level
		if (point == 3 || point == 4) {
			level = "Urgent";
		} else if (point == 2) {
			level = "Less Urgent";
		} else if (point <= 1) {
			level = "Non Urgent";
		}
		return level;

	}
	public static void main(String [] args) {
		Patient p = new Patient("452133", "Andrew", "1980-07-14");
		System.out.println(p.getVisitRecords()[0][0]);
	}
}

