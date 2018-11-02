//	This is javadoc of the Main menu of the app.
//	The user can view all patients in a list view by clicking on "View All Patients" button
//	or enter the health card number of a patient in the edittext field and 
//	view the PatientProfile menu by clicking on "Find Patient" button.

package group0930.hospitaltriage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AppConstant.context = getApplicationContext();
		AppConstant.p_records_path = getFilesDir() + "/patient_records.txt";
		AppConstant.v_records_path = getFilesDir() + "/visit_records.txt";
		AppConstant.prescriptions_path = getFilesDir() + "/prescriptions.txt";
		AppConstant.passwords_path = getFilesDir() + "/passwords.txt";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
		
	}
	
	
	public void viewallpatient(View view) {
		// Specifies the next Activity to move to: PatientList.
		 Intent intent = new Intent(this, PatientList.class);
		 		 
		 startActivity(intent); // Starts PatientList activity.
	}
	
	public void viewprofile(View view) {
		
		Intent intent = new Intent(this, PatientProfile.class);

		EditText hcntext = (EditText) findViewById(R.id.typehcn);
		String hcn = hcntext.getText().toString();

		intent.putExtra("patientnumber", hcn);

		startActivity(intent); 
	}
	
}
