package com.zhyea.argo.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * 并行处理工具包
 *
 * @author robin
 * @since 2025/9/16 22:56
 */
public final class ParallelKit {


	public static void main(String[] args) {
		// 创建任务列表
		List<CompletableFuture<Integer>> taskList = new ArrayList<>();

		// 添加5个并发任务
		for (int i = 0; i < 5; i++) {
			final int taskNumber = i + 1;
			CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
				return executeTask(taskNumber);
			});
			taskList.add(task);
		}

		// 等待所有任务完成并获取结果
		CompletableFuture<Void> allTasks = CompletableFuture.allOf(
				taskList.toArray(new CompletableFuture[0])
		);

		// 当所有任务完成后处理结果
		CompletableFuture<List<Integer>> allResults = allTasks.thenApply(v -> {
			return taskList.stream()
					.map(CompletableFuture::join)
					.collect(Collectors.toList());
		});

		try {
			// 获取所有任务的结果
			List<Integer> results = allResults.get();
			System.out.println("所有任务执行完成，结果如下：");
			for (int i = 0; i < results.size(); i++) {
				System.out.println("任务 " + (i + 1) + " 结果: " + results.get(i));
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	// 模拟任务执行
	private static Integer executeTask(int taskNumber) {
		try {
			// 随机休眠一段时间，模拟任务执行耗时
			int sleepTime = new Random().nextInt(1000) + 500; // 500-1500毫秒
			Thread.sleep(sleepTime);
			System.out.println("任务 " + taskNumber + " 执行完成，耗时 " + sleepTime + "ms");
			return taskNumber * 10; // 返回任务结果
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return -1; // 任务被中断
		}
	}

}
