package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
