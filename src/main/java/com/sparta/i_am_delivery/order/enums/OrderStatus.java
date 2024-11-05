package com.sparta.i_am_delivery.order.enums;

public enum OrderStatus {
    PENDING,    // 접수중
    PREPARING,  // 조리중
    DELIVERING, // 배달중
    COMPLETED,  // 배달완료
    CANCELED;   // 주문취소

    // 상태 전이 가능 여부 확인 메서드
    public boolean canChangeTo(OrderStatus newStatus) {
        switch (this) {
            case PENDING:
                return newStatus == PREPARING || newStatus == CANCELED;
            case PREPARING:
                return newStatus == DELIVERING || newStatus == CANCELED;
            case DELIVERING:
                return newStatus == COMPLETED || newStatus == CANCELED;
            case COMPLETED:
                return false;
            case CANCELED:
                return false;
            default:
                return false;
        }
    }
}
