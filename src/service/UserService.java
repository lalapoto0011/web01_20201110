package service;

import java.util.List;

import vo.UserVO;

public interface UserService {

	public List<UserVO> UserList();
	
	public void UserAdd(UserVO vo);
	
	public void UserDelete(String id);
	
	public void UserUpdate(UserVO vo);
	
	public UserVO getUser(String id);
	
	public List<UserVO> searchUser(String condition, String keyword);
	
	
	
	
}
