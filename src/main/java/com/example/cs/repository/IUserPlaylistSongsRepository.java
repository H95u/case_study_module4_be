package com.example.cs.repository;

import com.example.cs.model.UserPlaylistSongs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserPlaylistSongsRepository extends JpaRepository<UserPlaylistSongs, Long> {
    Optional<UserPlaylistSongs> findByUserPlaylistIdAndSongsId(Long userPlaylistId, Long songsId);
}
