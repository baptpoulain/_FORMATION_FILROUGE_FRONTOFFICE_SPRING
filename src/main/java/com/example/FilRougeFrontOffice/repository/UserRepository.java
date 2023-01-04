package com.example.FilRougeFrontOffice.repository;

import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UsersEntity, Integer> {


    Optional<UsersEntity> findByuserEmail(String userEmail);

    Optional<UsersEntity> findByUserName(String Name);

    Optional<UsersEntity> findByUserEmail(String userEmail);
}
