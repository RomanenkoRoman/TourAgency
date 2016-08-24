package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.dao.CatalogItemDAO;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Filter Catalog by
 * Created by Роман on 24.08.2016.
 */
public class FilterCatalogCommand extends Command {
    private static final long serialVersionUID = 8254012378863123L;

    private static final Logger LOG = Logger.getLogger(FilterCatalogCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("FilterCatalogCommand starts");


        String price = request.getParameter("price");
        String category = request.getParameter("category");
        String type = request.getParameter("hotel_type");
        String people = request.getParameter("people");

        List<CatalogItem> catalogItems =
                CatalogItemDAO.filterCatalog(price, category, type, people);
        LOG.trace("Find in DB --> " + catalogItems);

        request.setAttribute("catalogItems", catalogItems);


        return Path.COMMAND_LIST_CATALOG;
    }

}
