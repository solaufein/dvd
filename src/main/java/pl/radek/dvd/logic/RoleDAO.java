package pl.radek.dvd.logic;

import pl.radek.dvd.model.Roles;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-04-30
 * Time: 11:29
 */
public interface RoleDAO {
    public List<Roles> getRoles();
    public Roles getRole(int id);
    public void deleteRole(int id);
}
