package com.study.jpa.config;

import com.study.jpa.repository.HitsRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
public class RedisScheduler {

	private final Logger logger = LoggerFactory.getLogger(RedisScheduler.class);
	private final HitsRepositoryCustom hitsRepositoryCustom;

	@Scheduled(fixedDelay = 3600000)
	private void updateRDB() {
		logger.info("redis 조회수 RDB update start");
		hitsRepositoryCustom.updateRDB();
	}
}
