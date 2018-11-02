package group0930.hospitaltriage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A User with the authority of a nurse
 */

public class Nurse extends User{

	/**
	 * Add patientRecord to all the patients' records in patient_records.txt. 
	 * The new patientRecord is appended to the bottom of the file.
	 * 
	 * @param patientRecord the new patient's record
	 */
	public void addPatientRecord(String[] patientRecord){
		// Prepare the line to append
		String toAppend = "\n";
		for (String info : patientRecord){
			toAppend += info + ",";
		}
		toAppend = toAppend.substring(0, toAppend.length()-1);
		
		try{
			File fr = new File(AppConstant.p_records_path);
			RandomAccessFile raf = new RandomAccessFile(fr, "rw");
			long end = raf.length();
			raf.seek(end);
			raf.write(toAppend.getBytes());
			raf.close();
		}catch(IOException e){
			e.printStackTrace();	
		}
	}
	
	/**
	 * Returns an array of String array containing all the data of patients who
	 * have not seen by a doctor in patient_records.txt. Each subarray contains
	 * four String objects, which represents a patient's health card number, 
	 * name, date of birth, and urgency level, respectively.
	 * 
	 * @return all the data of patients who have not seen by a doctor
	 */
	public String[][] getToHelpPatients(){
		return null;
	}
}
