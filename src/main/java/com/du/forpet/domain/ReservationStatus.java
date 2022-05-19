package com.du.forpet.domain;

public enum ReservationStatus {
    REQ {
        @Override
        public String toString() {
            return "예약 요청";
        }
    },
    APPROVE {
        @Override
        public String toString() {
            return "예약 승인";
        }
    },
    COMPLETE {
        @Override
        public String toString() {
            return "완료";
        }
    },
    CANCEL {
        @Override
        public String toString() {
            return "취소";
        }
    };
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
