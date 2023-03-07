package mul.cam.a.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

public interface PdsDao {
	// List<PdsDto> pdslist();
	List<PdsDto> pdslist(PdsParam param);
	
	int uploadPds(PdsDto dto);
	
	int downCount(int seq);
	
	int readCount(int seq);
	
	PdsDto getPds(int seq);
	
	int updatePds(PdsDto dto);
}
