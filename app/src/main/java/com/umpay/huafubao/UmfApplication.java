/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 联动优势电子商务有限公司</p>
 * <p>17-2-15</p>
 *
 * @author xtdhwl
 * @description
 * @version 1.0
 * modified:
 */

package com.umpay.huafubao;

import android.app.Application;

import com.umf.pay.sdk.UmfPay;

/**
 * desc:
 * <p>
 * 创建人：xtdhwl 创建日期：17-2-15
 * </p>
 *
 * @version V1.0
 */
public class UmfApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化umfpay
        UmfPay.init(this);
    }
}
