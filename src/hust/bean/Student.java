package hust.bean;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private Integer id;
	private String name;
	private Card card;     //一对一
	private Grade grade;   //一对多（多对一）双向关联
	private List<Course> courses = new ArrayList<Course>();//多对多双向关联（Hibernate中需要有一个与中间表关联的属性，Mybatis不需要；感觉这个courses也没什么用，一切都是通过sql来保证）
	
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
}
