package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.ClientDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ClientModelInt;
import in.co.rays.project_3.model.IssueModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.RoleModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

/**
 * user functionality controller.to perform add,delete and update operation
 * 
 * @author Lokesh Solanki
 *
 */
@WebServlet(urlPatterns = { "/ctl/ClientCtl" })
public class ClientCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The log. */
	private static Logger log = Logger.getLogger(ClientCtl.class);

	protected void preload(HttpServletRequest request) {
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Low", "Low");
		map.put("Medium", "Medium");
		map.put("High", "High");
		request.setAttribute("map", map);
		try {
			List list = model.list();
			request.setAttribute("roleList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name "));
			System.out.println(pass);
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Name must contains alphabets only");
			System.out.println(pass);
			pass = false;
		}else if(request.getParameter("name").length()>20) {
			request.setAttribute("name", "Name must contains only 20 alphabets");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			System.out.println(pass);
			pass = false;
		} 
			if (DataValidator.isNull(request.getParameter("phone"))) {
				request.setAttribute("phone", PropertyReader.getValue("error.require", "mobile No"));
				pass = false;
			} else if (!DataValidator.isPhoneNo(request.getParameter("phone"))) {
				request.setAttribute("phone", "Please Enter Valid Mobile Number");
				pass = false;
			}	
		
			if (DataValidator.isNull(request.getParameter("version"))) {
				request.setAttribute("version", PropertyReader.getValue("error.require", "version"));
				System.out.println(pass);
				pass = false;
			}
		if (DataValidator.isNull(request.getParameter("priority"))) {
			request.setAttribute("priority", PropertyReader.getValue("error.require", "priority"));
			pass = false;
		}
		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		ClientDTO dto = new ClientDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setDate(DataUtility.getDate(request.getParameter("date")));
		dto.setAddress(DataUtility.getString(request.getParameter("address")));
		dto.setPhone(DataUtility.getString(request.getParameter("phone")));
		dto.setPriority(DataUtility.getString(request.getParameter("priority")));
		try {
		dto.setVersion(Double.parseDouble((request.getParameter("version"))));
		}catch(NumberFormatException e){
			System.out.println("invalid vaersion");
		}
		populateBean(dto, request);

		log.debug("UserRegistrationCtl Method populatedto Ended");

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("JobCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		ClientModelInt model = ModelFactory.getInstance().getClientModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			ClientDTO dto;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		
		ClientModelInt model = ModelFactory.getInstance().getClientModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			ClientDTO dto = (ClientDTO) populateDTO(request);
			System.out.println(" in do post method jkjjkjk++++++++" + dto.getId());
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
						ServletUtility.setErrorMessage("Title already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Title already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			ClientDTO dto = (ClientDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.CLIENT_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CLIENT_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CLIENT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("JobCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		return ORSView.CLIENT_VIEW;
	}

}
