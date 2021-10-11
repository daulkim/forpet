package com.du.forpet.domain;

public enum ReservationStatus {
    /**
     * P: 예약 요청
     * R: 예약 승인 완료
     * D: 완료
     * C: 취소
     */

    P, R, D, C;

    public static boolean updatableStatus(ReservationStatus currentStatus) {

        boolean updatableStatus = false;

        switch (currentStatus) {
            case P:
            case R:
                updatableStatus = true;
                break;
        }


        return updatableStatus;
    }
}
