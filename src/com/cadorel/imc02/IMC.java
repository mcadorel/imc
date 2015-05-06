package com.cadorel.imc02;

public class IMC {
	private double value;
	private String label;
	private String idConseil;;
	private String idAssocie;

	
	// Constructeurs
	public IMC() {}
	public IMC(double value)
	{
		this.value = value;
		
		// Interprétation de l'IMC pour établir un label
		if (this.value < 16.5) {
			this.label = "Dénutrition";
			this.idConseil = "lt165tip";
			this.idAssocie = "lt165";
		}
		else if (this.value < 18.5) {
			this.label = "Maigreur";
			this.idConseil = "lt185tip";
			this.idAssocie = "lt185";
		}
		else if (this.value < 25) {
			this.label = "Corpulence moyenne";
			this.idConseil = "lt25tip";
			this.idAssocie = "lt25";
		}
		else if (this.value < 30) {
			this.label = "Surpoids";
			this.idConseil = "lt30tip";
			this.idAssocie = "lt30";
		}
		else if (this.value < 35) {
			this.label = "Obésité modérée";
			this.idConseil = "lt35tip";
			this.idAssocie = "lt35";
		}
		else if (this.value < 40) {
			this.label = "Obésité sévère";
			this.idConseil = "lt40tip";
			this.idAssocie = "lt40";
		}
		else {
			this.label = "Obésité massive";
			this.idConseil = "gt40tip";
			this.idAssocie = "gt40";
		}
	}

	// Accesseurs, mutateurs
	public double getValue() {return this.value;}
	public void setValue(double value) {this.value = value;}
	public String getLabel() {return this.label;}
	public String getIdAssocie() {return this.idAssocie;}
}
