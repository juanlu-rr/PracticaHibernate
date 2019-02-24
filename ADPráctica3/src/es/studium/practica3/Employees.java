package es.studium.practica3;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employees {
	
	@Id
	@Column(name = "emp_no")
	private int emp_no;
	@Column(name = "birth_date")
	private Date birth_date;
	@Column(name = "first_name ")
	private String first_name ;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "gender")
	private String gender;
	@Column(name = "hire_date")
	private Date hire_date;
	
	public Employees() {
		emp_no = 0;
		birth_date =  new Date();
		first_name = "";
		last_name = "";
		gender = "";
		hire_date = new Date();
	}
	
	public Employees(int emp_no, Date birth_date, String first_name, String last_name, String gender, Date hire_date) {
		this.emp_no = emp_no;
		this.birth_date = birth_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.hire_date = hire_date;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	
}


