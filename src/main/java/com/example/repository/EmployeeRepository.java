package com.example.repository;

import com.example.entity.EmployeeEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long>
{
}
