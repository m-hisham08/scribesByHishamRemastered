package org.hisham.ScribesByHisham.repositories;

import org.hisham.ScribesByHisham.models.Role;
import org.hisham.ScribesByHisham.models.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(RoleName name);
}
