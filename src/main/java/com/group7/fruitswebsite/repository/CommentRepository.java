package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author duyenthai
 */
public interface CommentRepository extends JpaRepository<DhComment, Integer> {

    Page<DhComment> findByDhProductId(Pageable pageable, int productId);

    @Query(value = "select * from dh_comment where product_id = :productId",nativeQuery = true)
    List<DhComment> findByDhProductId(@Param("productId") int productId);
    
    @Query(value = "select * from dh_comment where product_id = :productId AND parent_id = :parendId",nativeQuery = true)
    List<DhComment> findByProductIdAndParentId(@Param("productId") Integer productId,@Param("parendId") Integer parentId);
}
