package com.company;

import java.util.Arrays;

public class Student {
	
	private int id;
	private String name;
	private char gender;
	private double gpa;
	private StudentRecord[] records;
	private static Student[] createdStudents = new Student[]{};

	/**
	 * Constructor for Student, Used to create a new Student when called
	 * @param id - ID of the student we are creating
	 * @param name - Name of student we are creating
	 * @param gender - Gender of student we are creating
	 */
	public Student(int id, String name, char gender){
		Student[] students = Student.createdStudents;
		boolean not_valid = false;
		// Constructor for Student

		// ensure neither ID nor name are null (ID cannot be null due to code design)
		if(name==null){not_valid = true;}

		// check ID is not taken by any other student
		for(Student item:students){
			if(item.id == id){not_valid = true;}
		}

		// ensure the gender is valid(M,F,X or Empty char)
		if (!(gender == 'M' | gender == 'F' | gender == 'X' | gender =='\0')){not_valid = true;}

		// If the data is all valid, then finish off instantiating the object, otherwise throw an error.
		if (not_valid){
			throw new IllegalArgumentException();
		}else{
			// Set variables to the correct data
			this.id = id;
			this.name = name;
			this.gender = gender;
			this.gpa = 0;
			this.records = new StudentRecord[]{};
			Student.addStudent(this);
		}
	}

	/**
	 * Calculates the GPA of the student and saves it into the private attribute gpa
	 */
	public void calcGPA(){
		//If there are no records, then set the GPA to 0, as it cannot be null
		if (this.records.length == 0){
			this.gpa = 0;
		}else{
			//calculate GPA from records in this.records
			// set total and counter to 0
			double total = 0;
			int counter = 0;
			// Iterate through all the students records
			for (StudentRecord record : this.records){
				// Add the records final score to the total
				total += record.GetFinalScore();
				// increment the counter
				counter++;
			}
			// Set the new GPA to total/counter (the average)
			this.gpa = total/counter;
		}
	}

	/**
	 * Enrolls the Student to a Module given the Module and the Marks the student has achieved
	 */
	public void enrollTo(Module m, double[] marks) {
		//marks is an array of doubles as found in the csv file and from inputs.

		// Create the new StudentRecord
		StudentRecord myRecord = new StudentRecord(this, m);

		// Add the marks to the record
		myRecord.AddMarks(marks);

		//Add the record to the module and this student
		this.addRecord(myRecord);
		m.addRecord(myRecord);
	}

	/**
	 * GETTER METHOD FOR THE GPA
	 * @return the GPA of this student
	 */
	public double getGPA(){
		return this.gpa;
	}

	/**
	 * Method that sorts the records into ascending order of data
	 * @return an array of all the ORDERED student records
	 */
	public StudentRecord[] getOrderedRecords() {
		// Make a copy of records

		// Make an empty StudentRecord array of the same length as the length of the studenst records array
		StudentRecord[] copy = new StudentRecord[this.records.length];

		// Copy over all the records into the copy array
		int index=0;
		for(StudentRecord r: this.records){
			copy[index] = r;
			index++;
		}

		//Initialize an array res, for the result
		StudentRecord[] res;

		if (this.records.length == 1) {
			// If the length of records is 1 then make res the records array as there is no order
			res = this.records;
		} else {
			res = new StudentRecord[]{};
			while(res.length != this.records.length){
				// While the res array isnt full
				// Set pos and counter to 0, and earliest to the first value in copy
				StudentRecord earliest = copy[0];
				int pos=0;
				int counter = 0;
				// For the rest of the records in copy
				for (StudentRecord r : copy) {
					// If the year is less than the earliest
					if (r.getModule().getYear() < earliest.getModule().getYear()) {
						// make earliest the current record
						earliest = r;
						// set position to the current counter value
						pos = counter;
					}else if (r.getModule().getYear() == earliest.getModule().getYear()) {
						// If the years of earliest and the current vale are equal, check the terms
						if (r.getModule().getTerm() < earliest.getModule().getTerm()) {
							// If the current term is less than the earliest term
							// make earliest the current record
							earliest = r;
							// set position to the current counter value
							pos = counter;
						}
					}
					// Increment counter
					counter++;
				}
				// Add earliest to result
				res = StudentRecord.append(res, earliest);
				// remove earliest from copy using the pos
				copy = StudentRecord.remove(copy, pos);
			}
		}
		// Return the res array
		return res;
	}

	/**
	 * STATIC METHOD TO ADD A STUDENT TO THE CLASS ARRAY OF CREATED STUDENTS
	 * @param s - the Student to add to the static variable belonging to the class
	 */
	public static void addStudent(Student s){
		// Create new array of length one greater than the current array and make it a copy of the original array
		Student.createdStudents = Arrays.copyOf(Student.createdStudents, Student.createdStudents.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		Student.createdStudents[Student.createdStudents.length - 1] = s;
	}

	/**
	 * METHOD TO ADD A RECORD TO THE METHODS ARRAY OF STUDENT RECORDS
	 * @param record- StudentRecord we are adding to the students array of records
	 */
	public void addRecord(StudentRecord record){
		// Create new array of length one greater than the current array and make it a copy of the original array
		this.records = Arrays.copyOf(this.records, this.records.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		this.records[this.records.length - 1] = record;
		this.calcGPA();
	}

	/**
	 * METHOD TO PRINT THE TRANSCRIPT OF A GIVEN STUDENT AS REQUIRED
	 * @return the String of the generated transcript of the correct layout
	 */
	public String printTranscript(){
		String res = "University of Knowledge - Official Transcript";
		res += "\n\n\n";
		res += ("ID: "+this.id + "\n");
		res += ("Name: "+this.name+ "\n");
		res += ("GPA: "+this.gpa+ "\n\n");
		//iterate through this.StudentRecords and generate ordered string
		StudentRecord[] orderedRecords = this.getOrderedRecords();
		for (int i=0;i<orderedRecords.length;i++){
			res += orderedRecords[i];
			res += "\n";
			//if the next record has a different year or term, then add another \n
			if(i<orderedRecords.length-1){
				if (orderedRecords[i].getModule().getYear() != orderedRecords[i+1].getModule().getYear()){
					res += "\n";
				}else if (orderedRecords[i].getModule().getTerm() != orderedRecords[i+1].getModule().getTerm()){
					res += "\n";
				}
			}
		}
		return res;
	}
}
