package in.co.rays.project_3.test;

import in.co.rays.project_3.dto.IssueDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.IssueModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;

public class BankModelTest {
	public static void main(String[] args) throws Exception, Exception {
		testAdd();
	}

	private static void testAdd() throws ApplicationException, Exception {
		//BankDTO dto=new BankDTO();
		IssueDTO dto = new IssueDTO();
		dto.setOpenDate(DataUtility.getDate("09/09/2000"));
		dto.setTitle("nandani");
		dto.setDescription("bad girl nh h");
		dto.setAssignTo("nandani");
		dto.setStatus("active");
		
		IssueModelInt modelint=ModelFactory.getInstance().getIssueModel();
		modelint.add(dto);
		
		
		
		
			//EmployeeModelInt EmployeeModel=ModelFactory.getInstance().getEmployeeModel();
		
		
		
	}

}
