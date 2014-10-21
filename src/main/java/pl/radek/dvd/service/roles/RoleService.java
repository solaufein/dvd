package pl.radek.dvd.service.roles;

import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.model.Roles;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-10-21
 * Time: 13:35
 */
public interface RoleService {
    public List<RoleData> getRoles();
    public Roles getRole(int id);
    public void deleteRole(int id);
}
