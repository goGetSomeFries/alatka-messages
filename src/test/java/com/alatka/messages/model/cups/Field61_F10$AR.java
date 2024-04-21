package com.alatka.messages.model.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.time.LocalDateTime;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F61_F10", usage = "AR", remark = "银联8583 61.10域usage=AR")
public class Field61_F10$AR implements Field121_F5 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 6, remark = "SR流水号")
    private Integer f61f10f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 19, remark = "验证服务时间", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime f61f10f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "发卡机构认证结果")
    private Integer f61f10f3;

    public Integer getF61f10f1() {
        return f61f10f1;
    }

    public void setF61f10f1(Integer f61f10f1) {
        this.f61f10f1 = f61f10f1;
    }

    public LocalDateTime getF61f10f2() {
        return f61f10f2;
    }

    public void setF61f10f2(LocalDateTime f61f10f2) {
        this.f61f10f2 = f61f10f2;
    }

    public Integer getF61f10f3() {
        return f61f10f3;
    }

    public void setF61f10f3(Integer f61f10f3) {
        this.f61f10f3 = f61f10f3;
    }
}