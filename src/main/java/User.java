
public class User {
	
	public int id;
	public String firstName;
	public String lastName;
	public char gender;
	public int age;
	
	
	public User(int id, String firstName, String lastName, char gender, int age) {
		this(id, firstName, lastName);
		this.gender = gender;
		this.age = age;
	}
	
	
	public User(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public char getGender() {
		return gender;
	}
	
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	
	public int getAge() {
		return age;
	}
	
	
	public void setAge(int age) {
		this.age = age;
	}
}
