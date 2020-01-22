package com.taylor.taylorapp.bmi;

import static java.lang.Integer.parseInt;

// BMI Calculator in Java
	// Uses metric units
	public class BMICalculatorInMetric {
		
		static double fic = 18.5;
		static double fic2 = 25.0;
		static double fic3 = 30.0;
		public String bmifix;

	public String getBmifix() {
		return bmifix;
	}

	public void setBmifix(String bmifix) {
		this.bmifix = bmifix;
	}

	
	 public double getWeight() {
			return weight;
		}
	 
		public void setWeight(double weight) {
			this.weight = weight;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
		}

	private double weight;
	 private double height;
		
		
		public String calculateBMI(String weight, String height) throws Exception {
	         
	        // multiplication by 100*100 for cm to m conversion
			double w = parseInt(weight);
			double h = parseInt(height);
	    	double bminumb = (100 * 100 * w)/(h * h);


			
	        if(bminumb < fic) {
	           	return "You are fitted: " + bminumb + " mass.";
	        }else if (bminumb < fic2) {
	        	
	        	return "You are medium: " + bminumb + " mass.";
	        }else if (bminumb < fic3) {
	        	return "You are large: " + bminumb + " mass.";
	        }else {
	        	return " " + bminumb + " mass.";
	        }

	        
	        
	    
	         
	    }
	     

	}

