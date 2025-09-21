package com.zhyea.argo.tools;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 并行处理工具包
 * 提供高性能、线程安全的并行执行能力
 *
 * @author robin
 * @since 2025/9/16 22:56
 */
@Slf4j
public final class ParallelKit {


	/**
	 * 默认线程池大小
	 */
	private static final int DEFAULT_POOL_SIZE = Math.max(4, Runtime.getRuntime().availableProcessors());

	/**
	 * 默认超时时间（秒）
	 */
	private static final long DEFAULT_TIMEOUT_SECONDS = 3;

	/**
	 * 线程池执行器
	 */
	private static final ExecutorService executor = new ThreadPoolExecutor(
			DEFAULT_POOL_SIZE, DEFAULT_POOL_SIZE * 2, 60L, TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(1000),
			r -> {
				Thread t = new Thread(r, "ParallelKit-" + System.currentTimeMillis());
				t.setDaemon(true);
				return t;
			},
			new ThreadPoolExecutor.CallerRunsPolicy()
	);

	/**
	 * 线程池执行器
	 */
	public static final ExecutorService BACKUP_EXECUTOR = new ThreadPoolExecutor(
			DEFAULT_POOL_SIZE, DEFAULT_POOL_SIZE * 2, 60L, TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(1000),
			r -> {
				Thread t = new Thread(r, "ParallelKit-" + System.currentTimeMillis());
				t.setDaemon(true);
				return t;
			},
			new ThreadPoolExecutor.CallerRunsPolicy()
	);


	@SafeVarargs
	public static <T> List<T> executeIgnoreErrors(Supplier<T>... functions) {
		if (null == functions || functions.length == 0) {
			return new ArrayList<>(0);
		}
		List<Supplier<T>> functionsList = new ArrayList<>(functions.length);
		functionsList.addAll(List.of(functions));
		return executeIgnoreErrors(functionsList);
	}


	/**
	 * 执行多个并行任务，忽略异常，返回成功的结果
	 *
	 * @param functions 并行执行的任务列表
	 * @param <T>       返回结果类型
	 * @return 成功执行的任务结果列表
	 */
	public static <T> List<T> executeIgnoreErrors(List<Supplier<T>> functions) {
		return executeIgnoreErrors(functions, executor);
	}


	/**
	 * 执行多个并行任务，忽略异常，返回成功的结果
	 *
	 * @param functions 并行执行的任务列表
	 * @param <T>       返回结果类型
	 * @return 成功执行的任务结果列表
	 */
	public static <T> List<T> executeIgnoreErrors(List<Supplier<T>> functions, ExecutorService executor) {
		if (functions == null || functions.isEmpty()) {
			return new ArrayList<>(0);
		}

		List<CompletableFuture<T>> futures = new ArrayList<>(functions.size());

		// 创建并行任务
		for (Supplier<T> function : functions) {
			CompletableFuture<T> future = CompletableFuture.supplyAsync(() -> {
				try {
					return function.get();
				} catch (Exception e) {
					logger.warn("ParallelKit task execution failed, ignoring error", e);
					return null;
				}
			}, executor);

			futures.add(future);
		}

		// 等待所有任务完成
		CompletableFuture<Void> allTasks = CompletableFuture.allOf(
				futures.toArray(new CompletableFuture[0])
		);

		try {
			allTasks.get(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);

			// 收集非空结果
			return futures.stream()
					.map(CompletableFuture::join)
					.collect(Collectors.toList());

		} catch (Exception e) {
			logger.error("ParallelKit execution failed", e);
			return new ArrayList<>(0);
		}
	}


	/**
	 * 执行单个异步任务
	 *
	 * @param function 要执行的任务
	 * @param <T>      返回结果类型
	 * @return CompletableFuture包装的结果
	 */
	public static <T> CompletableFuture<T> executeAsync(Supplier<T> function) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return function.get();
			} catch (Exception e) {
				logger.error("ParallelKit async task execution failed", e);
				throw new RuntimeException("Async task execution failed", e);
			}
		}, executor);
	}


	/**
	 * 关闭线程池（应用关闭时调用）
	 */
	public static void shutdown() {
		if (!executor.isShutdown()) {
			executor.shutdown();
			try {
				if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
					executor.shutdownNow();
				}
			} catch (InterruptedException e) {
				executor.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}
	}


	/**
	 * 获取线程池状态信息
	 *
	 * @return 线程池状态描述
	 */
	public static String getPoolStatus() {
		if (executor instanceof ThreadPoolExecutor) {
			ThreadPoolExecutor tpe = (ThreadPoolExecutor) executor;
			return String.format("Pool[active=%d, completed=%d, total=%d, queue=%d]",
					tpe.getActiveCount(),
					tpe.getCompletedTaskCount(),
					tpe.getTaskCount(),
					tpe.getQueue().size());
		}
		return "Pool status unavailable";
	}


	private ParallelKit() {
	}
}
