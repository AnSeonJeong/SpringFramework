package mul.cam.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {
	@Autowired
	SqlSession session;
	
	String ns = "Bbs.";

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		return session.selectList(ns + "bbslist", bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return session.selectOne(ns + "getAllBbs", bbs);
	}

	@Override
	public BbsDto getBbsdetail(int seq) {
		return session.selectOne(ns + "getBbsdetail", seq);
	}

	@Override
	public int bbswrite(BbsDto dto) {
		return session.insert(ns + "bbswrite", dto);
	}

	@Override
	public int updateBbs(BbsDto dto) {
		return session.update(ns + "updateBbs", dto);
	}

	@Override
	public int deleteBbs(int seq) {
		return session.update(ns + "deleteBbs", seq);
	}

	@Override
	public int readcount(int seq) {
		return session.update(ns + "readcount", seq);
	}

	@Override
	public int updateAnswer(int seq) {
		return session.update(ns + "updateAnswer", seq);
	}

	@Override
	public int insertAnswer(BbsDto dto) {
		return session.insert(ns + "insertAnswer", dto);
	}

	@Override
	public int insertComment(BbsComment bbs) {
		return session.insert(ns + "insertComment", bbs);
	}

	@Override
	public List<BbsComment> getCommentList(int seq) {
		return session.selectList(ns + "getCommentList", seq);
	}
}
