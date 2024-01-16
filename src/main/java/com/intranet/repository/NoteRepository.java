package com.intranet.repository;

import com.intranet.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    List<Note> findAllBySenderId(UUID id);
    List<Note> findAllByAddresseeId(UUID id);
}
