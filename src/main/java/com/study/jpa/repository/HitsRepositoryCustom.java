package com.study.jpa.repository;

public interface HitsRepositoryCustom {
	
	public void incrementHits(Long postId);

	public Integer getAndDel(Long postId);
    
	public void updateRDB();
}
