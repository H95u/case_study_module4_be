package com.example.cs.repository;

import com.example.cs.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISongRepository extends JpaRepository<Song, Long> {
    @Query(value = "select song.*,singer.name from song  inner join singer on song.singer_id = singer.id" +
            " where song.name like LOWER(:name) or singer.name like LOWER(:name)", nativeQuery = true)
    Page<Song> searchByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT s.* " +
            "FROM song s " +
            "INNER JOIN user_playlist_songs ps " +
            "   ON s.id = ps.songs_id " +
            "WHERE ps.user_playlist_id = :playlistId", nativeQuery = true)
    List<Song> findByUserPlayListId(@Param("playlistId") Long playlistId);
}
