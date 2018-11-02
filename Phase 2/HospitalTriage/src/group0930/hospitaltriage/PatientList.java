package group0930.hospitaltriage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PatientList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_list);
//		
//		populatelist();
//		viewprofile ();
//	
	  }
		
	
	private void populatelist() {
		String [] tempdata = {"Patient 1","Patient 2","Patient 3","Patient 4","Patient 5","Patient 6"};
		
	
		
		ArrayAdapter<String> pladapter = new ArrayAdapter<String>(this,R.layout.patientinlist,tempdata );
		
		ListView list = (ListView) findViewById(R.id.patientlist);
		list.setAdapter(pladapter);
	}
	
	
	private void viewprofile () {
		ListView list = (ListView) findViewById(R.id.patientlist);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				TextView txt = (TextView) view;
				String message = "Viewing profile of "+ txt.getText().toString();
				Toast.makeText(PatientList.this,message,Toast.LENGTH_LONG).show();
				
				
				Intent intent = new Intent(PatientList.this, PatientProfile.class);
				startActivity(intent);		
			}
		});
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_list, menu);
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
}
