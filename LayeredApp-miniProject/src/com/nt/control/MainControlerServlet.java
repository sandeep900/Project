
package com.nt.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.CoronaDto;
import com.nt.service.PatientManagementService;
import com.nt.service.PatientManagementServiceImpl;
import com.nt.vo.CoranaVo;

@WebServlet("/Controler")
public class MainControlerServlet extends HttpServlet {

	private PatientManagementService service;

	/*
	 * public MainControlerServlet() { service =new PatientManagementServiceImpl();
	 * }
	 */
	public void init() throws ServletException

	{
	service = new PatientManagementServiceImpl();
    }

	public void destroy() {
		service = null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = null;
		pw = response.getWriter();
		response.setContentType("text/html");

		CoranaVo vo = new CoranaVo();
		vo.setPname(request.getParameter("pname"));
		vo.setPage(Integer.parseInt(request.getParameter("page")));
		vo.setPaddrs(request.getParameter("addrs"));
		vo.setPstate(request.getParameter("pstate"));
		System.out.println(vo.getPname());

		CoronaDto dto = new CoronaDto();
		dto.setPname(vo.getPname());
		dto.setPage(vo.getPage());
		dto.setPaddrs(vo.getPaddrs());
		dto.setPstate(vo.getPstate());
		try {
			String result = service.register(dto);
			pw.println("<h1>  Register </h1>" + result);
			System.out.println(result);
		} catch (Exception e) {

			e.printStackTrace();
			pw.println("<h1>  internal problem</h1>");

		}
		pw.println("<a href='index.html'>home</a>");
		pw.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

/*
 * public static void main(String[] args) { Scanner sc=new Scanner(System.in);
 * System.out.println("enter name"); String pname=sc.next();
 * System.out.println("enter age"); String page=sc.next();
 * System.out.println("eneter addrs"); String addrs=sc.next();
 * System.out.println("enter state"); String pstate=sc.next();
 * 
 * CoranaVo vo=new CoranaVo(); vo.setPname(pname);
 * vo.setPage(Integer.parseInt(page)); vo.setPaddrs(addrs);
 * vo.setPstate(pstate);
 * 
 * CoronaDto dto =new CoronaDto(); dto.setPname(vo.getPname());
 * dto.setPage(vo.getPage()); dto.setPaddrs(vo.getPaddrs());
 * 
 * 
 * 
 * 
 * try { PatientManagementService service =new PatientManagementServiceImpl();
 * String result=service.register(dto); System.out.println(result); } catch
 * (Exception e) { e.printStackTrace(); }}
 */
