package group0930.hospitaltriage;

import java.util.ArrayList;
import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PatientProfile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_profile);
		Intent intent = getIntent();
		String hcn = (String) intent.getSerializableExtra("patientnumber"); 
		User currentuser = new User();
		Patient thepatient = currentuser.getPatient(hcn);
		
		populatelist(thepatient);
		viewrecord();
	}
	
	
	private void populatelist(Patient thisPatient) {

		// Setting thisPatient's info on the View
		String[] info = thisPatient.getInfo();
		TextView hcdTxtV = (TextView) findViewById(R.id.hcn);
		hcdTxtV.append("  "+info[0]);
		TextView nameTxtV = (TextView) findViewById(R.id.name);
		nameTxtV.append("  "+info[1]);
		TextView dobTxtV = (TextView) findViewById(R.id.dob);
		dobTxtV.append("  "+info[2]);
		
		// Setting thisPatient's visit records on the View
		String [][] data = thisPatient.getVisitRecords();

		ArrayList<String> vRecordsAL = new ArrayList<String>();
		String temp = "";
		for (String[] arr: data){
			temp = "";
			temp += arr[0] + "  " + thisPatient.urgentLevel(arr);
			vRecordsAL.add(temp);
		}

		ArrayAdapter<String> ppadapter = new ArrayAdapter<String>(this,R.layout.recordinlist, vRecordsAL);
		ListView list = (ListView) findViewById(R.id.recordlist);
		list.setAdapter(ppadapter);
		
		
//		ArrayList<String> strArr = new ArrayList<String>();
//		String temp = "";
//		for (String[] arr: data){
//			temp = "";
//			for (String s: arr){
//				temp += s;
//			}
//			strArr.add(temp);
//		}
//		
//		int size = strArr.size();
//		String[] strData = new String[size];
//		for (int i=0; i<size; i++){
//			strData[i] = strArr.get(i);
//		}
		
	}
	
	
	private void viewrecord () {
		ListView list = (ListView) findViewById(R.id.recordlist);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				TextView txt = (TextView) view;
				String message = "Back with clicking "+ txt.getText().toString();
				Toast.makeText(PatientProfile.this,message,Toast.LENGTH_LONG).show();
				
				
				Intent intent = new Intent(PatientProfile.this, VisitRecord.class);
				startActivity(intent);		
			}
		});
	}
	
	


}
