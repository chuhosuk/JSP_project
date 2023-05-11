package pj.mvc.jsp.dto;

import java.sql.Date;
import java.sql.Timestamp;

//DTO (Data Transfer Object) : setter -> 멤버변수 -> getter
//멤버변수 = 컬럼명 = input 태그명
public class CustomerDTO {
	private String id;
	private String password;
	private String name;
	private Date birthday;
	private String address;
	private String hp;
	private String email;
	private Timestamp regDate;
	private String cartNo;
	
	public CustomerDTO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}
	public String getCartNo() {
		return cartNo;
	}
	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", password=" + password + ", name=" + name + ", birthday=" + birthday
				+ ", address=" + address + ", hp=" + hp + ", email=" + email + ", regDate=" + regDate + ", cartNo="
				+ cartNo + "]";
	}
	
	
}


