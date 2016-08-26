package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.dao.OrderDAO;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Confirm order command
 * Created by Роман on 25.08.2016.
 */
public class ConfirmOrderCommand extends Command {
    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(ConfirmOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("ConfirmOrderCommand starts");

        HttpSession session = request.getSession();

        List<CatalogItem> list = (List<CatalogItem>) session.getAttribute("results");
        User user = (User) session.getAttribute("user");
        LOG.trace("Found order list in session --> " + list);
        LOG.trace("Found user in session --> " + user);

        OrderDAO.confirmOrder(list, user);


        LOG.trace("ConfirmOrderCommand finished");
        return Path.PAGE_PRIVATE_OFFICE;
    }
}
