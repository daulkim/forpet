package com.du.forpet.domain;

import lombok.Data;

@Data
public class KakaoReadyRequestDto {

        String cid;
        String partner_order_id;
        String partner_user_id;
        String item_name ;
        Integer quantity;
        Integer total_amount;
        Integer tax_free_amount;
        String approval_url;
        String cancel_url;
        String fail_url;

        @Override
        public String toString() {
                return "cid="+this.getCid()+
                        "&partner_order_id=" +this.getPartner_order_id()+
                        "&partner_user_id="+this.getPartner_user_id()+
                        "&item_name="+this.getItem_name()+
                        "&quantity="+this.getQuantity()+
                        "&total_amount="+this.getTotal_amount()+
                        "&tax_free_amount="+this.getTax_free_amount()+
                        "&approval_url="+this.getApproval_url()+
                        "&cancel_url="+this.getCancel_url()+
                        "&fail_url="+this.getFail_url();

        }
}
