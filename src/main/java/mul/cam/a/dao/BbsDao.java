package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsDao {
	List<BbsDto> bbslist(BbsParam bbs);
	
	int getAllBbs(BbsParam bbs);
	
	BbsDto getBbsdetail(int seq);
	
	int readcount(int seq);
	
	int bbswrite(BbsDto dto);
	
	int updateBbs(BbsDto dto);
	
	int deleteBbs(int seq);
	
	int updateAnswer(int seq);
	
	int insertAnswer(BbsDto dto);
	
	List<BbsComment> getCommentList(int seq);
	
	int insertComment(BbsComment bbs);
}
