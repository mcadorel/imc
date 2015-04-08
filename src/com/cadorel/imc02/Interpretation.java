package com.cadorel.imc02;

import java.util.Objects;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Interpretation extends Activity {
	
	Button back_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interpretation);
		
		// Listeners
		back_button = (Button)findViewById(R.id.btn_retour);
		back_button.setOnClickListener(listener);
		
		Bundle b = getIntent().getExtras();
		String label = b.getString("label");
		String idAssocie = b.getString("idAssocie");
		// Toast.makeText(Interpretation.this, label, Toast.LENGTH_SHORT).show(); // pour le débug
		
		// Colorer en orange le textView du dernier IMC calculé
		try{
			int resId = getResources().getIdentifier(idAssocie, "id", getPackageName());
			TextView tv = (TextView)findViewById(resId);
			tv.setTextColor(Color.parseColor("#FF6600"));
		}
		catch(Exception e){
			Toast.makeText(Interpretation.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interpretation, menu);
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
	
	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.btn_retour:
				Intent i = new Intent();
				setResult(Activity.RESULT_OK, i);
				finish();
			}
		}
	};
}
