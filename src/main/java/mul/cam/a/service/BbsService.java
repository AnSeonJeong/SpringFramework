package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsService {
	List<BbsDto> bbslist(BbsParam bbs);
	
	int getAllBbs(BbsParam bbs);
	
	BbsDto getBbsdetail(int seq);
	
	void readcount(int seq);
	
	boolean bbswrite(BbsDto dto);
	
	boolean updateBbs(BbsDto dto);
	
	boolean deleteBbs(int seq);	
	
	void updateAnswer(int seq);
	
	boolean insertAnswer(BbsDto dto);
	
	List<BbsComment> getCommentList(int seq);
	
	boolean insertComment(BbsComment bbs);
}
