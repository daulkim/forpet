package com.du.forpet.domain;

public enum ReservationStatus {
    /**
     * PRE: 예약 요청
     * RESERVE: 예약 승인 완료
     * COMP: 완료
     * CANCEL: 취소
     */

    PRE, RESERVE, COMP, CANCEL;

    public static boolean updatableStatus(ReservationStatus currentStatus) {

        boolean updatableStatus = false;

        switch (currentStatus) {
            case PRE:
            case RESERVE:
                updatableStatus = true;
                break;
        }

        return updatableStatus;
    }
}
