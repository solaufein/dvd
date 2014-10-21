package pl.radek.dvd.editor.employees;

import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.service.employees.EmployeeFacade;

import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * User: Sola
 * Date: 2014-10-21
 * Time: 14:16
 */
public class RoleEditor extends PropertyEditorSupport {
    private EmployeeFacade employeeFacade;

    /**
     * Constructs a <code>PropertyEditorSupport</code> object.
     *
     * @since 1.5
     */
    public RoleEditor(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    /**
     * Sets the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text The string to be parsed.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

    }
}
