package org.jugistanbul.secondopinion.api.repository;

import org.jugistanbul.secondopinion.api.entity.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NicknameRepository extends JpaRepository<Nickname, Long> {
}
