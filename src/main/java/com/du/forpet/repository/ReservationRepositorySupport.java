package com.du.forpet.repository;

import com.du.forpet.domain.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.du.forpet.domain.entity.QReservation.reservation;

@Repository
public class ReservationRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ReservationRepositorySupport(JPAQueryFactory queryFactory) {
        super(Reservation.class);
        this.queryFactory = queryFactory;
    }

    public long countByHelperAndDateTime(Long helperId) {
        return queryFactory
                .selectFrom(reservation)
                .where(reservation.helper.id.eq(helperId)).fetchCount();


    }
}
