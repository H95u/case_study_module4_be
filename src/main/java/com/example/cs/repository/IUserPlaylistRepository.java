package com.example.cs.repository;

import com.example.cs.model.UserPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IUserPlaylistRepository extends JpaRepository<UserPlaylist, Long> {
    List<UserPlaylist> findUserPlaylistByUserId(Long userId);

    @Modifying
    @Query(value = "INSERT INTO user_playlist_song (user_playlist_id, song_id) VALUES (:userPlaylistId, :songId)", nativeQuery = true)
    void addSongToPlaylist(@Param("userPlaylistId") Long userPlaylistId, @Param("songId") Long songId);
}
