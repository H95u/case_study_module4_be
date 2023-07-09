package com.example.cs.repository;

import com.example.cs.model.UserPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserPlaylistRepository extends JpaRepository<UserPlaylist, Long> {
}
