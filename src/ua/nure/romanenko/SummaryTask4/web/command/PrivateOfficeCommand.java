package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.bean.ExtendedUserOrderBean;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.db.entity.Order;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Private Office Command
 */
public class PrivateOfficeCommand extends Command {

    private static final long serialVersionUID = 3526153213L;

    private static final Logger LOG = Logger.getLogger(MakeAnOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("PrivateOfficeCommand starts");
        HttpSession session = request.getSession();
        DBManager manager = DBManager.getInstance();

        try {
            User user = (User) session.getAttribute("user");
//            List<CatalogItem> orders = manager.findCatalogItems(user);
            List<ExtendedUserOrderBean> orders = manager.getExtendedUserOrderBean(user);
            LOG.trace("Found user:  --> " + user);

            LOG.trace("Found orders:  --> " + orders);
            request.setAttribute("orders",orders);
        } catch (Exception e) {
            LOG.debug("Can't load user or orders",e);
        }

        LOG.debug("PrivateOfficeCommand finished");
        return Path.PAGE_PRIVATE_OFFICE;
    }
}
