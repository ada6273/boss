package com.cmcc.coc.cbpsp.alipay.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TaskScheduler {

	public void schedule(Digraph digraph) {
		while (true) {
			List<Task> todo = new ArrayList<Task>();
			for (Task task : digraph.getTasks()) {
				if (!task.hasExecuted()) {
					Set<Task> prevs = digraph.getMap().get(task);
					if (prevs != null && !prevs.isEmpty()) {
						boolean toAdd = true;
						for (Task tmpTask : prevs) {
							if (!tmpTask.hasExecuted()) {
								toAdd = false;
								break;
							}
						}
						if (toAdd) {
							todo.add(task);
						}
					} else {
						todo.add(task);
					}
				}
			}
			if (!todo.isEmpty()) {
				for (Task task : todo) {
					if (!task.execute()) {
						throw new RuntimeException();
					}
				}
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		Digraph digraph = new Digraph();
		Task taskA = new Task(1L, "Task A", 0);
		Task taskB = new Task(2L, "Task B", 0);
		Task taskC = new Task(3L, "Task C", 0);
		Task taskD = new Task(4L, "Task D", 0);
		Task taskE = new Task(5L, "Task E", 0);
		Task taskF = new Task(6L, "Task F", 0);
		Task taskG = new Task(7L, "Task G", 0);
		Task taskH = new Task(8L, "Task H", 0);

		digraph.addTask(taskA);
		digraph.addTask(taskB);
		digraph.addTask(taskC);
		digraph.addTask(taskD);
		digraph.addTask(taskE);
		digraph.addTask(taskF);
		digraph.addTask(taskG);
		digraph.addTask(taskH);
		digraph.addEdge(taskC, taskH);
		digraph.addEdge(taskA, taskC);
		digraph.addEdge(taskD, taskA);
		digraph.addEdge(taskF, taskD);
		digraph.addEdge(taskG, taskB);
		digraph.addEdge(taskA, taskG);
		digraph.addEdge(taskE, taskG);
		digraph.addEdge(taskF, taskE);
		digraph.addEdge(taskE, taskB);

		TaskScheduler scheduler = new TaskScheduler();
		scheduler.schedule(digraph);
		// Test input: Task A, Task B, Task C, Task D, Task E, Task F, Task G, Task H
		//
		// Test output: Task H, Task B, Task G, Task C, Task E, Task A, Task D, Task F
	}

}
