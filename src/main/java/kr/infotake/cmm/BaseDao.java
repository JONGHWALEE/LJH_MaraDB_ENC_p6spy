package kr.infotake.cmm;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * 단일 조회 (selectOne)
	 */
	public <T> T selectOne(String statement, Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	/**
	 * 목록 조회 (selectList)
	 */
	public <T> List<T> selectList(String statement, Object parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	/**
	 * 삽입 (insert)
	 */
	public int insert(String statement, Object parameter) {
		return sqlSession.insert(statement, parameter);
	}

	/**
	 * 수정 (update)
	 */
	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	/**
	 * 삭제 (delete)
	 */
	public int delete(String statement, Object parameter) {
		return sqlSession.delete(statement, parameter);
	}
}