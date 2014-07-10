package com.adwareresearch.service;

import com.adwareresearch.domain.AuthPermissions;
import java.util.List;

public interface AuthPermissionsService {
    public void save(AuthPermissions permission);
    public void delete(AuthPermissions permission);
    public List<AuthPermissions> list();
    public List<AuthPermissions> findAvailablePermissions();
}
