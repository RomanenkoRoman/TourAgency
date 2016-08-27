package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.dao.CatalogItemDAO;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Add catalog command
 * Created by Роман on 27.08.2016.
 */
public class AddCatalogItemCommand extends Command {

    private static final long serialVersionUID = -43564893712823L;

    private static final Logger LOG = Logger.getLogger(AddCatalogItemCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.trace("AddCatalogItemCommand starts");


        String name = request.getParameter("name");
        LOG.trace("Found parameter \'name\' --> " + name);

        String category = request.getParameter("categoryType");

        LOG.trace("Found parameter \'category\' --> " + category);


        String hotelType= request.getParameter("hotelType");
        LOG.trace("Found parameter \'hotelType\' --> " + hotelType);


        String quantity = request.getParameter("quantity");
        LOG.trace("Found parameter \'quantity\' --> " + quantity);

        String price = request.getParameter("price");
        LOG.trace("Found parameter \'price\' --> " + price);

        LOG.trace("AddCatalogItemCommand finished");

        if (CatalogItemDAO.addCatalogItem(name, category, hotelType, quantity, price)) {
            return Path.PAGE_CATALOG_ITEM_SUCCESSFUL_ADDED;
        }
        String message = "You should fill in all fields";
        request.setAttribute("message", message);

        return Path.PAGE_ADD_CATALOG_ITEM;
    }
}
