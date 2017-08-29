package io.github.joyoungc.mybatis.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import io.github.joyoungc.common.model.User;

@Component
public class UserDao {

	private final SqlSession sqlSession;

	public UserDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public User getUser(String userId) {
		return this.sqlSession.selectOne("getUser", userId);
	}

}
