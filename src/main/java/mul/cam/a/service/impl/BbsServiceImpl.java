package mul.cam.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		return dao.bbslist(bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return dao.getAllBbs(bbs);
	}

	@Override
	public BbsDto getBbsdetail(int seq) {
		return dao.getBbsdetail(seq);
	}

	@Override
	public boolean bbswrite(BbsDto dto) {
		int count = dao.bbswrite(dto);
		return count>0?true:false;
	}

	@Override
	public boolean updateBbs(BbsDto dto) {
		int count = dao.updateBbs(dto); 
		return count>0?true:false;
	}

	@Override
	public boolean deleteBbs(int seq) {
		int count = dao.deleteBbs(seq); 
		return count>0?true:false;
	}

	@Override
	public void readcount(int seq) {
		dao.readcount(seq);
	}

	@Override
	public void updateAnswer(int seq) {
		dao.updateAnswer(seq);
	}

	@Override
	public boolean insertAnswer(BbsDto dto) {
		int count = dao.insertAnswer(dto);
		return count>0?true:false;
	}

	@Override
	public List<BbsComment> getCommentList(int seq) {
		return dao.getCommentList(seq);
	}

	@Override
	public boolean insertComment(BbsComment bbs) {
		int count = dao.insertComment(bbs);
		return count>0?true:false;
	}
}
