package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Delete catalog items
 * Created by Роман on 26.08.2016.
 */
public class DeleteCatalogItemsCommand extends Command {
    private static final long serialVersionUID = 123124123L;

    private static final Logger LOG = Logger.getLogger(FilterCatalogCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("DeleteCatalogItemsCommand starts");

        DBManager manager = DBManager.getInstance();
        List<CatalogItem> catalogItems = manager.findCatalogItems();
        LOG.trace("Find in db catalog items --> "+catalogItems);

        request.setAttribute("catalogItems", catalogItems);

        LOG.debug("DeleteCatalogItemsCommand finished");
        return Path.PAGE_DELETE_ITEMS;
    }

}
