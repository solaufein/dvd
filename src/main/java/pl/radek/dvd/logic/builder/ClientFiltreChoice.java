package pl.radek.dvd.logic.builder;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.ListDataRequest;

/**
 * User: Sola
 * Date: 2014-07-29
 * Time: 14:23
 */
public class ClientFiltreChoice extends MultiFiltreChoice {
    protected ClientFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    @Override
    public Query filtreQuery() {
        return null;
    }

    @Override
    public int getNoOfRecords() {
        return 0;
    }
}
