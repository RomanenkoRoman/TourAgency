package ua.nure.romanenko.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.bean.ExtendedUserOrderBean;
import ua.nure.romanenko.SummaryTask4.db.dao.OrderDAO;
import ua.nure.romanenko.SummaryTask4.exception.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 *
 * Created by Роман on 24.08.2016.
 */
public class StatusController extends HttpServlet{

    private static final long serialVersionUID = 4712380827148L;

    private static final Logger LOG = Logger.getLogger(StatusController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Status controller starts");


        HttpSession session = request.getSession();
        List<ExtendedUserOrderBean> extendedUserOrderBeanList =
                (List<ExtendedUserOrderBean>) session.getAttribute("extendedUserOrderBeanList");
        LOG.trace("Find in attributes -->" + extendedUserOrderBeanList);

        for (int i = 1; i < extendedUserOrderBeanList.size()+1; i++) {

            if (request.getParameterValues("" + i) != null) {
                ExtendedUserOrderBean userOrderBean = extendedUserOrderBeanList.get(i - 1);

                try {
                    OrderDAO.updateStatusInOrder(userOrderBean,(request.getParameterValues("" + i)[0]));
                } catch (DBException e) {
                    LOG.error("cannot change status ",e);
                }

            }

            LOG.trace("Find in parameters in "+i+" --> "
                    + Arrays.toString(request.getParameterValues(""+i)));
        }


        LOG.debug("Status controller finished");
        response.sendRedirect(Path.COMMAND_LIST_ORDERS);



    }
}
