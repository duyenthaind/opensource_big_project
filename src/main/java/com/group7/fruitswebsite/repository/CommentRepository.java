package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author duyenthai
 */
public interface CommentRepository extends CrudRepository<DhComment, Integer> {

    Page<DhComment> findByDhProductId(Pageable pageable, int productId);

    List<DhComment> findByDhProductId(int productId);
}
