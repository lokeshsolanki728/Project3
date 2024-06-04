package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.JobRequirementDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.exception.RecordNotFoundException;

public interface JobModelInt {
	public long add(JobRequirementDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(JobRequirementDTO dto)throws ApplicationException;
	public void update(JobRequirementDTO dto)throws ApplicationException,DuplicateRecordException;
	public JobRequirementDTO findByPK(long pk)throws ApplicationException;
	public JobRequirementDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(JobRequirementDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(JobRequirementDTO dto)throws ApplicationException;
	

}
