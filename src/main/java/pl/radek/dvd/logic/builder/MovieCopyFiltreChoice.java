package pl.radek.dvd.logic.builder;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.SortInfo;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.MovieCopy;

import java.util.List;

public class MovieCopyFiltreChoice extends MultiFiltreChoice {

    protected MovieCopyFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
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
