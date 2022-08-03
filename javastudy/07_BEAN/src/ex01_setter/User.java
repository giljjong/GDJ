package ex01_setter;
import lombok.Getter;
import lombok.Setter;


public class User {
	
	private int useNo;
	private String id;
	private String email;
	
	public int getUseNo() {
		return useNo;
	}
	public void setUseNo(int useNo) {
		this.useNo = useNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [useNo=" + useNo + ", id=" + id + ", email=" + email + "]";
	}
	

	
	}

