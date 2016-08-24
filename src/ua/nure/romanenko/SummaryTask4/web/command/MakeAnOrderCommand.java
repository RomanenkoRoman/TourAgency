package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

/**
 * Make an order command
 * Created by Роман on 18.08.2016.
 */
public class MakeAnOrderCommand extends Command {

    private static final long serialVersionUID = -743829748923L;

    private static final Logger LOG = Logger.getLogger(MakeAnOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Make an order command starts");

        String[] results = request.getParameterValues("itemId");
        HttpSession session = request.getSession();


        if (results!=null) {
            DBManager manager = DBManager.getInstance();

            List<CatalogItem> list = manager.findCatalogItems(results);
            LOG.trace("Found in DB: OrderItemsList --> " + list);


//            request.setAttribute("results", list);
            session.setAttribute("results", list);
            LOG.trace("Set the request attribute: catalogItems --> " + list);

            LOG.debug("Make an order command finished");
            return Path.PAGE_YOUR_ORDER;
        }
            LOG.debug("Make an order command finished");
            return Path.PAGE_LIST_CATALOG;
    }
}
