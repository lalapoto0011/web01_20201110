package service;

import java.util.List;

import dao.UserDAO;
import vo.UserVO;

public class UserServiceImpl implements UserService {
	
	private UserDAO dao = null;

	public UserServiceImpl() {
//		super();	//지워도 자동으로 호출 됨
	}

	public UserServiceImpl(UserDAO dao) {
//		super();
		this.dao = dao;
	}

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<UserVO> UserList() {
		return dao.UserList();
	}

	@Override
	public void UserAdd(UserVO vo) {
		dao.UserAdd(vo);
		
	}

	@Override
	public void UserDelete(String id) {
		dao.UserDelete(id);
	}

	@Override
	public void UserUpdate(UserVO vo) {
		dao.UserUpdate(vo);
	}

	@Override
	public UserVO getUser(String id) {
		return dao.getUser(id);
	}

	@Override
	public List<UserVO> searchUser(String condition, String keyword) {
		return dao.searchUser(condition, keyword);
	}


}
