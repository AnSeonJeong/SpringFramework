package mul.cam.a.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

public interface PdsService {
	List<PdsDto> pdslist(PdsParam param);
	// List<PdsDto> pdslist();
	
	boolean uploadPds(PdsDto dto);
	
	boolean downCount(int seq);
	
	boolean readCount(int seq);
	
	PdsDto getPds(int seq);
	
	boolean updatePds(PdsDto dto);
}
