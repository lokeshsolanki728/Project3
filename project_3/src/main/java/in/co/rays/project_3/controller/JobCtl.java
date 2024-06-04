package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.JobRequirementDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.BankModelInt;
import in.co.rays.project_3.model.JobModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.RoleModelInt;
import in.co.rays.project_3.model.UserModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

/**
 * user functionality controller.to perform add,delete and update operation
 * @author Lokesh Solanki
 *
 */
@WebServlet(urlPatterns = { "/ctl/JobCtl" })
public class JobCtl extends BaseCtl {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The log. */
	private static Logger log = Logger.getLogger(JobCtl.class);

	protected void preload(HttpServletRequest request) {
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		try {
			List list = model.list();
			request.setAttribute("roleList", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
					
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("-------------validate started-------------");
		
		if (DataValidator.isNull(request.getParameter("title"))) {
			request.setAttribute("title", PropertyReader.getValue("error.require", "title "));
			System.out.println(pass);
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("title"))) {
			request.setAttribute("title", "Title must contains alphabets only");
			System.out.println(pass);
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("client"))) {
			request.setAttribute("client", PropertyReader.getValue("error.require", "client Name"));
			System.out.println(pass);
			pass = false;
		
	
		}if (DataValidator.isNull(request.getParameter("jobd"))) {
			request.setAttribute("jobd", PropertyReader.getValue("error.require", "JobDiscription"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "date"));
			pass = false;
		}
		
		System.out.println(request.getParameter("dob"));
		System.out.println("validate end ");
		System.out.println(pass);
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		JobRequirementDTO dto = new JobRequirementDTO();
		
         
         System.out.println(request.getParameter("dob"));      
   
		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setTitle(DataUtility.getString(request.getParameter("title")));
		dto.setOpenDate(DataUtility.getDate(request.getParameter("date")));
		dto.setClient(DataUtility.getString(request.getParameter("client")));
		dto.setJobDiscription(DataUtility.getString(request.getParameter("jobd")));
		populateBean(dto,request);
		
		log.debug("UserRegistrationCtl Method populatedto Ended");

		return dto;

	}
	/**
	 * Contain Display Logics.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("JobCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		JobModelInt model = ModelFactory.getInstance().getJobModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			JobRequirementDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}
	/**
	 * Contain Submit Logics.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("-------------------------------------------------------------------------dopost run-------");
		// get model
		JobModelInt model = ModelFactory.getInstance().getJobModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {
			JobRequirementDTO dto = (JobRequirementDTO) populateDTO(request);
              System.out.println(" in do post method jkjjkjk++++++++"+dto.getId());
			try {
				if (id > 0) {
					model.update(dto);
					
					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {
					
					try {
						 model.add(dto);
						 ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						log.error(e);
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Client already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);
				
				
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Client already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			JobRequirementDTO dto = (JobRequirementDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.JOB_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.JOB_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.JOB_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("JobCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.JOB_VIEW;
	}

}

