package group0930.hospitaltriage;

import java.io.*;

/**
 * A User
 */

public class User {
	
	/**
	 * Returns a Patient object which is constructed by using the data 
	 * of a patient, with the given healthCardNum, in patient_records.
	 * @param healthCardNum the health card number of a Patient
	 * @return a Patient who has a health card number of healthCardNum
	 */
	public Patient getPatient(String healthCardNum) {
		try {
			FileReader fr = new FileReader(new File(AppConstant.p_records_path));
			BufferedReader br = new BufferedReader(fr);
			
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
			return new Patient("Patient Not Found", "", "");
		} 
		catch (IOException e) {
			e.printStackTrace();
			return new Patient("Patient Not Found", "", "");
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
			int lineCount = this.countLines(AppConstant.p_records_path);
			FileReader fr = new FileReader(new File(AppConstant.p_records_path));
			BufferedReader br = new BufferedReader(fr);

			String[][] patientsData = new String[lineCount][];
			String line = br.readLine();
			int lineNum = 0;
			String[] data;

			// Save the file into an array from top to bottom.
			while (line != null){
				data = line.split(",");
				patientsData[lineNum] = data;
				line = br.readLine();
				lineNum++;
			} 
			br.close();
			return patientsData;
		} 
		catch (IOException e) {
			e.printStackTrace();
			String[][] emptyPData = new String[1][3];
			emptyPData[0] = new String[]{"No Patient's Data available", "", ""};
			return emptyPData;
		}
	}

	// Private method for counting lines in a text file from the given filepath
	private int countLines(String filePath){
		try {
			FileReader fr = new FileReader(new File(filePath));
			BufferedReader reader = new BufferedReader(fr);
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
	
	public String[][] getSavedAccounts(){
		try{
			int lineCounts = countLines(AppConstant.passwords_path);
			FileReader fr = new FileReader(new File(AppConstant.passwords_path));
			BufferedReader reader = new BufferedReader(fr);
			
			
			String[][] accounts = new String[lineCounts][3];
			int lineNum = 0;
			String line = reader.readLine();
			while(line != null){
				accounts[lineNum] = line.split(",");
				line = reader.readLine();
			}reader.close();
			return accounts;
		}catch(IOException e){
			e.printStackTrace();
			String[][] emptyAccount = new String[1][3];
			emptyAccount[0] = new String[]{"", "", ""};
			return emptyAccount;
		}
	}
	
	public String checkAccount(String[] accountInfo){
		String[][] allAccounts = this.getSavedAccounts();
		String checkMessage = "";
		for (String[] account: allAccounts){
			if (account[0] == accountInfo[0] && account[1] == accountInfo[1]){
				checkMessage = "Correct";
				break;
			} else if (account[0] == accountInfo[0]){
				checkMessage = "Incorrect password for the input account ID"; 
			} else{
				checkMessage = "This account does not exist.";
			}
		}
		return checkMessage;
	}
	
	public String getAccountType(String[] accountInfo){
		String[][] allAccounts = this.getSavedAccounts();
		for (String[] account: allAccounts){
			if (account[0] == accountInfo[0] && account[1] == accountInfo[0]){
				return account[2];
			} 
		}
		return "";
	}
}



