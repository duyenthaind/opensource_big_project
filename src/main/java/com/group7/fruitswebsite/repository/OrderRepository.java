package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhOrder;
import com.group7.fruitswebsite.repository.customupdate.OrderCustomUpdateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author duyenthai
 */
public interface OrderRepository extends JpaRepository<DhOrder, Integer>, OrderCustomUpdateRepository {
    @Query("from DhOrder where dhUser.id = :userId")
    List<DhOrder> findByUserId(@Param("userId") Integer userId);

    @Query(value = "select * from dh_order where user_id = :userId",nativeQuery = true)
    Page<DhOrder> findByUserId(@Param("userId") Integer userId, Pageable pageable);

    Page<DhOrder> findAll(Pageable pageable);

    @Transactional
    @Query("delete  from DhOrder  where id = :id and dhUser.id = :userId and (orderStatus <= 3 or orderStatus is null)")
    @Modifying
    void deleteByIdAndUserId(@Param("id") Integer id,@Param("userId") Integer userId);

    @Query("from DhOrder where id = :id and dhUser.id in (select id from DhUser u where u.username = :userName)")
    Optional<DhOrder> findByIdAndUserName(@Param("id") Integer id, @Param("userName") String userName);
}
