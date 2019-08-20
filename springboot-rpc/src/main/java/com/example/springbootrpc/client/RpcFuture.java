package com.example.springbootrpc.client;

import com.example.springbootrpc.model.RpcRequest;
import com.example.springbootrpc.model.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/14
 * @time: 0:43
 * @modifier:
 * @since:
 */
public class RpcFuture implements Future<Object>{

    private static final Logger logger = LoggerFactory.getLogger(RpcFuture.class);

    private RpcRequest request;

    private RpcResponse response;

    private Sync sync;

    private long startTime;

    private long responseTimeThreshold = 5000;

    private List<AsyncRpcCallback> pendingCallbacks = new ArrayList<AsyncRpcCallback>();

    private ReentrantLock lock = new ReentrantLock();

    public RpcFuture(RpcRequest request) {
        this.request = request;
        this.sync = new Sync();
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public Object get() {
        sync.acquire(-1);
        if (null == this.response) {
            return null;
        }

        return this.response.getResult();
    }

    public void done(RpcResponse response) {
        this.response = response;
        sync.release(1);
        invokeCallbacks();

        long responseTime = System.currentTimeMillis() - startTime;
        if (this.responseTimeThreshold < responseTime) {
            logger.warn("Service response time is too slow. Request id = "
                    + response.getRequestId() + ". Response Time = " + responseTime + "ms");
        }
    }

    private void invokeCallbacks() {
        lock.lock();
        try {
            for (final AsyncRpcCallback asyncRpcCallback : pendingCallbacks) {
                runCallback(asyncRpcCallback);
            }
        } finally {
            lock.unlock();
        }
    }

    private void runCallback(final AsyncRpcCallback asyncRpcCallback) {
        final RpcResponse response = this.response;
        RpcClient.submit(() -> {
            if (response.isError()) {
                asyncRpcCallback.success(response.getResult());
            } else {
                asyncRpcCallback.fail(new RuntimeException("Response error", new Throwable(response.getError())));
            }
        });
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDone() {
        return sync.isDone();
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        boolean isSuccess = sync.tryAcquireNanos(-1, unit.toNanos(timeout));

        if (!isSuccess) {
            throw new RuntimeException("Timeout exception. Request id: " + this.request.getRequestId()
                    + ". Request class name: " + this.request.getClassName()
                    + ". Request method: " + this.request.getMethodName());
        }

        if (null == this.response) {
            return null;
        }
        return this.response.getResult();

    }

    static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1L;

        /**
         * future status
         */
        private static final int DONE_STATE = 1;
        private final int PENDING_STATE = 0;

        @Override
        public boolean tryAcquire(int arg) {
            return getState() == DONE_STATE;
        }

        @Override
        public boolean tryRelease(int arg) {
            if (getState() == PENDING_STATE) {
                if (compareAndSetState(PENDING_STATE, DONE_STATE)) {
                    return true;
                }
                return false;
            }

            return false;
        }

        public boolean isDone() {
            getState();
            return getState() == DONE_STATE;
        }
    }

    private RpcFuture addCallback(AsyncRpcCallback callback) {
        lock.lock();
        try {
            if (isDone()) {
                runCallback(callback);
            } else {
                this.pendingCallbacks.add(callback);
            }
        } finally {
            lock.unlock();
        }

        return this;
    }
}
