package com.company;

import java.util.Arrays;

public class University {

	public ModuleDescriptor[] moduleDescriptors = new ModuleDescriptor[]{};
	public Student[] students = new Student[]{};
	public Module[] modules = new Module[]{};

	/**
	 * @return The number of students registered in the system.
	 */
	public int getTotalNumberStudents() {
		return this.students.length;
	}

	/**
	 * @return The student with the highest GPA.
	 */
	public Student getBestStudent() {
		Student best;
		if (this.students.length > 1){
			// FIND BEST STUDENT (IF THERE ARE >1 BEST STUDENTS, THE BEST ONES ARE STORED IN AN ARRAY AT THE END
			// OF THE FUNCTION HOWEVER NOT RETURNED AS THE RETURN TYPE FOR THE FUNCTION WAS STUDENT, NOT ARRAY, AND
			// I DIDNT WANT TO CHANGE THIS AS IT WAS ALREADY GIVEN)

			// Create empty array for the result
			Student[] multipleBestStudents = new Student[]{this.students[0]};
			// assume the best student is the first one
			best = this.students[0];

			// Iterate through the rest of the students in the array of Students
			for(Student s:this.students){
				if (s.getGPA() > best.getGPA()){
					// If the current student in the array has a better GPA:

					// make best, the current student
					best = s;
					// reset the best students array to hold the current students
					multipleBestStudents = new Student[]{s};
				}
				if (s.getGPA() == best.getGPA()){
					// Otherwise, if the current students in the array has an equal GPA
					// to the current best students:

					// Add this current student to the best students array
					// Create new array of length one greater than the current array and make it a copy of the original array
					multipleBestStudents = Arrays.copyOf(multipleBestStudents, multipleBestStudents.length + 1);
					// Add the new element to the last index of the new array (which should be empty)
					multipleBestStudents[multipleBestStudents.length - 1] = s;
				}
			}
			// return the value in best (the best student)
			return best;
		}else if(this.students.length == 1){
			// If there is only one student, its the best by default
			return this.students[0];
		}else{
			// If there are no students, there can be no best student
			return null;
		}

	}

	/**
	 * @return The module with the highest average score.
	 */
	public Module getBestModule() {
		Module best;
		if (this.modules.length > 1){
			// FIND BEST MODULE (IF THERE ARE >1 BEST MODULES, THE BEST ONES ARE STORED IN AN ARRAY AT THE END
			// OF THE FUNCTION HOWEVER NOT RETURNED AS THE RETURN TYPE FOR THE FUNCTION WAS MODULE, NOT ARRAY, AND
			// I DIDNT WANT TO CHANGE THIS AS IT WAS ALREADY GIVEN)

			// Create empty array for the result
			Module[] multipleBestModules = new Module[]{this.modules[0]};
			// assume the best module is the first one
			best = this.modules[0];

			// Iterate through the rest of the modules in the array of Modules
			for(Module m:this.modules){
				if (m.getFinalAverageGrade() > best.getFinalAverageGrade()){
					// If the current module in the array has a better final average grade:

					// make best, the current module
					best = m;
					// reset the best modules array to hold the current module
					multipleBestModules = new Module[]{m};
				} else if (m.getFinalAverageGrade() == best.getFinalAverageGrade()){
					// Otherwise, if the current module in the array has an equal final average grade
					// to the current best module:

					// Add this current Module to the best modules array
					// Create new array of length one greater than the current array and make it a copy of the original array
					multipleBestModules = Arrays.copyOf(multipleBestModules, multipleBestModules.length + 1);
					// Add the new element to the last index of the new array (which should be empty)
					multipleBestModules[multipleBestModules.length - 1] = m;
				}
			}

			// return the value in best (the best module)
			return best;
		}else if(this.modules.length == 1){
			// If there is only one module, its the best by default
			return this.modules[0];
		}else{
			// If there are no modules, there can be no best module
			return null;
		}
	}

	/**
	 * Main Class for the University
	 */
	public static void main(String[] args) {
		//Create the new University under the variable university
		University university = new University();

		//CREATE ALL NEW STUDENTS AND ADD THEM TO THE ARRAY
		university.addStudent(new Student(1000, "Ana", 'F'));
		university.addStudent(new Student(1001, "Oliver", 'M'));
		university.addStudent(new Student(1002, "Mary", 'F'));
		university.addStudent(new Student(1003, "John", 'M'));
		university.addStudent(new Student(1004, "Noah", 'M'));
		university.addStudent(new Student(1005, "Chico", 'M'));
		university.addStudent(new Student(1006, "Maria", 'F'));
		university.addStudent(new Student(1007, "Mark", 'X'));
		university.addStudent(new Student(1008, "Lia", 'F'));
		university.addStudent(new Student(1009, "Rachel", 'F'));

		//CREATE ALL MODULE DESCRIPTORS AND ADD THEM TO THE ARRAY
		university.addModuleDescriptor(new ModuleDescriptor("ECM0002", "Real World Mathematics", new double[]{0.1, 0.3, 0.6}));
		university.addModuleDescriptor(new ModuleDescriptor("ECM1400", "Programming", new double[]{0.25, 0.25, 0.25, 0.25}));
		university.addModuleDescriptor(new ModuleDescriptor("ECM1406", "Data Structures", new double[]{0.25, 0.25, 0.5}));
		university.addModuleDescriptor(new ModuleDescriptor("ECM1410", "Object-Orientated Programming", new double[]{0.2, 0.3, 0.5}));
		university.addModuleDescriptor(new ModuleDescriptor("BEM2027", "Information Systems", new double[]{0.1, 0.3, 0.3, 0.3}));
		university.addModuleDescriptor(new ModuleDescriptor("PHY2023", "Thermal Physics", new double[]{0.4, 0.6}));

		//CREATE ALL MODULES
		university.addModule(new Module(university.moduleDescriptors[1], 2019, (byte) 1));
		university.addModule(new Module(university.moduleDescriptors[5], 2019,(byte) 1));
		university.addModule(new Module(university.moduleDescriptors[4], 2019,(byte) 2));
		university.addModule(new Module(university.moduleDescriptors[1], 2019,(byte) 2));
		university.addModule(new Module(university.moduleDescriptors[2], 2020,(byte) 1));
		university.addModule(new Module(university.moduleDescriptors[3], 2020,(byte) 1));
		university.addModule(new Module(university.moduleDescriptors[0], 2020,(byte) 2));

		//ENROLL ALL STUDENTS TO THE CORRECT MODULE and pass in array of marks
		university.students[0].enrollTo(university.modules[0], new double[]{9,10,10,10});
		university.students[1].enrollTo(university.modules[0], new double[]{8,8,8,9});
		university.students[2].enrollTo(university.modules[0], new double[]{5,5,6,5});
		university.students[3].enrollTo(university.modules[0], new double[]{6,4,7,9});
		university.students[4].enrollTo(university.modules[0], new double[]{10,9,10,9});
		university.students[5].enrollTo(university.modules[1], new double[]{9,9});
		university.students[6].enrollTo(university.modules[1], new double[]{6,9});
		university.students[7].enrollTo(university.modules[1], new double[]{5,6});
		university.students[8].enrollTo(university.modules[1], new double[]{9,7});
		university.students[9].enrollTo(university.modules[1], new double[]{8,5});
		university.students[0].enrollTo(university.modules[2], new double[]{10,10,9.5,10});
		university.students[1].enrollTo(university.modules[2], new double[]{7,8.5, 8.2, 8});
		university.students[2].enrollTo(university.modules[2], new double[]{6.5, 7, 5.5, 8.5});
		university.students[3].enrollTo(university.modules[2], new double[]{5.5, 5, 6.5, 7});
		university.students[4].enrollTo(university.modules[2], new double[]{7,5,8,6});
		university.students[5].enrollTo(university.modules[3], new double[]{9,10,10,10});
		university.students[6].enrollTo(university.modules[3], new double[]{8,8,8,9});
		university.students[7].enrollTo(university.modules[3], new double[]{5,5,6,5});
		university.students[8].enrollTo(university.modules[3], new double[]{6,4,7,9});
		university.students[9].enrollTo(university.modules[3], new double[]{10,9,8,9});
		university.students[0].enrollTo(university.modules[4], new double[]{10,10,10});
		university.students[1].enrollTo(university.modules[4], new double[]{8,7.5,7.5});
		university.students[2].enrollTo(university.modules[4], new double[]{9,7,7});
		university.students[3].enrollTo(university.modules[4], new double[]{9,8,7});
		university.students[4].enrollTo(university.modules[4], new double[]{2,7,7});
		university.students[5].enrollTo(university.modules[4], new double[]{10,10,10});
		university.students[6].enrollTo(university.modules[4], new double[]{8,7.5,7.5});
		university.students[7].enrollTo(university.modules[4], new double[]{10,10,10});
		university.students[8].enrollTo(university.modules[4], new double[]{9,8,7});
		university.students[9].enrollTo(university.modules[4], new double[]{8,9,10});
		university.students[0].enrollTo(university.modules[5], new double[]{10,9,10});
		university.students[1].enrollTo(university.modules[5], new double[]{8.5, 9, 7.5});
		university.students[2].enrollTo(university.modules[5], new double[]{10,10,5.5});
		university.students[3].enrollTo(university.modules[5], new double[]{7,7,7});
		university.students[4].enrollTo(university.modules[5], new double[]{5,6,10});
		university.students[5].enrollTo(university.modules[6], new double[]{8,9,8});
		university.students[6].enrollTo(university.modules[6], new double[]{6.5, 9, 9.5});
		university.students[7].enrollTo(university.modules[6], new double[]{8.5, 10, 8.5});
		university.students[8].enrollTo(university.modules[6], new double[]{7.5, 8,10});
		university.students[9].enrollTo(university.modules[6], new double[]{10,6,10});

		//TESTING OUTPUTS AS REQUIRED
		// Print Reports
		System.out.println("The UoK has " + university.getTotalNumberStudents() + " students.");

		// best module
		System.out.println("The best module is:");
		System.out.println(university.getBestModule());

		// best student
		System.out.println("The best student is:");
		System.out.println(university.getBestStudent().printTranscript());
	}

	/**
	 * Add Module to the universities Module Array
	 * @param m - m is the Module we are adding to the Module[] modules
	 */
	public void addModule(Module m){
		// Create new array of length one greater than the current array and make it a copy of the original array
		this.modules = Arrays.copyOf(this.modules, this.modules.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		this.modules[this.modules.length - 1] = m;
	}

	/**
	 * Add Student to the universities Student Array
	 * @param s - s is the Student we are adding to the Student[] students
	 */
	public void addStudent(Student s){
		// Create new array of length one greater than the current array and make it a copy of the original array
		this.students = Arrays.copyOf(this.students, this.students.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		this.students[this.students.length - 1] = s;
	}

	/**
	 * Add Module Descriptor to the universities Module Descriptor Array
	 * @param d - d is the ModuleDescriptor we are adding to the ModuleDescriptor[] moduleDescriptors
	 */
	public void addModuleDescriptor(ModuleDescriptor d){
		// Create new array of length one greater than the current array and make it a copy of the original array
		this.moduleDescriptors = Arrays.copyOf(this.moduleDescriptors, this.moduleDescriptors.length + 1);
		// Add the new element to the last index of the new array (which should be empty)
		this.moduleDescriptors[this.moduleDescriptors.length - 1] = d;
	}
}
