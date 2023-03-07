package mul.cam.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

@Repository
public class PdsDaoImpl implements PdsDao {
	@Autowired
	SqlSessionTemplate session;
	
	String ns = "Pds.";
	@Override
	public List<PdsDto> pdslist(PdsParam param) {
		return session.selectList(ns + "pdslist", param);
	}
	/*
	@Override
	public List<PdsDto> pdslist() {
		return session.selectList(ns + "pdslist");
	}
	 */

	@Override
	public int uploadPds(PdsDto dto) {
		return session.insert(ns + "uploadPds", dto);
	}

	@Override
	public int downCount(int seq) {
		return session.update(ns + "downCount", seq);
	}

	@Override
	public int readCount(int seq) {
		return session.update(ns + "readCount", seq);
	}
	
	@Override
	public PdsDto getPds(int seq) {
		return session.selectOne(ns + "getPds", seq);
	}

	@Override
	public int updatePds(PdsDto dto) {
		return session.update(ns + "updatePds", dto);
	}

}
