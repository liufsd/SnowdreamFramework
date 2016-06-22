/*
 * Copyright (C) 2014 Snowdream Mobile <yanghui1986527@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.snowdream.android.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public final class DensityUtil {
    private DensityUtil() {
        throw new AssertionError("No constructor allowed here!");
    }

    /**
     * see {@link DensityUtil#applyDimension(Context, int, float)}
     */
    @Deprecated
    public static int dip2px(@NonNull Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * see {@link DensityUtil#applyDimension(Context, int, float)}
     */
    @Deprecated
    public static int px2dip(@NonNull Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * see {@link DensityUtil#applyDimension(Context, int, float)}
     */
    @Deprecated
    public static int sp2px(@NonNull Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * see {@link DensityUtil#applyDimension(Context, int, float)}
     */
    @Deprecated
    public static int px2sp(@NonNull Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * Conversion in dp、dip、sp、pt、px、mm、in according to the device
     *
     * @param context    Context
     * @param srcUnit    src unit,see {@link android.util.TypedValue}
     * @param srcValue   src value
     * @param targetUnit target unit,see {@link android.util.TypedValue}
     * @return target value with targetUnit
     */
    public static float applyDimension(@NonNull Context context, int srcUnit, float srcValue, int targetUnit) {
        float targetValue = 0f;

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        Float pxvalue = TypedValue.applyDimension(srcUnit, srcValue, metrics);

        switch (targetUnit) {
            case TypedValue.COMPLEX_UNIT_DIP:
                targetValue = pxvalue / metrics.density;
                break;
            case TypedValue.COMPLEX_UNIT_SP:
                targetValue = pxvalue / metrics.scaledDensity;
                break;
            case TypedValue.COMPLEX_UNIT_PX:
                targetValue = pxvalue;
                break;
            case TypedValue.COMPLEX_UNIT_MM:
                targetValue = (pxvalue * 25.4f) / metrics.xdpi;
                break;
            case TypedValue.COMPLEX_UNIT_IN:
                targetValue = pxvalue / metrics.xdpi;
                break;
            case TypedValue.COMPLEX_UNIT_PT:
                targetValue = (pxvalue * 72) / metrics.xdpi;
                break;
            default:
                targetValue = pxvalue;
                break;
        }

        return targetValue;
    }

    /**
     * Convert dp、dip、sp、pt、px、mm、in to px according to the device. <br/>
     * It is the same as: {@linkplain TypedValue#applyDimension(int, float, DisplayMetrics)   TypedValue.applyDimension(int, float, DisplayMetrics)}
     *
     * @param context    Context
     * @param srcUnit    src unit,see {@link android.util.TypedValue}
     * @param srcValue   src value
     * @return target value  px
     */
    public static float applyDimension(@NonNull Context context, int srcUnit, float srcValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        return  TypedValue.applyDimension(srcUnit,srcValue,metrics);
    }

    /**
     * Convert dp、dip、sp、pt、px、mm、in to px according to the device. <br/>
     * It is the same as: {@linkplain TypedValue#applyDimension(int, float, DisplayMetrics)   TypedValue.applyDimension(int, float, DisplayMetrics)}
     *
     * @param context    Context
     * @param srcUnit    src unit,see {@link android.util.TypedValue}
     * @param srcValue   src value
     * @return target value  px
     */
    public static int applyDimensionOffset(@NonNull Context context, int srcUnit, float srcValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        return  (int)TypedValue.applyDimension(srcUnit,srcValue,metrics);
    }
}
