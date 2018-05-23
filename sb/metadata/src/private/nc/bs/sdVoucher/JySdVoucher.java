package nc.bs.sdVoucher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.bs.hzjy.plugins.SdVoucherTask;

@SuppressWarnings("restriction")
public class JySdVoucher extends HttpServlet implements IHttpServletAdaptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5390046378541639257L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	public void doAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String msg = "";
		String id = req.getParameter("ID");
		try {
			if (SbSdVoucher.isEmpty(id)) {
				msg = " { \"success\" : false, \"flag\" : \"0\"£¬\"errorMessage\" : \"IDÎª¿Õ\",\"reserve5\" :null ,\"nc_voucher\" :null}";
			} else {
				SdVoucherTask st = new SdVoucherTask();
				msg = st.executeTask(id);
			}
			out.write(msg);
			out.flush();
			out.close();
		} catch (Exception e) {
			resp.getWriter().print(e.toString());
		}

	}
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && (obj.toString().trim().equals(""))) {
			return true;

		}
		return false;
	}
}
