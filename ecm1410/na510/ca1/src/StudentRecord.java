package com.company;

import java.util.Arrays;

public class StudentRecord {

	private Student student;
	private Module module;
	private double[] marks;
	private double finalScore;
	private Boolean isAboveAverage;

	/**
	 * Constructor for StudentRecord, Used to create a new StudentRecord when called
	 * @param student - Student we are creating the record for
	 * @param module - Module we are creating the record for
	 */
	public StudentRecord(Student student, Module module){
		//Make sure this same student doesnt have another record for this module
		for(StudentRecord rec:module.getRecords()){
			if(student == rec.student){
				throw new IllegalArgumentException();
			}
		}

		// Save the Required data
		this.student = student;
		this.module = module;
	}

	/**
	 * Allows the user to add marks to the record
	 * @param marks - Array of marks for the student
	 * Adds Marks to the studentRecord
	 */
	public void AddMarks(double[] marks){
		// Assume values are valid
		boolean notValid = false;
		// Get the weights from the module this record is for
		double[] weights = module.getModuleDescriptor().getContinuousAssignmentWeights();
		double total = 0.00;

		if(!(weights.length == marks.length)){
			//unable to calculate final score due to non 1-1 relation between weightings and marks
			System.out.println("WEIGHTS NOT EQUAL TO LENGTH");
			notValid = true;
		}else{
			//calculate total AND check conditions
			for(int i=0;i<=weights.length-1;i++){
				total += weights[i] * marks[i];
				// Condition to make sure marks are not <0 or >100
				if(marks[i] < 0 || marks[i] > 100){
					System.out.println("MARKS LESS THAN 0 OR GREATER THAN 100");
					notValid = true;
				}
			}
		}

		// make temporary variable finalScore equal to toal
		double finalScore = total;
		// Check the validity of finalScore
		if (notValid == true || finalScore<0 || finalScore>100){
			throw new IllegalArgumentException();
		}else{
			// Save Data
			this.finalScore = finalScore;
			this.marks = marks;
		}
		//UPDATE STUDENTS GPA
		module.resetAverage();
	}

	/**
	 * Checks if the Students final score is above or below the module average
	 */
	public void AboveAvgCheck(){
		if(this.finalScore <= module.getFinalAverageGrade()){
			this.isAboveAverage = false;
		}else{
			this.isAboveAverage = true;
		}
	}

	/**
	 * GETTER METHOD FOR THE MODULE THAT THE RECORD IS FOR
	 * @return the Module belonging to the record
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * GETTER METHOD FOR THE FINAL SCORE
	 * @return The final score of this record
	 */
	public double GetFinalScore() {
		return this.finalScore;
	}

	/**
	 * STATIC METHOD TO ADD A RECORD TO AN EXISTING STUDENTRECORD ARRAY
	 * @param array - Array of records we are adding to
	 * @param record - Record we are adding to the array
	 * @return the new array of records with the added record
	 */
	public static StudentRecord[] append(StudentRecord[] array, StudentRecord record){
		// Create new array of length one greater than the current array and make it a copy of the original array
		StudentRecord[] res = Arrays.copyOf(array, array.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		res[res.length - 1] = record;
		return res;
	}

	/**
	 * STATIC METHOD TO REMOVE A RECORD TO AN EXISTING STUDENTRECORD ARRAY
	 * @param array - Array of records we are removing from
	 * @param pos - Position of the array we want to remove
	 * @return - New Array with the removed value
	 */
	public static StudentRecord[] remove(StudentRecord[] array, int pos){
		// Copy all items to a new array of length one less than the original array, skipping
		// the position which is specified
		StudentRecord[] res = new StudentRecord[]{};
		for(int i=0;i<array.length;i++){
			if(i!=pos){
				res = StudentRecord.append(res, array[i]);
			}
		}
		return res;
	}

	@Override
	public String toString() {
		Module m = this.module;
		String res = "| ";
		res += m.getYear() + " | ";
		res += m.getTerm() + " | ";
		res += m.getCode() + " | ";
		res += this.finalScore + " |";
		return res;
	}
}
