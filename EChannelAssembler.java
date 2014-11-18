package com.sysway.boss.service.assembler.customer.echannel;

import com.sysway.boss.domain.product.Product;
import com.sysway.boss.domain.product.ProductHelper;
import com.sysway.boss.domain.resource.physical.SmartCard;

public abstract class EChannelAssembler {

    protected String getSmartCardCode(Product product) {
        if (product == null) {
            return "";
        }

        SmartCard smartCard = ProductHelper.findSmartCard(product);
        if (smartCard != null) {
            return smartCard.getCode();
        }

        return "";
    }
}
