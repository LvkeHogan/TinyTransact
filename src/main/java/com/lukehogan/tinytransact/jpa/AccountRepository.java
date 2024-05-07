package com.lukehogan.tinytransact.jpa;

import com.lukehogan.tinytransact.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

//Spring Data JPA implementation
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
