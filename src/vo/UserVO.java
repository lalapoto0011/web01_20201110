package vo; //VO 대신 DTO, bookRDC가 들어가기도 함.. 같은 말임

//BoardVO, UserVO 등 만들 수 있겠다.
public class UserVO {
	private String id ;
	private String password ; 
	private String name ;
	
	
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + "]";
	} 

	
	
	
	
}
