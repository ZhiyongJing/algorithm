package template;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Worker {
    private HashMap<Integer, TaskResource> taskResources;
    public Worker(){
        taskResources = new HashMap<Integer, TaskResource>();
    }
    public Iterable<TaskResource> getTaskResources() {
        return this.taskResources.values();
    }

    public TaskResource acquireTaskResource(int id) {
        TaskResource w = this.taskResources.getOrDefault(id, null);
        if (w == null) {
            w = new TaskResource(id);
            this.taskResources.put(id, w);
        }

        return w;
    }

    public void releaseTaskResource(int id) {
        TaskResource w = this.taskResources.getOrDefault(id, null);
        if (w == null)
            throw new IllegalArgumentException();

        w.close();
    }

    public class TaskResource implements AutoCloseable {
        private List<String> taskList;

        private int id;

        public int getId() {
            return this.id;
        }

        public List<String> getTasks() {
            return this.taskList;
        }

        public TaskResource(int id) {
            this.id = id;
            taskList = new LinkedList<>();
        }

        public void doTask(String task) {
            if (this.taskList == null)
                throw new IllegalStateException(this.getClass().getName());

            this.taskList.add(task);
        }

        @Override
        public void close() {
            this.taskList = null;
        }
    }

    public static void main(String[] args) {
        Worker d = new Worker();

        d.acquireTaskResource(1).doTask("Task11");
        d.acquireTaskResource(2).doTask("Task21");
        System.out.println(String.join(", ", d.acquireTaskResource(2).getTasks()));
        d.releaseTaskResource(2);
        d.acquireTaskResource(1).doTask("Task12");
        System.out.println(String.join(", ", d.acquireTaskResource(1).getTasks()));
        d.releaseTaskResource(1);
    }
}