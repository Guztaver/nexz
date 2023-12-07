package com.guztaver.nexz.repositories;

import com.guztaver.nexz.models.*;
import org.springframework.data.repository.*;
import org.springframework.security.core.userdetails.*;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    UserDetails findByUsernameIs(String username);
}