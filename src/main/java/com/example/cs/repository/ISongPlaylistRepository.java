package com.example.cs.repository;

import com.example.cs.model.SongPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongPlaylistRepository extends JpaRepository<SongPlaylist, Long> {
}
