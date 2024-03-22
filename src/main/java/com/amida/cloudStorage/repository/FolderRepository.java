package com.amida.cloudStorage.repository;

import com.amida.cloudStorage.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {

}

