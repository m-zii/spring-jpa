package com.study.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class HitsRepositoryImpl implements HitsRepositoryCustom {

	private final RedisTemplate<String, Integer> redisTemplate;
    private final PostRepository postRepository;
 
    private static final int SCAN_MATCH_LIMIT_COUNT = 10;
    private static final String SCAN_MATCH_PATTERN = "*";
    private static final String HITS_REDIS_PREFIX_FORMAT = "post:%s_hits";

	public void incrementHits(Long postId) {
		ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
		operations.increment(generateKey(postId));
	}

	@Override
	public Integer getAndDel(Long postId) {
		ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
		return operations.get(generateKey(postId));
	}

	@Transactional
	@Override
	public void updateRDB() {
        ScanOptions scanOptions = ScanOptions.scanOptions().match(SCAN_MATCH_PATTERN).count(SCAN_MATCH_LIMIT_COUNT).build();
		Cursor<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().scan(scanOptions);
		
		while (keys.hasNext()) {
			Long postId = extractPostId(keys);
			postRepository.findById(postId)
				.ifPresent(img -> img.addHits(getAndDel(postId)));
		}
	}

	private Long extractPostId(Cursor<byte[]> keys) {
		String key = new String(keys.next());
		int index = key.indexOf(":");
		int index1 = key.indexOf("_hits");

		return Long.valueOf(key.substring(index + 1, index1));
    }
    
    private String generateKey(Long postId) {
		return String.format(HITS_REDIS_PREFIX_FORMAT, postId);
	}
}
