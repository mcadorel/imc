package com.cadorel.imc02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity 
{
	// Déclaration des widgets
	private final String default_result = "Resultat : entrez un poids et une taille.";
	private final String megaString = "Vous faites un poids parfait !";
	Button calc_button 		= null;
	Button clear_button 	= null;
	Button interpreter		= null;
	EditText edit_weight 	= null;
	EditText edit_height 	= null;
	TextView result 		= null;
	RadioGroup scale		= null;
	CheckBox mega_func 		= null;
	
	// Catégories d'IMC
	IMC[] categories = {
	};
	
	// Dernière catégorie d'IMC calculée (pour une session)
	IMC currentIMC = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Création et binding des listeners
    	calc_button = (Button)findViewById(R.id.calc_button);
    	clear_button = (Button)findViewById(R.id.clear_button);
    	interpreter = (Button) findViewById(R.id.interp_button);
    	edit_weight = (EditText)findViewById(R.id.edit_weight);
    	edit_height = (EditText)findViewById(R.id.edit_height);
    	mega_func = (CheckBox)findViewById(R.id.methode);
    	scale = (RadioGroup)findViewById(R.id.choose_scale);
    	result = (TextView)findViewById(R.id.show_result);
    	
    	calc_button.setOnClickListener(listener);
    	clear_button.setOnClickListener(listener);
    	interpreter.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private OnClickListener listener = new OnClickListener()
    {
    	@Override
    	public void onClick(View v)
    	{
    		switch (v.getId())
    		{
    		case R.id.calc_button:
    			// Contrôles de saisie
        		if (edit_height.length() >0 && edit_weight.length() >0)
        		{
        			float hValue = Float.valueOf(edit_height.getText().toString());
        			float wValue = Float.valueOf(edit_weight.getText().toString());
        			
        			if (!mega_func.isChecked())
            		{
        				
        				currentIMC = new IMC(wValue / 
        						(float)Math.pow(
        								(scale.getCheckedRadioButtonId() == R.id.select_cm?hValue/100:hValue), 2
        								));
        				result.setText(String.format("Ton IMC est %s. Cela correspond à : %s.",
        						Double.toString(currentIMC.getValue()),
        						currentIMC.getLabel()));
            		}
            		else
            		{
        				result.setText(megaString);
            		}
        		}
        		else
        		{
        			Toast.makeText(
    						MainActivity.this,
    						"Saisissez votre taille et votre poids.",
    						Toast.LENGTH_SHORT).show();
        			return;
        		}
    			break;
    		case R.id.clear_button:
    			edit_weight.getText().clear();
        		edit_height.getText().clear();
        		result.setText(default_result);
    			break;
    			
    		case R.id.interp_button:
    			// Récupérer la dernière IMC calculée le cas échéant, 
    			// puis afficher le tableau de correspondances 
    			Intent i;
        		i = new Intent(
        				getApplicationContext(),
        				Interpretation.class);
        		if (null != currentIMC)
        		{
        			i.putExtra("label", currentIMC.getLabel());
        			i.putExtra("idAssocie", currentIMC.getIdAssocie());
        		}
        		startActivity(i);
    			break;
    		}
    	}
    };
}
