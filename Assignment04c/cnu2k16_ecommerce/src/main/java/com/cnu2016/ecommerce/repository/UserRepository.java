package com.cnu2016.ecommerce.repository;

import com.cnu2016.ecommerce.models.Product;
import com.cnu2016.ecommerce.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vipulj on 08/07/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findDistinctUserByCompanyName(String companyName);

}
