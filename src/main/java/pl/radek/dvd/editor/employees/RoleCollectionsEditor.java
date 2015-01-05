package pl.radek.dvd.editor.employees;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import pl.radek.dvd.service.employees.EmployeeFacade;

public class RoleCollectionsEditor extends CustomCollectionEditor {
    private static Logger logger = Logger.getLogger(RoleCollectionsEditor.class);

    private EmployeeFacade employeeFacade;

    public RoleCollectionsEditor(Class collectionType, boolean nullAsEmptyCollection, EmployeeFacade employeeFacade) {
        super(collectionType, nullAsEmptyCollection);
        this.employeeFacade = employeeFacade;
    }

    @Override
    protected Object convertElement(Object element) {
        logger.info("Inside method convert Element in RoleCollectionsEditor");
        try
        {
            // forms should return the id as the itemValue
            return employeeFacade.getRole(Integer.valueOf(element.toString()));
        }
        catch (NumberFormatException e)
        {
            logger.warn("Unable to convert " + element + " to an integer");
            return null;
        }
    }
}
