package kr.infotake.cmm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

	@Autowired
	private BaseDao baseDao;

	/**
	 * 단일 조회
	 */
	public <T> T get(String namespace, String id, Object parameter) {
		return baseDao.selectOne(namespace + "." + id, parameter);
	}

	/**
	 * 목록 조회
	 */
	public <T> List<T> list(String namespace, String id, Object parameter) {
		return baseDao.selectList(namespace + "." + id, parameter);
	}

	/**
	 * 삽입
	 */
	public int insert(String namespace, String id, Object parameter) {
		return baseDao.insert(namespace + "." + id, parameter);
	}

	/**
	 * 수정
	 */
	public int update(String namespace, String id, Object parameter) {
		return baseDao.update(namespace + "." + id, parameter);
	}

	/**
	 * 삭제
	 */
	public int delete(String namespace, String id, Object parameter) {
		return baseDao.delete(namespace + "." + id, parameter);
	}
}