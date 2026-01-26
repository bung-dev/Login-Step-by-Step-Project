package project.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.member.domain.Refresh;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshRepository extends JpaRepository<Refresh, Long> {
    Optional<Refresh> findByRefresh(String refresh);

    List<Refresh> findByLoginId(String loginId);

    void deleteByRefresh(String refresh);

    void deleteAllByLoginId(String loginId);
}
