package com.company;

import java.util.Arrays;

public class ModuleDescriptor {

	private String code;
	private String name;
	private double[] continuousAssignmentWeights;
	private static ModuleDescriptor[] createdDescriptors = new ModuleDescriptor[]{};

	/**
	 * Constructor for the ModuleDescriptor we are creating
	 * @param code - Module Code for the module we are creating
	 * @param name - Name for the module we are creating
	 * @param continuousAssignmentWeights - Assignment Weights for the module we are creating
	 */
	public ModuleDescriptor(String code, String name, double[] continuousAssignmentWeights){
		ModuleDescriptor[] moduleDescriptors = ModuleDescriptor.createdDescriptors;
		// Assume the parameters are valid
		boolean not_valid = false;
		// Constructor for ModuleDescriptor

		// ensure neither code nor name are null
		if(code==null | name==null){not_valid = true;}

		//check code is not used in another module
		for(ModuleDescriptor item:moduleDescriptors){
			if(item.code == code){not_valid = true;}
		}

		// ensure that the total weightings dont exceed 1.00
		double total_weight = 0;
		for(double val:continuousAssignmentWeights){
			total_weight += val;
			if (val < 0) {not_valid = true;}
		}
		if (total_weight > 1.00){not_valid = true;}

		// If the module is not valid, then throw and exception
		if (not_valid){
			throw new IllegalArgumentException();
		}else{
			// Save the required data
			this.code = code;
			this.name = name;
			this.continuousAssignmentWeights = continuousAssignmentWeights;
			ModuleDescriptor.addModuleDescriptor(this);
		}
	}

	/**
	 * GETTER METHOD FOR THE MODULE CODE
	 * @return the code for this Module Descriptor
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * GETTER METHOD FOR THE ARRAY OF ASSIGNMENT WEIGHTS
	 * @return the array of weights for this module descriptor
	 */
	public double[] getContinuousAssignmentWeights(){
		return continuousAssignmentWeights;
	}

	/**
	 * Adds the new descriptor we are creating to a static variable
	 * to allow us to look for identical copies
	 * @param d - The descriptor we are creating
	 */
	public static void addModuleDescriptor(ModuleDescriptor d){
		ModuleDescriptor.createdDescriptors = Arrays.copyOf(ModuleDescriptor.createdDescriptors, ModuleDescriptor.createdDescriptors.length + 1);
		ModuleDescriptor.createdDescriptors[ModuleDescriptor.createdDescriptors.length - 1] = d;
	}



}
