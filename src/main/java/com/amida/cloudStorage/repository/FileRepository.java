package com.amida.cloudStorage.repository;

import com.amida.cloudStorage.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}

