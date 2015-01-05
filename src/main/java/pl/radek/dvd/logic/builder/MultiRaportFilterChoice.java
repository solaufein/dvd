package pl.radek.dvd.logic.builder;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.ListDataRequest;

public abstract class MultiRaportFilterChoice extends MultiFiltreChoice {
    protected MultiRaportFilterChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    public abstract int getTotalRecords();
}
