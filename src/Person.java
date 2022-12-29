/**
 * 
 */

/**
 * @author hohsh
 *
 */
public class Person {
	private String name;
	private boolean maleFlag;
	private int age;

	public String getName() {
		return this.name;
	}

	public boolean isMale() {
		return this.maleFlag;
	}

	public int getAge() {
		return this.age;
	}

	public Person(String name, boolean maleFlag, int age) {
		this.name = name;
		this.maleFlag = maleFlag;
		this.age = age;
	}

}
