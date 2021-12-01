package com.du.forpet.domain;

public enum ReservationStatus {
    /**
     * REQ: 예약 요청
     * APPROVE: 예약 승인
     * COMPLETE: 완료
     * CANCEL: 취소
     */

    REQ, APPROVE, COMPLETE, CANCEL;

    public static boolean updatableStatus(ReservationStatus currentStatus) {

        boolean updatableStatus = false;

        switch (currentStatus) {
            case REQ:
            case APPROVE:
                updatableStatus = true;
                break;
        }

        return updatableStatus;
    }
}
