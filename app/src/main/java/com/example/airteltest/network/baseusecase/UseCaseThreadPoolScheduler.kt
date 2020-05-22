/*
 * JOHNSON CONTROLS CONFIDENTIAL *  
 * ©Johnson Controls International plc, All Rights Reserved.
 *
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Johnson Controls International plc and its suppliers,
 * if any.  The intellectual and technical concepts contained herein are proprietary to Johnson Controls International plc and its 
 * suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
 * from Johnson Controls International plc.
 *
 */

package com.example.airteltest.network.baseusecase

import android.os.Handler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class UseCaseThreadPoolScheduler : UseCaseScheduler {

    private val POOL_SIZE = 6

    private val MAX_POOL_SIZE = 8

    private val TIME_OUT = 30

    private val mHandler = Handler()

    private var mThreadPoolExecutor: ThreadPoolExecutor

    init {
        mThreadPoolExecutor = ThreadPoolExecutor(
                POOL_SIZE, MAX_POOL_SIZE, TIME_OUT.toLong(),
                TimeUnit.SECONDS, ArrayBlockingQueue(POOL_SIZE)
        )
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor.execute(runnable)
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(
            response: V,
            useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mHandler.post { useCaseCallback.onSuccess(response) }
    }

    override fun <V : UseCase.ResponseValue> onError(
            useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mHandler.post { useCaseCallback.onError() }
    }

}