package pl.radek.dvd.logic.builder;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.ListDataRequest;

public abstract class MultiFiltreChoice {
    protected ListDataRequest listDataRequest;
    protected HibernateTemplate hibernateTemplate;

    protected MultiFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        this.listDataRequest = listDataRequest;
        this.hibernateTemplate = hibernateTemplate;
    }

    public abstract Query filtreQuery();
    public abstract int getNoOfRecords();
}
