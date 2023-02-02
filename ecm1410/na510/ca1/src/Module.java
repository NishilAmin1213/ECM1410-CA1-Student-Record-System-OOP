package com.company;

import java.util.Arrays;

public class Module {
	
	private int year;
	private byte term;
	private ModuleDescriptor module;
	private StudentRecord[] records;
	private double finalAverageGrade;
	private static Module[] createdModules = new Module[]{};

	/**
	 * Constructor for Module, Used to create a new Module when called
	 * @param moduleDesc - Module Descriptor to base this module off
	 * @param year - Year this module takes place
	 * @param term - Term this module takes place
	 */
	public Module(ModuleDescriptor moduleDesc, int year, byte term){
		Module[] modules = Module.createdModules;
		// Assume that the module is valid
		boolean notValid = false;

		// Make sure no other modules exist with the same descriptor, term and year
		for(Module existingModule:modules){
			// Iterate through the existing modules and check the descriptor, year and term
			if (existingModule.getModuleDescriptor() == moduleDesc && existingModule.getTerm() == term && existingModule.getYear() == year){
				notValid=true;
			}
		}

		// If the data is all valid, then finish off instantiating the object, otherwise throw an error.
		if(notValid==true){
			throw new IllegalArgumentException();
		}else{
			// Set variables to the correct data
			this.year = year;
			this.term = term;
			this.module = moduleDesc;
			this.records = new StudentRecord[]{};
			Module.addModule(this);
		}
	}

	/**
	 * This code is used to re calculate the final Average grade for this module
	 */
	public void resetAverage(){
		// Set the counter and total to 0
		int counter = 0;
		double total = 0.00;

		// Iterate through all the records belonging to the module
		for (StudentRecord rec:this.records){
			// Add the final score of the record to the total
			total += rec.GetFinalScore();
			// increment the counter
			counter++;
		}
		// Set the new final average grade to the total/counter (the average)
		this.finalAverageGrade = total/counter;

		//Reset all students boolean values
		for (StudentRecord rec:this.records){
			rec.AboveAvgCheck();
		}
	}

	/**
	 * GETTER METHOD FOR YEAR
	 * @return the year for this module
	 */
	public int getYear(){
		return this.year;
	}

	/**
	 * GETTER METHOD FOR CODE
	 * @return the Module Code for this module
	 */
	public String getCode(){
		return this.getModuleDescriptor().getCode();
	}

	/**
	 * GETTER METHOD FOR TERM
	 * @return the Term value for this module
	 */
	public byte getTerm(){
		return this.term;
	}

	/**
	 * GETTER METHOD FOR FINAL AVERAGE GRADE
	 * @return The Average Grade of all students on this module
	 */
	public double getFinalAverageGrade(){
		return this.finalAverageGrade;
	}

	/**
	 * GETTER METHOD FOR RECORDS
	 * @return An array of all the StudentRecords for this module
	 */
	public StudentRecord[] getRecords() {
		return records;
	}

	/**
	 * GETTER METHOD FOR THE MODULE DESCRIPTOR
	 * @return The module descriptor for this module
	 */
	public ModuleDescriptor getModuleDescriptor(){
		return this.module;
	}

	/**
	 * METHOD TO ADD A RECORD TO THE METHODS ARRAY OF STUDENT RECORDS
	 * @param record - the StudentRecord we are adding to the records array
	 */
	public void addRecord(StudentRecord record){
		// Create new array of length one greater than the current array and make it a copy of the original array
		this.records = Arrays.copyOf(this.records, this.records.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		this.records[this.records.length - 1] = record;
	}

	/**
	 * STATIC METHOD TO ADD A MODULE TO THE CLASS ARRAY OF CREATED MODULES
	 * @param m - the Module to add to the static variable belonging to the class
	 * this keeps track of all previously created modules to ensure no duplicates are made
	 */
	public static void addModule(Module m){
		// Create new array of length one greater than the current array and make it a copy of the original array
		Module.createdModules = Arrays.copyOf(Module.createdModules, Module.createdModules.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		Module.createdModules[Module.createdModules.length - 1] = m;
	}
	
	@Override
	public String toString() {
		// ALLOWS THE OUTPUT OF MODULE OBJECTS
		return "Module[" +
				"code="+ getModuleDescriptor().getCode()+
				", year=" + year +
				", term=" + term +
				", finalAverageGrade=" + finalAverageGrade +
				']';
	}
}
