package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findRoleByName(String name);

    Role findRoleByRoleId(int roleId);

    Role findRoleByRoleIdAndName(int roleId, String Name);

}
