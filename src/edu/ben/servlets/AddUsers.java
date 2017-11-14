package edu.ben.servlets;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.ben.daos.ProductDao;
import edu.ben.daos.UserDao;
import edu.ben.models.Product;
import edu.ben.models.User;

/**
 * Servlet implementation class AddUsers
 */
@WebServlet("/AddUsers")
public class AddUsers extends Base {
	private static final long serialVersionUID = 1L;
	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUsers() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/addUsers.jsp").forward(request, response);			
		}else {
			errors.add("You are not authorized to access these pages. Please login or talk to site administrator.");
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		String file = "";
		file = saveFile(request,response, user);
		readUserFile(file, user.getUsername());
		
		
		request.setAttribute("errors", errors);
		request.setAttribute("success", success);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/addUsers.jsp").forward(request, response);	
	}
	
	/**
	 * Save uploaded file
	 * @param request request
	 * @param response response
	 * @param user logged in user
	 * @param type user or product to add
	 * @return String representing filepath and name
	 * @throws IOException throws exception
	 */
	private String saveFile(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
		String f = null;
		String uploadPath;
		// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(request)) {
			// if not, we stop here
			PrintWriter writer = response.getWriter();
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return null;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory
		uploadPath = "C:/add/" + user.getUsername() + "/";
		f = uploadPath;

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		try {
			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				int fileNumber = 1;
				for (FileItem item : formItems) {
					// processes only fields that are not form fields
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						f+= fileName;
						if (fileName.contains(".csv")) {
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);

							// saves the file on disk
							item.write(storeFile);

							// RunAnt.runAnt(fileNumber, filePath);
							fileNumber++;
							success.add("Uploaded succesfully.");
							request.setAttribute("success", success);
						}

					}
				}
			}
			return f;
		} catch (Exception ex) {
			System.out.println("failure to upload product file");
			ex.printStackTrace();
			errors.add("An issue occurred uploading file");
			return null;
		}
	}
	
	private void readUserFile(String filename, String username) {
		if (filename != null) {
			File file = new File(filename);
			if (file.exists()) {
				try {
					boolean flag = true;
					Scanner input = new Scanner(file);
					//skip first line
					if(input.hasNext()) {
						input.nextLine();
					}
					while (input.hasNextLine()) {
						String line = input.nextLine();
						String[] userInfo = line.split(",");
						// fname, lanem, email, uname, pwd, islocked
						if (userInfo.length == 7 && checkUser(userInfo)) {
							User insert = new User();
							insert.setFirstName(userInfo[0]);
							insert.setLastName(userInfo[1]);
							insert.setEmail(userInfo[2]);
							insert.setUsername(userInfo[3]);
							insert.setPassword(userInfo[4]);
							insert.setLocked(Boolean.getBoolean(userInfo[5]));
							insert.setSecurity(userInfo[6]);
							UserDao.insert(insert, username);
						} else {
							flag = false;
							errors.add("Incorrect format of user file. Should be firstName,lastName,email,username,password,isLocked,security. isLocked should be either 0 or 1");
						}
					}
					if (flag) {
						success.add("Users added successfully.");
					}
					
				} catch (FileNotFoundException e) {
					errors.add("An error occurred while adding products");
					e.printStackTrace();
				}
			} else {
				// output to user that file does not exist
				errors.add("An error occurred while adding users");
			}
		}
	}
	
	private boolean checkUser(String[] userInfo) {
		
		for (int i = 0; i < userInfo.length; i++) {
			if (userInfo[i] == null || userInfo[i].equals("")) {
				System.out.println(userInfo[i]);
				return false;
			}
		} 
		if (!userInfo[5].equals("0") && !userInfo[5].equals("1")) {
			System.out.println(userInfo[5]);
			return false;
		}
		
		return true;
	}

}
