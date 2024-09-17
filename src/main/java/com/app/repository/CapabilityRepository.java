package com.app.repository;

import com.app.entity.Capability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapabilityRepository extends JpaRepository<Capability,Long> {
}

