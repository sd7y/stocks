package net.aplat.stocks.repo;

import net.aplat.stocks.entity.DailyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRepository extends JpaRepository<DailyEntity, Long> {

}
