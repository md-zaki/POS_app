public class staff {

	private int employeeId;
	private char gender;
	private String name;
	private String jobTitle;
	public staff(int employeeId, char  gender, String name, String job)
	{
		this.employeeId = employeeId;
		this.gender = gender;
		this.name = name;
		this.jobTitle = job;
	}
	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

}