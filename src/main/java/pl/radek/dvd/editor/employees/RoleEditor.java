package pl.radek.dvd.editor.employees;

import org.apache.log4j.Logger;
import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.model.Roles;
import pl.radek.dvd.service.employees.EmployeeFacade;

import java.beans.PropertyEditorSupport;
import java.util.List;

public class RoleEditor extends PropertyEditorSupport {
    private static Logger logger = Logger.getLogger(RoleEditor.class);

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
        logger.info("Role Editor inside method: setAsText...");

        RoleData role = employeeFacade.getRole(Integer.parseInt(text));

        setValue(role);
    }
}
