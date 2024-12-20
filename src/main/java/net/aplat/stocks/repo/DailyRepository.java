package net.aplat.stocks.repo;

import jakarta.transaction.Transactional;
import net.aplat.stocks.entity.DailyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyRepository extends JpaRepository<DailyEntity, Long> {
    List<DailyEntity> findDailyEntitiesByTradeDate(LocalDate tradeDate);

    boolean existsByTradeDate(LocalDate tradeDate);

    @Transactional
    void deleteAllByTradeDate(LocalDate tradeDate);
}
