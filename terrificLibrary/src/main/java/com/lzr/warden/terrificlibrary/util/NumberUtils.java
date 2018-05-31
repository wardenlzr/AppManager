package com.lzr.warden.terrificlibrary.util;

import java.text.DecimalFormat;

/**
 * Created by yubin
 * 2018/5/31 0031-上午 9:03
 */
public class NumberUtils {

    private NumberUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取2位小数
     *
     * @return
     */
    public static String get2Decimal(long l) {
        DecimalFormat df = new DecimalFormat("0.00");
        return String.valueOf(df.format(l));
    }

    /**
     * 可重复使用的Long常量0L
     */
    public static final Long LONG_ZERO = Long.valueOf(0L);
    /**
     * 可重复使用的Long常量1L
     */
    public static final Long LONG_ONE = Long.valueOf(1L);
    /**
     * 可重复使用的Long常量-1L
     */
    public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
    /**
     * 可重复使用的Integer常量0
     */
    public static final Integer INTEGER_ZERO = Integer.valueOf(0);
    /**
     * 可重复使用的Integer常量1
     */
    public static final Integer INTEGER_ONE = Integer.valueOf(1);
    /**
     * 可重复使用的Integer常量-1
     */
    public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
    /**
     * 可重复使用的Short常量0
     */
    public static final Short SHORT_ZERO = Short.valueOf((short) 0);
    /**
     * 可重复使用的Short常量1
     */
    public static final Short SHORT_ONE = Short.valueOf((short) 1);
    /**
     * 可重复使用的Short常量-1
     */
    public static final Short SHORT_MINUS_ONE = Short.valueOf((short) -1);
    /**
     * 可重复使用的Byte常量0
     */
    public static final Byte BYTE_ZERO = Byte.valueOf((byte) 0);
    /**
     * 可重复使用的Byte常量1
     */
    public static final Byte BYTE_ONE = Byte.valueOf((byte) 1);
    /**
     * 可重复使用的Byte常量-1
     */
    public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte) -1);
    /**
     * 可重复使用的Double常量0.0d
     */
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    /**
     * 可重复使用的Double常量1.0d
     */
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    /**
     * 可重复使用的Double常量-1.0d
     */
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    /**
     * 可重复使用的Float常量0.0f
     */
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    /**
     * 可重复使用的Float常量1.0f
     */
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    /**
     * 可重复使用的Float常量-1.0f
     */
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    /**
     * 转换str为int,如果转换失败则返回0
     */
    public static int toInt(final String str) {
        return toInt(str, 0);
    }

    /**
     * 转换str为int，如果转换失败则返回defaultValue
     */
    public static int toInt(final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 转换str为long，如果转换失败则返回0L
     */
    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    /**
     * 转换str为long，如果转换失败则返回defaultValue
     */
    public static long toLong(final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 转换str为float，如果转换失败则返回0.0f
     */
    public static float toFloat(final String str) {
        return toFloat(str, 0.0f);
    }

    /**
     * 转换str为float，如果转换失败则返回defaultValue
     */
    public static float toFloat(final String str, final float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 转换str为double，如果转换失败则返回0.0d
     */
    public static double toDouble(final String str) {
        return toDouble(str, 0.0d);
    }

    /**
     * 转换str为double，如果转换失败则返回defaultValue
     */
    public static double toDouble(final String str, final double defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 转换str为byte，如果转换失败则返回0
     */
    public static byte toByte(final String str) {
        return toByte(str, (byte) 0);
    }

    /**
     * 转换str为byte，如果转换失败则返回defaultValue
     */
    public static byte toByte(final String str, final byte defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 转换str为short，如果转换失败则返回0
     */
    public static short toShort(final String str) {
        return toShort(str, (short) 0);
    }

    /**
     * 转换str为short，如果转换失败则返回defaultValue
     */
    public static short toShort(final String str, final short defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 过时的方法被isCreatable取代
     */
//    @Deprecated
    public static boolean isNumber(final String str) {
        return isCreatable(str);
    }

    /**
     * 校验String是否是一个有效的Java number
     * 校验number包含hexadecimal 的标记 0x or 0X、八进制数字、科学计数法和其他的数字标记类型，比如1234L
     * 以0开头的非十六进制的数统一作为八进制的数处理。所以String为09将返回false，因为9不是一个有效的八进制。但是是以0.开头的数字，都作为十进制处理
     * null或empty或blank字符串返回false
     */
    public static boolean isCreatable(final String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        final int start = chars[0] == '-' || chars[0] == '+' ? 1 : 0;
        final boolean hasLeadingPlusSign = start == 1 && chars[0] == '+';
        if (sz > start + 1 && chars[start] == '0') { // leading 0
            if (chars[start + 1] == 'x' || chars[start + 1] == 'X') { // leading 0x/0X
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            } else if (Character.isDigit(chars[start + 1])) {
                // leading 0, but not hex, must be octal
                int i = start + 1;
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || i < sz + 1 && allowSigns && !foundDigit) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                if (hasLeadingPlusSign && !hasDecPoint) {
                    return false;
                }
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                // single trailing decimal point after non-exponent is ok
                return foundDigit;
            }
            if (!allowSigns
                    && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L with an exponent or decimal point
                return foundDigit && !hasExp && !hasDecPoint;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }

}
