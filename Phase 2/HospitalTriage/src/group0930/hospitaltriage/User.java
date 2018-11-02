package group0930.hospitaltriage;

import java.io.*;

public class User {
	
	/**
	 * Returns a Patient object which is constructed by using the data 
	 * of a patient, with the given healthCardNum, in patient_records.
	 * @param healthCardNum the health card number of a Patient
	 * @return a Patient who has a health card number of healthCardNum
	 */
	public Patient getPatient(String healthCardNum) {
		try {
			InputStream inpStream = AppConstant.context.getResources().openRawResource(R.raw.patient_records);
			InputStreamReader inpStreamReader = new InputStreamReader(inpStream);
			BufferedReader br = new BufferedReader(inpStreamReader);
			String[] data;
			String line = br.readLine();

			while (line != null) {
				data = line.split(",");
				// Search the data of the patient with healthCardNum
				if (data[0].equals(healthCardNum)) {
					br.close();
					return new Patient(data[0], data[1], data[2]);
				}
				line = br.readLine();
			}
			br.close();
			return null;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns an array of String arrays containing all the patients' data
	 * in patient_records. Each subarray contains three string objects, which
	 * represent the Patient's health card number, name, and date of birth, 
	 * respectively.
	 * @return all the patient's data in patient_records
	 */
	public String[][] getPatientsData(){
		try {
			int lineCount = this.countLines(AppConstant.P_RECORDS);
			FileReader fr = new FileReader(AppConstant.P_RECORDS);
			BufferedReader br = new BufferedReader(fr);
			
			String[][] patientsData = new String[lineCount][];
			String line = br.readLine();
			int lineNum = 0;
			// Save the file into an array from top to bottom.
			while (line != null){
				String[] data = line.split(",");
				patientsData[lineNum] = data;
				line = br.readLine();
				lineNum++;
			} 
			br.close();
			return patientsData;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Private method for counting lines in a text file from the given filepath
	private int countLines(String filepath){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filepath));
			int count = 0;
			while (reader.readLine() != null) count ++;
			reader.close();
			return count;
		} 
		catch (IOException e){
			e.printStackTrace();
			return -1;
		}
	}
}

