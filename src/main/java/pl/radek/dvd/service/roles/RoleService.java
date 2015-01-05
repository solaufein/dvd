package pl.radek.dvd.service.roles;

import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.model.Roles;

import java.util.List;

public interface RoleService {
    public List<RoleData> getRoles();
    public RoleData getRole(int id);
    public Roles getRoleEntity(int id);
    public void deleteRole(int id);
}
