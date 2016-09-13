import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Given Tasks like (TaskName, priority, dependencies)
 * addTask(A, 10, [B])
 * addTask(B, 5, [])
 * addTask(C, 7, [])
 * addTask(D, 1, [])
 * 
 * Write a scheduler which will schedule this task in correct order.
 * Conditions are:
 * a. A given task can only execute if all of its dependencies are executed.
 * b. Tasks with higher priority execute first than the lower priority tasks.
 * c. If 2 tasks have same priority then any of them can execute.
 * d. higher number has higher priority, ex t1 = 10, t2 = 7 then t1 executes first then t2
 * 
Output for above input:
execution order: 
[name: C, priority: 7, dependencies: []]
[name: B, priority: 5, dependencies: []]
[name: A, priority: 10, dependencies: [B; ]]
[name: D, priority: 1, dependencies: []]

 * @author manib
 */
public class TaskScheduler {
	
	TaskNode [] tnodes;

	public static void main(String[] args) {
		TaskScheduler ts = new TaskScheduler();
		
		ts.init();
		
		ts.printTasks();
		
		ts.execute();
	}
	
	public void execute() {
		PriorityQueue<TaskNode> maxHeap = new PriorityQueue<TaskNode>(10, new TaskComparator());
		Queue<TaskNode> waitList = new LinkedList<TaskNode>();
		List<TaskNode> result = new ArrayList<TaskNode>();
		
		for (TaskNode node: tnodes) {
			maxHeap.add(node);
		}
		
		while (!maxHeap.isEmpty()) {
			TaskNode node = maxHeap.remove();
			boolean wait = false;
			
			if (node.dependencies.size() == 0) {
				//execute it
				result.add(node);
				node.isDone = true;
				while(!waitList.isEmpty()) {
					maxHeap.add(waitList.remove());
				}
			} else {
				int size = node.dependencies.size();
				boolean go = true;
				for (int i=0; i<size; i++) {
					TaskNode task = node.dependencies.get(i);
					if (!task.isDone) {
						go = false;
						break;
					}
				}
				if (go) {
					result.add(node);
					node.isDone = true;
					while(!waitList.isEmpty()) {
						maxHeap.add(waitList.remove());
					}
				} else {
					wait = true;
				}
			}
			if (wait) {
				waitList.add(node);
			}
		}
		
		System.out.println("execution order: ");
		for (TaskNode node: result) {
			System.out.println(node);
		}
	}
	
	public void init() {
		tnodes = new TaskNode[4];
		tnodes[0] = new TaskNode("A", 10);
		tnodes[1] = new TaskNode("B", 5);
		tnodes[2] = new TaskNode("C", 7);
		tnodes[3] = new TaskNode("D", 1);
		
		//add dependencies
		tnodes[0].dependencies.add(tnodes[1]);
	}
	
	public void printTasks() {
		if (tnodes == null) {
			return;
		}
		
		for (TaskNode node: tnodes) {
			System.out.println(node);
		}
	}
}

class TaskComparator implements Comparator<TaskNode> {

	@Override
	public int compare(TaskNode o1, TaskNode o2) {
		if (o1.priority < o2.priority) {
			return 1;
		} else if (o1.priority > o2.priority) {
			return -1;
		}
		return 0;
	}
	
}

class TaskNode {
	String name;
	int priority;
	boolean isDone = false;
	List<TaskNode> dependencies;
	
	
	public TaskNode(String name, int priority) {
		this.name = name;
		this.priority = priority;
		this.dependencies = new ArrayList<TaskNode>();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("name: " + this.name + ", ");
		sb.append("priority: " + this.priority + ", ");
		sb.append("dependencies: [");
		if (this.dependencies != null && this.dependencies.size() > 0) {
			int size = this.dependencies.size();
			for (int i=0; i<size; i++) {
				sb.append(this.dependencies.get(i).name + "; ");
			}
		}
		sb.append("]");
		sb.append("]");
		return sb.toString();
	}
}
