package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link BigDecimal}类型报文域解析器
 *
 * @author ybliu
 * @see NumberFieldBuilder
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BigDecimalFieldBuilder extends NumberFieldBuilder<BigDecimal> {

    private final Pattern pattern = Pattern.compile("^(P[0-9]+)(S[0-9]+)?([A-Z]{2})?");

    @Override
    protected byte[] fromObjectToAscii(BigDecimal value, FieldDefinition fieldDefinition) {
        BigDecimal result = this.fromConvert(value, fieldDefinition.getPattern());
        return result.toString().getBytes();
    }

    @Override
    protected byte[] fromObjectToBinary(BigDecimal value, FieldDefinition fieldDefinition) {
        BigDecimal result = this.fromConvert(value, fieldDefinition.getPattern());
        String str = Long.toBinaryString(result.longValue());
        return BytesUtil.binaryToBytes(str);
    }

    @Override
    protected byte[] fromObjectToBcd(BigDecimal value, FieldDefinition fieldDefinition) {
        BigDecimal result = this.fromConvert(value, fieldDefinition.getPattern());
        return BytesUtil.toBCD(result.toString());
    }

    @Override
    protected byte[] fromObjectToEbcdic(BigDecimal value, FieldDefinition fieldDefinition) {
        BigDecimal result = this.fromConvert(value, fieldDefinition.getPattern());
        return BytesUtil.toEBCDIC(result.toString());
    }

    @Override
    protected BigDecimal toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        BigDecimal value = new BigDecimal(new String(bytes));
        return this.toConvert(value, fieldDefinition.getPattern());
    }

    @Override
    protected BigDecimal toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        String binaryStr = BytesUtil.bytesToBinary(bytes);
        BigDecimal value = new BigDecimal(Long.parseLong(binaryStr, 2));
        return this.toConvert(value, fieldDefinition.getPattern());
    }

    @Override
    protected BigDecimal toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        BigDecimal value = new BigDecimal(BytesUtil.fromBCD(bytes));
        return this.toConvert(value, fieldDefinition.getPattern());
    }

    @Override
    protected BigDecimal toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        String str = BytesUtil.fromEBCDIC(bytes);
        BigDecimal value = new BigDecimal(str);
        return this.toConvert(value, fieldDefinition.getPattern());
    }

    private BigDecimal toConvert(BigDecimal value, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return value;
        }
        Wrapper wrapper = this.doConvert(pattern);
        return value.divide(BigDecimal.valueOf(Math.pow(10, wrapper.unit)), wrapper.scale, wrapper.mode);
    }

    private BigDecimal fromConvert(BigDecimal value, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return value;
        }
        Wrapper wrapper = this.doConvert(pattern);
        return value.setScale(wrapper.scale, wrapper.mode)
                .multiply(BigDecimal.valueOf(Math.pow(10, wrapper.unit))).setScale(0, RoundingMode.DOWN);
    }

    private Wrapper doConvert(String pattern) {
        Matcher matcher = this.pattern.matcher(pattern);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid pattern: " + pattern);
        }
        int unit = Integer.parseInt(matcher.group(1).substring(1));
        int scale = matcher.group(2) == null ? unit : Integer.parseInt(matcher.group(2).substring(1));
        RoundingMode mode = matcher.group(3) == null ? RoundingMode.HALF_UP : Mode.valueOf(matcher.group(3)).getRoundingMode();
        return new Wrapper(unit, scale, mode);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 3;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClassType() == BigDecimal.class;
    }

    private static class Wrapper {
        private final int unit;
        private final int scale;
        private final RoundingMode mode;

        Wrapper(int unit, int scale, RoundingMode mode) {
            this.unit = unit;
            this.scale = scale;
            this.mode = mode;
        }

    }

    private enum Mode {

        UP(RoundingMode.UP),
        DO(RoundingMode.DOWN),
        CE(RoundingMode.CEILING),
        FL(RoundingMode.FLOOR),
        HU(RoundingMode.HALF_UP),
        HD(RoundingMode.HALF_DOWN),
        HE(RoundingMode.HALF_EVEN),
        UN(RoundingMode.UNNECESSARY);

        private final RoundingMode roundingMode;

        Mode(RoundingMode roundingMode) {
            this.roundingMode = roundingMode;
        }

        public RoundingMode getRoundingMode() {
            return roundingMode;
        }
    }
}
