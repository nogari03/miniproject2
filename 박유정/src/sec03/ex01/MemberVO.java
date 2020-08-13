package sec03.ex01;

import java.sql.Date;

public class MemberVO {
	private String id = "";
	private String cId = "";
	private String pwd = "";
	private String name = "";
	
	public MemberVO() {
		System.out.println("MemberVO 생성자");
	}
	
	
	public MemberVO (String id2, String pwd, String name, String cId) {
		this.id = id2;
		this.pwd = pwd;
		this.name = name;
		this.cId = cId;
		
	}
	
	public String getcId() {
		return cId;
	}
	
	public void setcId(String cId) {
		this.cId = cId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
