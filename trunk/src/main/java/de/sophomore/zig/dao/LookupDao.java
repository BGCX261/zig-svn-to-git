package de.sophomore.zig.dao;

import de.sophomore.zig.model.Role;

import java.util.List;

/**
 * Lookup Data Access Object (GenericDao) interface.  This is used to lookup values in
 * the database (i.e. for drop-downs).
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @author <a href="mailto:alexandre.koval@gmail.com">Alexandre Koval</a>
 */
public interface LookupDao extends UniversalDao {

    /**
     * Returns all Roles ordered by name
     * @return populated list of roles
     */
    List<Role> getRoles();
    
}
