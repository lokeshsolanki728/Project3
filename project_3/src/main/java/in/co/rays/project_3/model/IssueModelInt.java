package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.IssueDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface IssueModelInt {
	public long add(IssueDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(IssueDTO dto)throws ApplicationException;
	public void update(IssueDTO dto)throws ApplicationException,DuplicateRecordException;
	public IssueDTO findByPK(long pk)throws ApplicationException;
	public IssueDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(IssueDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(IssueDTO dto)throws ApplicationException;
	
}
