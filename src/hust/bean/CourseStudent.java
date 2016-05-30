package hust.bean;

/**
 * 学生-课程中间表
 * @author liangjian
 *
 */
public class CourseStudent {

	private Integer cid;
	private Integer sid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
}
