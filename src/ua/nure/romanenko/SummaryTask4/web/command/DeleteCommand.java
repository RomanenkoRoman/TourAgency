package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.dao.CatalogItemDAO;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Delete selected catalog items command
 * Created by Роман on 26.08.2016.
 */
public class DeleteCommand extends Command {
    private static final long serialVersionUID = 4325399873223L;

    private static final Logger LOG = Logger.getLogger(FilterCatalogCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.trace("DeleteCommand starts");

        String[] delete = request.getParameterValues("delete");
        LOG.trace("Found delete list --> " + Arrays.toString(delete));
        CatalogItemDAO.deleteItems(delete);


        List<CatalogItem> catalogItems = DBManager.getInstance().findCatalogItems();
        request.setAttribute("catalogItems", catalogItems);
        LOG.trace("Find in DB --> " + catalogItems);


        LOG.trace("DeleteCommand finished");
        return Path.PAGE_DELETE_ITEMS;
    }
}
