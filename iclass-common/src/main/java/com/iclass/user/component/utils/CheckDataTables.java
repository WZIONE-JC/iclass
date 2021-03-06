package com.iclass.user.component.utils;

import com.iclass.user.component.entity.DataTablesRequestEntity;
import org.apache.commons.lang.StringUtils;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 2:18 PM.
 * 校对datatables请求参数
 */
public class CheckDataTables {

    public static DataTablesRequestEntity check(DataTablesRequestEntity requestEntity) {
        if (requestEntity != null) {
            if (requestEntity.getDraw() == null || StringUtils.isBlank(requestEntity.getDraw() + "")) {
                requestEntity.setDraw(1);
            }
            if (requestEntity.getStart() == null || StringUtils.isBlank(requestEntity.getStart() + "") || requestEntity.getStart() < 0) {
                requestEntity.setStart(0);
            }
            if (requestEntity.getLength() == null || StringUtils.isBlank(requestEntity.getLength() + "") || requestEntity.getLength() < 1) {
                requestEntity.setLength(10);
            }
        } else {
            return new DataTablesRequestEntity(1, 0, 10);
        }
        return requestEntity;
    }
}
