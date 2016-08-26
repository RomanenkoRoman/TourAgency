package ua.nure.romanenko.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.dao.DiscountDAO;
import ua.nure.romanenko.SummaryTask4.exception.DBException;
import ua.nure.romanenko.SummaryTask4.web.command.Command;
import ua.nure.romanenko.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Discount controller
 * Created by Роман on 26.08.2016.
 */
public class DiscountController extends HttpServlet {
    private static final long serialVersionUID = 4219742324L;

    private static final Logger LOG = Logger.getLogger(DiscountController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        LOG.trace("Discount controller starts");

        int max = Integer.parseInt(request.getParameter("max"));
        LOG.trace("Catch max --> " + max);
        int step = Integer.parseInt(request.getParameter("step"));
        LOG.trace("Catch step --> " + step);

        try {
            DiscountDAO.updateDiscount(max, step);
        } catch (DBException e) {
            LOG.error("cannot update discount", e);
        }
        LOG.trace("Discount controller finished");


        response.sendRedirect(Path.COMMAND_DISCOUNT);

    }
}
