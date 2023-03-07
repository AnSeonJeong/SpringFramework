package mul.cam.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.MemberDao;
import mul.cam.a.dto.MemberDto;

@Repository	// == 저장소
public class MemberDaoImpl implements MemberDao{
	// MyBatis 접근(생성)
	@Autowired 	// 자동생성
	SqlSession session;	// @Autowired를 통해 객체가 자동 생성됨
	
	String ns = "Member.";
	
	@Override
	public List<MemberDto> allMember() {
		return session.selectList(ns + "allMember");	// namespace -> member.allMember
	}

	@Override
	public int idCheck(String id) {
		return session.selectOne(ns + "idCheck", id);
	}

	@Override
	public int addMember(MemberDto dto) {
		return session.insert(ns + "addMember", dto);
	}

	@Override
	public MemberDto login(MemberDto dto) {
		// return session.selectOne(ns + "login", dto);
		MemberDto mem = session.selectOne(ns + "login", dto);
		return mem;
	}

}
